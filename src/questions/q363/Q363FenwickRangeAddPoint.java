package questions.q363; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Add to whole intervals and query individual values.
 * Contract: Start with n zero values. Operations[0,left,right,delta] add to[left,right);[1,index,0,0] query one value. Bounds are valid and accumulated values fit long.
 * Why: Adding delta over an interval changes its difference array only at the beginning and end. A point value equals the prefix total of differences, so one Fenwick tree supports range additions and point queries. Useful for overlapping scheduled adjustments.
 * Cost: O(q log(n+1)) time and O(n) space plus answers.
 */
public final class Q363FenwickRangeAddPoint { // Keep the algorithm, demonstration, and boundary checks together.
    private Q363FenwickRangeAddPoint() { } // Only static entry points are needed for this exercise.
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
    public static List<Long> solve(int n,int[][] operations) { // Store the difference array implicitly in a Fenwick tree.
        Fenwick differences=new Fenwick(n); List<Long> result=new ArrayList<>(); // Initial zero values require no construction updates.
        for(int[] op:operations) { // Range updates have two boundary effects regardless of interval length.
            if(op[0]==0) { // Add a constant to a half-open range.
                if(op[1]<n) differences.add(op[1],op[3]); // The adjustment starts at left.
                if(op[2]<n) differences.add(op[2],-(long)op[3]); // Cancel it at right; widening handles Integer.MIN_VALUE.
            } else if(op[0]==1) result.add(differences.prefix(op[1]+1)); // Include the queried index's difference to recover its current value.
            else throw new IllegalArgumentException("unknown operation"); // Only range additions and point queries are defined.
        }
        return result; // Prefix sums combine every update covering each queried index.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(4,new int[][]{{0,1,4,3},{0,2,3,2},{1,0,0,0},{1,2,0,0},{1,3,0,0}}))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(4,new int[][]{{0,1,4,3},{0,2,3,2},{1,0,0,0},{1,2,0,0},{1,3,0,0}}), List.of(0L,5L,3L)); // Two overlapping interval additions.
        Check.equal(solve(1,new int[][]{{0,0,1,-5},{1,0,0,0}}), List.of(-5L)); // Negative adjustment through the end boundary.
        Check.equal(solve(2,new int[][]{{0,1,1,7},{1,1,0,0}}), List.of(0L)); // An empty update cancels at the same boundary.
    }
}
