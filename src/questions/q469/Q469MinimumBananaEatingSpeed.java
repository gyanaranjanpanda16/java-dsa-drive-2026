package questions.q469;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q469: Minimum Banana Eating Speed.
 * Contract: One pile per hour, at most speed bananas from that pile; nonnegative pile sizes and hour budget.
 * Why it works: The total rounded-up processing time is nonincreasing with speed. Binary search therefore finds the first speed meeting the hour budget between a known lower bound and a feasible upper bound.
 * Cost: O(n log maximumPile) time and O(1) space.
 */
public final class Q469MinimumBananaEatingSpeed { // Keep this question independently runnable and testable.
    private Q469MinimumBananaEatingSpeed() { } // No object state is needed by this standalone exercise.
    public static int minimum(int[] piles,long hours) { // Expose the operation for an independent caller and the executable checks below.
        if(hours<0) throw new IllegalArgumentException("Nonnegative hour budget"); // A negative processing budget is impossible.
        int maximum=0,nonempty=0; // Each nonempty pile consumes at least one separate hour.
        for(int pile:piles) { if(pile<0) throw new IllegalArgumentException("Nonnegative piles"); maximum=Math.max(maximum,pile); if(pile>0) nonempty++; } // Establish necessary feasibility and the speed upper bound.
        if(maximum==0) return 0; if(hours<nonempty) return -1; // Empty work needs no speed; too few hours cannot be repaired by faster eating.
        int low=1,high=maximum; // The maximum pile size is a known feasible speed under the necessary hour bound.
        while(low<high) { // Keep the minimum feasible speed inside the interval.
            int speed=low+(high-low)/2; long needed=0; // Test a midpoint without overflowing its addition.
            for(int pile:piles) needed+=(pile+speed-1L)/speed; // Each pile rounds up because leftovers occupy another hour.
            if(needed<=hours) high=speed; else low=speed+1; // Higher speeds never require more hours, enabling monotonic elimination.
        }
        return low; // The first feasible integer speed remains.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(minimum(new int[]{3,6,7,11},8)); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(minimum(new int[]{},0)==0, "Q469 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(minimum(new int[]{1,1},1)==-1, "Q469 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(minimum(new int[]{30,11,23,4,20},5)==30, "Q469 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
