package questions.q381;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q381: Fast Recursive Power.
 * Contract: Compute real base raised to an int exponent; require nonzero base for negative exponents.
 * Why it works: Squaring the power for floor(e/2) reconstructs all paired factors. An odd exponent contributes one remaining factor.
 * Cost: O(log |exponent|) time and stack; floating-point overflow and rounding follow Java double semantics.
 */
public final class Q381FastRecursivePower { // Keep this question independently runnable and testable.
    private Q381FastRecursivePower() { } // No object state is needed by this standalone exercise.
    public static double power(double base, int exponent) { // Expose the operation for an independent caller and the executable checks below.
        long e = exponent; // Widen before negating so Integer.MIN_VALUE is representable.
        if (e < 0) return 1.0 / positive(base, -e); // Negative exponents invert the corresponding positive power.
        return positive(base, e); // Delegate only a nonnegative exponent to the recurrence.
    }
    private static double positive(double base, long e) { // Keep recursive state or a repeated sub-operation inside this solution.
        if (e == 0) return 1; // Every nonzero base to power zero equals one.
        double half = positive(base, e / 2); // Reuse the half-power instead of calculating it twice.
        return half * half * (e % 2 == 0 ? 1 : base); // An odd exponent has one unmatched base factor.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(power(2,10)); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(power(2,0)==1, "Q381 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(power(2,-3)==0.125, "Q381 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(power(-2,3)==-8, "Q381 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
