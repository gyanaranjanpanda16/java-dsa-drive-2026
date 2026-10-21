package questions.q051;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q051: Find a maximum-product contiguous subarray.
 * Return the maximum product of a nonempty contiguous subarray. Track both minimum and maximum ending products because multiplying by a negative reverses their ordering; overflow is reported.
 * Complexity: O(n) time and O(1) working space; all tracked products must fit long.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q051MaximumProduct { // Keep this question independently runnable and easy to find.
    private Q051MaximumProduct() { } // This exercise exposes behavior through static methods.
    public static long solve(int[] a) { // Define the input and result types of this particular problem.
        if (a.length == 0) throw new IllegalArgumentException("nonempty input required"); // The chosen segment must contain a value.
        long high = a[0], low = a[0], best = a[0]; // Negative products can become maxima after another negative multiplier.
        for (int i = 1; i < a.length; i++) { // Consider the next possible right endpoint.
            long x = Math.multiplyExact(high, a[i]), y = Math.multiplyExact(low, a[i]); // Preserve both prior extrema and reject long overflow.
            high = Math.max(a[i], Math.max(x,y)); // Restart or extend whichever previous sign gives the largest product.
            low = Math.min(a[i], Math.min(x,y)); // Retain the most negative product for future sign changes.
            best = Math.max(best, high); // Record the best product over every processed endpoint.
        }
        return best; // The two extrema cover every possible sign transition.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[]{2,3,-2,4}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[]{2,3,-2,4}), 6L); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[]{-2,0,-1}), 0L); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[]{-2,3,-4}), 24L); // Case 3: verify the expected result for this input.
    }
}
