package questions.q010;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q010: Find the minimum without sorting.
 * Return the smallest element of a nonempty array. A scan suffices because only one extreme is required; the relative order of other values is irrelevant.
 * Complexity: O(n) time and O(1) auxiliary space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q010Minimum { // Keep this question independently runnable and easy to find.
    private Q010Minimum() { } // This exercise exposes behavior through static methods.
    public static int solve(int[] a) { // Define the input and result types of this particular problem.
        if (a.length == 0) throw new IllegalArgumentException("minimum needs an element"); // Reject an undefined result.
        int best = a[0]; // Start with a valid candidate rather than a fragile numeric sentinel.
        for (int value : a) { // Examine every value in the unsorted array.
            best = Math.min(best, value); // Keep the minimum of the processed prefix.
        }
        return best; // The prefix now covers the entire input.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[]{3,9,4}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[]{3,9,4}), 3); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[]{-7,-2}), -7); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[]{5}), 5); // Case 3: verify the expected result for this input.
    }
}
