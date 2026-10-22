package questions.q441;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q441: Stock Profit With Cooldown.
 * Contract: Nonnegative prices; unlimited trades, one held share maximum, one cooldown day after each sale.
 * Why it works: Holding, sold-today, and resting states distinguish exactly whether buying is legal. Buying only from the previous rest state enforces the full cooldown day.
 * Cost: O(n) time and O(1) space.
 */
public final class Q441StockProfitWithCooldown { // Keep this question independently runnable and testable.
    private Q441StockProfitWithCooldown() { } // No object state is needed by this standalone exercise.
    public static long profit(int[] price) { // Expose the operation for an independent caller and the executable checks below.
        long hold=Long.MIN_VALUE/4,sold=Long.MIN_VALUE/4,rest=0; // Impossible holding/selling states begin below every feasible nonnegative-profit state.
        for(int value:price) { // Each transition uses only the previous day's states.
            if(value<0) throw new IllegalArgumentException("Nonnegative prices required"); // Prices model purchase costs.
            long nextHold=Math.max(hold,rest-value); // Keep the share or buy only after a rest day, never immediately after selling.
            long nextSold=hold+value; // Selling today requires a share held yesterday.
            long nextRest=Math.max(rest,sold); // Resting can continue or follow yesterday's completed sale.
            hold=nextHold; sold=nextSold; rest=nextRest; // Commit all states together to preserve day boundaries.
        }
        return Math.max(rest,sold); // Ending with an unsold share cannot realize its value.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(profit(new int[]{1,2,3,0,2})); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(profit(new int[]{})==0, "Q441 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(profit(new int[]{5})==0, "Q441 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(profit(new int[]{1,2,3,0,2})==3, "Q441 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
