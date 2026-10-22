package questions.q458;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q458: Minimum Candy Allocation.
 * Contract: Higher-rated adjacent children need strictly more candy; equal ratings impose no extra relation.
 * Why it works: The left-to-right pass gives the minimum required by ascending runs from the left; the reverse pass gives the analogous right requirement. Each child must meet both, so their maximum is the minimum feasible assignment.
 * Cost: O(n) time and O(n) space.
 */
public final class Q458MinimumCandyAllocation { // Keep this question independently runnable and testable.
    private Q458MinimumCandyAllocation() { } // No object state is needed by this standalone exercise.
    public static long minimum(int[] ratings) { // Expose the operation for an independent caller and the executable checks below.
        int[] candies=new int[ratings.length]; Arrays.fill(candies,1); // Every child must receive at least one candy.
        for(int i=1;i<ratings.length;i++) if(ratings[i]>ratings[i-1]) candies[i]=candies[i-1]+1; // Satisfy constraints imposed by a lower-rated left neighbor.
        for(int i=ratings.length-2;i>=0;i--) if(ratings[i]>ratings[i+1]) candies[i]=Math.max(candies[i],candies[i+1]+1); // Enforce right-neighbor constraints without undoing the left constraints.
        long total=0; for(int candy:candies) total+=candy; // Sum the smallest simultaneous per-child lower bounds.
        return total; // Taking the maximum of the two directional requirements is necessary and sufficient.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(minimum(new int[]{1,0,2})); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(minimum(new int[]{})==0, "Q458 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(minimum(new int[]{1,2,2})==4, "Q458 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(minimum(new int[]{3})==1, "Q458 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
