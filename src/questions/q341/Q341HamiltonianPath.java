package questions.q341; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q341: Decide whether a path visits every vertex exactly once.
 * Contract: Undirected graph with 0<=n<=18 and valid endpoints; loops are harmless; this exponential algorithm is for small graphs.
 * Why: A state records both the visited set and the endpoint because only those facts affect legal extensions. Inductively each transition appends one new neighbor, and every valid path can be split into its last vertex and a smaller recorded path. This models small itinerary constraints; Java bit masks compactly encode subsets, but the exponential growth restricts practical size.
 * Complexity: O(2^V V²) time and O(2^V V) space.
 */
public final class Q341HamiltonianPath { // One class contains this question, its answer, and reproducible checks.
    static List<List<Integer>> graph(int n,int[][] edges,boolean directed) { // Build adjacency lists matching the declared edge direction.
        List<List<Integer>> g=new ArrayList<>(); // Allocate one neighbor container per vertex.
        for(int v=0;v<n;v++) g.add(new ArrayList<>()); // Isolated vertices still need empty adjacency lists.
        for(int[] e:edges) { // Each row describes one edge between valid vertex indices.
            g.get(e[0]).add(e[1]); // Preserve the forward connection.
            if(!directed) g.get(e[1]).add(e[0]); // Undirected relationships must be traversable both ways.
        }
        return g; // Neighbor order follows input edge order, making traversals deterministic.
    }
    public static boolean solve(int n,int[][] edges) { // Expose the answer as a reusable method as well as a runnable program.
        if(n==0) return true; // The empty path visits every vertex of an empty graph.
        boolean[][] adjacent=new boolean[n][n]; // Constant-time adjacency tests suit this deliberately small exponential problem.
        for(int[] edge:edges) adjacent[edge[0]][edge[1]]=adjacent[edge[1]][edge[0]]=true; // Treat each supplied edge as undirected.
        boolean[][] dp=new boolean[1<<n][n]; // dp[mask][v] records a path using exactly mask and ending at v.
        for(int v=0;v<n;v++) dp[1<<v][v]=true; // Every single vertex starts a valid one-vertex path.
        for(int mask=1;mask<(1<<n);mask++) for(int last=0;last<n;last++) if(dp[mask][last]) { // Extend only states already proved reachable.
            for(int next=0;next<n;next++) if((mask&(1<<next))==0&&adjacent[last][next]) dp[mask|(1<<next)][next]=true; // Append an adjacent vertex only when it has not been used.
        }
        for(boolean possible:dp[(1<<n)-1]) if(possible) return true; // Any endpoint is acceptable once all vertices have been included.
        return false; // No full-mask state means every possible path misses a vertex.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(4,new int[][]{{0,1},{1,2},{2,3}})); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(4,new int[][]{{0,1},{1,2},{2,3}}), true); // A chain visits all vertices
        Check.equal(solve(4,new int[][]{{0,1},{0,2},{0,3}}), false); // A three-leaf star cannot visit every leaf without repetition
        Check.equal(solve(0,new int[][]{}), true); // Empty graph has an empty path
    }
}
