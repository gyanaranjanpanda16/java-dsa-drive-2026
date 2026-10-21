package questions.q376;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q376: Recursive Factorial.
 * Contract: Return n! for 0 <= n <= 20; larger values do not fit long.
 * Why it works: The product 1 through n is n times the product 1 through n-1. The zero case stops recursion and makes induction valid.
 * Cost: O(n) time and O(n) call-stack space; an iterative loop uses O(1) working space.
 */
public final class Q376RecursiveFactorial { // Keep this question independently runnable and testable.
    private Q376RecursiveFactorial() { } // No object state is needed by this standalone exercise.
    public static long factorial(int n) { // Expose the operation for an independent caller and the executable checks below.
        if (n < 0 || n > 20) throw new IllegalArgumentException("Require 0..20"); // Bound input so the product fits long.
        if (n == 0) return 1; // The empty product is the multiplicative identity.
        return n * factorial(n - 1); // Attach n to the product of all smaller positive integers.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(factorial(5)); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(factorial(0)==1, "Q376 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(factorial(1)==1, "Q376 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(factorial(10)==3628800L, "Q376 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
