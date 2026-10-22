package questions.q366; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Count out-of-order index pairs with compressed ranks.
 * Contract: Return the number of pairs i<j with a[i]>a[j], without changing input.
 * Why: Coordinate compression preserves relative order while replacing sparse or negative values with dense ranks. Scanning right to left, the Fenwick tree contains exactly later values; querying smaller ranks counts the inversions contributed by the current value. Useful for measuring ranking disorder.
 * Cost: O(n log(n+1)) time and O(n) space.
 */
public final class Q366InversionCount { // Keep the algorithm, demonstration, and boundary checks together.
    private Q366InversionCount() { } // Only static entry points are needed for this exercise.
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
    public static long solve(int[] a) { // Count strict inversions, excluding equal values.
        int[] sorted=a.clone(); Arrays.sort(sorted); int unique=0; // Sort a copy to define order-preserving ranks.
        for(int value:sorted) if(unique==0 || value!=sorted[unique-1]) sorted[unique++]=value; // Compact duplicate keys into one rank each.
        Fenwick seen=new Fenwick(unique); long inversions=0; // The tree counts values strictly to the right of the current index.
        for(int i=a.length-1;i>=0;i--) { // Insert only after querying so an element cannot pair with itself.
            int rank=Arrays.binarySearch(sorted,0,unique,a[i]); // Find the current value's dense zero-based rank.
            inversions+=seen.prefix(rank); // Ranks below this one correspond exactly to strictly smaller later values.
            seen.add(rank,1); // Make this occurrence available to earlier indices.
        }
        return inversions; // Every inversion was counted once at its left endpoint.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new int[]{5,2,6,1}))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new int[]{5,2,6,1}), 4L); // Interleaved larger and smaller values.
        Check.equal(solve(new int[]{2,2}), 0L); // Equal values are not inversions.
        Check.equal(solve(new int[]{3,2,1}), 3L); // Every pair in reverse order is inverted.
    }
}
