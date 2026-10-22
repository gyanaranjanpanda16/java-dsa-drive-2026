package questions.q331; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q331: Find all-pairs shortest distances.
 * Contract: Directed [from,to,int weight] edges; valid vertices; practical-size inputs with path arithmetic within long; any negative cycle produces the reported marker.
 * Why: With intermediate vertices restricted to0..k, an optimal route either avoids k or splits into i-to-k and k-to-j routes using earlier intermediates. This recurrence covers all possibilities and yields all shortest distances when no negative cycle exists.
 * Complexity: O(V³+E) time and O(V²) space.
 */
public final class Q331FloydWarshall { // One class contains this question, its answer, and reproducible checks.
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
        long inf=Long.MAX_VALUE/4; long[][] distance=new long[n][n]; // Reserve a distinct sentinel for absent paths.
        for(int i=0;i<n;i++) { Arrays.fill(distance[i],inf); distance[i][i]=0; } // Every vertex reaches itself by an empty path.
        for(int[] e:edges) distance[e[0]][e[1]]=Math.min(distance[e[0]][e[1]],e[2]); // Parallel edges retain only their cheapest direct cost.
        for(int k=0;k<n;k++) for(int i=0;i<n;i++) for(int j=0;j<n;j++) { // Admit one more possible intermediate vertex at each outer step.
            if(distance[i][k]!=inf&&distance[k][j]!=inf) distance[i][j]=Math.min(distance[i][j],distance[i][k]+distance[k][j]); // Compare avoiding k with a path split at k.
        }
        for(int i=0;i<n;i++) if(distance[i][i]<0) return "NEGATIVE CYCLE"; // A negative self-route implies unbounded negative cycling.
        List<List<String>> out=new ArrayList<>(); // Format all source-target answers while preserving vertex order.
        for(long[] row:distance) { List<String> values=new ArrayList<>(); for(long d:row) values.add(d==inf?"INF":Long.toString(d)); out.add(values); } // Keep absence distinguishable from finite negative distances.
        return out.toString(); // Each row corresponds to one source vertex.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(3,new int[][]{{0,1,3},{1,2,-1},{0,2,8}})); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(3,new int[][]{{0,1,3},{1,2,-1},{0,2,8}}), "[[0, 3, 2], [INF, 0, -1], [INF, INF, 0]]"); // An intermediate improves a direct path
        Check.equal(solve(1,new int[][]{}), "[[0]]"); // Single vertex
        Check.equal(solve(1,new int[][]{{0,0,-1}}), "NEGATIVE CYCLE"); // Negative self-loop
    }
}
