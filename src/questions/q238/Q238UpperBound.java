package questions.q238; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Find the first sorted value strictly greater than a target.
 * Contract: Return the first index with a[index]>target in a nondecreasing array, or length if none.
 * Why: The boundary separates values at most target from strictly larger values. Unlike lower bound, equality belongs to the discarded left side. Combining both boundaries counts duplicates or selects inclusive value ranges.
 * Cost: O(log(n+1)) time and O(1) space.
 */
public final class Q238UpperBound { // Keep the algorithm, demonstration, and boundary checks together.
    private Q238UpperBound() { } // Only static entry points are needed for this exercise.
    public static int solve(int[] a,int target) { // Locate the exclusive end of target's equal-value range.
        int low=0,high=a.length; // Include the end position as a valid boundary answer.
        while(low<high) { // Each comparison strictly shrinks the candidate boundary interval.
            int middle=low+(high-low)/2; // Avoid overflowing the midpoint calculation.
            if(a[middle]<=target) low=middle+1; else high=middle; // Equal entries must precede the first strictly greater value.
        }
        return low; // This boundary can be subtracted from a lower bound to count equal entries.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new int[]{1,2,2,4},2))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new int[]{1,2,2,4},2), 3); // Skip the entire equal run.
        Check.equal(solve(new int[]{1,2},0), 0); // All values are greater.
        Check.equal(solve(new int[]{},0), 0); // Empty array boundary.
    }
}
