package questions.q025;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q025: Copy an array without aliasing.
 * Return an independent copy of an int array. Assigning another variable to the same array would only create an alias; allocating new storage separates later mutations.
 * Complexity: O(n) time and O(n) output space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q025ArrayCopy { // Keep this question independently runnable and easy to find.
    private Q025ArrayCopy() { } // This exercise exposes behavior through static methods.
    public static int[] solve(int[] a) { // Define the input and result types of this particular problem.
        int[] copy = new int[a.length]; // Allocate a different array object of matching size.
        for (int i = 0; i < a.length; i++) { // Copy every position exactly once.
            copy[i] = a[i]; // Primitive values are copied, not shared mutable element objects.
        }
        return copy; // Mutating this result will not change the original primitive array.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[]{1,2}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[]{1,2}), new int[]{1,2}); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[]{}), new int[]{}); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[]{-1}), new int[]{-1}); // Case 3: verify the expected result for this input.
    }
}
