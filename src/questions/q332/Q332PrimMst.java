package questions.q332; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q332: Find an undirected minimum spanning-tree weight with Prim.
 * Contract: Undirected weighted edges [u,v,int weight], including negative weights; valid endpoints; DISCONNECTED when no spanning tree exists.
 * Why: At every step, the cheapest edge crossing from selected vertices to unselected vertices is safe by the cut property: any spanning tree omitting it can exchange another crossing edge without increasing weight.
 * Complexity: O(V+E log(E+1)) time and O(V+E) space.
 */
public final class Q332PrimMst { // One class contains this question, its answer, and reproducible checks.
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
        if(n==0) return "0"; // The empty graph needs no selected edges.
        List<List<int[]>> g=new ArrayList<>(); for(int v=0;v<n;v++) g.add(new ArrayList<>()); // Weighted adjacency supports crossing-edge discovery.
        for(int[] e:edges) { g.get(e[0]).add(new int[]{e[1],e[2]}); g.get(e[1]).add(new int[]{e[0],e[2]}); } // MST edges are undirected.
        PriorityQueue<int[]> heap=new PriorityQueue<>(Comparator.comparingInt(a->a[1])); heap.offer(new int[]{0,0}); // A zero-cost virtual edge starts vertex0.
        boolean[] used=new boolean[n]; int count=0; long total=0; // Track the chosen side of the cut and its total edge weight.
        while(!heap.isEmpty()) { // Candidate edges cross from chosen vertices to potential new vertices.
            int[] edge=heap.poll(); int v=edge[0]; // Extract the cheapest available crossing candidate.
            if(used[v]) continue; // An edge whose endpoint is already chosen would create no progress.
            used[v]=true; count++; total+=edge[1]; // Add one new vertex using its cheapest current crossing edge.
            for(int[] next:g.get(v)) if(!used[next[0]]) heap.offer(next); // New incident edges expand the cut frontier.
        }
        return count==n?Long.toString(total):"DISCONNECTED"; // A spanning tree exists only if every vertex was reached.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(3,new int[][]{{0,1,4},{1,2,1},{0,2,2}})); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(3,new int[][]{{0,1,4},{1,2,1},{0,2,2}}), "3"); // Choose two cheapest compatible edges
        Check.equal(solve(2,new int[][]{}), "DISCONNECTED"); // No spanning connection
        Check.equal(solve(1,new int[][]{}), "0"); // One vertex needs no edge
    }
}
