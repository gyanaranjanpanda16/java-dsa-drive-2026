package questions.q036;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q036: Remove all occurrences while preserving order.
 * Return a new array with every occurrence of a selected value removed. A logical write count makes the number of retained elements explicit.
 * Complexity: O(n) time and O(n) auxiliary/output space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q036RemoveValue { // Keep this question independently runnable and easy to find.
    private Q036RemoveValue() { } // This exercise exposes behavior through static methods.
    public static int[] solve(int[] a, int removed) { // Define the input and result types of this particular problem.
        int[] buffer = new int[a.length]; // The result cannot be longer than the input.
        int size = 0; // Track logical length separately from allocated capacity.
        for (int value : a) { // Preserve order by reading from left to right.
            if (value != removed) buffer[size++] = value; // Append only values that survive the filter.
        }
        return Arrays.copyOf(buffer, size); // Trim unused capacity so length reflects the actual answer.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[]{2,1,2,3},2))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[]{2,1,2,3},2), new int[]{1,3}); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[]{4,4},4), new int[]{}); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[]{},7), new int[]{}); // Case 3: verify the expected result for this input.
    }
}
