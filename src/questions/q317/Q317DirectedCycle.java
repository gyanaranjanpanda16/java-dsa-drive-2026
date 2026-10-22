package questions.q317; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q317: Detect a directed cycle using DFS colors.
 * Contract: Vertices are numbered0 through n-1; n>=0; edge rows contain valid endpoints; arrays are non-null. Source vertices, if supplied, exist.
 * Why: An edge to an active DFS vertex returns to an ancestor on the current directed path and closes a cycle. An edge to a finished vertex alone is not a cycle, which is why a single visited bit is insufficient.
 * Complexity: O(V+E) time and storage; O(V) recursion.
 */
public final class Q317DirectedCycle { // One class contains this question, its answer, and reproducible checks.
    static List<List<Integer>> graph(int n,int[][] edges,boolean directed) { // Build adjacency lists matching the declared edge direction.
        List<List<Integer>> g=new ArrayList<>(); // Allocate one neighbor container per vertex.
        for(int v=0;v<n;v++) g.add(new ArrayList<>()); // Isolated vertices still need empty adjacency lists.
        for(int[] e:edges) { // Each row describes one edge between valid vertex indices.
            g.get(e[0]).add(e[1]); // Preserve the forward connection.
            if(!directed) g.get(e[1]).add(e[0]); // Undirected relationships must be traversable both ways.
        }
        return g; // Neighbor order follows input edge order, making traversals deterministic.
    }    static boolean cycle(int v,List<List<Integer>> g,int[] color) { // Track whether v belongs to the current recursion path.
        color[v]=1; // Enter the active path before following outgoing edges.
        for(int next:g.get(v)) { // Every outgoing edge may extend or close the current path.
            if(color[next]==1) return true; // A back edge reaches an active ancestor.
            if(color[next]==0&&cycle(next,g,color)) return true; // Recurse only into unexplored vertices.
        }
        color[v]=2; // All outgoing paths are finished, so v leaves the active path.
        return false; // No cycle was reachable through this DFS call.
    }
    public static boolean solve(int n,int[][] edges) { // Expose the answer as a reusable method as well as a runnable program.
        List<List<Integer>> g=graph(n,edges,true); // Direction matters for the existence of a return path.
        int[] color=new int[n]; // Zero=unseen, one=active call, two=finished.
        for(int v=0;v<n;v++) if(color[v]==0&&cycle(v,g,color)) return true; // Cover every directed component.
        return false; // No edge returned to an active ancestor.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(3,new int[][]{{0,1},{1,2},{2,0}})); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(3,new int[][]{{0,1},{1,2},{2,0}}), true); // Directed triangle
        Check.equal(solve(3,new int[][]{{0,1},{0,2},{1,2}}), false); // Cross edge in a DAG is not a cycle
        Check.equal(solve(1,new int[][]{{0,0}}), true); // Self-loop
    }
}
