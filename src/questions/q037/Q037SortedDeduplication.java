package questions.q037;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q037: Remove duplicates from sorted input.
 * Given nondecreasing input, return each distinct value once in sorted order. Adjacency of duplicates lets one previous output value replace a hash set.
 * Complexity: O(n) time and O(n) output buffer space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q037SortedDeduplication { // Keep this question independently runnable and easy to find.
    private Q037SortedDeduplication() { } // This exercise exposes behavior through static methods.
    public static int[] solve(int[] a) { // Define the input and result types of this particular problem.
        if (a.length == 0) return new int[0]; // An empty sequence has no first distinct value.
        int[] result = new int[a.length]; // Upper-bound output capacity by input length.
        int size = 0; // Count how many distinct values have been emitted.
        for (int value : a) { // Equal values are adjacent because the input is nondecreasing.
            if (size == 0 || result[size - 1] != value) result[size++] = value; // Emit only the first value of each equal run.
        }
        return Arrays.copyOf(result, size); // Expose only the populated distinct prefix.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[]{1,1,2,3,3}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[]{1,1,2,3,3}), new int[]{1,2,3}); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[]{}), new int[]{}); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[]{5,5}), new int[]{5}); // Case 3: verify the expected result for this input.
    }
}
