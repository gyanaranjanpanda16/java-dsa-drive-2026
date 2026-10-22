package questions.q318; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q318: Produce a deterministic topological order or report a cycle.
 * Contract: Vertices are numbered0 through n-1; n>=0; edge rows contain valid endpoints; arrays are non-null. Source vertices, if supplied, exist.
 * Why: A zero-indegree vertex has no remaining prerequisite and is safe to output. Removing its outgoing constraints preserves the same problem on unscheduled vertices. If none is ready while vertices remain, following incoming edges eventually repeats a vertex, proving a cycle.
 * Complexity: O(E+V log(V+1)) time using the tie-breaking heap; O(V+E) space.
 */
public final class Q318TopologicalOrder { // One class contains this question, its answer, and reproducible checks.
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
        List<List<Integer>> g=graph(n,edges,true); // Edge u->v means u must appear before v.
        int[] indegree=new int[n]; // Count outstanding prerequisites for each vertex.
        for(int[] e:edges) indegree[e[1]]++; // Every incoming edge adds one prerequisite.
        PriorityQueue<Integer> ready=new PriorityQueue<>(); // Choose the smallest currently available vertex for deterministic output.
        for(int v=0;v<n;v++) if(indegree[v]==0) ready.offer(v); // Vertices with no prerequisites can be scheduled immediately.
        List<Integer> order=new ArrayList<>(); // Record the progressively completed schedule.
        while(!ready.isEmpty()) { // Continue while some unscheduled vertex is unconstrained.
            int v=ready.poll(); order.add(v); // Schedule a vertex whose prerequisites are all satisfied.
            for(int next:g.get(v)) if(--indegree[next]==0) ready.offer(next); // Completing v removes one prerequisite from each dependent.
        }
        return order.size()==n?order.toString():"CYCLE"; // Remaining vertices with no ready source form a cyclic dependency.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(4,new int[][]{{0,2},{1,2},{2,3}})); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(4,new int[][]{{0,2},{1,2},{2,3}}), "[0, 1, 2, 3]"); // Multiple initially ready vertices
        Check.equal(solve(2,new int[][]{{0,1},{1,0}}), "CYCLE"); // Mutual dependency
        Check.equal(solve(0,new int[][]{}), "[]"); // Empty ordering
    }
}
