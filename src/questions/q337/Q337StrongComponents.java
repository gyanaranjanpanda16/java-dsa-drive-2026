package questions.q337; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q337: Count strongly connected components with Kosaraju.
 * Contract: Vertices are numbered0 through n-1; n>=0; edge rows contain valid endpoints; arrays are non-null. Source vertices, if supplied, exist.
 * Why: Contracting SCCs gives a DAG. The highest finishing-time unprocessed component is a source in the remaining original component DAG and thus a sink in its transpose. A reverse traversal cannot escape it, so it discovers exactly one SCC.
 * Complexity: O(V+E) time and space; O(V) recursion.
 */
public final class Q337StrongComponents { // One class contains this question, its answer, and reproducible checks.
    static List<List<Integer>> graph(int n,int[][] edges,boolean directed) { // Build adjacency lists matching the declared edge direction.
        List<List<Integer>> g=new ArrayList<>(); // Allocate one neighbor container per vertex.
        for(int v=0;v<n;v++) g.add(new ArrayList<>()); // Isolated vertices still need empty adjacency lists.
        for(int[] e:edges) { // Each row describes one edge between valid vertex indices.
            g.get(e[0]).add(e[1]); // Preserve the forward connection.
            if(!directed) g.get(e[1]).add(e[0]); // Undirected relationships must be traversable both ways.
        }
        return g; // Neighbor order follows input edge order, making traversals deterministic.
    }    static void post(int v,List<List<Integer>> g,boolean[] seen,List<Integer> finish) { // Append after completing all reachable descendants.
        seen[v]=true; // Prevent cycles from re-entering the same vertex.
        for(int next:g.get(v)) if(!seen[next]) post(next,g,seen,finish); // Finish child searches first.
        finish.add(v); // This position records the DFS completion order.
    }
    static void mark(int v,List<List<Integer>> g,boolean[] seen) { // Mark one transpose-reachable component.
        seen[v]=true; // Enter once even when reverse edges contain cycles.
        for(int next:g.get(v)) if(!seen[next]) mark(next,g,seen); // Continue through all unvisited reverse neighbors.
    }
    public static int solve(int n,int[][] edges) { // Expose the answer as a reusable method as well as a runnable program.
        List<List<Integer>> g=graph(n,edges,true); // First pass follows the original directed edges.
        int[][] reversed=new int[edges.length][2]; for(int i=0;i<edges.length;i++) { reversed[i][0]=edges[i][1]; reversed[i][1]=edges[i][0]; } // Transpose every edge for the second pass.
        List<List<Integer>> reverse=graph(n,reversed,true); // Reversed reachability isolates components in finishing order.
        boolean[] seen=new boolean[n]; List<Integer> finish=new ArrayList<>(); // Record vertices only after their descendants finish.
        for(int v=0;v<n;v++) if(!seen[v]) post(v,g,seen,finish); // Cover all components of the original graph.
        Arrays.fill(seen,false); int count=0; // The second pass needs fresh visitation state.
        for(int i=finish.size()-1;i>=0;i--) if(!seen[finish.get(i)]) { count++; mark(finish.get(i),reverse,seen); } // Each reversed traversal started in descending finish order yields one SCC.
        return count; // Every vertex belongs to exactly one mutually reachable group.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(5,new int[][]{{0,1},{1,0},{1,2},{2,3},{3,2},{3,4}})); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(5,new int[][]{{0,1},{1,0},{1,2},{2,3},{3,2},{3,4}}), 3); // Two cycles and one singleton
        Check.equal(solve(3,new int[][]{}), 3); // All vertices isolated
        Check.equal(solve(0,new int[][]{}), 0); // Empty graph
    }
}
