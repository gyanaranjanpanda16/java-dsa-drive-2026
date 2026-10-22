package questions.q319; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q319: Check whether an undirected graph can be two-colored.
 * Contract: Vertices are numbered0 through n-1; n>=0; edge rows contain valid endpoints; arrays are non-null. Source vertices, if supplied, exist.
 * Why: Alternating colors along paths is necessary for every edge to cross the partition. If propagation finds a conflict, the competing path parity yields an odd cycle; otherwise the completed coloring itself witnesses a valid bipartition.
 * Complexity: O(V+E) time and storage.
 */
public final class Q319BipartiteCheck { // One class contains this question, its answer, and reproducible checks.
    static List<List<Integer>> graph(int n,int[][] edges,boolean directed) { // Build adjacency lists matching the declared edge direction.
        List<List<Integer>> g=new ArrayList<>(); // Allocate one neighbor container per vertex.
        for(int v=0;v<n;v++) g.add(new ArrayList<>()); // Isolated vertices still need empty adjacency lists.
        for(int[] e:edges) { // Each row describes one edge between valid vertex indices.
            g.get(e[0]).add(e[1]); // Preserve the forward connection.
            if(!directed) g.get(e[1]).add(e[0]); // Undirected relationships must be traversable both ways.
        }
        return g; // Neighbor order follows input edge order, making traversals deterministic.
    }
    public static boolean solve(int n,int[][] edges) { // Expose the answer as a reusable method as well as a runnable program.
        List<List<Integer>> g=graph(n,edges,false); // Adjacent vertices must receive opposite colors.
        int[] color=new int[n]; Arrays.fill(color,-1); // Minus one denotes an uncolored vertex.
        for(int start=0;start<n;start++) if(color[start]==-1) { // Disconnected components need independent color seeds.
            Deque<Integer> q=new ArrayDeque<>(); q.add(start); color[start]=0; // Either color may start a new component.
            while(!q.isEmpty()) { // Propagate required colors through that component.
                int v=q.remove(); // Its color is fixed by an earlier constraint or the seed.
                for(int next:g.get(v)) { // Check every adjacency constraint.
                    if(color[next]==-1) { color[next]=1-color[v]; q.add(next); } // An unseen neighbor must have the opposite color.
                    else if(color[next]==color[v]) return false; // A same-color edge contradicts bipartiteness.
                }
            }
        }
        return true; // Every edge joins opposite colors in the constructed coloring.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(4,new int[][]{{0,1},{1,2},{2,3},{3,0}})); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(4,new int[][]{{0,1},{1,2},{2,3},{3,0}}), true); // Even cycle
        Check.equal(solve(3,new int[][]{{0,1},{1,2},{2,0}}), false); // Odd cycle
        Check.equal(solve(2,new int[][]{}), true); // Isolated vertices
    }
}
