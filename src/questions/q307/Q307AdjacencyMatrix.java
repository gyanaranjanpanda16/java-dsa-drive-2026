package questions.q307; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q307: Build an undirected adjacency matrix.
 * Contract: Valid vertex endpoints; duplicate edges collapse to presence, not multiplicity.
 * Why: Matrix position[u][v] is one exactly when an edge connects the vertices. Setting both symmetric positions represents undirected edges; repeated edges collapse because this is a presence matrix.
 * Complexity: O(V²+E) time including allocation; O(V²) space.
 */
public final class Q307AdjacencyMatrix { // One class contains this question, its answer, and reproducible checks.
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
        int[][] matrix=new int[n][n]; // Default zeros represent absent connections.
        for(int[] e:edges) { // Each undirected edge affects both ordered endpoint pairs.
            matrix[e[0]][e[1]]=1; // Record travel from the first endpoint to the second.
            matrix[e[1]][e[0]]=1; // Symmetry records the reverse direction as well.
        }
        return Arrays.deepToString(matrix); // Display rows so constant-time edge lookup is visible.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(3,new int[][]{{0,1},{1,2}})); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(3,new int[][]{{0,1},{1,2}}), "[[0, 1, 0], [1, 0, 1], [0, 1, 0]]"); // A path becomes a symmetric matrix
        Check.equal(solve(0,new int[][]{}), "[]"); // Empty graph
        Check.equal(solve(1,new int[][]{{0,0}}), "[[1]]"); // Self-loop entry
    }
}
