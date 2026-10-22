package questions.q479; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Recover two distinct unpaired values with XOR partitioning.
 * Contract: Exactly two distinct int values occur once; every other value occurs twice. Return the two singletons in ascending numeric order.
 * Why: XORing everything gives a XOR b, whose set bit marks a position where the singletons differ. Partition all values by that bit: paired values stay together and cancel, while the singletons land in opposite groups. Useful for recovering two odd-parity records under strict assumptions.
 * Cost: O(n) time and O(1) working space plus a two-element result.
 */
public final class Q479TwoSingles { // Keep the algorithm, demonstration, and boundary checks together.
    private Q479TwoSingles() { } // Only static entry points are needed for this exercise.
    public static int[] solve(int[] a) { // First identify a bit that distinguishes the two promised singleton values.
        int combined=0; for(int value:a) combined^=value; // Paired values disappear, leaving the XOR of the singletons.
        if(combined==0) throw new IllegalArgumentException("two distinct singles required"); // Distinct singleton bit patterns must differ somewhere.
        int distinguishing=combined&-combined,first=0,second=0; // Isolate the lowest set bit, including the sign bit if necessary.
        for(int value:a) { if((value&distinguishing)==0) first^=value; else second^=value; } // Equal pairs share a partition and cancel within it.
        return first<second?new int[]{first,second}:new int[]{second,first}; // Define deterministic ascending output order.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new int[]{1,2,1,3,2,5}))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new int[]{1,2,1,3,2,5}), new int[]{3,5}); // Mixed pairs and two singletons.
        Check.equal(solve(new int[]{0,Integer.MIN_VALUE}), new int[]{Integer.MIN_VALUE,0}); // Only the sign bit distinguishes the singles.
        Check.equal(solve(new int[]{-1,2,7,7}), new int[]{-1,2}); // Signed values share the same bitwise proof.
    }
}
