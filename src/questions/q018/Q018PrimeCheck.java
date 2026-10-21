package questions.q018;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q018: Check whether an integer is prime.
 * Decide whether n is prime. Trial division only needs divisors through the square root because a larger factor would have a smaller paired factor.
 * Complexity: O(sqrt(n)) time for n≥2 and O(1) auxiliary space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q018PrimeCheck { // Keep this question independently runnable and easy to find.
    private Q018PrimeCheck() { } // This exercise exposes behavior through static methods.
    public static boolean solve(int n) { // Define the input and result types of this particular problem.
        if (n < 2) return false; // Primes are integers at least two.
        for (int divisor = 2; divisor <= n / divisor; divisor++) { // A factor pair has at least one member at most sqrt(n); division avoids squaring overflow.
            if (n % divisor == 0) return false; // A proper divisor proves the number is composite.
        }
        return true; // No possible small member of a factor pair divided n.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(29))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(29), true); // Case 1: verify the expected result for this input.
        Check.equal(solve(1), false); // Case 2: verify the expected result for this input.
        Check.equal(solve(49), false); // Case 3: verify the expected result for this input.
    }
}
