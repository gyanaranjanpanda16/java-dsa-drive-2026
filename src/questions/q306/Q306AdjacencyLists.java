package questions.q306; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q306: Build directed adjacency lists from an edge list.
 * Contract: Vertices are numbered0 through n-1; n>=0; edge rows contain valid endpoints; arrays are non-null. Source vertices, if supplied, exist.
 * Why: An edge list names connections globally; adjacency lists group them by their source. Every directed edge contributes exactly one neighbor entry, preserving direction and isolated vertices.
 * Complexity: O(V+E) time and O(V+E) storage.
 */
public final class Q306AdjacencyLists { // One class contains this question, its answer, and reproducible checks.
    static List<List<Integer>> graph(int n,int[][] edges,boolean directed) { // Build adjacency lists matching the declared edge direction.
        List<List<Integer>> g=new ArrayList<>(); // Allocate one neighbor container per vertex.
        for(int v=0;v<n;v++) g.add(new ArrayList<>()); // Isolated vertices still need empty adjacency lists.
        for(int[] e:edges) { // Each row describes one edge between valid vertex indices.
            g.get(e[0]).add(e[1]); // Preserve the forward connection.
            if(!directed) g.get(e[1]).add(e[0]); // Undirected relationships must be traversable both ways.
        }
        return g; // Neighbor order follows input edge order, making traversals deterministic.
    }
    public static String solve(int n,int[][] edges) { // Expose the answer as a reusable method as well as a runnable program.
        List<List<Integer>> g=graph(n,edges,true); // Store outgoing neighbors rather than allocating all possible vertex pairs.
        return g.toString(); // Nested lists expose the neighbors of vertex0, then1, and so on.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(4,new int[][]{{0,1},{0,2},{2,3}})); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(4,new int[][]{{0,1},{0,2},{2,3}}), "[[1, 2], [], [3], []]"); // Directed edges and empty neighbor lists
        Check.equal(solve(0,new int[][]{}), "[]"); // No vertices
        Check.equal(solve(2,new int[][]{{1,1}}), "[[], [1]]"); // A self-loop is represented explicitly
    }
}
