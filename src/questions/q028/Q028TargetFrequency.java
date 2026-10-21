package questions.q028;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q028: Count occurrences of one target.
 * Count target occurrences. A set would lose multiplicity, so scan the elements and count matching positions.
 * Complexity: O(n) time and O(1) space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q028TargetFrequency { // Keep this question independently runnable and easy to find.
    private Q028TargetFrequency() { } // This exercise exposes behavior through static methods.
    public static int solve(int[] a, int target) { // Define the input and result types of this particular problem.
        int matches = 0; // No input positions have been counted yet.
        for (int value : a) { // Repeated equal values are separate occurrences.
            if (value == target) matches++; // Increment only when this position contains the requested value.
        }
        return matches; // Each matching position contributed exactly one.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[]{2,1,2,2},2))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[]{2,1,2,2},2), 3); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[]{},0), 0); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[]{1},2), 0); // Case 3: verify the expected result for this input.
    }
}
