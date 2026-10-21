package questions.q013;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q013: Sum decimal digits.
 * Return the sum of decimal digits, ignoring the sign. Modulo extracts the last digit and division removes it, partitioning the number into its digits.
 * Complexity: O(number of digits) time and O(1) auxiliary space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q013DigitSum { // Keep this question independently runnable and easy to find.
    private Q013DigitSum() { } // This exercise exposes behavior through static methods.
    public static int solve(int number) { // Define the input and result types of this particular problem.
        long value = Math.abs((long) number); // Preserve the magnitude of every int, including its minimum value.
        int sum = 0; // No digits have contributed yet.
        while (value > 0) { // Stop once all digits have been removed.
            sum += (int) (value % 10); // Remainder extracts the final decimal digit.
            value /= 10; // Remove that digit so it cannot be counted twice.
        }
        return sum; // Zero has digit sum zero without a special branch.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(1234))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(1234), 10); // Case 1: verify the expected result for this input.
        Check.equal(solve(0), 0); // Case 2: verify the expected result for this input.
        Check.equal(solve(-305), 8); // Case 3: verify the expected result for this input.
    }
}
