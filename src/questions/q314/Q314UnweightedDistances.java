package questions.q314; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q314: Find shortest edge-count distances from a source.
 * Contract: Vertices are numbered0 through n-1; n>=0; edge rows contain valid endpoints; arrays are non-null. Source vertices, if supplied, exist.
 * Why: If a newly found neighbor had a shorter path, its predecessor on that path would belong to an earlier BFS layer and would already have discovered it. Thus first discovery gives minimum edge count.
 * Complexity: O(V+E) time and storage.
 */
public final class Q314UnweightedDistances { // One class contains this question, its answer, and reproducible checks.
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
        List<List<Integer>> g=graph(n,edges,true); // Each edge has equal unit cost in this problem.
        int[] distance=new int[n]; Arrays.fill(distance,-1); // Minus one distinguishes undiscovered vertices from distance zero.
        Deque<Integer> q=new ArrayDeque<>(); q.add(source); distance[source]=0; // Begin at the zero-length source path.
        while(!q.isEmpty()) { // FIFO visits increasing distance layers.
            int v=q.remove(); // Its recorded distance is already minimum.
            for(int next:g.get(v)) if(distance[next]==-1) { // First discovery is the earliest possible layer.
                distance[next]=distance[v]+1; q.add(next); // Extend the shortest path by exactly one edge.
            }
        }
        return Arrays.toString(distance); // Unreachable vertices retain minus one.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(5,new int[][]{{0,1},{0,2},{1,3},{2,3}},0)); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(5,new int[][]{{0,1},{0,2},{1,3},{2,3}},0), "[0, 1, 1, 2, -1]"); // Layers and unreachable vertex
        Check.equal(solve(1,new int[][]{},0), "[0]"); // Only source
        Check.equal(solve(2,new int[][]{{1,0}},0), "[0, -1]"); // No outgoing path
    }
}
