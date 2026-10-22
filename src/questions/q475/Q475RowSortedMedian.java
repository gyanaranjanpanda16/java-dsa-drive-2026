package questions.q475; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Find the median of a matrix with sorted rows.
 * Contract: Rows are nonempty, individually nondecreasing int arrays, possibly different lengths. Total element count must be odd. Return the median without flattening or globally sorting.
 * Why: For each candidate value, an upper-bound search in each row counts values at most that candidate. Summing those counts gives its global rank. The first candidate reaching the middle rank is the median, even though columns are not sorted. Useful for merging rank information from separately sorted partitions.
 * Cost: O(R*log(C+1)*log(valueSpan+1)) time and O(1) auxiliary space, with R rows and maximum row length C.
 */
public final class Q475RowSortedMedian { // Keep the algorithm, demonstration, and boundary checks together.
    private Q475RowSortedMedian() { } // Only static entry points are needed for this exercise.
    public static int solve(int[][] rows) { // Search the value domain while preserving every row's storage.
        long total=0,low=Integer.MAX_VALUE,high=Integer.MIN_VALUE; // Long bounds keep midpoint arithmetic safe across int extremes.
        for(int[] row:rows) { if(row.length==0) throw new IllegalArgumentException("nonempty rows required"); total+=row.length; low=Math.min(low,row[0]); high=Math.max(high,row[row.length-1]); } // Sorted endpoints bound every value.
        if(total==0 || total%2==0) throw new IllegalArgumentException("positive odd total required"); // This exercise returns a single middle value.
        long rank=total/2+1; // Convert the middle position to a one-based cumulative rank.
        while(low<high) { // Find the smallest value with enough elements at or below it.
            long middle=low+(high-low)/2,count=0; // Sum row-wise upper-bound positions.
            for(int[] row:rows) { // Each row supplies its own independently sorted search interval.
                int left=0,right=row.length; while(left<right) { int at=left+(right-left)/2; if(row[at]<=middle) left=at+1; else right=at; } // Upper bound counts all duplicates of the candidate.
                count+=left; // Every earlier position in this row is at most middle.
            }
            if(count>=rank) high=middle; else low=middle+1; // Cumulative counts form a monotone predicate.
        }
        return (int)low; // The surviving threshold is an actual median int value.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new int[][]{{1,3,5},{2,6,9},{3,6,9}}))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new int[][]{{1,3,5},{2,6,9},{3,6,9}}), 5); // Rows are sorted without globally sorted layout.
        Check.equal(solve(new int[][]{{-3}}), -3); // A singleton median.
        Check.equal(solve(new int[][]{{Integer.MIN_VALUE,0,Integer.MAX_VALUE}}), 0); // Full signed-int value range.
    }
}
