package questions.q481; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Compute the bitwise AND of an inclusive integer range.
 * Contract: For0<=left<=right<=Integer.MAX_VALUE, return the AND of every integer in[left,right].
 * Why: Every changing low bit becomes zero somewhere across the interval. Only the common high-bit prefix survives, so shift both endpoints until equal and restore their shared prefix. Useful for reasoning about bitmask ranges without iterating over their numeric span.
 * Cost: O(31) time and O(1) space.
 */
public final class Q481RangeBitwiseAnd { // Keep the algorithm, demonstration, and boundary checks together.
    private Q481RangeBitwiseAnd() { } // Only static entry points are needed for this exercise.
    public static int solve(int left,int right) { // Restrict the range to nonnegative signed-int values.
        if(left<0||left>right) throw new IllegalArgumentException("invalid range"); // Ordered nonnegative bounds define the inclusive interval.
        int shifts=0; // Count low bits known to vary somewhere across the interval.
        while(left!=right) { left>>>=1; right>>>=1; shifts++; } // Remove differing suffix bits until both endpoints share one prefix.
        return left<<shifts; // Every removed bit contributes zero to the full-range AND.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(5,7))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(5,7), 4); // A range with a shared high prefix.
        Check.equal(solve(0,Integer.MAX_VALUE), 0); // Including zero clears all bits.
        Check.equal(solve(9,9), 9); // A singleton range preserves its value.
    }
}
