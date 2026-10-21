package questions.q394;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q394: Combination Sum Single Use.
 * Contract: Positive integer inputs may contain duplicates; each index can be selected once; target small enough to enumerate.
 * Why it works: Advancing to i+1 enforces single use. Skipping equal sibling choices removes duplicates while still permitting equal values from different depths when available.
 * Cost: O(n*2^n) upper-bound output time, O(n) stack/path plus output.
 */
public final class Q394CombinationSumSingleUse { // Keep this question independently runnable and testable.
    private Q394CombinationSumSingleUse() { } // No object state is needed by this standalone exercise.
    public static List<List<Integer>> combinations(int[] input,int target) { // Expose the operation for an independent caller and the executable checks below.
        int[] a=input.clone(); Arrays.sort(a); // Equal values become adjacent for duplicate suppression.
        for(int v:a) if(v<=0) throw new IllegalArgumentException("Positive values required"); // Positive values make remaining-sum pruning sound.
        List<List<Integer>> out=new ArrayList<>(); // Keep one copy of each value combination.
        if(target>=0) choose(a,0,target,new ArrayList<>(),out); // A negative target is unreachable using positive values.
        return out; // Caller input remains unchanged.
    }
    private static void choose(int[] a,int start,int rest,List<Integer> path,List<List<Integer>> out) { // Keep recursive state or a repeated sub-operation inside this solution.
        if(rest==0) { out.add(new ArrayList<>(path)); return; } // Snapshot a completed exact-sum selection.
        for(int i=start;i<a.length&&a[i]<=rest;i++) { // Select among positions not used by the prefix.
            if(i>start&&a[i]==a[i-1]) continue; // Equal alternatives at the same depth would duplicate a value sequence.
            path.add(a[i]); choose(a,i+1,rest-a[i],path,out); path.remove(path.size()-1); // Advance past i so each position is used at most once.
        }
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(combinations(new int[]{1,1,2,5},3)); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(combinations(new int[]{},0).equals(List.of(List.of())), "Q394 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(combinations(new int[]{2},4).isEmpty(), "Q394 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(combinations(new int[]{1,1,2},3).equals(List.of(List.of(1,2))), "Q394 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
