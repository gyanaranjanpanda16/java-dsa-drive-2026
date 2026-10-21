package questions.q049;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q049: Find a maximum nonempty contiguous sum.
 * Return the maximum sum of a nonempty contiguous subarray. The recurrence considers the only two ways to form a segment ending at the current index.
 * Complexity: O(n) time and O(1) auxiliary space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q049MaximumSubarray { // Keep this question independently runnable and easy to find.
    private Q049MaximumSubarray() { } // This exercise exposes behavior through static methods.
    public static long solve(int[] a) { // Define the input and result types of this particular problem.
        if (a.length == 0) throw new IllegalArgumentException("nonempty input required"); // A nonempty subarray cannot exist in an empty array.
        long ending = a[0], best = a[0]; // Start with an actual element so all-negative inputs work.
        for (int i = 1; i < a.length; i++) { // Consider every possible right endpoint.
            ending = Math.max((long) a[i], ending + a[i]); // A best subarray ending here either starts here or extends the previous one.
            best = Math.max(best, ending); // Compare the best answer over all processed right endpoints.
        }
        return best; // All possible endpoints have been considered.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[]{-2,3,-1,4,-5}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[]{-2,3,-1,4,-5}), 6L); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[]{-4,-2,-7}), -2L); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[]{5}), 5L); // Case 3: verify the expected result for this input.
    }
}
