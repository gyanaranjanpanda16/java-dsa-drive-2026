package questions.q476; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Count one bits by clearing the lowest set bit.
 * Contract: Return the number of one bits in the32-bit two-complement representation of any int, including negatives.
 * Why: Subtracting one flips the lowest set bit and all lower zero bits; AND with the original clears exactly that one bit. Repeating until zero counts set bits directly. Useful for bitmask cardinality and packed feature counts.
 * Cost: O(number of set bits), at most32 iterations, and O(1) space.
 */
public final class Q476SetBitCount { // Keep the algorithm, demonstration, and boundary checks together.
    private Q476SetBitCount() { } // Only static entry points are needed for this exercise.
    public static int solve(int value) { // Interpret all32 bits, not the decimal sign.
        int count=0; // No set bits have been removed initially.
        while(value!=0) { value&=value-1; count++; } // Clear exactly one set bit even when signed arithmetic wraps.
        return count; // Zero remaining bits means every original one bit was counted.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(12))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(12), 2); // Two separated high bits.
        Check.equal(solve(-1), 32); // Every bit is set in two-complement negative one.
        Check.equal(solve(0), 0); // Zero has no set bits.
    }
}
