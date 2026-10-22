package questions.q416;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q416: Minimum Subset Sum Difference.
 * Contract: Nonnegative integers; total/2 table must fit memory.
 * Why it works: Complementary subset sums s and total-s differ by total-2s when s is the smaller. Maximizing a reachable s not exceeding half minimizes that expression.
 * Cost: O(n*total) time and O(total) space up to constant halves.
 */
public final class Q416MinimumSubsetSumDifference { // Keep this question independently runnable and testable.
    private Q416MinimumSubsetSumDifference() { } // No object state is needed by this standalone exercise.
    public static long minimum(int[] values) { // Expose the operation for an independent caller and the executable checks below.
        long total=0; for(int v:values) { if(v<0) throw new IllegalArgumentException("Nonnegative values required"); total+=v; } // Compute the full sum and enforce monotone subset totals.
        if(total/2>Integer.MAX_VALUE-1) throw new IllegalArgumentException("Table too large"); // Keep the half-sum index representable.
        int half=(int)(total/2); boolean[] reachable=new boolean[half+1]; reachable[0]=true; // A smaller partition never needs a sum beyond half.
        for(int value:values) for(int sum=half;sum>=value;sum--) reachable[sum]|=reachable[sum-value]; // Descending0/1 updates select each position at most once.
        for(int sum=half;sum>=0;sum--) if(reachable[sum]) return total-2L*sum; // The closest achievable half-total minimizes the gap between complements.
        throw new AssertionError("Zero is always reachable"); // The empty subset guarantees the search returns.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(minimum(new int[]{1,6,11,5})); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(minimum(new int[]{})==0, "Q416 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(minimum(new int[]{3})==3, "Q416 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(minimum(new int[]{1,2,3})==0, "Q416 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
