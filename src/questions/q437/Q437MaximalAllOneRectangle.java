package questions.q437;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q437: Maximal All One Rectangle.
 * Contract: Rectangular zero/one matrix; area must fit int.
 * Why it works: Consecutive-one heights convert rectangles ending at a row into histogram rectangles. A monotonic stack resolves each limiting bar exactly when its right boundary is known.
 * Cost: O(rows*columns) time and O(columns) space.
 */
public final class Q437MaximalAllOneRectangle { // Keep this question independently runnable and testable.
    private Q437MaximalAllOneRectangle() { } // No object state is needed by this standalone exercise.
    public static int area(int[][] grid) { // Expose the operation for an independent caller and the executable checks below.
        if(grid.length==0) return 0; // There is no rectangle without rows.
        int columns=grid[0].length,best=0; int[] heights=new int[columns]; // Heights summarize consecutive ones ending at the current row.
        for(int[] row:grid) { // Each row is considered as the bottom boundary of a rectangle.
            if(row.length!=columns) throw new IllegalArgumentException("Rectangular grid required"); // Histogram positions must align between rows.
            for(int c=0;c<columns;c++) heights[c]=row[c]==1?heights[c]+1:0; // A zero breaks a vertical run; one extends it.
            Deque<Integer> stack=new ArrayDeque<>(); // Increasing-height indices wait for their first smaller right boundary.
            for(int c=0;c<=columns;c++) { // A final sentinel height clears all unresolved bars.
                int h=c==columns?0:heights[c]; // The synthetic final zero cannot add a new positive rectangle.
                while(!stack.isEmpty()&&heights[stack.peek()]>h) { // The current bar closes every taller pending rectangle.
                    int height=heights[stack.pop()]; int left=stack.isEmpty()?-1:stack.peek(); // The remaining top is the nearest smaller-or-equal left boundary.
                    best=Math.max(best,Math.multiplyExact(height,c-left-1)); // Width excludes both limiting boundaries.
                }
                if(c<columns) stack.push(c); // Keep only real bars so later height lookup stays in bounds.
            }
        }
        return best; // Every maximal rectangle has a bottom row and a limiting histogram bar.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(area(new int[][]{{1,0,1,0,0},{1,0,1,1,1},{1,1,1,1,1},{1,0,0,1,0}})); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(area(new int[][]{})==0, "Q437 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(area(new int[][]{{0}})==0, "Q437 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(area(new int[][]{{1,1},{1,1}})==4, "Q437 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
