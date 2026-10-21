package questions.q021;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q021: Compute a nonnegative integer power.
 * Compute base raised to a nonnegative exponent, reporting long overflow. This is the elementary repeated-multiplication method; later binary exponentiation reduces the number of multiplications.
 * Complexity: O(exponent) time and O(1) auxiliary space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q021Power { // Keep this question independently runnable and easy to find.
    private Q021Power() { } // This exercise exposes behavior through static methods.
    public static long solve(int base, int exponent) { // Define the input and result types of this particular problem.
        if (exponent < 0) throw new IllegalArgumentException("nonnegative exponent required"); // Integer results cannot represent arbitrary negative powers.
        long result = 1; // The exponent-zero convention is one, including 0^0 in this exercise.
        for (int i = 0; i < exponent; i++) { // Introduce one factor of base per iteration.
            result = Math.multiplyExact(result, base); // Report overflow instead of returning a silently wrapped answer.
        }
        return result; // Exactly exponent factors have been multiplied.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(2,10))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(2,10), 1024L); // Case 1: verify the expected result for this input.
        Check.equal(solve(7,0), 1L); // Case 2: verify the expected result for this input.
        Check.equal(solve(-2,3), -8L); // Case 3: verify the expected result for this input.
    }
}
