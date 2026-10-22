package questions.q410;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q410: Count All One Squares.
 * Contract: Rectangular zero/one matrix; return the total number of all-one square submatrices.
 * Why it works: The largest square ending at a cell implies every smaller side also fits there. Summing these side counts counts each square once by its bottom-right corner.
 * Cost: O(rows*columns) time and space.
 */
public final class Q410CountAllOneSquares { // Keep this question independently runnable and testable.
    private Q410CountAllOneSquares() { } // No object state is needed by this standalone exercise.
    public static long count(int[][] grid) { // Expose the operation for an independent caller and the executable checks below.
        if(grid.length==0) return 0; // No bottom-right corners exist.
        int columns=grid[0].length; int[][] dp=new int[grid.length+1][columns+1]; long total=0; // Count squares by their unique bottom-right corner.
        for(int row=1;row<=grid.length;row++) { // Solve squares after their upper neighbors.
            if(grid[row-1].length!=columns) throw new IllegalArgumentException("Rectangular grid required"); // Keep positional dependencies valid.
            for(int col=1;col<=columns;col++) { // Solve left neighbors before extending into the current cell.
                if(grid[row-1][col-1]==1) dp[row][col]=1+Math.min(dp[row-1][col-1],Math.min(dp[row-1][col],dp[row][col-1])); // Find the largest supported side at this corner.
                total+=dp[row][col]; // Every side from1 through that maximum yields exactly one square at this corner.
            }
        }
        return total; // Corners and side lengths uniquely identify all counted squares.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(count(new int[][]{{1,1},{1,1}})); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(count(new int[][]{})==0, "Q410 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(count(new int[][]{{0,0}})==0, "Q410 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(count(new int[][]{{1,1,1}})==3, "Q410 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
