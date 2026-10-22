package questions.q480; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Recover a singleton when every other value occurs three times.
 * Contract: One int occurs once and all other distinct ints occur exactly three times. Return the singleton.
 * Why: Track each bit count modulo3 in two bitmasks. The state cycles absent→seen once→seen twice→absent. Bits from triples vanish, leaving the singleton mask, including its sign bit. This generalizes XOR cancellation beyond pairs.
 * Cost: O(n) time and O(1) space for32 simultaneous modulo-three counters.
 */
public final class Q480SingleAmongTriples { // Keep the algorithm, demonstration, and boundary checks together.
    private Q480SingleAmongTriples() { } // Only static entry points are needed for this exercise.
    public static int solve(int[] a) { // Process all32 independent bit counters in parallel.
        int once=0,twice=0; // No bit has yet appeared once or twice modulo three.
        for(int value:a) { // Update the modulo-three state for each incoming bit pattern.
            once=(once^value)&~twice; // Toggle once bits unless the old twice state requires this occurrence to reset them.
            twice=(twice^value)&~once; // Use the new once mask to exclude bits that have just entered their first-count state.
        }
        return once; // Triples reset to zero; only the promised singleton remains in the once state.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new int[]{2,2,3,2}))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new int[]{2,2,3,2}), 3); // Triple bits cancel modulo three.
        Check.equal(solve(new int[]{-7,5,5,5}), -7); // Negative singleton sign bits survive.
        Check.equal(solve(new int[]{Integer.MIN_VALUE,0,0,0}), Integer.MIN_VALUE); // A lone sign bit is recovered exactly.
    }
}
