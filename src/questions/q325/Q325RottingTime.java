package questions.q325; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q325: Find the time until all reachable fresh oranges rot.
 * Contract: Rectangular grid using0=empty,1=fresh,2=rotten; four-way spread; input preserved.
 * Why: BFS begins with every rotten cell, so first arrival at a fresh cell is its earliest infection minute. Counting unconverted cells distinguishes a long but possible spread from an impossible disconnected one.
 * Complexity: O(RC) time and O(RC) space.
 */
public final class Q325RottingTime { // One class contains this question, its answer, and reproducible checks.
    static final int[][] DIRECTIONS={{1,0},{-1,0},{0,1},{0,-1}}; // Four-way adjacency excludes diagonal contact.
    static boolean inside(int r,int c,int rows,int cols) { // Centralize the grid-boundary condition used by every neighbor.
        return r>=0&&r<rows&&c>=0&&c<cols; // Only valid coordinates may index an array.
    }
    public static int solve(int[][] grid) { // Expose the answer as a reusable method as well as a runnable program.
        if(grid.length==0) return 0; // There are no fresh cells to wait for.
        int rows=grid.length,cols=grid[0].length,fresh=0,time=0; // Count required conversions and the latest infection time.
        boolean[][] rotten=new boolean[rows][cols]; Deque<int[]> q=new ArrayDeque<>(); // Track discovery separately so input remains unchanged.
        for(int r=0;r<rows;r++) for(int c=0;c<cols;c++) { // Initialize all simultaneous infection sources.
            if(grid[r][c]==1) fresh++; // Fresh cells must eventually be reached.
            if(grid[r][c]==2) { rotten[r][c]=true; q.add(new int[]{r,c,0}); } // Initially rotten cells begin at minute zero.
        }
        while(!q.isEmpty()) { // Expand each infection front in increasing time.
            int[] cell=q.remove(); time=cell[2]; // The latest removed timestamp is the maximum time reached so far.
            for(int[] d:DIRECTIONS) { // Infection crosses orthogonal adjacency in one minute.
                int nr=cell[0]+d[0],nc=cell[1]+d[1]; // Compute the candidate neighboring cell.
                if(inside(nr,nc,rows,cols)&&grid[nr][nc]==1&&!rotten[nr][nc]) { rotten[nr][nc]=true; fresh--; q.add(new int[]{nr,nc,time+1}); } // Convert each fresh cell once and schedule its next-minute spread.
            }
        }
        return fresh==0?time:-1; // Any fresh cell left is separated from all original sources.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(new int[][]{{2,1,1},{1,1,0},{0,1,1}})); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(new int[][]{{2,1,1},{1,1,0},{0,1,1}}), 4); // Spread across several layers
        Check.equal(solve(new int[][]{{2,0,1}}), -1); // Fresh cell is isolated by empty space
        Check.equal(solve(new int[][]{{0}}), 0); // No fresh oranges
    }
}
