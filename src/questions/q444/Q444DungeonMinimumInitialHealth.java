package questions.q444;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q444: Dungeon Minimum Initial Health.
 * Contract: Nonempty rectangular dungeon; move right/down; health must remain strictly positive.
 * Why it works: The needed health before a cell is the chosen successor requirement minus the current cell gain, clamped to at least one. Backward evaluation preserves survival at every intermediate position.
 * Cost: O(rows*columns) time and space.
 */
public final class Q444DungeonMinimumInitialHealth { // Keep this question independently runnable and testable.
    private Q444DungeonMinimumInitialHealth() { } // No object state is needed by this standalone exercise.
    public static long minimum(int[][] dungeon) { // Expose the operation for an independent caller and the executable checks below.
        if(dungeon.length==0||dungeon[0].length==0) throw new IllegalArgumentException("Nonempty dungeon required"); // A route needs a real starting cell.
        int rows=dungeon.length,cols=dungeon[0].length; long[][] need=new long[rows+1][cols+1]; // Work backward from survival requirements rather than forward profit.
        for(long[] row:need) Arrays.fill(row,Long.MAX_VALUE/4); // Off-grid exits are impossible choices.
        need[rows][cols-1]=need[rows-1][cols]=1; // Leaving the destination still requires at least one health point.
        for(int r=rows-1;r>=0;r--) { // Downward successor requirements are already known.
            if(dungeon[r].length!=cols) throw new IllegalArgumentException("Rectangular dungeon required"); // Preserve the grid neighbor model.
            for(int c=cols-1;c>=0;c--) need[r][c]=Math.max(1,Math.min(need[r+1][c],need[r][c+1])-dungeon[r][c]); // Enter with enough health for the cheaper exit after this cell's effect.
        }
        return need[0][0]; // This entry requirement guarantees survival along an optimal right/down route.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(minimum(new int[][]{{-2,-3,3},{-5,-10,1},{10,30,-5}})); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(minimum(new int[][]{{5}})==1, "Q444 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(minimum(new int[][]{{-5}})==6, "Q444 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(minimum(new int[][]{{0,0}})==1, "Q444 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
