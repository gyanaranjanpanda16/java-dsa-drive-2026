package questions.q320; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q320: Enumerate every source-to-target path in a DAG.
 * Contract: Directed acyclic simple graph, valid existing source/target; small inputs because the number of paths may be exponential.
 * Why: Each recursive branch appends exactly one next edge. Distinct source-to-target edge choices form distinct recursion branches, so every route is generated once. Copying completed paths and undoing the latest choice prevents sibling branches from sharing mutable output.
 * Complexity: Output-sensitive and potentially exponential; O(V) recursion/current path plus stored paths.
 */
public final class Q320AllDagPaths { // One class contains this question, its answer, and reproducible checks.
    static List<List<Integer>> graph(int n,int[][] edges,boolean directed) { // Build adjacency lists matching the declared edge direction.
        List<List<Integer>> g=new ArrayList<>(); // Allocate one neighbor container per vertex.
        for(int v=0;v<n;v++) g.add(new ArrayList<>()); // Isolated vertices still need empty adjacency lists.
        for(int[] e:edges) { // Each row describes one edge between valid vertex indices.
            g.get(e[0]).add(e[1]); // Preserve the forward connection.
            if(!directed) g.get(e[1]).add(e[0]); // Undirected relationships must be traversable both ways.
        }
        return g; // Neighbor order follows input edge order, making traversals deterministic.
    }    static void enumerate(int v,int target,List<List<Integer>> g,List<Integer> current,List<List<Integer>> paths) { // current is exactly the source-to-v route for this branch.
        if(v==target) { paths.add(new ArrayList<>(current)); return; } // Copy now so later backtracking cannot modify the saved path.
        for(int next:g.get(v)) { // Explore every possible next edge independently.
            current.add(next); // Extend the shared working path for this branch.
            enumerate(next,target,g,current,paths); // Generate every completion of the extended prefix.
            current.remove(current.size()-1); // Restore the parent prefix before exploring another neighbor.
        }
    }
    public static String solve(int n,int[][] edges,int source,int target) { // Expose the answer as a reusable method as well as a runnable program.
        List<List<Integer>> g=graph(n,edges,true); // The contract guarantees no cycle, so every recursive branch terminates.
        List<List<Integer>> paths=new ArrayList<>(); // Store independent completed paths.
        List<Integer> current=new ArrayList<>(); current.add(source); // Start with the zero-edge source prefix.
        enumerate(source,target,g,current,paths); // Extend every possible prefix through its outgoing edges.
        return paths.toString(); // Input neighbor order defines deterministic enumeration order.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(4,new int[][]{{0,1},{0,2},{1,3},{2,3}},0,3)); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(4,new int[][]{{0,1},{0,2},{1,3},{2,3}},0,3), "[[0, 1, 3], [0, 2, 3]]"); // Two route choices
        Check.equal(solve(2,new int[][]{},0,1), "[]"); // Unreachable target
        Check.equal(solve(1,new int[][]{},0,0), "[[0]]"); // Zero-edge path
    }
}
