package questions.q406;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q406: Grid Paths With Obstacles.
 * Contract: Rectangular grid; zero means open and nonzero means blocked. Only right/down moves are allowed.
 * Why it works: At each free cell, every route comes from above or left. Setting obstacle states to zero excludes all routes crossing forbidden cells without changing that recurrence.
 * Cost: O(rows*columns) time and O(columns) space; count overflow is reported.
 */
public final class Q406GridPathsWithObstacles { // Keep this question independently runnable and testable.
    private Q406GridPathsWithObstacles() { } // No object state is needed by this standalone exercise.
    public static long paths(int[][] blocked) { // Expose the operation for an independent caller and the executable checks below.
        if(blocked.length==0||blocked[0].length==0) return 0; // No destination exists in an empty grid.
        int columns=blocked[0].length; long[] dp=new long[columns]; dp[0]=1; // Seed a single route before considering the starting obstacle.
        for(int[] row:blocked) { // Process every row from top to bottom.
            if(row.length!=columns) throw new IllegalArgumentException("Rectangular grid required"); // Neighbor positions must have consistent columns.
            for(int col=0;col<columns;col++) { // Left-to-right order makes the current row's left count available.
                if(row[col]!=0) dp[col]=0; // An obstacle destroys every route ending at this cell.
                else if(col>0) dp[col]=Math.addExact(dp[col],dp[col-1]); // Combine routes from above and left when the cell is open.
            }
        }
        return dp[columns-1]; // This state describes the bottom-right destination.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(paths(new int[][]{{0,0,0},{0,1,0},{0,0,0}})); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(paths(new int[][]{})==0, "Q406 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(paths(new int[][]{{1}})==0, "Q406 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(paths(new int[][]{{0,0},{0,0}})==2, "Q406 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
