package questions.q310; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q310: Traverse reachable vertices in breadth-first order.
 * Contract: Vertices are numbered0 through n-1; n>=0; edge rows contain valid endpoints; arrays are non-null. Source vertices, if supplied, exist.
 * Why: Enqueueing children at the back makes vertices at distance d leave before vertices at distance d+1. Marking at discovery gives each reachable vertex exactly one queue entry.
 * Complexity: O(V+E) time including graph construction; O(V+E) storage and O(V) traversal workspace.
 */
public final class Q310BreadthFirstOrder { // One class contains this question, its answer, and reproducible checks.
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
        List<List<Integer>> g=graph(n,edges,true); // Use outgoing neighbors to respect directed reachability.
        boolean[] seen=new boolean[n]; // Mark vertices when discovered so cycles cannot enqueue them repeatedly.
        Deque<Integer> q=new ArrayDeque<>(); q.add(source); seen[source]=true; // Start the distance-zero frontier with the source.
        List<Integer> order=new ArrayList<>(); // Record the order of completed queue removals.
        while(!q.isEmpty()) { // FIFO completes each distance layer before the next.
            int v=q.remove(); order.add(v); // The oldest pending vertex is visited next.
            for(int next:g.get(v)) if(!seen[next]) { // Ignore edges leading to vertices already scheduled.
                seen[next]=true; q.add(next); // Mark before enqueueing to avoid duplicate discovery through another parent.
            }
        }
        return order.toString(); // Unreachable vertices are deliberately absent.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(5,new int[][]{{0,1},{0,2},{1,3},{2,3}},0)); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(5,new int[][]{{0,1},{0,2},{1,3},{2,3}},0), "[0, 1, 2, 3]"); // Diamond with an unreachable vertex
        Check.equal(solve(1,new int[][]{},0), "[0]"); // Only source
        Check.equal(solve(2,new int[][]{{0,1},{1,0}},0), "[0, 1]"); // Cycle does not repeat visits
    }
}
