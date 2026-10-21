package questions.q002;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q002: Print array indices beside values.
 * Return index:value pairs for every array element. An indexed loop is needed because this output requires both the position and the stored value.
 * Complexity: O(n) time and O(output length) space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q002PrintIndices { // Keep this question independently runnable and easy to find.
    private Q002PrintIndices() { } // This exercise exposes behavior through static methods.
    public static String solve(int[] a) { // Define the input and result types of this particular problem.
        StringJoiner result = new StringJoiner(", "); // Separate index:value entries without trailing punctuation.
        for (int i = 0; i < a.length; i++) { // Stop before length because the last valid index is length minus one.
            result.add(i + ":" + a[i]); // Use the index to access and label exactly one array slot.
        }
        return result.toString(); // Preserve the association between each position and value.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[]{4,8}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[]{4,8}), "0:4, 1:8"); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[]{}), ""); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[]{0}), "0:0"); // Case 3: verify the expected result for this input.
    }
}
