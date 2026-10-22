package questions.q439;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q439: Burst Balloons.
 * Contract: Nonnegative balloon values; virtual endpoint values are one.
 * Why it works: Choosing the first burst creates changing neighbors, but choosing the last fixes them. The remaining balloons separate into independent intervals, yielding a valid interval recurrence.
 * Cost: O(n³) time and O(n²) space; intermediate reward overflow throws.
 */
public final class Q439BurstBalloons { // Keep this question independently runnable and testable.
    private Q439BurstBalloons() { } // No object state is needed by this standalone exercise.
    public static long maximum(int[] values) { // Expose the operation for an independent caller and the executable checks below.
        int n=values.length; long[] a=new long[n+2]; a[0]=a[n+1]=1; // Fixed virtual neighbors make boundary balloon rewards uniform.
        for(int i=0;i<n;i++) { if(values[i]<0) throw new IllegalArgumentException("Nonnegative values required"); a[i+1]=values[i]; } // Preserve input and make nonnegative initial DP values valid.
        long[][] dp=new long[n+2][n+2]; // dp[left][right] concerns balloons strictly between fixed boundaries.
        for(int gap=2;gap<n+2;gap++) for(int left=0;left+gap<n+2;left++) { // Solve smaller open intervals before larger ones.
            int right=left+gap; // The two boundary balloons are not burst inside this subproblem.
            for(int last=left+1;last<right;last++) { // Choosing the last balloon leaves fixed neighbors left and right.
                long reward=Math.multiplyExact(Math.multiplyExact(a[left],a[last]),a[right]); // Compute exactly the reward earned by the final burst.
                long candidate=Math.addExact(Math.addExact(dp[left][last],dp[last][right]),reward); // Earlier bursts lie in independent left and right intervals.
                dp[left][right]=Math.max(dp[left][right],candidate); // Keep the most rewarding last-balloon choice.
            }
        }
        return dp[0][n+1]; // The outer virtual boundaries enclose every original balloon.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(maximum(new int[]{3,1,5,8})); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(maximum(new int[]{})==0, "Q439 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(maximum(new int[]{5})==5, "Q439 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(maximum(new int[]{1,5})==10, "Q439 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
