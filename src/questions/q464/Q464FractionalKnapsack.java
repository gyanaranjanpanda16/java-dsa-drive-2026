package questions.q464;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q464: Fractional Knapsack.
 * Contract: Each item is[weight,value]; fractions allowed, positive int weights, nonnegative values.
 * Why it works: With divisible items, exchanging lower-density selected weight for equal higher-density unselected weight cannot decrease value. Filling by descending density therefore reaches an optimum.
 * Cost: O(n log n) time and O(n) copied storage.
 */
public final class Q464FractionalKnapsack { // Keep this question independently runnable and testable.
    private Q464FractionalKnapsack() { } // No object state is needed by this standalone exercise.
    public static double maximum(int[][] items,int capacity) { // Expose the operation for an independent caller and the executable checks below.
        if(capacity<0) throw new IllegalArgumentException("Nonnegative capacity"); // Capacity is remaining weight, not item count.
        int[][] sorted=Arrays.stream(items).map(int[]::clone).toArray(int[][]::new); // Fractions are taken without changing the input records.
        for(int[] item:sorted) if(item.length!=2||item[0]<=0||item[1]<0) throw new IllegalArgumentException("Positive weight and nonnegative value"); // A finite positive denominator defines value density.
        Arrays.sort(sorted,(a,b)->Long.compare((long)b[1]*a[0],(long)a[1]*b[0])); // Cross products compare descending value-per-weight without division rounding.
        double result=0; int room=capacity; // Accumulate full and fractional values.
        for(int[] item:sorted) { int take=Math.min(room,item[0]); result+=(double)take*item[1]/item[0]; room-=take; if(room==0) break; } // Each unit of remaining capacity buys the highest available marginal value.
        return result; // Fractional values follow ordinary double rounding.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(maximum(new int[][]{{10,60},{20,100},{30,120}},50)); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(maximum(new int[][]{},3)==0, "Q464 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(maximum(new int[][]{{2,6}},1)==3, "Q464 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(Math.abs(maximum(new int[][]{{10,60},{20,100},{30,120}},50)-240)<1e-9, "Q464 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
