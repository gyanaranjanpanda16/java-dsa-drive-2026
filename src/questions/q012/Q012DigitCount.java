package questions.q012;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q012: Count decimal digits of an integer.
 * Count the decimal digits of any int, excluding its sign. Repeated division by ten removes one digit per iteration; a do-while loop handles zero.
 * Complexity: O(number of digits) time and O(1) auxiliary space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q012DigitCount { // Keep this question independently runnable and easy to find.
    private Q012DigitCount() { } // This exercise exposes behavior through static methods.
    public static int solve(int number) { // Define the input and result types of this particular problem.
        long value = Math.abs((long) number); // Widen first so Integer.MIN_VALUE has a representable positive magnitude.
        int digits = 0; // Count divisions rather than allocating a decimal string.
        do { // Execute once even for zero, which has one decimal digit.
            digits++; // Account for the current least-significant digit.
            value /= 10; // Remove one decimal digit, guaranteeing progress toward zero.
        } while (value != 0);
        return digits; // The sign is not counted as a digit.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(12345))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(12345), 5); // Case 1: verify the expected result for this input.
        Check.equal(solve(0), 1); // Case 2: verify the expected result for this input.
        Check.equal(solve(Integer.MIN_VALUE), 10); // Case 3: verify the expected result for this input.
    }
}
