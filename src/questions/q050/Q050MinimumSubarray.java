package questions.q050;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q050: Find a minimum nonempty contiguous sum.
 * Return the minimum contiguous sum, useful for identifying the worst consecutive loss period. Reversing the optimization direction changes the maximum-subarray recurrence into a minimum one.
 * Complexity: O(n) time and O(1) auxiliary space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q050MinimumSubarray { // Keep this question independently runnable and easy to find.
    private Q050MinimumSubarray() { } // This exercise exposes behavior through static methods.
    public static long solve(int[] a) { // Define the input and result types of this particular problem.
        if (a.length == 0) throw new IllegalArgumentException("nonempty input required"); // Require at least one eligible segment.
        long ending = a[0], best = a[0]; // The first element is the initial nonempty candidate.
        for (int i = 1; i < a.length; i++) { // Advance the right endpoint one position.
            ending = Math.min((long) a[i], ending + a[i]); // Keep the cheaper of restarting and extending.
            best = Math.min(best, ending); // Retain the smallest segment sum across endpoints.
        }
        return best; // The recurrence covers every nonempty contiguous segment.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[]{3,-4,2,-3,5}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[]{3,-4,2,-3,5}), -5L); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[]{2,4}), 2L); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[]{-1}), -1L); // Case 3: verify the expected result for this input.
    }
}
