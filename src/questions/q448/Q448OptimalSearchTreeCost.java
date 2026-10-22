package questions.q448;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q448: Optimal Search Tree Cost.
 * Contract: Frequencies for sorted distinct keys; successful searches only; root depth is one comparison.
 * Why it works: Choosing a root divides sorted keys into independent left and right intervals. All searches in the interval pay one root comparison, so add the interval frequency total to optimal child costs.
 * Cost: O(n³) time and O(n²) space; aggregate weighted costs must fit long.
 */
public final class Q448OptimalSearchTreeCost { // Keep this question independently runnable and testable.
    private Q448OptimalSearchTreeCost() { } // No object state is needed by this standalone exercise.
    public static long minimum(int[] frequencies) { // Expose the operation for an independent caller and the executable checks below.
        int n=frequencies.length; if(n==0) return 0; // An empty search tree has zero weighted comparisons.
        long[] prefix=new long[n+1]; // Prefix frequencies provide each interval total in constant time.
        for(int i=0;i<n;i++) { if(frequencies[i]<0) throw new IllegalArgumentException("Nonnegative frequencies"); prefix[i+1]=prefix[i]+frequencies[i]; } // Frequencies correspond to already-sorted distinct keys.
        long[][] dp=new long[n][n]; // A state stores the minimum successful-search comparison cost for an ordered interval.
        for(int len=1;len<=n;len++) for(int left=0;left+len<=n;left++) { // Smaller left/right subtree intervals are available.
            int right=left+len-1; dp[left][right]=Long.MAX_VALUE; // Initialize before trying each root.
            for(int root=left;root<=right;root++) { long children=(root>left?dp[left][root-1]:0)+(root<right?dp[root+1][right]:0); dp[left][right]=Math.min(dp[left][right],children+prefix[right+1]-prefix[left]); } // Every key in the interval pays one extra comparison under this root.
        }
        return dp[0][n-1]; // The complete key interval determines the best ordered tree shape.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(minimum(new int[]{34,8,50})); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(minimum(new int[]{})==0, "Q448 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(minimum(new int[]{5})==5, "Q448 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(minimum(new int[]{1,1})==3, "Q448 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
