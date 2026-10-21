package questions.q393;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q393: Combination Sum Unlimited.
 * Contract: Distinct positive candidates; nonnegative small target for enumeration.
 * Why it works: Choosing nondecreasing candidate indices removes ordering duplicates. Positive candidates strictly decrease the remaining sum, so recursion terminates.
 * Cost: Output-sensitive exponential search; depth at most target/minCandidate plus sorting O(n log n).
 */
public final class Q393CombinationSumUnlimited { // Keep this question independently runnable and testable.
    private Q393CombinationSumUnlimited() { } // No object state is needed by this standalone exercise.
    public static List<List<Integer>> combinations(int[] candidates,int target) { // Expose the operation for an independent caller and the executable checks below.
        int[] a=candidates.clone(); Arrays.sort(a); // Increasing order gives canonical combinations and early stopping.
        for(int i=0;i<a.length;i++) if(a[i]<=0 || (i>0&&a[i]==a[i-1])) throw new IllegalArgumentException("Distinct positive candidates"); // Guarantee progress and avoid duplicate answers.
        List<List<Integer>> out=new ArrayList<>(); // Each result is one nondecreasing combination.
        if(target>=0) choose(a,0,target,new ArrayList<>(),out); // Negative targets have no positive-coin representation.
        return out; // Return every combination rather than every ordering.
    }
    private static void choose(int[] a,int start,int rest,List<Integer> path,List<List<Integer>> out) { // Keep recursive state or a repeated sub-operation inside this solution.
        if(rest==0) { out.add(new ArrayList<>(path)); return; } // Copy a complete exact-sum combination.
        for(int i=start;i<a.length&&a[i]<=rest;i++) { // Later candidates are no smaller, enabling pruning.
            path.add(a[i]); choose(a,i,rest-a[i],path,out); path.remove(path.size()-1); // Reuse i, recurse on a smaller sum, then restore.
        }
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(combinations(new int[]{2,3,6,7},7)); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(combinations(new int[]{2},0).equals(List.of(List.of())), "Q393 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(combinations(new int[]{2},3).isEmpty(), "Q393 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(combinations(new int[]{2,3,6,7},7).size()==2, "Q393 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
