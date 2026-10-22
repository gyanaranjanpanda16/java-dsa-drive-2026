package questions.q379; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Partition values into k equal-sum subsets.
 * Contract: Given at most16 nonnegative int values and k between1 and the number of values, decide whether every value can be assigned to exactly one of k nonempty equal-sum groups. Preserve the input. Invalid bounds or negative values are rejected.
 * Why: A divisible total is necessary but not sufficient: [2,2,2] cannot form two groups summing3. Assign the largest remaining value first so impossible bucket capacities are exposed early. Buckets with equal current sums are interchangeable for existence, so try only one of each sum. If the total is positive and all buckets end at target, every bucket is nonempty; if all values are zero, n>=k lets us explicitly distribute at least one to each group. This is a small-instance load-balancing feasibility problem.
 * Cost: O(n log n + k^n) worst-case time and O(n+k) working space. Symmetry pruning helps but does not make this polynomial; the16-element contract keeps it an educational small-instance search.
 */
public final class Q379EqualSumPartitions { // Keep the algorithm, demonstration, and boundary checks together.
    private Q379EqualSumPartitions() { } // Only static entry points are needed for this exercise.
    public static boolean solve(int[] values,int k) { // Return feasibility rather than enumerating all partitions.
        if(values.length>16||k<1||k>values.length) throw new IllegalArgumentException("1<=k<=n<=16 required"); // Each group needs at least one available element.
        long total=0; for(int value:values) { if(value<0) throw new IllegalArgumentException("nonnegative values required"); total+=value; } // Widen the sum before adding.
        if(total%k!=0) return false; // Unequal fractional targets cannot be reached with integers.
        if(total==0) return true; // Nonnegative inputs are all zero and can fill k nonempty groups.
        int[] sorted=values.clone(); Arrays.sort(sorted); // Leave the caller's order intact.
        return place(sorted,sorted.length-1,new long[k],total/k); // Visit values from largest to smallest.
    }
    private static boolean place(int[] values,int at,long[] sums,long target) { // Bucket sums record exactly the assigned values.
        if(at<0) return true; // The full total is assigned and no bucket exceeded target, so every bucket equals target.
        for(int bucket=0;bucket<sums.length;bucket++) { // Try placing this value into each meaningfully different bucket.
            boolean repeated=false; for(int earlier=0;earlier<bucket;earlier++) if(sums[earlier]==sums[bucket]) repeated=true; // Equal sums produce symmetric subproblems.
            if(repeated||sums[bucket]+values[at]>target) continue; // Skip symmetry and impossible capacity.
            sums[bucket]+=values[at]; // Make one tentative choice.
            if(place(values,at-1,sums,target)) return true; // A successful completion proves feasibility.
            sums[bucket]-=values[at]; // Undo the failed choice before another bucket is tested.
        }
        return false; // Every distinct feasible bucket choice failed.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new int[]{4,3,2,3,5,2,1},4))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new int[]{4,3,2,3,5,2,1},4), true); // Multiple nontrivial groups.
        Check.equal(solve(new int[]{2,2,2},2), false); // Divisibility alone is insufficient.
        Check.equal(solve(new int[]{0,0,0},2), true); // Zero-sum groups must still be nonempty.
    }
}
