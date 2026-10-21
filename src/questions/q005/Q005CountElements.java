package questions.q005;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q005: Count elements with a loop.
 * Count how many elements an array contains using iteration. This teaches a counting invariant; in application code a.length already gives the answer in O(1).
 * Complexity: This learning implementation is O(n) time and O(1) auxiliary space; a.length is O(1).
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q005CountElements { // Keep this question independently runnable and easy to find.
    private Q005CountElements() { } // This exercise exposes behavior through static methods.
    public static int solve(int[] a) { // Define the input and result types of this particular problem.
        int count = 0; // No elements have been visited initially.
        for (int ignored : a) { // This exercise intentionally counts visits instead of using length directly.
            count++; // Maintain the invariant that count equals the number of elements visited.
        }
        return count; // At termination every element has contributed exactly one.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[]{5,6,7}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[]{5,6,7}), 3); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[]{}), 0); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[]{0}), 1); // Case 3: verify the expected result for this input.
    }
}
