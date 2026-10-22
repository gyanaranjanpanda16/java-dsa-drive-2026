package questions.q365; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Locate a cumulative rank in changing frequencies.
 * Contract: Initial frequencies are nonnegative ints. Operations[0,index,delta] adjust a frequency without making it negative;[1,rank,0] ask for the zero-based bucket containing a one-based rank, returning-1 outside1..total. Counts fit long.
 * Why: Nonnegative frequencies make cumulative totals monotone. Binary lifting skips a Fenwick block when its count is still below the desired rank, subtracting skipped counts. This finds a weighted order statistic in one logarithmic traversal, useful for ranked event frequencies.
 * Cost: O((n+q) log(n+1)) time and O(n) space.
 */
public final class Q365FenwickOrderStatistic { // Keep the algorithm, demonstration, and boundary checks together.
    private Q365FenwickOrderStatistic() { } // Only static entry points are needed for this exercise.
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
    public static List<Integer> solve(int[] initial,int[][] operations) { // Interpret counts as repeated ordered bucket occurrences.
        int n=initial.length; long[] counts=new long[n]; Fenwick bit=new Fenwick(n); // Retain raw frequencies to validate decreases.
        for(int i=0;i<n;i++) { if(initial[i]<0) throw new IllegalArgumentException("negative frequency"); counts[i]=initial[i]; bit.add(i,initial[i]); } // Build a monotone cumulative distribution.
        List<Integer> result=new ArrayList<>(); // One output per rank query.
        for(int[] op:operations) { // Point changes preserve nonnegative frequencies.
            if(op[0]==0) { // Change one bucket's multiplicity by a signed amount.
                long updated=counts[op[1]]+op[2]; if(updated<0) throw new IllegalArgumentException("negative frequency"); // Negative counts would destroy monotonicity.
                counts[op[1]]=updated; bit.add(op[1],op[2]); // Synchronize raw counts and cumulative blocks.
            } else if(op[0]==1) { // Find the first cumulative total reaching the requested rank.
                long rank=op[1]; if(rank<=0 || rank>bit.prefix(n)) { result.add(-1); continue; } // Out-of-range ranks have no bucket.
                int skipped=0; // This prefix length has cumulative total strictly below the residual target.
                for(int step=Integer.highestOneBit(n);step!=0;step>>=1) { // Try powers of two from largest to smallest.
                    int next=skipped+step; if(next<=n && bit.tree[next]<rank) { rank-=bit.tree[next]; skipped=next; } // Skip an entire insufficient Fenwick block.
                }
                result.add(skipped); // The next one-based position is skipped+1, hence zero-based index skipped.
            } else throw new IllegalArgumentException("unknown operation"); // Restrict commands to frequency updates and rank queries.
        }
        return result; // Zero-count buckets are automatically skipped by cumulative search.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new int[]{2,0,3},new int[][]{{1,3,0},{0,0,-2},{1,1,0}}))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new int[]{2,0,3},new int[][]{{1,3,0},{0,0,-2},{1,1,0}}), List.of(2,2)); // Zero-count buckets and a frequency decrease.
        Check.equal(solve(new int[]{1},new int[][]{{1,0,0},{1,2,0}}), List.of(-1,-1)); // Both lower and upper invalid ranks.
        Check.equal(solve(new int[]{},new int[][]{{1,1,0}}), List.of(-1)); // An empty distribution.
    }
}
