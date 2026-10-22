package questions.q415;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q415: Equal Sum Partition.
 * Contract: Nonnegative values, feasible target-sized memory; empty partitions are permitted.
 * Why it works: A subset summing to half the total is equivalent to an equal partition. Descending updates implement a0/1 choice for each element rather than unlimited reuse.
 * Cost: O(n*target) time and O(target) space; pseudo-polynomial.
 */
public final class Q415EqualSumPartition { // Keep this question independently runnable and testable.
    private Q415EqualSumPartition() { } // No object state is needed by this standalone exercise.
    public static boolean possible(int[] values) { // Expose the operation for an independent caller and the executable checks below.
        long total=0; // Sum in a wider type before choosing an array-backed state range.
        for(int value:values) { if(value<0) throw new IllegalArgumentException("Nonnegative values required"); total+=value; } // Negative values need a different state indexing scheme.
        if((total&1)!=0) return false; // Two equal integer sums cannot split an odd total.
        if(total/2>Integer.MAX_VALUE-1) throw new IllegalArgumentException("Target too large for table"); // Avoid invalid array-length conversion.
        int target=(int)(total/2); boolean[] dp=new boolean[target+1]; dp[0]=true; // Empty selection reaches zero.
        for(int value:values) { // Each position may appear in the chosen subset at most once.
            for(int sum=target;sum>=value;sum--) dp[sum]|=dp[sum-value]; // Descending order prevents reusing the current value in its own transition.
        }
        return dp[target]; // Reaching half the total leaves an equal-sum complement automatically.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(possible(new int[]{1,5,11,5})); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(possible(new int[]{}), "Q415 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(!possible(new int[]{1,2,5}), "Q415 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(possible(new int[]{0,0}), "Q415 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
