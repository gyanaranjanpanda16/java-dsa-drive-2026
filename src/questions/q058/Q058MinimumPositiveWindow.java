package questions.q058;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q058: Find a shortest positive-sum threshold window.
 * For strictly positive integers and positive target, return the shortest contiguous length with sum at least target, or zero if none exists. Monotonic sum changes let each pointer advance at most n times.
 * Complexity: O(n) time and O(1) space; the positivity assumption is essential.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q058MinimumPositiveWindow { // Keep this question independently runnable and easy to find.
    private Q058MinimumPositiveWindow() { } // This exercise exposes behavior through static methods.
    public static int solve(int[] a, long target) { // Define the input and result types of this particular problem.
        if (target <= 0) throw new IllegalArgumentException("target must be positive"); // This exercise searches for a nonempty positive-threshold window.
        int left = 0, best = Integer.MAX_VALUE; // A sentinel represents that no valid window has been found.
        long sum = 0; // Maintain the sum between left and the current right endpoint.
        for (int right = 0; right < a.length; right++) { // Expanding a positive-valued window cannot lower its sum.
            if (a[right] <= 0) throw new IllegalArgumentException("values must be positive"); // Negative values would invalidate the monotonic shrinking argument.
            sum += a[right]; // Include the newly arrived value.
            while (sum >= target) { // Shrink as far as possible while the threshold is satisfied.
                best = Math.min(best,right-left+1); // Every valid shorter suffix is evaluated before removing its left value.
                sum -= a[left++]; // Positivity ensures shrinking decreases the sum.
            }
        }
        return best == Integer.MAX_VALUE ? 0 : best; // Zero explicitly means no qualifying nonempty window.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[]{2,3,1,2,4,3},7L))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[]{2,3,1,2,4,3},7L), 2); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[]{1,1},5L), 0); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[]{9},7L), 1); // Case 3: verify the expected result for this input.
    }
}
