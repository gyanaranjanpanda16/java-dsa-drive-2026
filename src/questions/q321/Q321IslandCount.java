package questions.q321; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q321: Count four-connected islands in a binary grid.
 * Contract: Rectangular non-null grid containing only0 and1; four-way adjacency; input preserved.
 * Why: Grid adjacency turns the problem into connected-component counting. Flooding a new land seed marks its complete component, so later cells in that island cannot increment the count again.
 * Complexity: O(RC) time and O(RC) visited/queue space.
 */
public final class Q321IslandCount { // One class contains this question, its answer, and reproducible checks.
    static final int[][] DIRECTIONS={{1,0},{-1,0},{0,1},{0,-1}}; // Four-way adjacency excludes diagonal contact.
    static boolean inside(int r,int c,int rows,int cols) { // Centralize the grid-boundary condition used by every neighbor.
        return r>=0&&r<rows&&c>=0&&c<cols; // Only valid coordinates may index an array.
    }
    public static int solve(int[][] grid) { // Expose the answer as a reusable method as well as a runnable program.
        if(grid.length==0) return 0; // An empty grid has no land components.
        int rows=grid.length,cols=grid[0].length,count=0; // Treat each land cell as a graph vertex.
        boolean[][] seen=new boolean[rows][cols]; // Record land already assigned to an island.
        for(int r=0;r<rows;r++) for(int c=0;c<cols;c++) if(grid[r][c]==1&&!seen[r][c]) { // Only new land starts an island.
            count++; Deque<int[]> q=new ArrayDeque<>(); q.add(new int[]{r,c}); seen[r][c]=true; // Count this component once before exploring it.
            while(!q.isEmpty()) { // Visit every cell connected to this island seed.
                int[] cell=q.remove(); // Expand one discovered land cell.
                for(int[] d:DIRECTIONS) { // Consider exactly the four orthogonal neighbors.
                    int nr=cell[0]+d[0],nc=cell[1]+d[1]; // Translate a direction into a neighbor coordinate.
                    if(inside(nr,nc,rows,cols)&&grid[nr][nc]==1&&!seen[nr][nc]) { seen[nr][nc]=true; q.add(new int[]{nr,nc}); } // Enqueue unassigned neighboring land once.
                }
            }
        }
        return count; // Each island contributed one seed and no more.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(new int[][]{{1,1,0},{0,0,1},{1,0,1}})); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(new int[][]{{1,1,0},{0,0,1},{1,0,1}}), 3); // Separated land regions
        Check.equal(solve(new int[][]{{0,0}}), 0); // Only water
        Check.equal(solve(new int[][]{}), 0); // Empty grid
    }
}
