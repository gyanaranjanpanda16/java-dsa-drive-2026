package questions.q020;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q020: Compute the least common multiple.
 * Return the nonnegative LCM of two ints, with zero if either is zero. The identity gcd(a,b)×lcm(a,b)=|ab| avoids enumerating multiples.
 * Complexity: O(log(max(|a|,|b|)+1)) time and O(1) space; products of int magnitudes fit long.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q020LeastCommonMultiple { // Keep this question independently runnable and easy to find.
    private Q020LeastCommonMultiple() { } // This exercise exposes behavior through static methods.
    public static long solve(int a, int b) { // Define the input and result types of this particular problem.
        long x = Math.abs((long) a), y = Math.abs((long) b); // Magnitudes make the least common multiple nonnegative.
        if (x == 0 || y == 0) return 0; // Zero is handled before division by the gcd.
        long p = x, q = y; // Preserve original magnitudes while computing their gcd.
        while (q != 0) { // Euclid reduces the second operand at every step.
            long r = p % q; // Compute the smaller equivalent gcd problem.
            p = q; // Shift the divisor forward.
            q = r; // The remainder becomes the next divisor.
        }
        return (x / p) * y; // Divide before multiplying to reduce intermediate growth.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(12,18))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(12,18), 36L); // Case 1: verify the expected result for this input.
        Check.equal(solve(0,9), 0L); // Case 2: verify the expected result for this input.
        Check.equal(solve(-3,5), 15L); // Case 3: verify the expected result for this input.
    }
}
