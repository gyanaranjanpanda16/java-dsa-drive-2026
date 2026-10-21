package questions.q404;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q404: Circular House Robber.
 * Contract: Integer house values; selecting no house is allowed.
 * Why it works: The first and last houses are adjacent. Removing either endpoint turns the circle into a line, and every valid circular selection appears in at least one of those two cases.
 * Cost: O(n) time and O(1) working space.
 */
public final class Q404CircularHouseRobber { // Keep this question independently runnable and testable.
    private Q404CircularHouseRobber() { } // No object state is needed by this standalone exercise.
    public static long maximum(int[] money) { // Expose the operation for an independent caller and the executable checks below.
        if(money.length==0) return 0; // An empty circle has no value to collect.
        if(money.length==1) return Math.max(0,money[0]); // The single house must not be excluded by both linear ranges.
        return Math.max(line(money,0,money.length-1),line(money,1,money.length)); // Every valid solution excludes the first or the last adjacent house.
    }
    private static long line(int[] a,int start,int end) { // Keep recursive state or a repeated sub-operation inside this solution.
        long older=0,recent=0; // Solve one half-open linear range with the ordinary nonadjacency state.
        for(int i=start;i<end;i++) { // End is excluded so the selected circular endpoint is omitted.
            long next=Math.max(recent,older+a[i]); // Choose between skipping and taking this house.
            older=recent; recent=next; // Advance the two prefix states.
        }
        return recent; // This range contains no circular edge conflict.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(maximum(new int[]{2,3,2})); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(maximum(new int[]{})==0, "Q404 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(maximum(new int[]{5})==5, "Q404 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(maximum(new int[]{1,2,3,1})==4, "Q404 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
