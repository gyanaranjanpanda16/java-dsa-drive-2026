package questions.q316; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q316: Detect a cycle in a simple undirected graph.
 * Contract: Simple undirected graph with valid vertices and no parallel edges; self-loops count as cycles; recursive depth must fit the JVM stack.
 * Why: In undirected DFS, the edge back to the immediate parent is expected. Reaching any other already visited neighbor closes a cycle because tree paths already connect the two vertices.
 * Complexity: O(V+E) time and storage; O(V) worst-case recursion.
 */
public final class Q316UndirectedCycle { // One class contains this question, its answer, and reproducible checks.
    static List<List<Integer>> graph(int n,int[][] edges,boolean directed) { // Build adjacency lists matching the declared edge direction.
        List<List<Integer>> g=new ArrayList<>(); // Allocate one neighbor container per vertex.
        for(int v=0;v<n;v++) g.add(new ArrayList<>()); // Isolated vertices still need empty adjacency lists.
        for(int[] e:edges) { // Each row describes one edge between valid vertex indices.
            g.get(e[0]).add(e[1]); // Preserve the forward connection.
            if(!directed) g.get(e[1]).add(e[0]); // Undirected relationships must be traversable both ways.
        }
        return g; // Neighbor order follows input edge order, making traversals deterministic.
    }    static boolean cycle(int v,int parent,List<List<Integer>> g,boolean[] seen) { // Parent identifies the reverse of the tree edge just used.
        seen[v]=true; // Mark before descending to detect return edges.
        for(int next:g.get(v)) { // Inspect every incident edge of this vertex.
            if(next==parent) continue; // Ignore only the undirected reverse edge to the parent.
            if(seen[next]||cycle(next,v,g,seen)) return true; // A visited non-parent or a child cycle proves a cycle exists.
        }
        return false; // Every child subtree completed without a cycle.
    }
    public static boolean solve(int n,int[][] edges) { // Expose the answer as a reusable method as well as a runnable program.
        List<List<Integer>> g=graph(n,edges,false); // Add reverse edges so each component can be fully explored.
        boolean[] seen=new boolean[n]; // Preserve discovery across disconnected components.
        for(int v=0;v<n;v++) if(!seen[v]&&cycle(v,-1,g,seen)) return true; // Start DFS only for a new component.
        return false; // No explored component contained a non-parent return edge.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(3,new int[][]{{0,1},{1,2},{2,0}})); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(3,new int[][]{{0,1},{1,2},{2,0}}), true); // Triangle cycle
        Check.equal(solve(3,new int[][]{{0,1},{1,2}}), false); // A tree has no cycle
        Check.equal(solve(0,new int[][]{}), false); // No vertices
    }
}
