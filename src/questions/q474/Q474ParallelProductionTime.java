package questions.q474; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Find the earliest time machines finish a target workload.
 * Contract: Each positive duration is one machine item time; machines run independently from time0. Return the minimum time producing at least target>=0 items. At least one machine is required for positive target, and minDuration*target must fit long.
 * Why: At time t, machine i produces floor(t/duration[i]) items. Total production never decreases with t. Count only until the target is reached to avoid overflow, then binary-search the first sufficient time. Useful for simple parallel capacity planning.
 * Cost: O(n log(minDuration*target+1)) time and O(1) space.
 */
public final class Q474ParallelProductionTime { // Keep the algorithm, demonstration, and boundary checks together.
    private Q474ParallelProductionTime() { } // Only static entry points are needed for this exercise.
    public static long solve(int[] duration,long target) { // Use a deterministic processing-time model without threads or wall-clock waits.
        if(target<0) throw new IllegalArgumentException("negative target"); // Required production cannot be negative.
        int fastest=Integer.MAX_VALUE; for(int time:duration) { if(time<=0) throw new IllegalArgumentException("positive duration required"); fastest=Math.min(fastest,time); } // Validate divisors and find a feasible single-machine bound.
        if(target==0) return 0; if(duration.length==0) throw new IllegalArgumentException("no machines"); // Empty work needs no time, but positive work needs a producer.
        long low=0,high=Math.multiplyExact((long)fastest,target); // The fastest machine alone can meet the target by this time.
        while(low<high) { // Test the earliest feasible integer completion time.
            long middle=low+(high-low)/2,count=0; // Accumulate only as much production as the feasibility question needs.
            for(int time:duration) { long made=middle/time; if(made>=target-count) { count=target; break; } count+=made; } // Saturate at target instead of overflowing a potentially huge total.
            if(count>=target) high=middle; else low=middle+1; // Later time cannot decrease completed items.
        }
        return low; // No smaller time satisfies the production target.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new int[]{2,3},4))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new int[]{2,3},4), 6L); // Machines contribute different floor counts.
        Check.equal(solve(new int[]{},0), 0L); // Zero demand needs no machines.
        Check.equal(solve(new int[]{5},3), 15L); // One machine gives a direct multiple.
    }
}
