package questions.q249; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Sort a bounded integer range with occurrence counts.
 * Contract: Return a sorted copy. Allow negative ints, but require max-min+1<=1000000 to bound allocated counting storage.
 * Why: Translate each value by the minimum into a nonnegative bucket index. Emitting every bucket in increasing index order reconstructs the sorted multiset. Useful when the value universe is small compared with the input size.
 * Cost: O(n+R) time and O(n+R) space, where R is the value range; unsuitable for sparse huge ranges.
 */
public final class Q249CountingSort { // Keep the algorithm, demonstration, and boundary checks together.
    private Q249CountingSort() { } // Only static entry points are needed for this exercise.
    public static int[] solve(int[] input) { // Sorting by value counts does not require pairwise comparisons.
        if(input.length==0) return new int[0]; // Empty input has no minimum or maximum to measure.
        int minimum=input[0],maximum=input[0]; // Establish the bounded universe from actual values.
        for(int value:input) { minimum=Math.min(minimum,value); maximum=Math.max(maximum,value); } // Find both range endpoints.
        long range=(long)maximum-minimum+1; if(range>1000000) throw new IllegalArgumentException("range too large"); // Widen before subtracting and reject excessive allocation.
        int[] counts=new int[(int)range]; // One bucket stores each possible offset from the minimum.
        for(int value:input) counts[value-minimum]++; // The accepted range makes offset arithmetic safe.
        int[] result=new int[input.length]; int out=0; // Reserve exactly one slot per original occurrence.
        for(int offset=0;offset<counts.length;offset++) for(int copy=0;copy<counts[offset];copy++) result[out++]=minimum+offset; // Increasing buckets yield increasing values.
        return result; // Occurrence counts preserve duplicates exactly.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new int[]{-2,1,-2,0}))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new int[]{-2,1,-2,0}), new int[]{-2,-2,0,1}); // Negative values use a minimum offset.
        Check.equal(solve(new int[]{}), new int[]{}); // Empty input.
        Check.equal(solve(new int[]{Integer.MAX_VALUE,Integer.MAX_VALUE}), new int[]{Integer.MAX_VALUE,Integer.MAX_VALUE}); // A large absolute value can still have a tiny range.
    }
}
