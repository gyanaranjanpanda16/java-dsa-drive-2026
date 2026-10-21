package questions.q003;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q003: Print an array backwards without changing it.
 * Produce the array elements in reverse order without modifying the input. Reversing the traversal order is sufficient; allocating a reversed numeric array is unnecessary.
 * Complexity: O(n) time and O(output length) space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q003ReversePrint { // Keep this question independently runnable and easy to find.
    private Q003ReversePrint() { } // This exercise exposes behavior through static methods.
    public static String solve(int[] a) { // Define the input and result types of this particular problem.
        StringJoiner result = new StringJoiner(" "); // Collect formatted values in the requested output order.
        for (int i = a.length - 1; i >= 0; i--) { // Start at the final valid index and move toward the beginning.
            result.add(Integer.toString(a[i])); // Reading rather than assigning preserves the caller's array.
        }
        return result.toString(); // Empty input skips the loop and yields empty text.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[]{1,2,3}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[]{1,2,3}), "3 2 1"); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[]{}), ""); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[]{7}), "7"); // Case 3: verify the expected result for this input.
    }
}
