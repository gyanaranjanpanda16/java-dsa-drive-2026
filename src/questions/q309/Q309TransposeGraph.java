package questions.q309; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q309: Reverse every directed graph edge.
 * Contract: Vertices are numbered0 through n-1; n>=0; edge rows contain valid endpoints; arrays are non-null. Source vertices, if supplied, exist.
 * Why: Every original path reverses into a path in the transpose because each constituent edge is reversed. This representation is useful for predecessor queries and strongly connected components.
 * Complexity: O(V+E) time and space.
 */
public final class Q309TransposeGraph { // One class contains this question, its answer, and reproducible checks.
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
        List<List<Integer>> reverse=new ArrayList<>(); // Incoming neighbors become outgoing neighbors in the transpose.
        for(int v=0;v<n;v++) reverse.add(new ArrayList<>()); // Preserve isolated vertices in the reversed representation.
        for(int[] edge:edges) reverse.get(edge[1]).add(edge[0]); // Reverse the edge orientation without changing its endpoints.
        return reverse.toString(); // The resulting graph supports reverse reachability queries.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(3,new int[][]{{0,1},{0,2},{1,2}})); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(3,new int[][]{{0,1},{0,2},{1,2}}), "[[], [0], [0, 1]]"); // Reverse a directed triangle
        Check.equal(solve(1,new int[][]{}), "[[]]"); // Isolated vertex remains
        Check.equal(solve(1,new int[][]{{0,0}}), "[[0]]"); // A loop reverses to itself
    }
}
