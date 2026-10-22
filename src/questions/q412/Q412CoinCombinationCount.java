package questions.q412;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q412: Coin Combination Count.
 * Contract: Distinct positive denominations; unlimited supply; count unordered combinations for a nonnegative feasible table size.
 * Why it works: Processing coin types outside the amount loop gives each multiset a canonical type order. The recurrence adds combinations containing another copy of the current coin without creating different orderings of identical choices.
 * Cost: O(amount*coinCount) time and O(amount+coinCount) space; overflow is reported.
 */
public final class Q412CoinCombinationCount { // Keep this question independently runnable and testable.
    private Q412CoinCombinationCount() { } // No object state is needed by this standalone exercise.
    public static long count(int[] coins,int amount) { // Expose the operation for an independent caller and the executable checks below.
        if(amount<0) throw new IllegalArgumentException("Nonnegative amount required"); // A negative target is outside the counting domain.
        Set<Integer> unique=new HashSet<>(); // Duplicate denominations would otherwise count the same combination repeatedly.
        for(int coin:coins) if(coin<=0||!unique.add(coin)) throw new IllegalArgumentException("Distinct positive coins required"); // Enforce a unique coin-type model.
        long[] dp=new long[amount+1]; dp[0]=1; // One empty multiset makes amount zero.
        for(int coin:coins) { // Finish one denomination before introducing the next to avoid counting permutations.
            for(int sum=coin;sum<=amount;sum++) dp[sum]=Math.addExact(dp[sum],dp[sum-coin]); // Increasing totals permit unlimited copies of the current coin.
        }
        return dp[amount]; // Each multiset was introduced according to its final denomination type.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(count(new int[]{1,2,5},5)); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(count(new int[]{},0)==1, "Q412 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(count(new int[]{2},3)==0, "Q412 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(count(new int[]{1,2},4)==3, "Q412 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
