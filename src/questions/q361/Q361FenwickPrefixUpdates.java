package questions.q361; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Maintain prefix totals under point additions.
 * Contract: Operations[0,index,delta] add to one initial int value;[1,end,0] query sum[0,end). Require valid indices/bounds and long-representable intermediate sums.
 * Why: Fenwick nodes cache binary-sized blocks. A point addition visits exactly the blocks containing that point; a prefix query partitions its range into disjoint cached blocks. This supports frequently changing cumulative metrics more efficiently than rebuilding prefix sums.
 * Cost: O((n+q) log(n+1)) time and O(n) tree space plus answers.
 */
public final class Q361FenwickPrefixUpdates { // Keep the algorithm, demonstration, and boundary checks together.
    private Q361FenwickPrefixUpdates() { } // Only static entry points are needed for this exercise.
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
    public static List<Long> solve(int[] initial,int[][] operations) { // Combine independent point changes with cumulative queries.
        Fenwick bit=new Fenwick(initial.length); for(int i=0;i<initial.length;i++) bit.add(i,initial[i]); // Build cached blocks from the initial values.
        List<Long> result=new ArrayList<>(); // Updates produce no output; queries append one total.
        for(int[] op:operations) { // Each operation uses zero-based problem coordinates.
            if(op[0]==0) { // Additive point updates require an existing element.
                if(op[1]<0||op[1]>=initial.length) throw new IllegalArgumentException("invalid index"); // Prevent invalid or nonprogressing Fenwick loops.
                bit.add(op[1],op[2]); // Propagate only the supplied difference.
            } else if(op[0]==1) { // Prefix endpoints may include zero and the full length.
                if(op[1]<0||op[1]>initial.length) throw new IllegalArgumentException("invalid end"); // Keep the query inside the represented array.
                result.add(bit.prefix(op[1])); // Binary block decomposition returns the current prefix total.
            } else throw new IllegalArgumentException("unknown operation"); // Reject unsupported command codes.
        }
        return result; // Query order matches the operation stream.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new int[]{1,2,3},new int[][]{{1,3,0},{0,1,5},{1,2,0}}))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new int[]{1,2,3},new int[][]{{1,3,0},{0,1,5},{1,2,0}}), List.of(6L,8L)); // Prefix totals before and after a point addition.
        Check.equal(solve(new int[]{},new int[][]{{1,0,0}}), List.of(0L)); // The empty prefix is zero.
        Check.equal(solve(new int[]{Integer.MAX_VALUE,Integer.MAX_VALUE},new int[][]{{1,2,0}}), List.of(4294967294L)); // Aggregation exceeds int safely.
    }
}
