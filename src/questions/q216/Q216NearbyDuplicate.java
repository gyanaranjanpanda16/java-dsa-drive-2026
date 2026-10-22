package questions.q216; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Detect equal values within an index distance.
 * Contract: Return whether distinct indices i,j satisfy a[i]==a[j] and |i-j|<=k; k must be nonnegative.
 * Why: Store the latest occurrence of each value. If even its nearest earlier occurrence is too far away, every older occurrence is also too far. This detects repeated event identifiers inside a bounded number of arrivals.
 * Cost: Expected O(n) time and O(u) space for u distinct values.
 */
public final class Q216NearbyDuplicate { // Keep the algorithm, demonstration, and boundary checks together.
    private Q216NearbyDuplicate() { } // Only static entry points are needed for this exercise.
    public static boolean solve(int[] a,int k) { // Inspect proximity in positions rather than in numeric value.
        if(k<0) throw new IllegalArgumentException("negative distance"); // A distance bound cannot be negative.
        Map<Integer,Integer> latest=new HashMap<>(); // Retain only the closest earlier occurrence of each value.
        for(int i=0;i<a.length;i++) { // Visit indices in increasing order so stored positions are earlier.
            Integer previous=latest.put(a[i],i); // Replace the old position and retrieve it in one map operation.
            if(previous!=null && i-previous<=k) return true; // One valid pair proves existence.
        }
        return false; // No latest earlier occurrence met the bound.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new int[]{1,2,1},2))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new int[]{1,2,1},2), true); // Pair exactly on the distance boundary.
        Check.equal(solve(new int[]{1,2,1},1), false); // Equal values too far apart.
        Check.equal(solve(new int[]{7,7},0), false); // Distinct indices cannot have distance zero.
    }
}
