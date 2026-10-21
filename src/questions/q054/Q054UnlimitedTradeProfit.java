package questions.q054;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q054: Maximize profit with unlimited transactions.
 * Return maximum profit with unlimited trades, at most one held share, no fees and no cooldown. Every rising day can be collected; grouping consecutive rises into a trade has the same sum.
 * Complexity: O(n) time and O(1) space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q054UnlimitedTradeProfit { // Keep this question independently runnable and easy to find.
    private Q054UnlimitedTradeProfit() { } // This exercise exposes behavior through static methods.
    public static long solve(int[] prices) { // Define the input and result types of this particular problem.
        long profit = 0; // Choosing no trades is always allowed.
        for (int i = 1; i < prices.length; i++) { // Examine each consecutive daily change.
            if (prices[i] > prices[i - 1]) profit += (long) prices[i] - prices[i - 1]; // Collect every rise while skipping losses.
        }
        return profit; // Consecutive rises telescope into the same profit as one longer holding period.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[]{7,1,5,3,6,4}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[]{7,1,5,3,6,4}), 7L); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[]{1,2,3}), 2L); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[]{4,2}), 0L); // Case 3: verify the expected result for this input.
    }
}
