package questions.q395;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q395: Recursive Subset Sum Decision.
 * Contract: Input non-null; all residual arithmetic must fit long; empty subset is allowed.
 * Why it works: The include/exclude partition considers every subset, including those using negative values. No positivity-based pruning is used, so negative inputs remain valid.
 * Cost: O(2^n) time and O(n) stack; use small arrays.
 */
public final class Q395RecursiveSubsetSumDecision { // Keep this question independently runnable and testable.
    private Q395RecursiveSubsetSumDecision() { } // No object state is needed by this standalone exercise.
    public static boolean canMake(int[] a,long target) { // Expose the operation for an independent caller and the executable checks below.
        return choose(a,0,target); // Ask whether some subset of the entire input reaches target.
    }
    private static boolean choose(int[] a,int i,long rest) { // Keep recursive state or a repeated sub-operation inside this solution.
        if(i==a.length) return rest==0; // After all decisions, only an exact residual zero succeeds.
        return choose(a,i+1,rest)||choose(a,i+1,rest-a[i]); // Every subset excludes or includes this position.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(canMake(new int[]{3,-2,7},5)); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(canMake(new int[]{},0), "Q395 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(!canMake(new int[]{},1), "Q395 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(canMake(new int[]{3,-2,7},5), "Q395 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
