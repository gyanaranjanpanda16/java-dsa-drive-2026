package questions.q019;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q019: Compute the greatest common divisor.
 * Return the nonnegative GCD of two ints, defining gcd(0,0)=0. Euclid works because a divisor of x and y also divides x mod y, and conversely.
 * Complexity: O(log(max(|a|,|b|)+1)) time and O(1) auxiliary space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q019GreatestCommonDivisor { // Keep this question independently runnable and easy to find.
    private Q019GreatestCommonDivisor() { } // This exercise exposes behavior through static methods.
    public static long solve(int a, int b) { // Define the input and result types of this particular problem.
        long x = Math.abs((long) a), y = Math.abs((long) b); // Use nonnegative long magnitudes to cover all int inputs.
        while (y != 0) { // Remainders strictly decrease until the second operand becomes zero.
            long remainder = x % y; // Euclid's identity preserves the set of common divisors.
            x = y; // Replace the larger problem with the previous divisor.
            y = remainder; // Continue with the strictly smaller remainder.
        }
        return x; // gcd(x,0)=x; this convention gives gcd(0,0)=0.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(48,18))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(48,18), 6L); // Case 1: verify the expected result for this input.
        Check.equal(solve(0,0), 0L); // Case 2: verify the expected result for this input.
        Check.equal(solve(-12,8), 4L); // Case 3: verify the expected result for this input.
    }
}
