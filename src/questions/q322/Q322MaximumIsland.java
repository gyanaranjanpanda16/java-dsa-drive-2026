package questions.q322; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q322: Find the area of the largest island.
 * Contract: Rectangular binary grid; four-way adjacency; input preserved.
 * Why: A component traversal counts every land vertex exactly once. Taking the maximum over disjoint components yields the greatest possible island area without merging diagonal neighbors.
 * Complexity: O(RC) time and O(RC) space.
 */
public final class Q322MaximumIsland { // One class contains this question, its answer, and reproducible checks.
    static final int[][] DIRECTIONS={{1,0},{-1,0},{0,1},{0,-1}}; // Four-way adjacency excludes diagonal contact.
    static boolean inside(int r,int c,int rows,int cols) { // Centralize the grid-boundary condition used by every neighbor.
        return r>=0&&r<rows&&c>=0&&c<cols; // Only valid coordinates may index an array.
    }
    public static int solve(int[][] grid) { // Expose the answer as a reusable method as well as a runnable program.
        if(grid.length==0) return 0; // No cells means no positive island area.
        int rows=grid.length,cols=grid[0].length,best=0; // Track the greatest completed component size.
        boolean[][] seen=new boolean[rows][cols]; // Each land cell belongs to exactly one explored component.
        for(int r=0;r<rows;r++) for(int c=0;c<cols;c++) if(grid[r][c]==1&&!seen[r][c]) { // Begin a new unmeasured component.
            int area=0; Deque<int[]> q=new ArrayDeque<>(); q.add(new int[]{r,c}); seen[r][c]=true; // Initialize the component frontier.
            while(!q.isEmpty()) { // Exhaust this component before updating the maximum.
                int[] cell=q.remove(); area++; // Each dequeued land cell contributes one unit of area.
                for(int[] d:DIRECTIONS) { // Inspect orthogonal neighbors only.
                    int nr=cell[0]+d[0],nc=cell[1]+d[1]; // Form the adjacent cell coordinates.
                    if(inside(nr,nc,rows,cols)&&grid[nr][nc]==1&&!seen[nr][nc]) { seen[nr][nc]=true; q.add(new int[]{nr,nc}); } // Avoid counting any cell twice.
                }
            }
            best=Math.max(best,area); // Only completed component areas compete for the maximum.
        }
        return best; // Zero remains correct when there is no land.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(new int[][]{{1,1,0},{0,1,0},{1,0,1}})); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(new int[][]{{1,1,0},{0,1,0},{1,0,1}}), 3); // Largest of several islands
        Check.equal(solve(new int[][]{{0}}), 0); // No land
        Check.equal(solve(new int[][]{{1,1}}), 2); // One horizontal island
    }
}
