package questions.q239; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Search a rotated sorted array with distinct values.
 * Contract: Return target index or-1 in a rotation of a strictly increasing int array.
 * Why: At least one half around the midpoint is sorted. Test whether target lies within that half before eliminating it or choosing it. This preserves logarithmic search after a cyclic shift of an ordered sequence.
 * Cost: O(log(n+1)) time and O(1) space; distinctness is required for this elimination rule.
 */
public final class Q239RotatedSearch { // Keep the algorithm, demonstration, and boundary checks together.
    private Q239RotatedSearch() { } // Only static entry points are needed for this exercise.
    public static int solve(int[] a,int target) { // Exploit one sorted half even though the full array wraps once.
        int low=0,high=a.length-1; // Begin with every position still possible.
        while(low<=high) { // Continue while at least one candidate remains.
            int middle=low+(high-low)/2; if(a[middle]==target) return middle; // A direct match ends the search.
            if(a[low]<=a[middle]) { // With distinct keys, the left half is monotonically ordered.
                if(a[low]<=target && target<a[middle]) high=middle-1; else low=middle+1; // Keep that half only when its value bounds include target.
            } else { // The rotation break is on the left, so the right half is sorted.
                if(a[middle]<target && target<=a[high]) low=middle+1; else high=middle-1; // Use the right half's value bounds symmetrically.
            }
        }
        return -1; // Target lies in neither surviving range.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new int[]{4,5,6,7,0,1,2},0))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new int[]{4,5,6,7,0,1,2},0), 4); // Target lies after the rotation break.
        Check.equal(solve(new int[]{1,2,3},2), 1); // Unrotated input still works.
        Check.equal(solve(new int[]{},4), -1); // Empty input has no target.
    }
}
