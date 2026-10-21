package questions.q029;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q029: Find the second distinct maximum.
 * Return the second-largest distinct value using OptionalInt when absent. Tracking two candidates avoids sorting and correctly handles duplicates and Integer.MIN_VALUE.
 * Complexity: O(n) time and O(1) space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q029SecondDistinctMaximum { // Keep this question independently runnable and easy to find.
    private Q029SecondDistinctMaximum() { } // This exercise exposes behavior through static methods.
    public static OptionalInt solve(int[] a) { // Define the input and result types of this particular problem.
        Integer largest = null, second = null; // Absence is separate from every valid int value.
        for (int value : a) { // Update the two best distinct candidates after each input.
            if (largest == null || value > largest) { // A new maximum pushes the previous maximum into second place.
                second = largest; // Preserve the former maximum before overwriting it.
                largest = value; // Record the new best value.
            } else if (value < largest && (second == null || value > second)) { // Ignore duplicate maxima and improve only the second slot.
                second = value; // The candidate is strictly between the tracked extrema.
            }
        }
        return second == null ? OptionalInt.empty() : OptionalInt.of(second); // Fewer than two distinct values has no answer.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[]{9,9,7,2}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[]{9,9,7,2}), OptionalInt.of(7)); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[]{5,5}), OptionalInt.empty()); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[]{}), OptionalInt.empty()); // Case 3: verify the expected result for this input.
    }
}
