package questions.q027;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q027: Find the last occurrence of a value.
 * Return the last matching index, or -1. Reversing the search direction avoids storing every previous match.
 * Complexity: O(n) time and O(1) space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q027LastOccurrence { // Keep this question independently runnable and easy to find.
    private Q027LastOccurrence() { } // This exercise exposes behavior through static methods.
    public static int solve(int[] a, int target) { // Define the input and result types of this particular problem.
        for (int i = a.length - 1; i >= 0; i--) { // Search from the end to encounter the largest matching index first.
            if (a[i] == target) return i; // Stop because all unvisited indices are smaller.
        }
        return -1; // The scan exhausted every possible position.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[]{4,2,4},4))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[]{4,2,4},4), 2); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[]{},3), -1); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[]{1,2},9), -1); // Case 3: verify the expected result for this input.
    }
}
