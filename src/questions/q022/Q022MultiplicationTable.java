package questions.q022;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q022: Build a multiplication table.
 * Return number×1 through number×rows. Separating zero-based array positions from one-based multipliers prevents an off-by-one error.
 * Complexity: O(rows) time and output space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q022MultiplicationTable { // Keep this question independently runnable and easy to find.
    private Q022MultiplicationTable() { } // This exercise exposes behavior through static methods.
    public static long[] solve(int number, int rows) { // Define the input and result types of this particular problem.
        if (rows < 0) throw new IllegalArgumentException("rows cannot be negative"); // An output length must be valid.
        long[] table = new long[rows]; // Allocate exactly one result per multiplier.
        for (int i = 0; i < rows; i++) { // Index i represents multiplier i+1.
            table[i] = (long) number * (i + 1L); // Widen before multiplying to avoid int overflow.
        }
        return table; // The array preserves increasing multiplier order.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(3,4))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(3,4), new long[]{3,6,9,12}); // Case 1: verify the expected result for this input.
        Check.equal(solve(2,0), new long[]{}); // Case 2: verify the expected result for this input.
        Check.equal(solve(-2,2), new long[]{-2,-4}); // Case 3: verify the expected result for this input.
    }
}
