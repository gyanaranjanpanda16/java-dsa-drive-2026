package questions.q364; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Support range additions and range totals with two Fenwick trees.
 * Contract: Start with n zeros. Operations[0,left,right,delta] add to[left,right);[1,left,right,0] query its sum. Bounds are valid; all products and sums fit long.
 * Why: If D is the difference array, prefixSum(end)=sum_{j<end}(end-j)*D[j]. Two Fenwick trees store D[j] and j*D[j], giving end*sum(D)-sum(jD). Subtract two prefixes for a range. This supports bulk adjustments and aggregate reporting together.
 * Cost: O(q log(n+1)) time and O(n) space.
 */
public final class Q364FenwickRangeAddSum { // Keep the algorithm, demonstration, and boundary checks together.
    private Q364FenwickRangeAddSum() { } // Only static entry points are needed for this exercise.
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
    private static void boundary(Fenwick plain,Fenwick weighted,int n,int index,long delta) { // Apply one difference-array boundary change to both moments.
        if(index<n) { plain.add(index,delta); weighted.add(index,delta*index); } // A boundary at n affects no stored element.
    }
    private static long total(Fenwick plain,Fenwick weighted,int end) { // Sum current values before the exclusive endpoint.
        return end*plain.prefix(end)-weighted.prefix(end); // Each difference contributes once to every covered later position.
    }
    public static List<Long> solve(int n,int[][] operations) { // Use two synchronized Fenwick trees over the same difference array.
        Fenwick plain=new Fenwick(n),weighted=new Fenwick(n); List<Long> result=new ArrayList<>(); // Zero initialization represents an all-zero array.
        for(int[] op:operations) { // Every command needs only a constant number of logarithmic operations.
            if(op[0]==0) { // Encode the beginning and cancellation of a range increment.
                boundary(plain,weighted,n,op[1],op[3]); boundary(plain,weighted,n,op[2],-(long)op[3]); // Both updates preserve the weighted-prefix identity.
            } else if(op[0]==1) result.add(total(plain,weighted,op[2])-total(plain,weighted,op[1])); // Cancel the prefix outside the requested range.
            else throw new IllegalArgumentException("unknown operation"); // Reject undefined commands.
        }
        return result; // Empty query ranges return zero by exact prefix cancellation.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(4,new int[][]{{0,0,4,2},{0,1,3,3},{1,1,4,0}}))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(4,new int[][]{{0,0,4,2},{0,1,3,3},{1,1,4,0}}), List.of(12L)); // Overlapping updates and a partial sum.
        Check.equal(solve(3,new int[][]{{0,0,3,-2},{1,0,3,0}}), List.of(-6L)); // Negative range totals.
        Check.equal(solve(0,new int[][]{{1,0,0,0}}), List.of(0L)); // Empty represented array.
    }
}
