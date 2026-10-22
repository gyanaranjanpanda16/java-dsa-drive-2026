package questions.q313; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q313: Determine whether a directed target is reachable.
 * Contract: Vertices are numbered0 through n-1; n>=0; edge rows contain valid endpoints; arrays are non-null. Source vertices, if supplied, exist.
 * Why: Every pushed vertex is reachable because it is the source or an outgoing neighbor of a reachable vertex. Conversely, exploring every outgoing edge ensures no reachable target can be missed.
 * Complexity: O(V+E) time and storage.
 */
public final class Q313Reachability { // One class contains this question, its answer, and reproducible checks.
    static List<List<Integer>> graph(int n,int[][] edges,boolean directed) { // Build adjacency lists matching the declared edge direction.
        List<List<Integer>> g=new ArrayList<>(); // Allocate one neighbor container per vertex.
        for(int v=0;v<n;v++) g.add(new ArrayList<>()); // Isolated vertices still need empty adjacency lists.
        for(int[] e:edges) { // Each row describes one edge between valid vertex indices.
            g.get(e[0]).add(e[1]); // Preserve the forward connection.
            if(!directed) g.get(e[1]).add(e[0]); // Undirected relationships must be traversable both ways.
        }
        return g; // Neighbor order follows input edge order, making traversals deterministic.
    }
    public static boolean solve(int n,int[][] edges,int source,int target) { // Expose the answer as a reusable method as well as a runnable program.
        List<List<Integer>> g=graph(n,edges,true); // Use directed edges because reverse travel is not implied.
        boolean[] seen=new boolean[n]; Deque<Integer> stack=new ArrayDeque<>(); // Track discovered work explicitly without recursive calls.
        stack.push(source); seen[source]=true; // The source is reachable by a path of zero edges.
        while(!stack.isEmpty()) { // Every pending vertex already has a known path from source.
            int v=stack.pop(); if(v==target) return true; // A discovered target provides a witness that a path exists.
            for(int next:g.get(v)) if(!seen[next]) { seen[next]=true; stack.push(next); } // Extend known paths only to new vertices.
        }
        return false; // Exhausting all reachable vertices without the target proves absence.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(4,new int[][]{{0,1},{1,2}},0,2)); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(4,new int[][]{{0,1},{1,2}},0,2), true); // A two-edge path exists
        Check.equal(solve(4,new int[][]{{0,1},{1,2}},2,0), false); // Direction prevents reverse travel
        Check.equal(solve(1,new int[][]{},0,0), true); // Zero-edge path
    }
}
