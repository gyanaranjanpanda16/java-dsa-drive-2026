package questions.q047;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q047: Find an equilibrium index.
 * Find the first index whose preceding values sum to the same amount as its following values. The current element belongs to neither side.
 * Complexity: O(n) time and O(1) space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q047EquilibriumIndex { // Keep this question independently runnable and easy to find.
    private Q047EquilibriumIndex() { } // This exercise exposes behavior through static methods.
    public static int solve(int[] a) { // Define the input and result types of this particular problem.
        long total = 0; // Accumulate the whole array so right-side sums can be derived.
        for (int value : a) total += value; // Use long for sums of int values.
        long left = 0; // Nothing lies before index zero.
        for (int i = 0; i < a.length; i++) { // Test positions in order to return the first equilibrium.
            if (left == total - left - a[i]) return i; // Exclude the current value from both sides.
            left += a[i]; // Advance the left sum for the next position.
        }
        return -1; // No position balanced its two surrounding sums.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[]{1,7,3,6,5,6}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[]{1,7,3,6,5,6}), 3); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[]{1,2,3}), -1); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[]{4}), 0); // Case 3: verify the expected result for this input.
    }
}
