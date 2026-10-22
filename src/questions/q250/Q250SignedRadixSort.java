package questions.q250; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Sort signed ints using four stable byte passes.
 * Contract: Return an ascending copy of arbitrary signed32-bit integers using least-significant-byte radix sort.
 * Why: Stable passes preserve previously ordered lower bytes. Flipping the sign bit transforms signed numeric order into unsigned bit-pattern order; sorting all four transformed bytes therefore gives signed order without comparisons. Useful for large fixed-width numeric batches.
 * Cost: O(4*(n+256)) time and O(n+256) auxiliary space.
 */
public final class Q250SignedRadixSort { // Keep the algorithm, demonstration, and boundary checks together.
    private Q250SignedRadixSort() { } // Only static entry points are needed for this exercise.
    public static int[] solve(int[] input) { // Treat signed values through an order-preserving bit transformation.
        int[] a=input.clone(),buffer=new int[a.length]; // Alternate source and destination buffers on each pass.
        for(int shift=0;shift<32;shift+=8) { // Process bytes from least significant to most significant.
            int[] next=new int[256]; // One counter per possible byte value.
            for(int value:a) next[((value^Integer.MIN_VALUE)>>>shift)&255]++; // Count transformed byte frequencies using unsigned shift.
            int total=0; // Convert frequencies into the starting offset of each byte bucket.
            for(int key=0;key<256;key++) { int count=next[key]; next[key]=total; total+=count; } // Prefix totals reserve consecutive bucket ranges.
            for(int value:a) buffer[next[((value^Integer.MIN_VALUE)>>>shift)&255]++]=value; // Forward traversal preserves stability inside each bucket.
            int[] saved=a; a=buffer; buffer=saved; // The completed pass becomes the next pass's input without copying.
        }
        return a; // All32 transformed bits are now ordered from most to least significant.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new int[]{3,-1,Integer.MIN_VALUE,0,Integer.MAX_VALUE}))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new int[]{3,-1,Integer.MIN_VALUE,0,Integer.MAX_VALUE}), new int[]{Integer.MIN_VALUE,-1,0,3,Integer.MAX_VALUE}); // Both signed extremes and zero.
        Check.equal(solve(new int[]{}), new int[]{}); // Empty buffers remain empty.
        Check.equal(solve(new int[]{-2,-2,-3}), new int[]{-3,-2,-2}); // Negative duplicates retain their multiplicity.
    }
}
