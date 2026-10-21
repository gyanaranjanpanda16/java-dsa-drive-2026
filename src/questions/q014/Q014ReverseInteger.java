package questions.q014;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q014: Reverse decimal digits into a long.
 * Reverse an int's decimal digits and return a long so a reversed 10-digit input can fit. Leading zeros of the reversed value disappear because the result is numeric.
 * Complexity: O(number of digits) time and O(1) auxiliary space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q014ReverseInteger { // Keep this question independently runnable and easy to find.
    private Q014ReverseInteger() { } // This exercise exposes behavior through static methods.
    public static long solve(int number) { // Define the input and result types of this particular problem.
        long value = Math.abs((long) number); // Work with a nonnegative magnitude safely.
        long reversed = 0; // Build the result from left to right.
        while (value > 0) { // Process every original digit once.
            reversed = reversed * 10 + value % 10; // Shift existing digits and append the extracted digit.
            value /= 10; // Remove the digit just appended.
        }
        return number < 0 ? -reversed : reversed; // Restore the original sign after reversing the magnitude.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(120))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(120), 21L); // Case 1: verify the expected result for this input.
        Check.equal(solve(-321), -123L); // Case 2: verify the expected result for this input.
        Check.equal(solve(0), 0L); // Case 3: verify the expected result for this input.
    }
}
