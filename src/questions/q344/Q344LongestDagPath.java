package questions.q344; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q344: Find the longest directed path length in a DAG.
 * Contract: Directed graph with valid endpoints; answer is the maximum number of edges in any path, or CYCLE if the graph is not a DAG; empty graph returns0.
 * Why: For a DAG, every path ending at v either starts there or extends a path ending at one predecessor. Processing vertices topologically makes those predecessor optima final before v is released. This computes dependency depth or the longest chain of unit-duration tasks; a general cyclic longest-simple-path problem is much harder.
 * Complexity: O(V+E) time and space.
 */
public final class Q344LongestDagPath { // One class contains this question, its answer, and reproducible checks.
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
        List<List<Integer>> g=graph(n,edges,true); int[] indegree=new int[n],length=new int[n]; // length[v] stores the best path ending at v in edge units.
        for(int[] edge:edges) indegree[edge[1]]++; // A vertex becomes final only after all incoming predecessors are processed.
        Deque<Integer> q=new ArrayDeque<>(); for(int v=0;v<n;v++) if(indegree[v]==0) q.add(v); // Every DAG source can start a zero-edge path.
        int processed=0,best=0; // Count processed vertices to distinguish a DAG from a directed cycle.
        while(!q.isEmpty()) { // Topological order ensures each predecessor has already contributed its best result.
            int v=q.remove(); processed++; best=Math.max(best,length[v]); // A path may end at any vertex.
            for(int next:g.get(v)) { // Extend the best path at v across each outgoing edge.
                length[next]=Math.max(length[next],length[v]+1); // Keep the longest of all predecessor alternatives.
                if(--indegree[next]==0) q.add(next); // Schedule next only when all alternatives have been considered.
            }
        }
        return processed==n?Integer.toString(best):"CYCLE"; // The recurrence requires an acyclic graph.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(5,new int[][]{{0,1},{1,2},{0,3},{3,2},{2,4}})); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(5,new int[][]{{0,1},{1,2},{0,3},{3,2},{2,4}}), "3"); // Longest chain has three edges
        Check.equal(solve(3,new int[][]{}), "0"); // Isolated vertices give zero-edge paths
        Check.equal(solve(2,new int[][]{{0,1},{1,0}}), "CYCLE"); // A directed cycle violates the DAG requirement
    }
}
