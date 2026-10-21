package questions.q377;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q377: Recursive Digit Sum.
 * Contract: Sum decimal digits of a nonnegative long.
 * Why it works: Modulo ten extracts the last digit and integer division removes it. Every recursive call shortens the decimal representation.
 * Cost: O(d) time and O(d) stack for d decimal digits.
 */
public final class Q377RecursiveDigitSum { // Keep this question independently runnable and testable.
    private Q377RecursiveDigitSum() { } // No object state is needed by this standalone exercise.
    public static int digitSum(long value) { // Expose the operation for an independent caller and the executable checks below.
        if (value < 0) throw new IllegalArgumentException("Use nonnegative input"); // Avoid sign and MIN_VALUE ambiguity.
        if (value < 10) return (int) value; // One digit already is its own sum.
        return (int)(value % 10) + digitSum(value / 10); // Split off the final decimal digit.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(digitSum(5072)); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(digitSum(0)==0, "Q377 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(digitSum(9)==9, "Q377 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(digitSum(12345)==15, "Q377 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
