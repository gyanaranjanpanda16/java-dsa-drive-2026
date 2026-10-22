package questions.q213;

import java.util.*;
import questions.support.Check;

/**
 * Q213: Compare arrays while preserving duplicate multiplicities.
 * Contract: Return whether arrays contain the same integer multiset regardless of order.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: Count the first array and consume one count per second-array value. A missing count proves an unmatched occurrence. Equal lengths plus successful consumption imply no occurrences remain. This compares unordered batches where duplicates matter.
 * Complexity: O(n+m) expected time and O(u) space.
 */
public final class Q213EqualMultisets { // Keep one question and its tests together for independent study.
    public static boolean solve(int[] left,int[] right) { // Compare multiplicities instead of only distinct membership.
        if(left.length!=right.length) return false; // Equal multisets necessarily have equal total counts.
        Map<Integer,Integer> remaining=new HashMap<>(); // Track unmatched occurrences from the left batch.
        for(int value:left) remaining.merge(value,1,Integer::sum); // Every occurrence supplies one future match.
        for(int value:right) { // Consume matches in arbitrary right-array order.
            int count=remaining.getOrDefault(value,0); // Missing entries mean no unused matching occurrence remains.
            if(count==0) return false; // This occurrence has no partner,so equality is impossible.
            if(count==1) remaining.remove(value); else remaining.put(value,count-1); // Remove exhausted counts to preserve the unmatched-occurrence invariant.
        }
        return true; // Equal lengths and no failed match exhaust all supplied occurrences.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve(new int[]{1,2,1},new int[]{2,1,1})); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve(new int[]{1,2,1},new int[]{2,1,1}),true); // Permuting duplicates preserves a multiset.
    Check.equal(solve(new int[]{1,1,2},new int[]{1,2,2}),false); // Equal distinct sets are insufficient when multiplicities differ.
    Check.equal(solve(new int[]{},new int[]{}),true); // Empty multisets match.
    }
}
