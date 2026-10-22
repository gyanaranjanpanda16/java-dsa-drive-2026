package questions.q324; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q324: Compute each cell distance to the nearest zero.
 * Contract: Rectangular binary grid; four-way movement; minus one means no zero is reachable because none exists.
 * Why: Starting BFS from every zero is equivalent to adding a virtual source connected to all zeros. Layers therefore represent the minimum distance to any zero, avoiding one search per cell.
 * Complexity: O(RC) time and O(RC) space.
 */
public final class Q324NearestZero { // One class contains this question, its answer, and reproducible checks.
    static final int[][] DIRECTIONS={{1,0},{-1,0},{0,1},{0,-1}}; // Four-way adjacency excludes diagonal contact.
    static boolean inside(int r,int c,int rows,int cols) { // Centralize the grid-boundary condition used by every neighbor.
        return r>=0&&r<rows&&c>=0&&c<cols; // Only valid coordinates may index an array.
    }
    public static String solve(int[][] grid) { // Expose the answer as a reusable method as well as a runnable program.
        if(grid.length==0) return "[]"; // No cells need distances.
        int rows=grid.length,cols=grid[0].length; int[][] distance=new int[rows][cols]; // Store the shortest known distance for every cell.
        Deque<int[]> q=new ArrayDeque<>(); // All zero cells are simultaneous distance-zero sources.
        for(int r=0;r<rows;r++) { // Initialize every row before expansion begins.
            Arrays.fill(distance[r],-1); // Minus one marks cells no source has reached yet.
            for(int c=0;c<cols;c++) if(grid[r][c]==0) { distance[r][c]=0; q.add(new int[]{r,c}); } // Seed every zero, not just the first one.
        }
        while(!q.isEmpty()) { // FIFO expands distance layers outward from all sources together.
            int[] cell=q.remove(); // This cell already has its minimum source distance.
            for(int[] d:DIRECTIONS) { // Movement crosses one orthogonal edge.
                int nr=cell[0]+d[0],nc=cell[1]+d[1]; // Compute the neighboring cell.
                if(inside(nr,nc,rows,cols)&&distance[nr][nc]==-1) { distance[nr][nc]=distance[cell[0]][cell[1]]+1; q.add(new int[]{nr,nc}); } // First discovery chooses the nearest zero automatically.
            }
        }
        return Arrays.deepToString(distance); // Without any zero, all distances remain minus one.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(new int[][]{{0,1,1},{1,1,0}})); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(new int[][]{{0,1,1},{1,1,0}}), "[[0, 1, 1], [1, 1, 0]]"); // Two source fronts meet
        Check.equal(solve(new int[][]{{1,1}}), "[[-1, -1]]"); // No zero exists
        Check.equal(solve(new int[][]{{0}}), "[[0]]"); // A zero needs no movement
    }
}
