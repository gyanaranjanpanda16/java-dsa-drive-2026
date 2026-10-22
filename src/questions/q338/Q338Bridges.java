package questions.q338; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q338: Find edges whose removal disconnects an undirected graph.
 * Contract: Simple undirected graph with valid vertices and no parallel edges; output sorted by endpoints; recursive depth must fit stack.
 * Why: low[v] is the earliest discovery time reachable from the DFS subtree of v through tree edges followed by at most one back edge. A child subtree with low[child]>entered[parent] cannot reach the parent or an ancestor without its tree edge, so that edge is a bridge.
 * Complexity: O(V+E+B log(B+1)) time including sorted output; O(V+E) space.
 */
public final class Q338Bridges { // One class contains this question, its answer, and reproducible checks.
    static List<List<Integer>> graph(int n,int[][] edges,boolean directed) { // Build adjacency lists matching the declared edge direction.
        List<List<Integer>> g=new ArrayList<>(); // Allocate one neighbor container per vertex.
        for(int v=0;v<n;v++) g.add(new ArrayList<>()); // Isolated vertices still need empty adjacency lists.
        for(int[] e:edges) { // Each row describes one edge between valid vertex indices.
            g.get(e[0]).add(e[1]); // Preserve the forward connection.
            if(!directed) g.get(e[1]).add(e[0]); // Undirected relationships must be traversable both ways.
        }
        return g; // Neighbor order follows input edge order, making traversals deterministic.
    }    static void search(int v,int parent,List<List<Integer>> g,int[] entered,int[] low,int[] clock,List<int[]> bridges) { // Compute one subtree's low-link information.
        entered[v]=low[v]=clock[0]++; // Initially only v itself is known reachable from this subtree.
        for(int next:g.get(v)) { // Inspect all tree and back edges incident to v.
            if(next==parent) continue; // Ignore the reverse of the unique tree edge in this simple graph.
            if(entered[next]==-1) { // An unseen neighbor becomes a DFS child.
                search(next,v,g,entered,low,clock,bridges); // Complete the child's low-link computation first.
                low[v]=Math.min(low[v],low[next]); // Child reachability may reach an ancestor of v.
                if(low[next]>entered[v]) bridges.add(new int[]{Math.min(v,next),Math.max(v,next)}); // No alternate route crosses this parent-child cut.
            } else low[v]=Math.min(low[v],entered[next]); // A back edge reaches an already entered vertex directly.
        }
    }
    public static String solve(int n,int[][] edges) { // Expose the answer as a reusable method as well as a runnable program.
        List<List<Integer>> g=graph(n,edges,false); // Bridge analysis uses both directions of each edge.
        int[] entered=new int[n],low=new int[n],clock={0}; Arrays.fill(entered,-1); // Entry times distinguish tree edges from already discovered neighbors.
        List<int[]> bridges=new ArrayList<>(); // Record normalized endpoint pairs of critical edges.
        for(int v=0;v<n;v++) if(entered[v]==-1) search(v,-1,g,entered,low,clock,bridges); // Cover disconnected components independently.
        bridges.sort(Comparator.<int[]>comparingInt(e->e[0]).thenComparingInt(e->e[1])); // Normalize result order independently of DFS traversal order.
        return Arrays.deepToString(bridges.toArray(new int[0][])); // Display sorted critical endpoint pairs.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(4,new int[][]{{0,1},{1,2},{2,0},{1,3}})); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(4,new int[][]{{0,1},{1,2},{2,0},{1,3}}), "[[1, 3]]"); // Only the tail edge is critical
        Check.equal(solve(3,new int[][]{{0,1},{1,2},{2,0}}), "[]"); // A cycle has no bridge
        Check.equal(solve(2,new int[][]{{0,1}}), "[[0, 1]]"); // Single edge is a bridge
    }
}
