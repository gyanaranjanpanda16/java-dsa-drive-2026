package questions.q031;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q031: Reverse an array by swapping endpoints.
 * Return a reversed copy using endpoint swaps. Outside the two pointers, values are already in their final positions; each swap extends that correct region.
 * Complexity: O(n) time, O(n) returned copy, O(1) additional working space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q031ReverseArray { // Keep this question independently runnable and easy to find.
    private Q031ReverseArray() { } // This exercise exposes behavior through static methods.
    public static int[] solve(int[] input) { // Define the input and result types of this particular problem.
        int[] a = input.clone(); // Return a reversed copy while preserving the input contract.
        for (int left = 0, right = a.length - 1; left < right; left++, right--) { // The unswapped middle shrinks from both ends.
            int saved = a[left]; // Save one endpoint before overwriting it.
            a[left] = a[right]; // Put the right endpoint into its final reversed position.
            a[right] = saved; // Complete the swap without losing either value.
        }
        return a; // Every endpoint pair has reached its reversed position.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[]{1,2,3}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[]{1,2,3}), new int[]{3,2,1}); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[]{}), new int[]{}); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[]{7}), new int[]{7}); // Case 3: verify the expected result for this input.
    }
}
