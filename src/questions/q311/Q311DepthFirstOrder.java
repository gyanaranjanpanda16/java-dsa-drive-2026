package questions.q311; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q311: Traverse reachable vertices in depth-first order.
 * Contract: Vertices are numbered0 through n-1; n>=0; edge rows contain valid endpoints; arrays are non-null. Source vertices, if supplied, exist.
 * Why: Marking before recursive descent prevents cycles from reopening an active or completed vertex. Each call explores all outgoing edges of one vertex, so induction over the recursive exploration covers exactly the reachable set.
 * Complexity: O(V+E) time and graph storage; O(V) recursion/visited workspace.
 */
public final class Q311DepthFirstOrder { // One class contains this question, its answer, and reproducible checks.
    static List<List<Integer>> graph(int n,int[][] edges,boolean directed) { // Build adjacency lists matching the declared edge direction.
        List<List<Integer>> g=new ArrayList<>(); // Allocate one neighbor container per vertex.
        for(int v=0;v<n;v++) g.add(new ArrayList<>()); // Isolated vertices still need empty adjacency lists.
        for(int[] e:edges) { // Each row describes one edge between valid vertex indices.
            g.get(e[0]).add(e[1]); // Preserve the forward connection.
            if(!directed) g.get(e[1]).add(e[0]); // Undirected relationships must be traversable both ways.
        }
        return g; // Neighbor order follows input edge order, making traversals deterministic.
    }    static void visit(int v,List<List<Integer>> g,boolean[] seen,List<Integer> out) { // One call completes the unexplored region reachable through v.
        if(seen[v]) return; // Previously entered vertices must not be entered again.
        seen[v]=true; out.add(v); // Mark before following edges so a back edge terminates.
        for(int next:g.get(v)) visit(next,g,seen,out); // Finish each neighbor branch in adjacency order.
    }
    public static String solve(int n,int[][] edges,int source) { // Expose the answer as a reusable method as well as a runnable program.
        List<List<Integer>> g=graph(n,edges,true); // Preserve directed neighbor order from the input.
        List<Integer> order=new ArrayList<>(); // Record each vertex on its first recursive entry.
        visit(source,g,new boolean[n],order); // Explore a complete branch before trying its next sibling.
        return order.toString(); // Only vertices reachable from source are included.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(4,new int[][]{{0,1},{0,2},{1,3}},0)); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(4,new int[][]{{0,1},{0,2},{1,3}},0), "[0, 1, 3, 2]"); // Follow the first branch deeply
        Check.equal(solve(1,new int[][]{},0), "[0]"); // Singleton graph
        Check.equal(solve(2,new int[][]{{0,1},{1,0}},0), "[0, 1]"); // Visited marks terminate cycles
    }
}
