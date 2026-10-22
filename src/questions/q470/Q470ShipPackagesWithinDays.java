package questions.q470;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q470: Ship Packages Within Days.
 * Contract: Nonnegative ordered package weights; preserve order, no package splitting; use at most days.
 * Why it works: For a fixed capacity, filling each day until the next package would exceed it minimizes the number of ordered days. Feasibility is monotone in capacity, so binary search returns the smallest sufficient one.
 * Cost: O(n log(totalWeight+1)) time and O(1) space.
 */
public final class Q470ShipPackagesWithinDays { // Keep this question independently runnable and testable.
    private Q470ShipPackagesWithinDays() { } // No object state is needed by this standalone exercise.
    public static long capacity(int[] weights,int days) { // Expose the operation for an independent caller and the executable checks below.
        if(days<1) throw new IllegalArgumentException("Positive day budget"); // At least one loading day is available.
        long low=0,high=0; for(int w:weights) { if(w<0) throw new IllegalArgumentException("Nonnegative weights"); low=Math.max(low,w); high+=w; } // Capacity must hold the heaviest package and never needs to exceed the total.
        while(low<high) { // Search the first capacity supporting the ordered schedule.
            long middle=low+(high-low)/2,load=0; int used=1; // Greedily fill each day as much as possible without changing package order.
            for(int w:weights) { if(load+w>middle) { used++; load=0; } load+=w; } // Starting a new day only when necessary minimizes days for this capacity.
            if(used<=days) high=middle; else low=middle+1; // More capacity cannot increase the minimum number of days.
        }
        return low; // Empty work has low=high=0 and returns zero.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(capacity(new int[]{1,2,3,4,5,6,7,8,9,10},5)); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(capacity(new int[]{},2)==0, "Q470 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(capacity(new int[]{3,2,2,4,1,4},3)==6, "Q470 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(capacity(new int[]{5},1)==5, "Q470 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
