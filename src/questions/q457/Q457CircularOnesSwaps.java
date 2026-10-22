package questions.q457; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Minimum swaps to group all ones in a circular array.
 * Contract: For a binary array, return the minimum number of swaps between any two positions needed to make all ones consecutive on a circle. Swaps need not be adjacent. Empty arrays and arrays with no ones require zero swaps.
 * Why: If there are w ones, the final occupied block must be a circular window of length w. A window with z zeros needs exactly z swaps: pair each inside zero with an outside one. Evaluate all starts with a sliding count rather than recounting every window. This models grouping selected circular slots with unrestricted exchanges; adjacent swaps are a different distance-based problem.
 * Cost: O(n) time and O(1) extra space. Circular indexing avoids allocating a doubled array.
 */
public final class Q457CircularOnesSwaps { // Keep the algorithm, demonstration, and boundary checks together.
    private Q457CircularOnesSwaps() { } // Only static entry points are needed for this exercise.
    public static int solve(int[] values) { // Count swaps of arbitrary positions, not neighboring moves.
        int ones=0; for(int value:values) { if(value!=0&&value!=1) throw new IllegalArgumentException("binary values required"); ones+=value; } // The number of ones fixes every candidate window's length.
        if(ones==0) return 0; // Also handles an empty input without modulo by zero.
        int inside=0; for(int i=0;i<ones;i++) inside+=values[i]; // Count ones in the window beginning at zero.
        int best=inside,n=values.length; // Maximizing retained ones minimizes required swaps.
        for(int start=1;start<n;start++) { // Every circular block has one of these n starting positions.
            inside-=values[start-1]; // Remove the old left endpoint.
            inside+=values[(int)(((long)start+ones-1)%n)]; // Add the new endpoint, wrapping without int addition overflow.
            best=Math.max(best,inside); // Retain the most populated block seen so far.
        }
        return ones-best; // Each missing inside one can be supplied by one outside one.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new int[]{0,1,0,1,1,0,0}))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new int[]{0,1,0,1,1,0,0}), 1); // Best block still needs one exchange.
        Check.equal(solve(new int[]{1,0,0,1}), 0); // An optimal block wraps around.
        Check.equal(solve(new int[]{0,0}), 0); // No ones to group.
        Check.equal(solve(new int[]{}), 0); // Empty array.
    }
}
