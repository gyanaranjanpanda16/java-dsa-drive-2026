package questions.q060;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q060: Place even values before odd values stably.
 * Return even values followed by odd values while preserving order inside each category. Two stable scans are simpler than in-place swaps when stability is required.
 * Complexity: O(n) time and O(n) output space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q060StableParityPartition { // Keep this question independently runnable and easy to find.
    private Q060StableParityPartition() { } // This exercise exposes behavior through static methods.
    public static int[] solve(int[] a) { // Define the input and result types of this particular problem.
        int[] result = new int[a.length]; // Preserve the input and reserve exactly enough output positions.
        int write = 0; // Track the next free output slot.
        for (int value : a) if (value % 2 == 0) result[write++] = value; // First emit even values in their original order.
        for (int value : a) if (value % 2 != 0) result[write++] = value; // Then emit odd values in their original order, including negative odds.
        return result; // The two disjoint parity categories cover every element exactly once.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[]{3,2,1,4}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[]{3,2,1,4}), new int[]{2,4,3,1}); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[]{-3,-2,0}), new int[]{-2,0,-3}); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[]{}), new int[]{}); // Case 3: verify the expected result for this input.
    }
}
