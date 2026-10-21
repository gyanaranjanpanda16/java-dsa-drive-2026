package questions.q011;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q011: Compute an array average.
 * Return the arithmetic mean of a nonempty integer array. Integer division discards fractions, so promote the accumulated total before dividing.
 * Complexity: O(n) time and O(1) auxiliary space; result uses double precision.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q011Average { // Keep this question independently runnable and easy to find.
    private Q011Average() { } // This exercise exposes behavior through static methods.
    public static double solve(int[] a) { // Define the input and result types of this particular problem.
        if (a.length == 0) throw new IllegalArgumentException("average needs data"); // Division by zero would not define a useful average.
        long total = 0; // Avoid int overflow while accumulating int inputs.
        for (int value : a) total += value; // Every observation contributes once to the total.
        return (double) total / a.length; // Convert before division so a fractional part is retained.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[]{1,2}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[]{1,2}), 1.5); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[]{-2,2}), 0.0); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[]{7}), 7.0); // Case 3: verify the expected result for this input.
    }
}
