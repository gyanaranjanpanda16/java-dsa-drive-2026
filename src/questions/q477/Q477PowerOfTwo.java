package questions.q477; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Recognize a positive power of two.
 * Contract: Return whether an int is a positive mathematical power of two.
 * Why: A positive power of two has exactly one set bit, so clearing its lowest set bit yields zero. The positivity check excludes zero and the negative sign-bit pattern. Useful for validating power-of-two capacities and alignments.
 * Cost: O(1) time and space for fixed-width ints.
 */
public final class Q477PowerOfTwo { // Keep the algorithm, demonstration, and boundary checks together.
    private Q477PowerOfTwo() { } // Only static entry points are needed for this exercise.
    public static boolean solve(int value) { // Separate numeric positivity from the bit-pattern test.
        return value>0 && (value&(value-1))==0; // Exactly one set bit identifies a positive power of two.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(16))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(16), true); // One positive set bit.
        Check.equal(solve(0), false); // Zero is not a power of two.
        Check.equal(solve(Integer.MIN_VALUE), false); // A single sign bit is still a negative number.
        Check.equal(solve(18), false); // Multiple set bits.
    }
}
