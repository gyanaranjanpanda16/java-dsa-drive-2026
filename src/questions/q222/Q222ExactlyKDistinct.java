package questions.q222; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Count subarrays containing exactly k distinct values.
 * Contract: Return the number of nonempty subarrays with exactly k distinct integers; k>=0.
 * Why: Exactly-k equals at-most-k minus at-most-(k-1). For each right endpoint, the smallest valid left endpoint makes every later start valid, contributing right-left+1. This counts diversity-constrained stretches without enumerating all intervals.
 * Cost: Expected O(n) time and O(min(n,k+1)) working space.
 */
public final class Q222ExactlyKDistinct { // Keep the algorithm, demonstration, and boundary checks together.
    private Q222ExactlyKDistinct() { } // Only static entry points are needed for this exercise.
    public static long solve(int[] a,int k) { // Subarray counts may exceed the int range.
        if(k<0) throw new IllegalArgumentException("nonnegative k required"); // Negative distinct counts have no application meaning.
        return atMost(a,k)-atMost(a,k-1); // Subtract the nested family with fewer distinct values.
    }
    private static long atMost(int[] a,int k) { // Count all windows satisfying an upper bound on distinctness.
        if(k<0) return 0; // This base case makes exactly-zero counting work naturally.
        Map<Integer,Integer> counts=new HashMap<>(); int left=0; long total=0; // Start with an empty valid window.
        for(int right=0;right<a.length;right++) { // Extend each possible right endpoint once.
            counts.merge(a[right],1,Integer::sum); // Include its value in the window multiplicities.
            while(counts.size()>k) { // Shrink until every allowed starting position lies at or after left.
                int value=a[left++],remaining=counts.get(value)-1; // Remove one leftmost occurrence.
                if(remaining==0) counts.remove(value); else counts.put(value,remaining); // Distinctness falls only on the final removal.
            }
            total+=right-left+1L; // All suffixes of a valid window also satisfy the at-most bound.
        }
        return total; // Each nonempty interval was counted at its right endpoint.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new int[]{1,2,1,2,3},2))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new int[]{1,2,1,2,3},2), 7L); // Repeated values form many valid intervals.
        Check.equal(solve(new int[]{1,1},1), 3L); // Every interval has one distinct value.
        Check.equal(solve(new int[]{1},0), 0L); // No nonempty interval has zero distinct values.
    }
}
