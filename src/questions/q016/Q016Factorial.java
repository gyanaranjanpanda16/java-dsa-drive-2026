package questions.q016;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q016: Calculate a bounded factorial.
 * Compute n! for 0≤n≤20. Multiplication accumulates the product; the explicit bound prevents silent numeric overflow.
 * Complexity: O(n) time and O(1) auxiliary space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q016Factorial { // Keep this question independently runnable and easy to find.
    private Q016Factorial() { } // This exercise exposes behavior through static methods.
    public static long solve(int n) { // Define the input and result types of this particular problem.
        if (n < 0 || n > 20) throw new IllegalArgumentException("require 0..20"); // 21! exceeds the long range.
        long result = 1; // The empty product defines 0! as one.
        for (int value = 2; value <= n; value++) { // Multiply every required factor exactly once.
            result *= value; // Maintain result = value! after this step.
        }
        return result; // The input bound guarantees an exact long result.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(5))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(5), 120L); // Case 1: verify the expected result for this input.
        Check.equal(solve(0), 1L); // Case 2: verify the expected result for this input.
        Check.equal(solve(1), 1L); // Case 3: verify the expected result for this input.
    }
}
