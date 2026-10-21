package questions.q405;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q405: Unique Grid Paths.
 * Contract: Count right/down paths across nonnegative grid dimensions.
 * Why it works: Every right/down route to a cell arrives from above or left. These last moves are disjoint, so adding their counts gives the answer.
 * Cost: O(rows*columns) time and O(columns) space; overflow throws ArithmeticException.
 */
public final class Q405UniqueGridPaths { // Keep this question independently runnable and testable.
    private Q405UniqueGridPaths() { } // No object state is needed by this standalone exercise.
    public static long paths(int rows,int columns) { // Expose the operation for an independent caller and the executable checks below.
        if(rows<0||columns<0) throw new IllegalArgumentException("Nonnegative dimensions"); // Invalid sizes cannot describe a grid.
        if(rows==0||columns==0) return 0; // No cell exists in an empty dimension.
        long[] dp=new long[columns]; Arrays.fill(dp,1); // Every cell in the first row has one all-right path.
        for(int row=1;row<rows;row++) { // Process rows after the initial boundary row.
            for(int col=1;col<columns;col++) dp[col]=Math.addExact(dp[col],dp[col-1]); // Old dp[col] is above; updated dp[col-1] is left.
        }
        return dp[columns-1]; // The bottom-right cell accumulates all permitted incoming paths.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(paths(3,7)); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(paths(0,3)==0, "Q405 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(paths(1,4)==1, "Q405 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(paths(3,3)==6, "Q405 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
