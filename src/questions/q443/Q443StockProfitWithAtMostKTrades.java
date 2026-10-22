package questions.q443;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q443: Stock Profit With At Most K Trades.
 * Contract: Nonnegative int prices; at most one held share; no fees or cooldown.
 * Why it works: For each transaction budget track the best holding and nonholding profit. A purchase consumes the opportunity for the next completed trade, while a sale realizes that budget state.
 * Cost: O(n*min(k,n)) time, O(k) space when k<n/2; otherwise O(n) time and O(1) space.
 */
public final class Q443StockProfitWithAtMostKTrades { // Keep this question independently runnable and testable.
    private Q443StockProfitWithAtMostKTrades() { } // No object state is needed by this standalone exercise.
    public static long profit(int[] prices,int k) { // Expose the operation for an independent caller and the executable checks below.
        if(k<0) throw new IllegalArgumentException("Nonnegative trade limit required"); // A negative transaction budget is meaningless.
        if(k>=prices.length/2) { long gain=0; for(int i=1;i<prices.length;i++) gain+=Math.max(0L,(long)prices[i]-prices[i-1]); return gain; } // A large budget permits collecting every rise.
        long[] buy=new long[k+1],sell=new long[k+1]; Arrays.fill(buy,Long.MIN_VALUE/4); // Index t represents the budget of t completed transactions.
        for(int price:prices) for(int t=k;t>=1;t--) { // Descending budgets keep smaller-budget states from the previous day.
            sell[t]=Math.max(sell[t],buy[t]+price); // Complete transaction t from a previously held position.
            buy[t]=Math.max(buy[t],sell[t-1]-price); // Buy after at most t-1 completed transactions.
        }
        return sell[k]; // The initialized zero allows fewer than k trades when that is better.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(profit(new int[]{3,2,6,5,0,3},2)); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(profit(new int[]{1,2},0)==0, "Q443 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(profit(new int[]{},3)==0, "Q443 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(profit(new int[]{3,2,6,5,0,3},2)==7, "Q443 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
