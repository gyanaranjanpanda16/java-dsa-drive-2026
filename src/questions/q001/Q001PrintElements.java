package questions.q001;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q001: Print every array element in order.
 * Return the elements of an integer array as one space-separated line. Iteration is necessary because every element must appear; a StringJoiner preserves order and handles separators without special first/last cases.
 * Complexity: O(n) element visits and O(output length) result space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q001PrintElements { // Keep this question independently runnable and easy to find.
    private Q001PrintElements() { } // This exercise exposes behavior through static methods.
    public static String solve(int[] values) { // Define the input and result types of this particular problem.
        StringJoiner output = new StringJoiner(" "); // Insert a separator only between elements, avoiding a trailing space.
        for (int value : values) { // Visit each stored value in its existing order.
            output.add(Integer.toString(value)); // Convert the number to text before appending it.
        }
        return output.toString(); // An empty array naturally produces an empty line.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[]{10,20,30}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[]{10,20,30}), "10 20 30"); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[]{}), ""); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[]{-2}), "-2"); // Case 3: verify the expected result for this input.
    }
}
