package questions.q408;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q408: Triangle Minimum Path.
 * Contract: Row r has r+1 values; choose a child at the same or next column; totals fit long.
 * Why it works: From each triangle cell a route chooses exactly one of two children. Bottom-up optimal child costs therefore provide all possibilities for the optimal parent cost.
 * Cost: O(n²) time and O(n) space for n rows.
 */
public final class Q408TriangleMinimumPath { // Keep this question independently runnable and testable.
    private Q408TriangleMinimumPath() { } // No object state is needed by this standalone exercise.
    public static long minimum(int[][] triangle) { // Expose the operation for an independent caller and the executable checks below.
        if(triangle.length==0) return 0; // The empty triangle has an empty path of cost zero.
        long[] dp=new long[triangle.length+1]; // One zero boundary row makes the last actual row a uniform transition.
        for(int row=triangle.length-1;row>=0;row--) { // Children must be solved before their parent choices.
            if(triangle[row].length!=row+1) throw new IllegalArgumentException("Row r needs r+1 values"); // Enforce the two-child triangle geometry.
            for(int col=0;col<=row;col++) dp[col]=triangle[row][col]+Math.min(dp[col],dp[col+1]); // Choose the cheaper of the two adjacent child paths.
        }
        return dp[0]; // The top state summarizes the cheapest complete route.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(minimum(new int[][]{{2},{3,4},{6,5,7},{4,1,8,3}})); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(minimum(new int[][]{})==0, "Q408 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(minimum(new int[][]{{-3}})==-3, "Q408 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(minimum(new int[][]{{1},{2,3}})==3, "Q408 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
