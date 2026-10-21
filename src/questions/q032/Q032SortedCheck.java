package questions.q032;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q032: Check nondecreasing array order.
 * Return whether the array is nondecreasing, allowing equal neighboring values. Empty and singleton arrays satisfy the condition vacuously.
 * Complexity: O(n) time and O(1) space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q032SortedCheck { // Keep this question independently runnable and easy to find.
    private Q032SortedCheck() { } // This exercise exposes behavior through static methods.
    public static boolean solve(int[] a) { // Define the input and result types of this particular problem.
        for (int i = 1; i < a.length; i++) { // Every adjacent pair is checked once.
            if (a[i] < a[i - 1]) return false; // One descending adjacent pair disproves nondecreasing order.
        }
        return true; // Transitivity makes pairwise nondecreasing order sufficient globally.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[]{1,1,3}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[]{1,1,3}), true); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[]{3,1}), false); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[]{}), true); // Case 3: verify the expected result for this input.
    }
}
