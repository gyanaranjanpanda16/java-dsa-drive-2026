package questions.q420;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q420: Count Subsets With Target Sum.
 * Contract: Nonnegative values; count positional subsets, including the empty subset for target zero.
 * Why it works: For each position, subsets split into those excluding it and those including it. Adding the previous smaller-total count implements this disjoint partition.
 * Cost: O(n*target) time and O(target) space; overflow throws.
 */
public final class Q420CountSubsetsWithTargetSum { // Keep this question independently runnable and testable.
    private Q420CountSubsetsWithTargetSum() { } // No object state is needed by this standalone exercise.
    public static long count(int[] values,int target) { // Expose the operation for an independent caller and the executable checks below.
        if(target<0) throw new IllegalArgumentException("Nonnegative target required"); // Array indexing represents only nonnegative sums.
        long[] dp=new long[target+1]; dp[0]=1; // The empty subset is one way to achieve zero.
        for(int value:values) { // Equal values at different positions remain distinct subset choices.
            if(value<0) throw new IllegalArgumentException("Nonnegative values required"); // Negative inputs require a different state representation.
            for(int sum=target;sum>=value;sum--) dp[sum]=Math.addExact(dp[sum],dp[sum-value]); // Descending order prevents reuse; a zero doubles each existing count.
        }
        return dp[target]; // Each subset has one unique series of include/exclude position choices.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(count(new int[]{1,2,3,3},6)); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(count(new int[]{},0)==1, "Q420 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(count(new int[]{0,0},0)==4, "Q420 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(count(new int[]{1,2},4)==0, "Q420 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
