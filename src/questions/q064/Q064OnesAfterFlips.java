package questions.q064;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q064: Find the longest binary window after at most k zero flips.
 * Return the longest subarray that can become all ones by changing at most k zeros. Counting zeros turns the requirement into a sliding-window budget.
 * Complexity: O(n) time and O(1) space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q064OnesAfterFlips { // Keep this question independently runnable and easy to find.
    private Q064OnesAfterFlips() { } // This exercise exposes behavior through static methods.
    public static int solve(int[] a, int k) { // Define the input and result types of this particular problem.
        if (k < 0) throw new IllegalArgumentException("nonnegative budget required"); // A negative number of allowed flips has no meaning.
        int left = 0, zeros = 0, best = 0; // Maintain a window with at most k zeros after shrinking.
        for (int right = 0; right < a.length; right++) { // Expand the candidate window one element at a time.
            if (a[right] != 0 && a[right] != 1) throw new IllegalArgumentException("binary input required"); // Each zero consumes exactly one flip.
            if (a[right] == 0) zeros++; // Account for the new value's cost.
            while (zeros > k) { // Remove old elements until the flip budget is satisfied again.
                if (a[left++] == 0) zeros--; // Expiring a zero refunds one required flip.
            }
            best = Math.max(best,right-left+1); // The surviving window is valid and longest for this right endpoint.
        }
        return best; // Both pointers move forward at most n times.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[]{1,0,1,0,1},1))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[]{1,0,1,0,1},1), 3); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[]{0,0},2), 2); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[]{},0), 0); // Case 3: verify the expected result for this input.
    }
}
