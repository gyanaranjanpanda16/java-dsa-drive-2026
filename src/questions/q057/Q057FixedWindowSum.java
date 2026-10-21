package questions.q057;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q057: Find the largest sum of exactly k consecutive values.
 * Return the maximum total among all windows containing exactly k elements. Adjacent windows share k-1 elements, so update their sum using only the departing and arriving values.
 * Complexity: O(n) time and O(1) auxiliary space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q057FixedWindowSum { // Keep this question independently runnable and easy to find.
    private Q057FixedWindowSum() { } // This exercise exposes behavior through static methods.
    public static long solve(int[] a, int k) { // Define the input and result types of this particular problem.
        if (k < 1 || k > a.length) throw new IllegalArgumentException("invalid window size"); // Require at least one complete nonempty window.
        long window = 0; // Track only the total of the current window.
        for (int i = 0; i < k; i++) window += a[i]; // Initialize the first complete window.
        long best = window; // A real window handles all-negative arrays correctly.
        for (int i = k; i < a.length; i++) { // Slide the right edge across the remaining elements.
            window += (long) a[i] - a[i-k]; // Add the arriving value and remove the expired value.
            best = Math.max(best,window); // Compare each complete window exactly once.
        }
        return best; // Reusing the overlap avoids rescanning k elements per window.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[]{2,1,5,1,3,2},3))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[]{2,1,5,1,3,2},3), 9L); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[]{-3,-2},1), -2L); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[]{4,1},2), 5L); // Case 3: verify the expected result for this input.
    }
}
