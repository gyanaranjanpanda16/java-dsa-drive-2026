package questions.q308; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q308: Count degrees in an undirected multigraph.
 * Contract: Vertices are numbered0 through n-1; n>=0; edge rows contain valid endpoints; arrays are non-null. Source vertices, if supplied, exist.
 * Why: Degree counts incident edge ends, not distinct neighbors. Incrementing both endpoints handles repeated edges and the two ends of a self-loop consistently with the handshaking identity.
 * Complexity: O(V+E) time and O(V) output space.
 */
public final class Q308VertexDegrees { // One class contains this question, its answer, and reproducible checks.
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
        int[] degree=new int[n]; // Each counter starts with no incident edge ends.
        for(int[] e:edges) { // An undirected edge contributes one end at each endpoint.
            degree[e[0]]++; // Count the first end, even when both endpoints coincide.
            degree[e[1]]++; // A self-loop therefore correctly contributes two to its vertex degree.
        }
        return Arrays.toString(degree); // Degree sum equals twice the edge count.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(3,new int[][]{{0,1},{0,2},{1,2}})); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(3,new int[][]{{0,1},{0,2},{1,2}}), "[2, 2, 2]"); // Triangle degrees
        Check.equal(solve(2,new int[][]{}), "[0, 0]"); // Isolated vertices
        Check.equal(solve(1,new int[][]{{0,0}}), "[2]"); // Loop contributes two ends
    }
}
