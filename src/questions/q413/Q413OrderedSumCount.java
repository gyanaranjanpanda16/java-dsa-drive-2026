package questions.q413;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q413: Ordered Sum Count.
 * Contract: Distinct positive choices may be reused; count ordered sequences with a nonnegative target.
 * Why it works: Every ordered sequence has a unique final value; removing it leaves a sequence counted by the smaller total. Summing these disjoint final-value cases counts orderings correctly.
 * Cost: O(target*valueCount) time and O(target+valueCount) space; overflow throws.
 */
public final class Q413OrderedSumCount { // Keep this question independently runnable and testable.
    private Q413OrderedSumCount() { } // No object state is needed by this standalone exercise.
    public static long count(int[] values,int target) { // Expose the operation for an independent caller and the executable checks below.
        if(target<0) throw new IllegalArgumentException("Nonnegative target required"); // The recurrence is defined on smaller nonnegative totals.
        Set<Integer> unique=new HashSet<>(); // Equal choices would duplicate the same value sequence.
        for(int value:values) if(value<=0||!unique.add(value)) throw new IllegalArgumentException("Distinct positive values required"); // Positivity guarantees finite sequences for a fixed target.
        long[] dp=new long[target+1]; dp[0]=1; // The empty sequence contributes one way to total zero.
        for(int total=1;total<=target;total++) { // Solve totals before using them as prefixes of longer sequences.
            for(int value:values) if(value<=total) dp[total]=Math.addExact(dp[total],dp[total-value]); // Partition sequences by their final value.
        }
        return dp[target]; // Different orders remain different because final-value transitions are counted separately.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(count(new int[]{1,2,3},4)); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(count(new int[]{},0)==1, "Q413 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(count(new int[]{2},3)==0, "Q413 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(count(new int[]{1,2},3)==3, "Q413 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
