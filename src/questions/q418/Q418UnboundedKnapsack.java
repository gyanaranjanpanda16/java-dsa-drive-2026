package questions.q418;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q418: Unbounded Knapsack.
 * Contract: Positive weights and reusable integer-valued items; choosing none is allowed.
 * Why it works: Including one copy reduces the capacity but may leave a solution containing the same type. Ascending updates represent exactly that permitted reuse.
 * Cost: O(types*capacity) time and O(capacity) space.
 */
public final class Q418UnboundedKnapsack { // Keep this question independently runnable and testable.
    private Q418UnboundedKnapsack() { } // No object state is needed by this standalone exercise.
    public static long maximum(int[] weight,int[] value,int capacity) { // Expose the operation for an independent caller and the executable checks below.
        if(weight.length!=value.length||capacity<0) throw new IllegalArgumentException("Matching arrays and nonnegative capacity"); // Validate the item representation and table bound.
        long[] dp=new long[capacity+1]; // Empty selections have value zero for every capacity bound.
        for(int item=0;item<weight.length;item++) { // Introduce each reusable item type.
            if(weight[item]<=0) throw new IllegalArgumentException("Positive weights required"); // Zero-weight positive value would make the optimum unbounded.
            for(int room=weight[item];room<=capacity;room++) dp[room]=Math.max(dp[room],dp[room-weight[item]]+value[item]); // Ascending order deliberately allows another copy of the same type.
        }
        return dp[capacity]; // The table has considered every multiplicity fitting the capacity.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(maximum(new int[]{2,3},new int[]{4,5},6)); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(maximum(new int[]{},new int[]{},3)==0, "Q418 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(maximum(new int[]{3},new int[]{5},2)==0, "Q418 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(maximum(new int[]{2},new int[]{4},6)==12, "Q418 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
