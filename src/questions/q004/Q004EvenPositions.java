package questions.q004;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q004: Select elements at even indices.
 * Return values at indices 0, 2, 4, and so on. Even positions refer to indices, not whether the stored values are even.
 * Complexity: O(n) time and O(n) output space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q004EvenPositions { // Keep this question independently runnable and easy to find.
    private Q004EvenPositions() { } // This exercise exposes behavior through static methods.
    public static int[] solve(int[] a) { // Define the input and result types of this particular problem.
        int[] result = new int[(a.length + 1) / 2]; // There is one output for indices 0, 2, 4, and so on.
        for (int i = 0; i < a.length; i += 2) { // Skip odd indices instead of visiting and rejecting them.
            result[i / 2] = a[i]; // Map sparse input positions to consecutive output positions.
        }
        return result; // Return a copy so the caller can use the selected values independently.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[]{9,8,7,6,5}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[]{9,8,7,6,5}), new int[]{9,7,5}); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[]{}), new int[]{}); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[]{3}), new int[]{3}); // Case 3: verify the expected result for this input.
    }
}
