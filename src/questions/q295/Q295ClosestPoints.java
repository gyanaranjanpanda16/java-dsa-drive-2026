package questions.q295; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q295: Return k points nearest the origin.
 * Contract: Each row has two coordinates with absolute value <=1,000,000,000 so squared sums fit long; 0<=k<=n.
 * Why: For nonnegative distances, squaring preserves comparisons. The queue repeatedly exposes the closest remaining point; coordinate ties make the example deterministic without altering the distance criterion.
 * Complexity: O(n log(n+1)+k log(n+1)) time and O(n+k) storage.
 */
public final class Q295ClosestPoints { // One class contains this question, its answer, and reproducible checks.
    static long distance(int[] p) { // Compare squared lengths instead of approximate square roots.
        return (long)p[0]*p[0]+(long)p[1]*p[1]; // Widen before multiplication to preserve the promised coordinate range.
    }
    public static String solve(int[][] points,int k) { // Expose the answer as a reusable method as well as a runnable program.
        if(k<0||k>points.length) throw new IllegalArgumentException("invalid count"); // Require exactly k available points.
        PriorityQueue<int[]> heap=new PriorityQueue<>((a,b)->{ // The smallest distance is removed first.
            int order=Long.compare(distance(a),distance(b)); // Squared distance preserves Euclidean ordering without square roots.
            if(order!=0) return order; // Different distances determine priority immediately.
            order=Integer.compare(a[0],b[0]); return order!=0?order:Integer.compare(a[1],b[1]); // Coordinates give deterministic ties.
        });
        for(int[] point:points) heap.offer(point); // Include every point occurrence without changing its coordinates.
        List<String> result=new ArrayList<>(); // Display selected coordinates in priority order.
        for(int i=0;i<k;i++) result.add(Arrays.toString(heap.poll())); // Exactly k removals answer the requested selection.
        return result.toString(); // Equal points remain separate occurrences.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(new int[][]{{3,3},{1,1},{-1,0}},2)); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(new int[][]{{3,3},{1,1},{-1,0}},2), "[[-1, 0], [1, 1]]"); // Different distances
        Check.equal(solve(new int[][]{},0), "[]"); // No requested points
        Check.equal(solve(new int[][]{{1,0},{-1,0}},2), "[[-1, 0], [1, 0]]"); // Coordinate tie-break
    }
}
