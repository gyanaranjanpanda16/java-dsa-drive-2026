package questions.q402;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q402: Minimum Cost Climbing Stairs.
 * Contract: Nonnegative step costs; start at step0 or step1 and pay on departure.
 * Why it works: To reach a position, the previous jump must come from one of the preceding two steps. Taking the cheaper total among these exhaustive choices gives the optimal substructure.
 * Cost: O(n) time and O(1) space.
 */
public final class Q402MinimumCostClimbingStairs { // Keep this question independently runnable and testable.
    private Q402MinimumCostClimbingStairs() { } // No object state is needed by this standalone exercise.
    public static long minimum(int[] cost) { // Expose the operation for an independent caller and the executable checks below.
        long twoBack=0,oneBack=0; // Starting just before step zero or one costs nothing.
        for(int top=2;top<=cost.length;top++) { // Solve the minimum cost to reach each successive position.
            long next=Math.min(oneBack+cost[top-1],twoBack+cost[top-2]); // The last paid step is either one or two positions behind.
            twoBack=oneBack; oneBack=next; // Drop states no longer needed by a future transition.
        }
        return oneBack; // Position cost.length is the top, beyond the final paid step.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(minimum(new int[]{10,15,20})); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(minimum(new int[]{})==0, "Q402 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(minimum(new int[]{5})==0, "Q402 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(minimum(new int[]{1,100,1,1,1,100,1,1,100,1})==6, "Q402 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
