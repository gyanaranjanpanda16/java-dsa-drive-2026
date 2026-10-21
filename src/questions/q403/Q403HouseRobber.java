package questions.q403;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q403: House Robber.
 * Contract: Return the largest sum of nonadjacent values; choosing no values is permitted.
 * Why it works: An optimal solution either excludes the final house or includes it and excludes its predecessor. The recurrence compares exactly those possibilities.
 * Cost: O(n) time and O(1) space.
 */
public final class Q403HouseRobber { // Keep this question independently runnable and testable.
    private Q403HouseRobber() { } // No object state is needed by this standalone exercise.
    public static long maximum(int[] money) { // Expose the operation for an independent caller and the executable checks below.
        long twoBack=0,oneBack=0; // Choosing no houses is allowed and yields zero.
        for(int value:money) { // Consider whether to include the current house in the chosen prefix.
            long next=Math.max(oneBack,twoBack+value); // Skip this house or take it after the best nonadjacent prefix.
            twoBack=oneBack; oneBack=next; // Preserve the two states required for the following house.
        }
        return oneBack; // Every prefix choice obeys the no-adjacent-houses constraint.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(maximum(new int[]{2,7,9,3,1})); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(maximum(new int[]{})==0, "Q403 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(maximum(new int[]{-2,-1})==0, "Q403 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(maximum(new int[]{2,7,9,3,1})==12, "Q403 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
