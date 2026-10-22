package questions.q478; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Recover one unpaired value with XOR.
 * Contract: A nonempty int array has exactly one value occurring once and every other distinct value occurring exactly twice. Return the singleton.
 * Why: XOR is associative and commutative, and x XOR x=0. Every pair cancels regardless of position, leaving the singleton. This is a compact parity-based recovery technique; it relies on the stated multiplicity contract.
 * Cost: O(n) time and O(1) space; no frequency map is needed under the exact pair assumption.
 */
public final class Q478SingleAmongPairs { // Keep the algorithm, demonstration, and boundary checks together.
    private Q478SingleAmongPairs() { } // Only static entry points are needed for this exercise.
    public static int solve(int[] a) { // Recover the unmatched32-bit pattern using cancellation.
        int result=0; // Zero is the identity for XOR.
        for(int value:a) result^=value; // Equal pairs cancel independently of their order.
        return result; // The only uncancelled occurrence is the promised singleton.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new int[]{4,1,2,1,2}))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new int[]{4,1,2,1,2}), 4); // Pairs are interleaved.
        Check.equal(solve(new int[]{-3}), -3); // A single negative value.
        Check.equal(solve(new int[]{Integer.MIN_VALUE,7,7}), Integer.MIN_VALUE); // The sign bit is preserved by XOR.
    }
}
