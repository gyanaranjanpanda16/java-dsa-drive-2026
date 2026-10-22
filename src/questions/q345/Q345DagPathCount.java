package questions.q345; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q345: Count directed source-to-target paths in a DAG.
 * Contract: Directed simple graph; n>=1; valid source and target; no parallel edges. Returns CYCLE for any cycle; counts must fit signed long or ArithmeticException is raised. The empty source-to-itself path counts once.
 * Why: Each source-to-v path has a unique last incoming edge, so summing predecessor counts neither omits nor double-counts paths in a DAG. A topological sweep makes these sums final in dependency order. This counts alternatives through workflows or acyclic routing systems; Java long stores exact bounded counts and Math.addExact rejects overflow.
 * Complexity: O(V+E) time and space.
 */
public final class Q345DagPathCount { // One class contains this question, its answer, and reproducible checks.
    static List<List<Integer>> graph(int n,int[][] edges,boolean directed) { // Build adjacency lists matching the declared edge direction.
        List<List<Integer>> g=new ArrayList<>(); // Allocate one neighbor container per vertex.
        for(int v=0;v<n;v++) g.add(new ArrayList<>()); // Isolated vertices still need empty adjacency lists.
        for(int[] e:edges) { // Each row describes one edge between valid vertex indices.
            g.get(e[0]).add(e[1]); // Preserve the forward connection.
            if(!directed) g.get(e[1]).add(e[0]); // Undirected relationships must be traversable both ways.
        }
        return g; // Neighbor order follows input edge order, making traversals deterministic.
    }
    public static String solve(int n,int[][] edges,int source,int target) { // Expose the answer as a reusable method as well as a runnable program.
        List<List<Integer>> g=graph(n,edges,true); int[] indegree=new int[n]; long[] ways=new long[n]; // ways[v] accumulates paths whose final vertex is v.
        for(int[] edge:edges) indegree[edge[1]]++; // Dependencies determine when each path count becomes final.
        Deque<Integer> q=new ArrayDeque<>(); for(int v=0;v<n;v++) if(indegree[v]==0) q.add(v); // Include unreachable components so cycles are still detected globally.
        ways[source]=1; int processed=0; // The empty path at the source is the base case for extending paths.
        while(!q.isEmpty()) { // Each predecessor contributes before its successor is released.
            int v=q.remove(); processed++; // Count completion for global acyclicity checking.
            for(int next:g.get(v)) { // Every outgoing edge creates a distinct extension of each path to v.
                ways[next]=Math.addExact(ways[next],ways[v]); // Fail explicitly if the exact count cannot fit long.
                if(--indegree[next]==0) q.add(next); // All incoming contributions are now included.
            }
        }
        return processed==n?Long.toString(ways[target]):"CYCLE"; // A cycle invalidates finite DAG path counting.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(4,new int[][]{{0,1},{0,2},{1,3},{2,3},{0,3}},0,3)); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(4,new int[][]{{0,1},{0,2},{1,3},{2,3},{0,3}},0,3), "3"); // Two indirect paths and one direct path
        Check.equal(solve(2,new int[][]{},0,1), "0"); // Unreachable target
        Check.equal(solve(1,new int[][]{},0,0), "1"); // The empty path counts when source equals target
    }
}
