package questions.q411;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q411: Minimum Coin Count.
 * Contract: Unlimited positive denomination coins; nonnegative amount small enough to allocate a table.
 * Why it works: Removing the final coin leaves a smaller amount. Trying every denomination includes the last coin of an optimal solution, while the stored smaller answer is already optimal.
 * Cost: O(amount*coinCount) time and O(amount) space; pseudo-polynomial in the numeric amount.
 */
public final class Q411MinimumCoinCount { // Keep this question independently runnable and testable.
    private Q411MinimumCoinCount() { } // No object state is needed by this standalone exercise.
    public static int minimum(int[] coins,int amount) { // Expose the operation for an independent caller and the executable checks below.
        if(amount<0||amount==Integer.MAX_VALUE) throw new IllegalArgumentException("Valid nonnegative amount required"); // Reserve amount+1 safely as an unreachable sentinel.
        for(int coin:coins) if(coin<=0) throw new IllegalArgumentException("Positive denominations required"); // Nonpositive coins would invalidate the smaller-amount recurrence.
        int[] dp=new int[amount+1]; Arrays.fill(dp,amount+1); dp[0]=0; // Zero coins make zero while positive amounts begin unreachable.
        for(int sum=1;sum<=amount;sum++) { // All smaller remaining amounts have already been solved.
            for(int coin:coins) if(coin<=sum) dp[sum]=Math.min(dp[sum],dp[sum-coin]+1); // Try every possible final coin.
        }
        return dp[amount]>amount?-1:dp[amount]; // No valid solution requires more than amount positive-valued coins.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(minimum(new int[]{1,3,4},6)); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(minimum(new int[]{2},3)==-1, "Q411 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(minimum(new int[]{},0)==0, "Q411 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(minimum(new int[]{1,3,4},6)==2, "Q411 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
