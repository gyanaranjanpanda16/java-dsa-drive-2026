package questions.q035;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q035: Move zeros to the end stably.
 * Return a copy with zeros at the end and nonzero order preserved. A write pointer identifies the next retained position; filtering into that prefix then filling its suffix is sufficient.
 * Complexity: O(n) time and O(n) returned copy; O(1) extra working space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q035MoveZeros { // Keep this question independently runnable and easy to find.
    private Q035MoveZeros() { } // This exercise exposes behavior through static methods.
    public static int[] solve(int[] input) { // Define the input and result types of this particular problem.
        int[] a = input.clone(); // Keep mutation limited to the returned copy.
        int write = 0; // The prefix before write contains exactly the nonzero values already seen.
        for (int value : input) { // Read the original values in order.
            if (value != 0) a[write++] = value; // Compact retained elements without changing their relative order.
        }
        Arrays.fill(a, write, a.length, 0); // Every remaining output slot must be zero.
        return a; // The output consists of the stable nonzero prefix followed by zeros.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[]{0,1,0,3}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[]{0,1,0,3}), new int[]{1,3,0,0}); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[]{0,0}), new int[]{0,0}); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[]{2,1}), new int[]{2,1}); // Case 3: verify the expected result for this input.
    }
}
