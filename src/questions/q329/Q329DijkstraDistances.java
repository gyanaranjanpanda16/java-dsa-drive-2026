package questions.q329; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q329: Find shortest paths with nonnegative weights.
 * Contract: Directed edges are [from,to,nonnegative int weight]; valid existing source; path sums use long.
 * Why: With nonnegative weights, the smallest pending distance cannot be improved through a more expensive unfinished prefix. Relaxation explores every outgoing continuation, and stale-entry rejection preserves this settled-distance reasoning.
 * Complexity: O(V+E log(E+1)) time and O(V+E) space.
 */
public final class Q329DijkstraDistances { // One class contains this question, its answer, and reproducible checks.
    static List<List<Integer>> graph(int n,int[][] edges,boolean directed) { // Build adjacency lists matching the declared edge direction.
        List<List<Integer>> g=new ArrayList<>(); // Allocate one neighbor container per vertex.
        for(int v=0;v<n;v++) g.add(new ArrayList<>()); // Isolated vertices still need empty adjacency lists.
        for(int[] e:edges) { // Each row describes one edge between valid vertex indices.
            g.get(e[0]).add(e[1]); // Preserve the forward connection.
            if(!directed) g.get(e[1]).add(e[0]); // Undirected relationships must be traversable both ways.
        }
        return g; // Neighbor order follows input edge order, making traversals deterministic.
    }
    public static String solve(int n,int[][] edges,int source) { // Expose the answer as a reusable method as well as a runnable program.
        List<List<int[]>> g=new ArrayList<>(); for(int v=0;v<n;v++) g.add(new ArrayList<>()); // Weighted adjacency stores destination and edge cost together.
        for(int[] e:edges) { if(e[2]<0) throw new IllegalArgumentException("negative weight"); g.get(e[0]).add(new int[]{e[1],e[2]}); } // Reject weights that invalidate Dijkstra's proof.
        long[] distance=new long[n]; Arrays.fill(distance,Long.MAX_VALUE); distance[source]=0; // Unreachable vertices have an explicit sentinel.
        PriorityQueue<long[]> heap=new PriorityQueue<>(Comparator.comparingLong(s->s[1])); heap.offer(new long[]{source,0}); // Expand the smallest tentative distance first.
        while(!heap.isEmpty()) { // Improved distances may leave obsolete entries in the heap.
            long[] state=heap.poll(); int v=(int)state[0]; // Recover the vertex attached to this tentative distance.
            if(state[1]!=distance[v]) continue; // Ignore a stale entry replaced by a shorter route.
            for(int[] e:g.get(v)) if(distance[v]+e[1]<distance[e[0]]) { // Relax only routes that improve a neighbor.
                distance[e[0]]=distance[v]+e[1]; heap.offer(new long[]{e[0],distance[e[0]]}); // Queue the improved route without needing decrease-key support.
            }
        }
        List<String> out=new ArrayList<>(); for(long d:distance) out.add(d==Long.MAX_VALUE?"INF":Long.toString(d)); // Render unreachable vertices without confusing them with large finite distances.
        return out.toString(); // Vertex order is preserved in the answer.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(4,new int[][]{{0,1,8},{0,2,2},{2,1,1}},0)); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(4,new int[][]{{0,1,8},{0,2,2},{2,1,1}},0), "[0, 3, 2, INF]"); // An indirect route improves a direct one
        Check.equal(solve(1,new int[][]{},0), "[0]"); // Only source
        Check.equal(solve(2,new int[][]{{0,1,0}},0), "[0, 0]"); // Zero-weight edge
    }
}
