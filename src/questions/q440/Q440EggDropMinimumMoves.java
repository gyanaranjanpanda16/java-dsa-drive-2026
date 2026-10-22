package questions.q440;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q440: Egg Drop Minimum Moves.
 * Contract: Find a threshold floor in the worst case; eggs may be reused only if they survive.
 * Why it works: A tested floor splits into a break branch using one fewer egg and a survive branch retaining all eggs. Their covered intervals plus the tested floor give the maximum range resolvable in one more move.
 * Cost: O(eggs*answer) time and O(eggs) space; dimensions must fit available memory.
 */
public final class Q440EggDropMinimumMoves { // Keep this question independently runnable and testable.
    private Q440EggDropMinimumMoves() { } // No object state is needed by this standalone exercise.
    public static int moves(int eggs,int floors) { // Expose the operation for an independent caller and the executable checks below.
        if(eggs<1||floors<0) throw new IllegalArgumentException("Positive eggs and nonnegative floors"); // At least one egg is needed for a positive-height experiment.
        long[] covered=new long[eggs+1]; int moves=0; // covered[e] is the number of floors distinguishable with the current move budget.
        while(covered[eggs]<floors) { // Increase the budget until it covers the requested search range.
            moves++; // One additional experiment expands the distinguishable floor interval.
            for(int e=eggs;e>=1;e--) covered[e]=Math.min((long)floors,covered[e]+covered[e-1]+1); // Break covers below, survival covers above, plus the tested floor; cap prevents growth beyond need.
        }
        return moves; // This is the first move budget sufficient for all floors.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(moves(2,100)); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(moves(1,5)==5, "Q440 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(moves(3,0)==0, "Q440 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(moves(2,6)==3, "Q440 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
