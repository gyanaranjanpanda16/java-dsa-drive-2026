package questions.q409;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q409: Maximal Square Area.
 * Contract: Rectangular zero/one matrix; return the area of the largest all-one square.
 * Why it works: A square ending at a one-cell can extend only as far as the smallest square above, left, and diagonally above-left. Their overlap covers every cell of the proposed larger square.
 * Cost: O(rows*columns) time and space.
 */
public final class Q409MaximalSquareArea { // Keep this question independently runnable and testable.
    private Q409MaximalSquareArea() { } // No object state is needed by this standalone exercise.
    public static int area(int[][] grid) { // Expose the operation for an independent caller and the executable checks below.
        if(grid.length==0) return 0; // Empty input contains no square.
        int columns=grid[0].length,best=0; int[][] dp=new int[grid.length+1][columns+1]; // Zero padding removes boundary branches.
        for(int row=1;row<=grid.length;row++) { // Each state represents a square ending at its bottom-right cell.
            if(grid[row-1].length!=columns) throw new IllegalArgumentException("Rectangular grid required"); // Match the DP columns to the input geometry.
            for(int col=1;col<=columns;col++) { // The three prerequisite squares are already available.
                if(grid[row-1][col-1]==1) dp[row][col]=1+Math.min(dp[row-1][col-1],Math.min(dp[row-1][col],dp[row][col-1])); // All three adjacent regions must support an extension.
                best=Math.max(best,dp[row][col]); // Retain the largest side over all bottom-right corners.
            }
        }
        return Math.multiplyExact(best,best); // Convert side length to area and reject int overflow.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(area(new int[][]{{1,1,0},{1,1,1},{0,1,1}})); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(area(new int[][]{})==0, "Q409 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(area(new int[][]{{0}})==0, "Q409 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(area(new int[][]{{1,1},{1,1}})==4, "Q409 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
