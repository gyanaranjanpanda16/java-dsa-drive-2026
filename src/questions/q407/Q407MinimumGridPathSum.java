package questions.q407;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q407: Minimum Grid Path Sum.
 * Contract: Nonempty rectangular int grid; sums must fit long; moves only right/down.
 * Why it works: A right/down path has a final move from above or left. Replacing its prefix with the cheaper optimal prefix cannot make it worse, so choosing the smaller predecessor cost is correct.
 * Cost: O(rows*columns) time and O(columns) space.
 */
public final class Q407MinimumGridPathSum { // Keep this question independently runnable and testable.
    private Q407MinimumGridPathSum() { } // No object state is needed by this standalone exercise.
    public static long minimum(int[][] cost) { // Expose the operation for an independent caller and the executable checks below.
        if(cost.length==0||cost[0].length==0) throw new IllegalArgumentException("Nonempty grid required"); // A path must include a starting and ending cell.
        int columns=cost[0].length; long[] dp=new long[columns]; // Keep one row of minimum costs.
        for(int row=0;row<cost.length;row++) { // Dependencies above have already been solved.
            if(cost[row].length!=columns) throw new IllegalArgumentException("Rectangular grid required"); // A ragged row would break the neighbor model.
            for(int col=0;col<columns;col++) { // Updated left entries belong to the current row.
                if(row==0&&col==0) dp[col]=cost[row][col]; // The starting cell has no predecessor.
                else if(row==0) dp[col]=dp[col-1]+cost[row][col]; // Top-row cells can only be reached from the left.
                else if(col==0) dp[col]+=cost[row][col]; // First-column cells can only be reached from above.
                else dp[col]=Math.min(dp[col],dp[col-1])+cost[row][col]; // Choose the cheaper exhaustive predecessor option.
            }
        }
        return dp[columns-1]; // The last entry is the minimum complete path cost.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(minimum(new int[][]{{1,3,1},{1,5,1},{4,2,1}})); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(minimum(new int[][]{{7}})==7, "Q407 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(minimum(new int[][]{{1,2,3}})==6, "Q407 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(minimum(new int[][]{{1,3},{2,1}})==4, "Q407 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
