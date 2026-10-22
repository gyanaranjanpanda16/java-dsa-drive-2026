package questions.q240; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Find the minimum in a rotated array with duplicates.
 * Contract: Return OptionalInt minimum of a rotated nondecreasing array, or empty for no elements.
 * Why: Comparing midpoint to the right endpoint identifies which side contains a smaller value. Equality is ambiguous: removing the right endpoint is safe because the midpoint preserves an equal candidate. Duplicates can force linear work.
 * Cost: O(log n) on informative comparisons, O(n) worst-case with duplicates; O(1) space.
 */
public final class Q240RotatedMinimum { // Keep the algorithm, demonstration, and boundary checks together.
    private Q240RotatedMinimum() { } // Only static entry points are needed for this exercise.
    public static OptionalInt solve(int[] a) { // Represent empty input without reserving an integer sentinel.
        if(a.length==0) return OptionalInt.empty(); // No minimum exists in an empty sequence.
        int low=0,high=a.length-1; // At least one minimum remains inside this inclusive interval.
        while(low<high) { // Stop when one candidate position remains.
            int middle=low+(high-low)/2; // Probe without arithmetic overflow.
            if(a[middle]>a[high]) low=middle+1; // The wrap and smaller values must occur after middle.
            else if(a[middle]<a[high]) high=middle; // The right side is ordered above a candidate at middle.
            else high--; // An equal midpoint preserves any minimum represented by the removed endpoint.
        }
        return OptionalInt.of(a[low]); // The invariant leaves a minimum at the surviving index.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new int[]{2,2,2,0,1}))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new int[]{2,2,2,0,1}), OptionalInt.of(0)); // Duplicates before a unique minimum.
        Check.equal(solve(new int[]{1,1,1}), OptionalInt.of(1)); // Every value is a minimum.
        Check.equal(solve(new int[]{}), OptionalInt.empty()); // Empty sequence.
    }
}
