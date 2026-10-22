package questions.q336; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q336: Count provinces from a symmetric connection matrix.
 * Contract: Square symmetric binary matrix; diagonal values do not affect component membership.
 * Why: A province is a connected component. Matrix rows enumerate every possible neighbor rather than only actual neighbors, but the visited/component-start invariant is unchanged.
 * Complexity: O(V²) time and O(V) traversal space beyond input.
 */
public final class Q336MatrixComponents { // One class contains this question, its answer, and reproducible checks.
    static List<List<Integer>> graph(int n,int[][] edges,boolean directed) { // Build adjacency lists matching the declared edge direction.
        List<List<Integer>> g=new ArrayList<>(); // Allocate one neighbor container per vertex.
        for(int v=0;v<n;v++) g.add(new ArrayList<>()); // Isolated vertices still need empty adjacency lists.
        for(int[] e:edges) { // Each row describes one edge between valid vertex indices.
            g.get(e[0]).add(e[1]); // Preserve the forward connection.
            if(!directed) g.get(e[1]).add(e[0]); // Undirected relationships must be traversable both ways.
        }
        return g; // Neighbor order follows input edge order, making traversals deterministic.
    }
    public static int solve(int[][] connected) { // Expose the answer as a reusable method as well as a runnable program.
        int n=connected.length,count=0; boolean[] seen=new boolean[n]; // Each matrix index represents one city.
        for(int start=0;start<n;start++) if(!seen[start]) { // An unseen city begins a previously unexplored province.
            count++; Deque<Integer> q=new ArrayDeque<>(); q.add(start); seen[start]=true; // Count the province once before discovering all members.
            while(!q.isEmpty()) { // Exhaust the connected group represented by this matrix component.
                int city=q.remove(); // Scan this city's full row because a matrix does not list only existing edges.
                for(int next=0;next<n;next++) if(connected[city][next]!=0&&!seen[next]) { seen[next]=true; q.add(next); } // Discover every directly connected unvisited city.
            }
        }
        return count; // Isolated cities still count as one province.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(new int[][]{{1,1,0},{1,1,0},{0,0,1}})); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(new int[][]{{1,1,0},{1,1,0},{0,0,1}}), 2); // One pair and one isolated city
        Check.equal(solve(new int[][]{}), 0); // No cities
        Check.equal(solve(new int[][]{{1,0},{0,1}}), 2); // Two isolated cities
    }
}
