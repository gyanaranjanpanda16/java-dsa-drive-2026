package questions.q483; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Generate a sequence whose adjacent bitmasks differ by one bit.
 * Contract: For0<=bits<=16, return all2^bits reflected Gray codes starting at zero. Adjacent codes, including last-to-first for positive bits, differ in exactly one bit.
 * Why: The formula i XOR(i shifted right) converts binary carry changes into a single Gray-bit change. This creates a complete fixed-width traversal with minimal adjacent changes, useful in state encoding and subset-order exercises.
 * Cost: O(2^bits) time and output space; the bound prevents accidental exponential allocation.
 */
public final class Q483GrayCode { // Keep the algorithm, demonstration, and boundary checks together.
    private Q483GrayCode() { } // Only static entry points are needed for this exercise.
    public static int[] solve(int bits) { // Keep the exponential output explicitly bounded for this demonstration.
        if(bits<0||bits>16) throw new IllegalArgumentException("require zero to sixteen bits"); // Reject impractical or invalid output sizes.
        int[] codes=new int[1<<bits]; // Every possible bit pattern appears exactly once.
        for(int i=0;i<codes.length;i++) codes[i]=i^(i>>>1); // Adjacent binary carry patterns cancel down to one changed Gray bit.
        return codes; // Zero bits yields the one empty-pattern value zero.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(2))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(2), new int[]{0,1,3,2}); // A complete two-bit Gray cycle.
        Check.equal(solve(0), new int[]{0}); // The empty bit pattern.
        Check.equal(solve(1), new int[]{0,1}); // A one-bit cycle.
    }
}
