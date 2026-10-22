package questions.q482; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Reverse all32 bits and return their unsigned value.
 * Contract: Reverse the32-bit pattern of any int and return its unsigned numeric value as a long.
 * Why: Read the low bit of the input and append it to the growing result, then shift the input logically. Exactly32 iterations preserve leading and trailing zeros, unlike decimal digit reversal. Useful for bit-oriented encodings and permutation exercises.
 * Cost: O(32) time and O(1) space.
 */
public final class Q482ReverseBits { // Keep the algorithm, demonstration, and boundary checks together.
    private Q482ReverseBits() { } // Only static entry points are needed for this exercise.
    public static long solve(int value) { // Use long so a reversed sign bit is displayed as a nonnegative unsigned value.
        long reversed=0; // The result initially contains no processed bits.
        for(int i=0;i<32;i++) { reversed=(reversed<<1)|(value&1); value>>>=1; } // Append one original low bit and discard it using zero-fill shift.
        return reversed; // Exactly32 appends keep the result within the unsigned32-bit range.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(1))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(1), 2147483648L); // The lowest bit becomes the highest.
        Check.equal(solve(-1), 4294967295L); // All bits stay set.
        Check.equal(solve(0), 0L); // All bits stay clear.
    }
}
