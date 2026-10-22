package questions.q455;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q455: Jump Game Reachability.
 * Contract: Nonnegative maximum forward jump lengths; start at0 and ask whether the final index is reachable.
 * Why it works: Reachable positions form a prefix because each jump can use any distance up to its maximum. Tracking only the farthest frontier is therefore sufficient; a gap before the next index is impossible to cross.
 * Cost: O(n) time and O(1) space.
 */
public final class Q455JumpGameReachability { // Keep this question independently runnable and testable.
    private Q455JumpGameReachability() { } // No object state is needed by this standalone exercise.
    public static boolean reachable(int[] jumps) { // Expose the operation for an independent caller and the executable checks below.
        if(jumps.length==0) return false; // There is no starting position in empty input.
        long farthest=0; // The initial position is reachable without a jump.
        for(int i=0;i<jumps.length&&i<=farthest;i++) { // Only positions inside the reachable prefix can extend it.
            if(jumps[i]<0) throw new IllegalArgumentException("Nonnegative jump lengths"); // Reachability is defined by forward jumps of bounded length.
            farthest=Math.max(farthest,(long)i+jumps[i]); // Any permitted shorter jump fills the interval up to this endpoint.
            if(farthest>=jumps.length-1) return true; // The destination belongs to the reachable prefix.
        }
        return false; // The next unprocessed index lies beyond every reachable jump.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(reachable(new int[]{2,3,1,1,4})); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(reachable(new int[]{0}), "Q455 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(!reachable(new int[]{3,2,1,0,4}), "Q455 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(!reachable(new int[]{}), "Q455 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
