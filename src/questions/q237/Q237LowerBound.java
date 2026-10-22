package questions.q237; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Find the first sorted value at least a target.
 * Contract: Return the first index with a[index]>=target in a nondecreasing array, or length if none.
 * Why: Maintain a half-open candidate interval for the boundary between values below target and values at least target. An equal value stays in the right candidate half because an earlier equal value may exist. Useful for insertion positions and inclusive range starts.
 * Cost: O(log(n+1)) time and O(1) space.
 */
public final class Q237LowerBound { // Keep the algorithm, demonstration, and boundary checks together.
    private Q237LowerBound() { } // Only static entry points are needed for this exercise.
    public static int solve(int[] a,int target) { // Return a boundary position, which may be one past the last element.
        int low=0,high=a.length; // The half-open interval avoids a special absent-result case.
        while(low<high) { // Converge to the first position satisfying the monotone predicate.
            int middle=low+(high-low)/2; // middle remains a valid array index while low is below high.
            if(a[middle]<target) low=middle+1; else high=middle; // Equality may be the boundary, so do not discard it.
        }
        return low; // All earlier entries are below target; this entry, if any, is at least target.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new int[]{1,2,2,4},2))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new int[]{1,2,2,4},2), 1); // Return the first duplicate.
        Check.equal(solve(new int[]{1,2},5), 2); // Length denotes the end insertion position.
        Check.equal(solve(new int[]{},0), 0); // The only empty-array boundary.
    }
}
