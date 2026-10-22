package questions.q453;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q453: Assign Cookies.
 * Contract: Nonnegative greed requirements and cookie sizes; one cookie per child.
 * Why it works: Giving the smallest adequate cookie to the least-demanding unsatisfied child cannot reduce the number of future matches. Any assignment using a larger cookie can exchange it with this smaller one.
 * Cost: O(n log n + m log m) time and O(n+m) copied space.
 */
public final class Q453AssignCookies { // Keep this question independently runnable and testable.
    private Q453AssignCookies() { } // No object state is needed by this standalone exercise.
    public static int satisfied(int[] greed,int[] cookies) { // Expose the operation for an independent caller and the executable checks below.
        int[] need=greed.clone(),size=cookies.clone(); Arrays.sort(need); Arrays.sort(size); // Compare children and cookies from smallest requirements upward.
        int child=0; // This is the least-demanding child not yet satisfied.
        for(int cookie:size) { // Never consume a larger cookie before a smaller usable one.
            if(child<need.length&&cookie>=need[child]) child++; // Assign the smallest adequate cookie, preserving larger ones for harder children.
        }
        return child; // Each increment corresponds to one distinct child receiving one cookie.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(satisfied(new int[]{1,2,3},new int[]{1,1})); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(satisfied(new int[]{},new int[]{1})==0, "Q453 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(satisfied(new int[]{1},new int[]{})==0, "Q453 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(satisfied(new int[]{1,2},new int[]{1,2})==2, "Q453 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
