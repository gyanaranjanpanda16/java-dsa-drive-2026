package questions.q457;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q457: Gas Station Circuit.
 * Contract: Nonnegative fuel and costs around a circle; unlimited tank; return one valid start or-1.
 * Why it works: If a segment from a candidate has negative total, none of its later starts before that failure can succeed because their preceding candidate-prefix balance was nonnegative. Discarding the whole segment preserves every possible solution.
 * Cost: O(n) time and O(1) space.
 */
public final class Q457GasStationCircuit { // Keep this question independently runnable and testable.
    private Q457GasStationCircuit() { } // No object state is needed by this standalone exercise.
    public static int start(int[] gas,int[] cost) { // Expose the operation for an independent caller and the executable checks below.
        if(gas.length!=cost.length) throw new IllegalArgumentException("Matching arrays"); // Each station needs both supplied fuel and outgoing cost.
        if(gas.length==0) return -1; // No station can serve as a starting point.
        long total=0,tank=0; int candidate=0; // Track global feasibility separately from the current trial segment.
        for(int i=0;i<gas.length;i++) { // Examine the net fuel gained at each station once.
            long gain=(long)gas[i]-cost[i]; total+=gain; tank+=gain; // Wide arithmetic preserves accumulated deficits.
            if(tank<0) { candidate=i+1; tank=0; } // Every start inside the failed segment also fails before or at this boundary.
        }
        return total<0?-1:candidate%gas.length; // Nonnegative total guarantees the surviving candidate can wrap around successfully.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(start(new int[]{1,2,3,4,5},new int[]{3,4,5,1,2})); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(start(new int[]{},new int[]{})==-1, "Q457 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(start(new int[]{2},new int[]{3})==-1, "Q457 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(start(new int[]{3},new int[]{2})==0, "Q457 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
