package questions.q463;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q463: Maximum Units On A Truck.
 * Contract: Each type is[available boxes, units per box]; box-count capacity is nonnegative.
 * Why it works: All boxes consume equal capacity, so swapping any selected lower-unit box for a higher-unit available box improves or preserves value. Choosing in descending unit order exhausts these improvements.
 * Cost: O(t log t) time and O(t) copied storage for t types.
 */
public final class Q463MaximumUnitsOnATruck { // Keep this question independently runnable and testable.
    private Q463MaximumUnitsOnATruck() { } // No object state is needed by this standalone exercise.
    public static long maximum(int[][] types,int capacity) { // Expose the operation for an independent caller and the executable checks below.
        if(capacity<0) throw new IllegalArgumentException("Nonnegative capacity"); // Capacity counts boxes, not their unit totals.
        int[][] sorted=Arrays.stream(types).map(int[]::clone).toArray(int[][]::new); // Keep caller ordering unchanged.
        for(int[] t:sorted) if(t.length!=2||t[0]<0||t[1]<0) throw new IllegalArgumentException("Nonnegative box count and units"); // Each type supplies identical boxes.
        Arrays.sort(sorted,(a,b)->Integer.compare(b[1],a[1])); // A box with more units dominates a lower-unit box when every box uses one slot.
        long total=0; int room=capacity; // Track accumulated units and unfilled box slots.
        for(int[] type:sorted) { int take=Math.min(room,type[0]); total+=(long)take*type[1]; room-=take; if(room==0) break; } // Fill available slots from highest unit value downward.
        return total; // No chosen lower-value box can be exchanged for an available better one.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(maximum(new int[][]{{1,3},{2,2},{3,1}},4)); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(maximum(new int[][]{},5)==0, "Q463 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(maximum(new int[][]{{5,10}},0)==0, "Q463 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(maximum(new int[][]{{2,7}},3)==14, "Q463 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
