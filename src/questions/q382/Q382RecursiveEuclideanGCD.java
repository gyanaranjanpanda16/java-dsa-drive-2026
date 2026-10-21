package questions.q382;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q382: Recursive Euclidean GCD.
 * Contract: Nonnegative long inputs; define gcd(0,0)=0.
 * Why it works: If a = qb+r, a number divides both a and b exactly when it divides both b and r. Replacing the pair preserves the answer while shrinking the second value.
 * Cost: O(log min(a,b)) remainder steps for positive inputs, with the same recursion depth.
 */
public final class Q382RecursiveEuclideanGCD { // Keep this question independently runnable and testable.
    private Q382RecursiveEuclideanGCD() { } // No object state is needed by this standalone exercise.
    public static long gcd(long a, long b) { // Expose the operation for an independent caller and the executable checks below.
        if (a < 0 || b < 0) throw new IllegalArgumentException("Nonnegative values required"); // Make remainders and the zero convention explicit.
        if (b == 0) return a; // Every integer divides zero, leaving a as the greatest common divisor.
        return gcd(b, a % b); // Common divisors are unchanged when a is replaced by its remainder.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(gcd(48,18)); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(gcd(0,0)==0, "Q382 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(gcd(0,7)==7, "Q382 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(gcd(35,64)==1, "Q382 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
