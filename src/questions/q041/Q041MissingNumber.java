package questions.q041;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q041: Find the missing value in zero through n.
 * Input contains distinct values from 0..n with exactly one absent and length n. XOR all expected values with all observed values; paired values cancel, leaving the missing one.
 * Complexity: O(n) time and O(1) space, under the stated uniqueness/range contract.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q041MissingNumber { // Keep this question independently runnable and easy to find.
    private Q041MissingNumber() { } // This exercise exposes behavior through static methods.
    public static int solve(int[] a) { // Define the input and result types of this particular problem.
        int missing = a.length; // Include the upper endpoint n of the expected range.
        for (int i = 0; i < a.length; i++) { // Every other expected value appears as an index.
            missing ^= i ^ a[i]; // Equal expected and observed values cancel under XOR.
        }
        return missing; // The sole unpaired value is the absent number.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[]{3,0,1}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[]{3,0,1}), 2); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[]{}), 0); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[]{0}), 1); // Case 3: verify the expected result for this input.
    }
}
