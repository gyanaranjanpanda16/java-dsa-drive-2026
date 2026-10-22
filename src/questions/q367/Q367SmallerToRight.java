package questions.q367; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Count strictly smaller later values for every position.
 * Contract: Return an array where output[i] counts j>i with a[j]<a[i].
 * Why: Scan from right to left and query frequencies of compressed ranks below the current rank. Unlike a single global inversion total, retain each position contribution. This provides per-record rank diagnostics in an ordered stream.
 * Cost: O(n log(n+1)) time and O(n) space.
 */
public final class Q367SmallerToRight { // Keep the algorithm, demonstration, and boundary checks together.
    private Q367SmallerToRight() { } // Only static entry points are needed for this exercise.
    private static final class Fenwick { // tree[i] stores the sum of a lowbit-sized block ending at one-based index i.
        final long[] tree; // Wider storage protects ordinary int-input aggregates.
        Fenwick(int size) { tree=new long[size+1]; } // Index zero stays unused to simplify parent jumps.
        void add(int index,long delta) { // Change a zero-based position by an additive difference.
            for(int i=index+1;i<tree.length;i+=i&-i) tree[i]+=delta; // Every containing block is reached by adding the lowest set bit.
        }
        long prefix(int end) { // Sum the half-open original range [0,end).
            long sum=0; for(int i=end;i>0;i-=i&-i) sum+=tree[i]; // Remove the final covered block until no prefix remains.
            return sum; // The visited blocks are disjoint and exactly cover the prefix.
        }
    }
    public static int[] solve(int[] a) { // Produce a local count for each original index.
        int[] sorted=a.clone(); Arrays.sort(sorted); int unique=0; // Dense ranks keep the tree proportional to distinct values.
        for(int value:sorted) if(unique==0||value!=sorted[unique-1]) sorted[unique++]=value; // Share one rank among duplicates.
        Fenwick later=new Fenwick(unique); int[] result=new int[a.length]; // Initially there are no later positions.
        for(int i=a.length-1;i>=0;i--) { // Grow the known suffix by one occurrence each iteration.
            int rank=Arrays.binarySearch(sorted,0,unique,a[i]); // Compression preserves strict comparisons even for negative values.
            result[i]=(int)later.prefix(rank); // At most a.length-1 positions can contribute, so the count fits int.
            later.add(rank,1); // Include this value only for earlier positions' queries.
        }
        return result; // Output positions retain the original input order.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new int[]{5,2,6,1}))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new int[]{5,2,6,1}), new int[]{2,1,1,0}); // Different per-position counts.
        Check.equal(solve(new int[]{-1,-1}), new int[]{0,0}); // Duplicates are excluded by strict rank bounds.
        Check.equal(solve(new int[]{}), new int[]{}); // No positions to report.
    }
}
