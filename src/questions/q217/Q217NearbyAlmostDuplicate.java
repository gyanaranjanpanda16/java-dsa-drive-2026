package questions.q217; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Detect nearby values with a bounded numeric difference.
 * Contract: Return whether distinct indices differ by at most k and their int values differ by at most tolerance; both bounds are nonnegative.
 * Why: Keep the previous k values in a TreeSet. The first value at least x-tolerance is the only candidate needed: if it exceeds x+tolerance, every later value does too. Before any duplicate would collapse in the set, it already proves success. This models near-duplicate measurements in a recent window.
 * Cost: O(n log(k+1)) time and O(k) space.
 */
public final class Q217NearbyAlmostDuplicate { // Keep the algorithm, demonstration, and boundary checks together.
    private Q217NearbyAlmostDuplicate() { } // Only static entry points are needed for this exercise.
    public static boolean solve(int[] a,int k,long tolerance) { // Widen arithmetic before comparing int-domain differences.
        if(k<0 || tolerance<0) throw new IllegalArgumentException("negative bound"); // Reject invalid window or distance bounds.
        if(k==0) return false; // No pair of distinct positions can fit this window.
        tolerance=Math.min(tolerance,4294967295L); // No two int values differ by more than this; clamping also prevents long overflow.
        NavigableSet<Long> window=new TreeSet<>(); // Maintain ordered values of at most k preceding positions.
        for(int i=0;i<a.length;i++) { // Each current value is compared only against earlier positions.
            long value=a[i]; // Promotion makes subtraction safe across both int extremes.
            Long candidate=window.ceiling(value-tolerance); // Skip all values that are too small.
            if(candidate!=null && candidate<=value+tolerance) return true; // The candidate satisfies both bounds.
            window.add(value); // Make this value available to subsequent positions.
            if(i>=k) window.remove((long)a[i-k]); // Remove the position that would be too old for the next iteration.
        }
        return false; // Every ordered-window query failed.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new int[]{1,5,9,1},3,0))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new int[]{1,5,9,1},3,0), true); // Same value at the last allowed distance.
        Check.equal(solve(new int[]{1,5,9,1},2,2), false); // Near positions have values too far apart.
        Check.equal(solve(new int[]{Integer.MIN_VALUE,Integer.MAX_VALUE},1,4294967295L), true); // Full int-domain difference does not overflow.
    }
}
