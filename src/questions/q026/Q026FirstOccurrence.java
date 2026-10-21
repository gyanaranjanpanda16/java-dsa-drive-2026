package questions.q026;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q026: Find the first occurrence of a value.
 * Return the first index containing target, or -1 when absent. Since the array need not be sorted, a scan is necessary in the worst case.
 * Complexity: O(n) time and O(1) space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q026FirstOccurrence { // Keep this question independently runnable and easy to find.
    private Q026FirstOccurrence() { } // This exercise exposes behavior through static methods.
    public static int solve(int[] a, int target) { // Define the input and result types of this particular problem.
        for (int i = 0; i < a.length; i++) { // Scan left to right so the first match has the smallest index.
            if (a[i] == target) return i; // Once found, no later index can improve the answer.
        }
        return -1; // A negative index unambiguously represents absence.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[]{4,2,4},4))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[]{4,2,4},4), 0); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[]{},3), -1); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[]{1,2},2), 1); // Case 3: verify the expected result for this input.
    }
}
