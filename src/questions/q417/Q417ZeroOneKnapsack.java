package questions.q417;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q417: Zero One Knapsack.
 * Contract: Positive weights, integer values, at most one copy per item; totals fit long.
 * Why it works: An optimal prefix solution either skips the new item or includes it with a best earlier-item solution using the remaining capacity. Descending capacity order enforces the one-copy rule.
 * Cost: O(n*capacity) time and O(capacity) space.
 */
public final class Q417ZeroOneKnapsack { // Keep this question independently runnable and testable.
    private Q417ZeroOneKnapsack() { } // No object state is needed by this standalone exercise.
    public static long maximum(int[] weight,int[] value,int capacity) { // Expose the operation for an independent caller and the executable checks below.
        if(weight.length!=value.length||capacity<0) throw new IllegalArgumentException("Matching arrays and nonnegative capacity"); // Every item needs one weight and one value.
        long[] dp=new long[capacity+1]; // Zero means choosing no item, which remains allowed.
        for(int item=0;item<weight.length;item++) { // Introduce each item exactly once.
            if(weight[item]<=0) throw new IllegalArgumentException("Positive weights required"); // Positive weights make descending-state separation straightforward.
            for(int room=capacity;room>=weight[item];room--) dp[room]=Math.max(dp[room],dp[room-weight[item]]+value[item]); // Read a state that has not yet used this item.
        }
        return dp[capacity]; // Unused capacity is allowed because states represent weight at most the capacity.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(maximum(new int[]{2,3,4},new int[]{4,5,7},5)); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(maximum(new int[]{},new int[]{},4)==0, "Q417 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(maximum(new int[]{2},new int[]{5},1)==0, "Q417 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(maximum(new int[]{2,3},new int[]{4,5},5)==9, "Q417 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
