package questions.q339; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q339: Find vertices whose removal increases component count.
 * Contract: Simple undirected graph; valid endpoints; no parallel edges; recursion must fit JVM stack.
 * Why: For a non-root vertex, a child with low[child]>=entered[v] cannot reconnect above v after v is removed. A DFS root has no ancestor, so it is a cut vertex exactly when it has at least two DFS children.
 * Complexity: O(V+E) time and space.
 */
public final class Q339ArticulationPoints { // One class contains this question, its answer, and reproducible checks.
    static List<List<Integer>> graph(int n,int[][] edges,boolean directed) { // Build adjacency lists matching the declared edge direction.
        List<List<Integer>> g=new ArrayList<>(); // Allocate one neighbor container per vertex.
        for(int v=0;v<n;v++) g.add(new ArrayList<>()); // Isolated vertices still need empty adjacency lists.
        for(int[] e:edges) { // Each row describes one edge between valid vertex indices.
            g.get(e[0]).add(e[1]); // Preserve the forward connection.
            if(!directed) g.get(e[1]).add(e[0]); // Undirected relationships must be traversable both ways.
        }
        return g; // Neighbor order follows input edge order, making traversals deterministic.
    }    static void search(int v,int parent,List<List<Integer>> g,int[] entered,int[] low,int[] clock,boolean[] cut) { // Combine child low-link summaries to identify separating vertices.
        entered[v]=low[v]=clock[0]++; int children=0; // Count DFS children separately from raw neighbors.
        for(int next:g.get(v)) { // Evaluate every incident edge.
            if(next==parent) continue; // The reverse tree edge supplies no alternate path.
            if(entered[next]==-1) { // Unseen neighbors create new DFS subtrees.
                children++; search(next,v,g,entered,low,clock,cut); // Determine whether this child can reach above v.
                low[v]=Math.min(low[v],low[next]); // Propagate the earliest ancestor reachable from the child region.
                if(parent!=-1&&low[next]>=entered[v]) cut[v]=true; // Removing v disconnects this child from v's ancestors.
            } else low[v]=Math.min(low[v],entered[next]); // A back edge may bypass a candidate separating vertex.
        }
        if(parent==-1&&children>1) cut[v]=true; // Distinct root child subtrees cannot reconnect without the root.
    }
    public static String solve(int n,int[][] edges) { // Expose the answer as a reusable method as well as a runnable program.
        List<List<Integer>> g=graph(n,edges,false); // Undirected adjacency determines whether child regions can reconnect.
        int[] entered=new int[n],low=new int[n],clock={0}; Arrays.fill(entered,-1); // Store discovery and ancestor-reachability timestamps.
        boolean[] cut=new boolean[n]; // Multiple separating children still mark one articulation vertex.
        for(int v=0;v<n;v++) if(entered[v]==-1) search(v,-1,g,entered,low,clock,cut); // Apply the root rule independently to each component.
        List<Integer> result=new ArrayList<>(); for(int v=0;v<n;v++) if(cut[v]) result.add(v); // Report vertex IDs in deterministic ascending order.
        return result.toString(); // Unmarked vertices can be removed without splitting their component.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(4,new int[][]{{0,1},{1,2},{1,3}})); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(4,new int[][]{{0,1},{1,2},{1,3}}), "[1]"); // Central vertex separates branches
        Check.equal(solve(3,new int[][]{{0,1},{1,2},{2,0}}), "[]"); // Cycle reconnects around every removed vertex
        Check.equal(solve(2,new int[][]{{0,1}}), "[]"); // Removing an endpoint does not increase component count
    }
}
