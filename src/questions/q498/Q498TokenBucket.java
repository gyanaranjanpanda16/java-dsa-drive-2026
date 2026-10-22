package questions.q498; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Limit arrivals with a periodically refilled token bucket.
 * Contract: Start with capacity>=0 tokens at time0. Add one token at every positive multiple of period>0, capped at capacity. Nonnegative arrival times are nondecreasing; each admitted request consumes one token. Return admission booleans.
 * Why: Track both tokens and the last accounted refill tick. Whole elapsed periods add tokens; advancing only by complete periods preserves fractional waiting time. The cap allows bounded bursts while limiting sustained admissions. This models a single-threaded periodic token bucket, not a distributed rate limiter.
 * Cost: O(number of arrivals) time and O(1) working space plus output.
 */
public final class Q498TokenBucket { // Keep the algorithm, demonstration, and boundary checks together.
    private Q498TokenBucket() { } // Only static entry points are needed for this exercise.
    public static List<Boolean> solve(int capacity,int period,int[] arrivals) { // Use discrete logical ticks with no clock or concurrency dependence.
        if(capacity<0||period<=0) throw new IllegalArgumentException("invalid capacity or period"); // Capacity zero is valid, but refill periods must be positive.
        long tokens=capacity,lastRefill=0,previous=-1; List<Boolean> result=new ArrayList<>(); // Start full to permit the configured initial burst.
        for(int time:arrivals) { // Process requests in their chronological order, including simultaneous arrivals.
            if(time<0||time<previous) throw new IllegalArgumentException("nondecreasing times required"); previous=time; // Reject backwards movement of the logical clock.
            long added=(time-lastRefill)/period; tokens=Math.min(capacity,tokens+added); lastRefill+=added*period; // Keep the partial period rather than resetting refill time to each arrival.
            boolean admitted=tokens>0; if(admitted) tokens--; // A request consumes exactly one available token or is rejected.
            result.add(admitted); // Rejected requests do not create debt or postpone future refill ticks.
        }
        return result; // Idle periods cannot accumulate more than capacity tokens.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(2,5,new int[]{0,0,0,4,5,10}))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(2,5,new int[]{0,0,0,4,5,10}), List.of(true,true,false,false,true,true)); // Burst allowance followed by periodic refill.
        Check.equal(solve(1,3,new int[]{2,3,3}), List.of(true,true,false)); // Preserve the unfinished period before the tick at3.
        Check.equal(solve(0,1,new int[]{0,10}), List.of(false,false)); // Zero capacity never admits a request.
    }
}
