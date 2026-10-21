package questions.q033;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q033: Rotate an array left by one.
 * Rotate a copy left by one position. Saving the first element before shifting prevents it from being overwritten.
 * Complexity: O(n) time and O(n) output space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q033RotateOne { // Keep this question independently runnable and easy to find.
    private Q033RotateOne() { } // This exercise exposes behavior through static methods.
    public static int[] solve(int[] input) { // Define the input and result types of this particular problem.
        int[] a = input.clone(); // Preserve the caller's data while modifying the output copy.
        if (a.length == 0) return a; // There is no first element to save on empty input.
        int first = a[0]; // Save the element that wraps to the final position.
        for (int i = 1; i < a.length; i++) a[i - 1] = a[i]; // Shift later values into the preceding position.
        a[a.length - 1] = first; // Complete the wraparound after all shifts.
        return a; // Rotation preserves the multiset and relative cyclic order.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[]{1,2,3}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[]{1,2,3}), new int[]{2,3,1}); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[]{}), new int[]{}); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[]{4}), new int[]{4}); // Case 3: verify the expected result for this input.
    }
}
