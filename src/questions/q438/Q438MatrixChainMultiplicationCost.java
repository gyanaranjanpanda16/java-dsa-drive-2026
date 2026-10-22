package questions.q438;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q438: Matrix Chain Multiplication Cost.
 * Contract: Matrix i has dimensions[i] rows and dimensions[i+1] columns; dimensions are positive.
 * Why it works: The final multiplication splits the chain into two independently parenthesized intervals. Trying every final split and optimal subintervals covers all parenthesizations.
 * Cost: O(n³) time and O(n²) space for n matrices; arithmetic overflow throws.
 */
public final class Q438MatrixChainMultiplicationCost { // Keep this question independently runnable and testable.
    private Q438MatrixChainMultiplicationCost() { } // No object state is needed by this standalone exercise.
    public static long minimum(int[] dimensions) { // Expose the operation for an independent caller and the executable checks below.
        for(int d:dimensions) if(d<=0) throw new IllegalArgumentException("Positive dimensions required"); // Matrix dimensions must describe real nonempty shapes.
        int n=dimensions.length-1; if(n<=1) return 0; // Zero or one matrix requires no multiplication.
        long[][] dp=new long[n][n]; // dp[left][right] is the cheapest way to multiply that matrix interval.
        for(int length=2;length<=n;length++) for(int left=0;left+length<=n;left++) { // All possible smaller split intervals are solved first.
            int right=left+length-1; dp[left][right]=Long.MAX_VALUE; // Initialize this interval before considering its final multiplication.
            for(int split=left;split<right;split++) { // The last multiplication partitions the chain at exactly one boundary.
                long merge=Math.multiplyExact(Math.multiplyExact((long)dimensions[left],dimensions[split+1]),dimensions[right+1]); // The two product shapes determine scalar multiplication cost.
                long cost=Math.addExact(Math.addExact(dp[left][split],dp[split+1][right]),merge); // Include both independent subchains and their final merge.
                dp[left][right]=Math.min(dp[left][right],cost); // Retain the cheapest legal parenthesization.
            }
        }
        return dp[0][n-1]; // The full chain interval is the requested problem.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(minimum(new int[]{40,20,30,10,30})); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(minimum(new int[]{})==0, "Q438 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(minimum(new int[]{10,20})==0, "Q438 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(minimum(new int[]{10,20,30})==6000, "Q438 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
