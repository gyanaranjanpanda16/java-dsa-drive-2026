package questions.q243; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Find the median of two sorted arrays by partitioning.
 * Contract: Two nondecreasing int arrays have at least one element in total. Return their combined median as double without merging them.
 * Why: Choose a partition in the shorter array; the total left-side size fixes the other partition. When both left maxima are at most the opposite right minima, all left values precede all right values. This selects the middle rank without materializing the union.
 * Cost: O(log(min(n,m)+1)) time and O(1) auxiliary space.
 */
public final class Q243MedianSortedArrays { // Keep the algorithm, demonstration, and boundary checks together.
    private Q243MedianSortedArrays() { } // Only static entry points are needed for this exercise.
    public static double solve(int[] a,int[] b) { // Search only the smaller array to keep partition bounds valid.
        if(a.length>b.length) return solve(b,a); // One swap ensures logarithmic dependence on the shorter length.
        if(a.length==0 && b.length==0) throw new IllegalArgumentException("no median"); // Empty combined input has no middle rank.
        int low=0,high=a.length; long total=(long)a.length+b.length; // Use long for combined lengths and sentinel arithmetic.
        int leftSize=(int)((total+1)/2); // The left side holds one extra element for odd total size.
        while(low<=high) { // Every candidate is a count of elements taken from a.
            int i=low+(high-low)/2,j=leftSize-i; // Determine the matching partition of b.
            long al=i==0?Long.MIN_VALUE:a[i-1],ar=i==a.length?Long.MAX_VALUE:a[i]; // Missing a-side neighbors act as infinities.
            long bl=j==0?Long.MIN_VALUE:b[j-1],br=j==b.length?Long.MAX_VALUE:b[j]; // Do the same for b's boundary positions.
            if(al<=br && bl<=ar) { // The combined partition is globally ordered across its cut.
                long left=Math.max(al,bl); // This is the largest value in the lower half.
                return total%2==1?left:(left+Math.min(ar,br))/2.0; // Average middle ints in long to prevent overflow.
            }
            if(al>br) high=i-1; else low=i+1; // Move the a partition away from the side causing the cross-boundary violation.
        }
        throw new IllegalArgumentException("arrays must be sorted"); // Valid sorted inputs always admit a partition.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new int[]{1,3},new int[]{2}))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new int[]{1,3},new int[]{2}), 2.0); // Odd total length.
        Check.equal(solve(new int[]{},new int[]{2,4}), 3.0); // One empty array.
        Check.equal(solve(new int[]{Integer.MAX_VALUE},new int[]{Integer.MAX_VALUE}), 2147483647.0); // Even median addition is widened.
    }
}
