package questions.q220; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Find a longest consecutive integer set run.
 * Contract: Return the maximum length of consecutive distinct integer values, ignoring duplicates and original order.
 * Why: Start scanning only at values whose predecessor is absent. Each distinct value belongs to exactly one such run, so all successor scans together are linear in the number of unique values. Useful for finding uninterrupted identifier or day-number coverage.
 * Cost: Expected O(n) time and O(u) space.
 */
public final class Q220LongestConsecutive { // Keep the algorithm, demonstration, and boundary checks together.
    private Q220LongestConsecutive() { } // Only static entry points are needed for this exercise.
    public static int solve(int[] a) { // Consecutiveness concerns numeric neighbors, not adjacent array positions.
        Set<Integer> values=new HashSet<>(); for(int value:a) values.add(value); // Remove duplicates before measuring run lengths.
        int best=0; // Empty input has no run.
        for(int value:values) { // Try only true starts rather than rescanning every suffix.
            if(value!=Integer.MIN_VALUE && values.contains(value-1)) continue; // Guard subtraction at the smallest int.
            int end=value,length=1; // Every start already contributes one distinct value.
            while(end!=Integer.MAX_VALUE && values.contains(end+1)) { end++; length++; } // Guard successor overflow at the largest int.
            best=Math.max(best,length); // Keep the longest complete run found.
        }
        return best; // Each distinct member was scanned from its one run start.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new int[]{100,4,200,1,3,2}))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new int[]{100,4,200,1,3,2}), 4); // Order is irrelevant.
        Check.equal(solve(new int[]{1,1,2}), 2); // Duplicates do not extend a run.
        Check.equal(solve(new int[]{Integer.MIN_VALUE,Integer.MAX_VALUE}), 1); // Int overflow must not join extreme values.
    }
}
