package questions.q442;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q442: Stock Profit With Transaction Fee.
 * Contract: Nonnegative prices and fee; unlimited transactions, at most one held share.
 * Why it works: At each day the only relevant distinction is whether a share is held. Choosing the better hold/sell/buy transitions covers all legal trading histories, charging a fee only on sale.
 * Cost: O(n) time and O(1) space.
 */
public final class Q442StockProfitWithTransactionFee { // Keep this question independently runnable and testable.
    private Q442StockProfitWithTransactionFee() { } // No object state is needed by this standalone exercise.
    public static long profit(int[] price,int fee) { // Expose the operation for an independent caller and the executable checks below.
        if(fee<0) throw new IllegalArgumentException("Nonnegative fee required"); // A fee is charged once for each completed trade.
        long cash=0,hold=Long.MIN_VALUE/4; // Start with no share and zero realized profit.
        for(int value:price) { // Consider either keeping the existing state or trading today.
            if(value<0) throw new IllegalArgumentException("Nonnegative prices required"); // Retain the purchase-cost interpretation.
            long nextCash=Math.max(cash,hold+value-fee); // Selling pays the fee exactly once.
            long nextHold=Math.max(hold,cash-value); // Buy using yesterday's cash so transitions do not accidentally chain.
            cash=nextCash; hold=nextHold; // Move both states to the next day.
        }
        return cash; // Only realized cash is a completed trading answer.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(profit(new int[]{1,3,2,8,4,9},2)); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(profit(new int[]{},2)==0, "Q442 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(profit(new int[]{1,5},1)==3, "Q442 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(profit(new int[]{5,1},0)==0, "Q442 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
