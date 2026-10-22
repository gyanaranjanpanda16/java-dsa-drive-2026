package questions.q419;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q419: Rod Cutting Revenue.
 * Contract: price[i] prices a piece of length i+1; cut the full rod of length price.length, even if prices are negative.
 * Why it works: Every nonempty cutting plan has a first piece. Trying every length for that piece and combining it with an optimal remainder covers every possible plan.
 * Cost: O(n²) time and O(n) space.
 */
public final class Q419RodCuttingRevenue { // Keep this question independently runnable and testable.
    private Q419RodCuttingRevenue() { } // No object state is needed by this standalone exercise.
    public static long maximum(int[] price) { // Expose the operation for an independent caller and the executable checks below.
        long[] dp=new long[price.length+1]; // dp[length] stores the best revenue for exactly that rod length.
        Arrays.fill(dp,Long.MIN_VALUE); dp[0]=0; // Only the empty rod has a known value before any cuts are considered.
        for(int length=1;length<=price.length;length++) { // Every shorter leftover rod is already solved.
            for(int first=1;first<=length;first++) dp[length]=Math.max(dp[length],price[first-1]+dp[length-first]); // Choose the first piece and optimally cut the remaining length.
        }
        return dp[price.length]; // Every unit of the original rod has been assigned to a priced piece.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(maximum(new int[]{1,5,8,9})); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(maximum(new int[]{})==0, "Q419 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(maximum(new int[]{-2})==-2, "Q419 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(maximum(new int[]{1,5,8,9})==10, "Q419 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
