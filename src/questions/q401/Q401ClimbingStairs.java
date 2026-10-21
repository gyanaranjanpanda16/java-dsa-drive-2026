package questions.q401;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q401: Climbing Stairs.
 * Contract: Count ways to climb n steps using moves of one or two; 0<=n<=91.
 * Why it works: Paths ending with one step correspond to paths to n-1; paths ending with two correspond to n-2. These disjoint cases cover every valid path.
 * Cost: O(n) time and O(1) working space.
 */
public final class Q401ClimbingStairs { // Keep this question independently runnable and testable.
    private Q401ClimbingStairs() { } // No object state is needed by this standalone exercise.
    public static long ways(int n) { // Expose the operation for an independent caller and the executable checks below.
        if(n<0||n>91) throw new IllegalArgumentException("Use 0..91 steps"); // Bound the Fibonacci-like count to long.
        long before=1,current=1; // Empty stairs and one stair each have one valid construction.
        for(int step=2;step<=n;step++) { // Build answers only after both smaller prerequisites are known.
            long next=before+current; // Every path ends with either a one-step or a two-step move.
            before=current; current=next; // Retain the two consecutive counts required by the recurrence.
        }
        return current; // The maintained state is the count for the requested height.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(ways(5)); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(ways(0)==1, "Q401 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(ways(2)==2, "Q401 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(ways(5)==8, "Q401 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
