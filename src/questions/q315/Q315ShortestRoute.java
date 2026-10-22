package questions.q315; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q315: Reconstruct one shortest unweighted route.
 * Contract: Vertices are numbered0 through n-1; n>=0; edge rows contain valid endpoints; arrays are non-null. Source vertices, if supplied, exist.
 * Why: BFS first discovery establishes a shortest distance. Saving the edge that caused discovery gives a predecessor whose distance is exactly one smaller; following these links reaches the source without cycles.
 * Complexity: O(V+E) time and storage; route length is at most V.
 */
public final class Q315ShortestRoute { // One class contains this question, its answer, and reproducible checks.
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
        List<List<Integer>> g=graph(n,edges,true); // Directed adjacency defines allowed route steps.
        int[] parent=new int[n]; Arrays.fill(parent,-1); // Minus one marks vertices with no discovered predecessor.
        Deque<Integer> q=new ArrayDeque<>(); q.add(source); parent[source]=source; // The source points to itself to mark discovery and terminate reconstruction.
        while(!q.isEmpty()&&parent[target]==-1) { // Once target is discovered its BFS route is already shortest.
            int v=q.remove(); // Expand the oldest distance frontier vertex.
            for(int next:g.get(v)) if(parent[next]==-1) { parent[next]=v; q.add(next); } // Store the edge responsible for first discovery.
        }
        if(parent[target]==-1) return "[]"; // No route exists if the target remained undiscovered.
        List<Integer> route=new ArrayList<>(); // Parent links naturally produce the route backwards.
        for(int v=target;;v=parent[v]) { route.add(v); if(v==source) break; } // Follow one predecessor per shortest-path layer.
        Collections.reverse(route); // Present the route from source to target.
        return route.toString(); // Adjacency order chooses among equal-length routes.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(4,new int[][]{{0,1},{0,2},{1,3},{2,3}},0,3)); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(4,new int[][]{{0,1},{0,2},{1,3},{2,3}},0,3), "[0, 1, 3]"); // Choose one of two shortest routes
        Check.equal(solve(2,new int[][]{},0,1), "[]"); // Unreachable destination
        Check.equal(solve(1,new int[][]{},0,0), "[0]"); // Source is destination
    }
}
