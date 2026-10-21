package questions.q009;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q009: Find the maximum without sorting.
 * Return the largest value in a nonempty array. Initializing to zero would be wrong for an all-negative array, so use the first actual element.
 * Complexity: O(n) time and O(1) auxiliary space; sorting would cost more.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q009Maximum { // Keep this question independently runnable and easy to find.
    private Q009Maximum() { } // This exercise exposes behavior through static methods.
    public static int solve(int[] a) { // Define the input and result types of this particular problem.
        if (a.length == 0) throw new IllegalArgumentException("maximum needs an element"); // Empty input has no maximum.
        int best = a[0]; // A real element handles all-negative inputs correctly.
        for (int value : a) { // Compare every candidate to the best seen so far.
            if (value > best) best = value; // Replace the best only when the candidate is larger.
        }
        return best; // No input value exceeds the final best.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[]{3,9,4}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[]{3,9,4}), 9); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[]{-7,-2}), -2); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[]{5}), 5); // Case 3: verify the expected result for this input.
    }
}
