package questions.q312; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q312: Count connected components in an undirected graph.
 * Contract: Vertices are numbered0 through n-1; n>=0; edge rows contain valid endpoints; arrays are non-null. Source vertices, if supplied, exist.
 * Why: A traversal from one start reaches exactly its connected component. Once exhausted, every unseen vertex lies in a different component, so counting new traversal starts counts components without overlap.
 * Complexity: O(V+E) time and storage.
 */
public final class Q312ConnectedComponents { // One class contains this question, its answer, and reproducible checks.
    static List<List<Integer>> graph(int n,int[][] edges,boolean directed) { // Build adjacency lists matching the declared edge direction.
        List<List<Integer>> g=new ArrayList<>(); // Allocate one neighbor container per vertex.
        for(int v=0;v<n;v++) g.add(new ArrayList<>()); // Isolated vertices still need empty adjacency lists.
        for(int[] e:edges) { // Each row describes one edge between valid vertex indices.
            g.get(e[0]).add(e[1]); // Preserve the forward connection.
            if(!directed) g.get(e[1]).add(e[0]); // Undirected relationships must be traversable both ways.
        }
        return g; // Neighbor order follows input edge order, making traversals deterministic.
    }
    public static int solve(int n,int[][] edges) { // Expose the answer as a reusable method as well as a runnable program.
        List<List<Integer>> g=graph(n,edges,false); // Connectivity requires traversing each undirected edge both ways.
        boolean[] seen=new boolean[n]; int components=0; // Each vertex will join exactly one discovered component.
        for(int start=0;start<n;start++) if(!seen[start]) { // An unseen vertex starts a component not reached before.
            components++; Deque<Integer> q=new ArrayDeque<>(); q.add(start); seen[start]=true; // Count once before exploring that component.
            while(!q.isEmpty()) { // Exhaust the entire reachable component before choosing a new start.
                int v=q.remove(); // Process the next discovered member.
                for(int next:g.get(v)) if(!seen[next]) { seen[next]=true; q.add(next); } // Mark every newly connected vertex at discovery.
            }
        }
        return components; // Isolated vertices each initiate their own traversal.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(5,new int[][]{{0,1},{1,2},{3,4}})); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(5,new int[][]{{0,1},{1,2},{3,4}}), 2); // Two connected groups
        Check.equal(solve(3,new int[][]{}), 3); // Three isolated vertices
        Check.equal(solve(0,new int[][]{}), 0); // Empty graph
    }
}
