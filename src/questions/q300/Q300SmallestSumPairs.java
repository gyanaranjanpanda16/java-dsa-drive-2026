package questions.q300; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q300: Find k pairs with the smallest sums.
 * Contract: Both arrays nondecreasing; pairs refer to distinct index combinations; k>=0.
 * Why: For each fixed index in a, adding sorted b produces a sorted row of sums. A heap merges these rows exactly as it merges sorted feeds, avoiding materializing every pair.
 * Complexity: O(n log(n+1)+min(k,nm) log(n+1)) time; O(n) heap plus output.
 */
public final class Q300SmallestSumPairs { // One class contains this question, its answer, and reproducible checks.

    public static String solve(int[] a,int[] b,int k) { // Expose the answer as a reusable method as well as a runnable program.
        if(k<0) throw new IllegalArgumentException("negative count"); // Zero pairs is a valid request.
        PriorityQueue<int[]> heap=new PriorityQueue<>((x,y)->{ // Compare pair cursors by exact long sums.
            int order=Long.compare((long)a[x[0]]+b[x[1]],(long)a[y[0]]+b[y[1]]); // Widen before addition to avoid int overflow.
            if(order!=0) return order; // Smaller sums must be output first.
            order=Integer.compare(x[0],y[0]); return order!=0?order:Integer.compare(x[1],y[1]); // Indices break sum ties deterministically.
        });
        if(b.length>0) for(int i=0;i<a.length;i++) heap.offer(new int[]{i,0}); // Each fixed-a row is a sorted sequence of pair sums.
        List<String> result=new ArrayList<>(); // Preserve repeated index pairs even when values coincide.
        while(result.size()<k&&!heap.isEmpty()) { // Stop after k pairs or after every pair is exhausted.
            int[] cursor=heap.poll(); // The smallest row head is globally the next pair.
            result.add("["+a[cursor[0]]+", "+b[cursor[1]]+"]"); // Convert the selected coordinates into values.
            if(++cursor[1]<b.length) heap.offer(cursor); // Advance only the row just consumed.
        }
        return result.toString(); // Output ordering follows sum, then original indices.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(new int[]{1,7},new int[]{2,4},3)); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(new int[]{1,7},new int[]{2,4},3), "[[1, 2], [1, 4], [7, 2]]"); // Merge pair-sum rows
        Check.equal(solve(new int[]{},new int[]{1},2), "[]"); // Empty first array
        Check.equal(solve(new int[]{1},new int[]{2},9), "[[1, 2]]"); // Requested count exceeds available pairs
    }
}
