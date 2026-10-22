package questions.q449;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q449: Frog Jump Across Stones.
 * Contract: Strictly increasing nonnegative int stone positions beginning at0; jump lengths remain positive.
 * Why it works: Position alone is insufficient because allowed next moves depend on the previous jump length. Recording both values makes every legal continuation explicit and avoids losing a useful arrival state.
 * Cost: O(n²) possible states and expected O(n²) time/space for n stones.
 */
public final class Q449FrogJumpAcrossStones { // Keep this question independently runnable and testable.
    private Q449FrogJumpAcrossStones() { } // No object state is needed by this standalone exercise.
    public static boolean possible(int[] stones) { // Expose the operation for an independent caller and the executable checks below.
        if(stones.length==0||stones[0]!=0) return false; // The frog starts at position zero and requires a starting stone.
        Map<Integer,Set<Integer>> jumps=new HashMap<>(); // Each stone records jump lengths that can arrive there.
        for(int i=0;i<stones.length;i++) { if(i>0&&stones[i]<=stones[i-1]) throw new IllegalArgumentException("Strictly increasing stones"); jumps.put(stones[i],new HashSet<>()); } // Distinct increasing positions allow forward processing.
        jumps.get(0).add(0); // A previous jump of zero makes the first positive jump exactly one.
        for(int stone:stones) for(int previous:jumps.get(stone)) { // Process all reachable states at this position.
            for(int step=Math.max(1,previous-1);step<=previous+1;step++) { long next=(long)stone+step; if(next<=Integer.MAX_VALUE&&jumps.containsKey((int)next)) jumps.get((int)next).add(step); } // The next positive jump may change length by at most one.
        }
        return !jumps.get(stones[stones.length-1]).isEmpty(); // Reachability of any jump state at the last stone is sufficient.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(possible(new int[]{0,1,3,5,6,8,12,17})); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(possible(new int[]{0}), "Q449 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(!possible(new int[]{0,2}), "Q449 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(!possible(new int[]{0,1,2,3,4,8,9,11}), "Q449 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
