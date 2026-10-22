package questions.q330; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q330: Find shortest paths allowing negative edges.
 * Contract: Edges are directed [from,to,int weight]; valid source; practical-size graphs with all intermediate arithmetic within long.
 * Why: After p full edge passes, every shortest route using at most p edges has been considered. Without a reachable negative cycle, a shortest route can be simple and needs at most V-1 edges. Another possible relaxation therefore signals an unbounded improvement cycle.
 * Complexity: O(VE) time and O(V) distance storage.
 */
public final class Q330BellmanFord { // One class contains this question, its answer, and reproducible checks.
    static List<List<Integer>> graph(int n,int[][] edges,boolean directed) { // Build adjacency lists matching the declared edge direction.
        List<List<Integer>> g=new ArrayList<>(); // Allocate one neighbor container per vertex.
        for(int v=0;v<n;v++) g.add(new ArrayList<>()); // Isolated vertices still need empty adjacency lists.
        for(int[] e:edges) { // Each row describes one edge between valid vertex indices.
            g.get(e[0]).add(e[1]); // Preserve the forward connection.
            if(!directed) g.get(e[1]).add(e[0]); // Undirected relationships must be traversable both ways.
        }
        return g; // Neighbor order follows input edge order, making traversals deterministic.
    }
    public static String solve(int n,int[][] edges,int source) { // Expose the answer as a reusable method as well as a runnable program.
        long inf=Long.MAX_VALUE/4; long[] distance=new long[n]; Arrays.fill(distance,inf); distance[source]=0; // A finite sentinel leaves headroom for adding int weights.
        for(int pass=1;pass<n;pass++) { // A simple shortest path uses at most V-1 edges when no reachable negative cycle exists.
            boolean changed=false; // Early termination is safe when no relaxation can improve any distance.
            for(int[] e:edges) if(distance[e[0]]!=inf&&distance[e[0]]+e[2]<distance[e[1]]) { // Only reachable prefixes can extend a route.
                distance[e[1]]=distance[e[0]]+e[2]; changed=true; // Keep the cheaper route discovered in this pass.
            }
            if(!changed) break; // A fixed point already satisfies every edge inequality.
        }
        for(int[] e:edges) if(distance[e[0]]!=inf&&distance[e[0]]+e[2]<distance[e[1]]) return "NEGATIVE CYCLE"; // A further improvement requires a repeatable negative cycle reachable from source.
        List<String> out=new ArrayList<>(); for(long d:distance) out.add(d==inf?"INF":Long.toString(d)); // Preserve legitimate negative distances separately from absence.
        return out.toString(); // Distances follow vertex index order.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(3,new int[][]{{0,1,4},{0,2,5},{1,2,-2}},0)); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(3,new int[][]{{0,1,4},{0,2,5},{1,2,-2}},0), "[0, 4, 2]"); // Negative edge without negative cycle
        Check.equal(solve(2,new int[][]{{0,1,-1},{1,0,-1}},0), "NEGATIVE CYCLE"); // Reachable negative cycle
        Check.equal(solve(2,new int[][]{},0), "[0, INF]"); // Unreachable vertex
    }
}
