package questions.q006;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q006: Sum array values safely in long.
 * Return the sum of integer array elements as a long. The accumulator represents the sum of the already-visited prefix; adding the next value preserves that invariant.
 * Complexity: O(n) time and O(1) auxiliary space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q006SumElements { // Keep this question independently runnable and easy to find.
    private Q006SumElements() { } // This exercise exposes behavior through static methods.
    public static long solve(int[] a) { // Define the input and result types of this particular problem.
        long sum = 0L; // Start with the additive identity and a wider accumulator.
        for (int value : a) { // Include each input value once.
            sum += value; // Promotion to long avoids overflow of an int accumulator.
        }
        return sum; // The sum of an empty array is zero.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[]{3,4,5}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[]{3,4,5}), 12L); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[]{}), 0L); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[]{Integer.MAX_VALUE,Integer.MAX_VALUE}), 4294967294L); // Case 3: verify the expected result for this input.
    }
}
