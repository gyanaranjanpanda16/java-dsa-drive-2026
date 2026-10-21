package questions.q008;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q008: Count positive negative and zero values.
 * Return [positive count, negative count, zero count]. The mutually exclusive branches ensure no element is missed or double-counted.
 * Complexity: O(n) time and O(1) result and auxiliary space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q008SignCounts { // Keep this question independently runnable and easy to find.
    private Q008SignCounts() { } // This exercise exposes behavior through static methods.
    public static int[] solve(int[] a) { // Define the input and result types of this particular problem.
        int[] counts = new int[3]; // Slots represent positive, negative, and zero counts respectively.
        for (int value : a) { // Classify each value exactly once.
            if (value > 0) counts[0]++; // Positive values belong in the first category.
            else if (value < 0) counts[1]++; // Negative values belong in the second category.
            else counts[2]++; // The remaining possibility is exactly zero.
        }
        return counts; // The three categories partition the input, so their sum equals its length.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[]{-1,0,2,3}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[]{-1,0,2,3}), new int[]{2,1,1}); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[]{}), new int[]{0,0,0}); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[]{0,0}), new int[]{0,0,2}); // Case 3: verify the expected result for this input.
    }
}
