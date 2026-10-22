package questions.q445;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q445: Minimum Falling Path Sum.
 * Contract: Nonempty rectangular int grid; move down one row and at most one column sideways.
 * Why it works: Every falling path has one of three predecessors for each cell. An optimal prefix to a predecessor can replace any more expensive prefix without changing allowed future moves.
 * Cost: O(rows*columns) time and O(columns) space.
 */
public final class Q445MinimumFallingPathSum { // Keep this question independently runnable and testable.
    private Q445MinimumFallingPathSum() { } // No object state is needed by this standalone exercise.
    public static long minimum(int[][] grid) { // Expose the operation for an independent caller and the executable checks below.
        if(grid.length==0||grid[0].length==0) throw new IllegalArgumentException("Nonempty grid required"); // A falling path starts in a real top-row cell.
        int cols=grid[0].length; long[] previous=new long[cols]; // One row stores all possible preceding endpoints.
        for(int c=0;c<cols;c++) previous[c]=grid[0][c]; // Any top-row column may start the path.
        for(int r=1;r<grid.length;r++) { // Build paths one row at a time.
            if(grid[r].length!=cols) throw new IllegalArgumentException("Rectangular grid required"); // Column offsets must remain well-defined.
            long[] next=new long[cols]; // Separate rows prevent reusing a same-row state.
            for(int c=0;c<cols;c++) { // A path may arrive from above-left, above, or above-right.
                long best=previous[c]; if(c>0) best=Math.min(best,previous[c-1]); if(c+1<cols) best=Math.min(best,previous[c+1]); // Compare only valid predecessor columns.
                next[c]=best+grid[r][c]; // Include the current cell exactly once.
            }
            previous=next; // Complete the row before using it as a predecessor layer.
        }
        long answer=Long.MAX_VALUE; for(long value:previous) answer=Math.min(answer,value); // The path may end at any bottom-row column.
        return answer; // All possible bottom endpoints have been considered.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(minimum(new int[][]{{2,1,3},{6,5,4},{7,8,9}})); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(minimum(new int[][]{{5}})==5, "Q445 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(minimum(new int[][]{{1},{2},{3}})==6, "Q445 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(minimum(new int[][]{{-2,3},{4,-5}})==-7, "Q445 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
