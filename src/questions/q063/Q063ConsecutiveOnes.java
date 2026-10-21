package questions.q063;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q063: Find the longest run of ones.
 * For a binary array, return the length of its longest contiguous run of ones. Only the current run length is needed because a zero makes every earlier suffix ineligible for extension.
 * Complexity: O(n) time and O(1) space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q063ConsecutiveOnes { // Keep this question independently runnable and easy to find.
    private Q063ConsecutiveOnes() { } // This exercise exposes behavior through static methods.
    public static int solve(int[] a) { // Define the input and result types of this particular problem.
        int run = 0, best = 0; // Track the current suffix of ones and the longest suffix seen anywhere.
        for (int value : a) { // Every element either extends or breaks the current run.
            if (value != 0 && value != 1) throw new IllegalArgumentException("binary input required"); // The contract is a binary sequence.
            run = value == 1 ? run + 1 : 0; // A zero separates runs, so it resets the suffix length.
            best = Math.max(best,run); // Every possible run endpoint is compared.
        }
        return best; // Empty input has no run and returns zero.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[]{1,1,0,1,1,1}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[]{1,1,0,1,1,1}), 3); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[]{0,0}), 0); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[]{}), 0); // Case 3: verify the expected result for this input.
    }
}
