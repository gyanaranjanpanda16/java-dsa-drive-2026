package questions.q373; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Answer static range minima in constant query time.
 * Contract: For a static int array and valid nonempty half-open queries[left,right], return each minimum. Empty arrays may have no queries.
 * Why: Precompute minima for every power-of-two interval. A query is covered by two possibly overlapping blocks of the largest fitting size; overlap is harmless because min is idempotent. This trades preprocessing and memory for very fast immutable range queries, and does not support efficient point updates.
 * Cost: O(n log(n+1)+q) time and O(n log(n+1)) table space.
 */
public final class Q373SparseTableMinimum { // Keep the algorithm, demonstration, and boundary checks together.
    private Q373SparseTableMinimum() { } // Only static entry points are needed for this exercise.
    public static int[] solve(int[] a,int[][] queries) { // Precompute reusable minima only for an immutable array.
        int[] log=new int[a.length+1]; for(int i=2;i<log.length;i++) log[i]=log[i/2]+1; // Store floor(log2(length)) without floating-point rounding.
        int[][] table=new int[log[a.length]+1][a.length]; table[0]=a.clone(); // Level zero represents one-element ranges.
        for(int level=1;level<table.length;level++) { // Combine two adjacent half-length blocks into each larger block.
            int length=1<<level,half=length/2; // The represented interval length doubles each level.
            for(int start=0;start<=a.length-length;start++) table[level][start]=Math.min(table[level-1][start],table[level-1][start+half]); // Both halves were computed at the prior level.
        }
        int[] result=new int[queries.length]; // Every query needs only two cached values after preprocessing.
        for(int i=0;i<queries.length;i++) { // The contract supplies nonempty, in-bounds intervals.
            int left=queries[i][0],right=queries[i][1],level=log[right-left],length=1<<level; // Choose the largest power-of-two block fitting the query.
            result[i]=Math.min(table[level][left],table[level][right-length]); // Cover both ends; overlap does not change a minimum.
        }
        return result; // Query order is independent of preprocessing order.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new int[]{5,2,8,1,4},new int[][]{{1,5},{0,3}}))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new int[]{5,2,8,1,4},new int[][]{{1,5},{0,3}}), new int[]{1,2}); // Power-of-two and overlapping-block queries.
        Check.equal(solve(new int[]{-3},new int[][]{{0,1}}), new int[]{-3}); // One-element interval.
        Check.equal(solve(new int[]{},new int[][]{}), new int[]{}); // No queries on empty input.
    }
}
