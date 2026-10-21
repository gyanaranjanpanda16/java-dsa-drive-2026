package questions.q053;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q053: Maximize profit from one buy and sell.
 * Given daily nonnegative prices, return maximum profit using at most one buy followed by one sale. A running minimum is sufficient because a more expensive earlier purchase can never improve a future sale.
 * Complexity: O(n) time and O(1) space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q053SingleTradeProfit { // Keep this question independently runnable and easy to find.
    private Q053SingleTradeProfit() { } // This exercise exposes behavior through static methods.
    public static long solve(int[] prices) { // Define the input and result types of this particular problem.
        long cheapest = Long.MAX_VALUE, best = 0; // Doing no trade gives profit zero.
        for (int price : prices) { // A sale must use a purchase from this day or an earlier day.
            cheapest = Math.min(cheapest, price); // Retain the cheapest purchase opportunity seen so far.
            best = Math.max(best, (long) price - cheapest); // Selling today is optimal with the cheapest eligible purchase.
        }
        return best; // Every possible sale day has been considered.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[]{7,1,5,3,6,4}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[]{7,1,5,3,6,4}), 5L); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[]{5,4,2}), 0L); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[]{}), 0L); // Case 3: verify the expected result for this input.
    }
}
