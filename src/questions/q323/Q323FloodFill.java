package questions.q323; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q323: Recolor the connected region containing a chosen pixel.
 * Contract: Nonempty rectangular image with valid starting coordinates; any int colors; four-way adjacency.
 * Why: Reachability is restricted to pixels with the original seed color. Changing color on enqueue marks discovery; when replacement differs from original, no explicit visited array is needed.
 * Complexity: O(RC) time and output/queue space.
 */
public final class Q323FloodFill { // One class contains this question, its answer, and reproducible checks.
    static final int[][] DIRECTIONS={{1,0},{-1,0},{0,1},{0,-1}}; // Four-way adjacency excludes diagonal contact.
    static boolean inside(int r,int c,int rows,int cols) { // Centralize the grid-boundary condition used by every neighbor.
        return r>=0&&r<rows&&c>=0&&c<cols; // Only valid coordinates may index an array.
    }
    public static String solve(int[][] image,int sr,int sc,int replacement) { // Expose the answer as a reusable method as well as a runnable program.
        int rows=image.length,cols=image[0].length; // A valid starting pixel implies at least one cell.
        int[][] result=new int[rows][]; for(int r=0;r<rows;r++) result[r]=image[r].clone(); // Copy rows so recoloring does not mutate the original image.
        int original=result[sr][sc]; // Only pixels matching the seed's original color belong to the region.
        if(original==replacement) return Arrays.deepToString(result); // Avoid revisiting pixels when recoloring would not mark them differently.
        Deque<int[]> q=new ArrayDeque<>(); q.add(new int[]{sr,sc}); result[sr][sc]=replacement; // Recolor on discovery to double as the visited mark.
        while(!q.isEmpty()) { // Expand through the entire connected same-color region.
            int[] cell=q.remove(); // Process one recolored pixel's neighbors.
            for(int[] d:DIRECTIONS) { // Diagonal pixels do not share an edge with this pixel.
                int nr=cell[0]+d[0],nc=cell[1]+d[1]; // Compute an orthogonal neighbor.
                if(inside(nr,nc,rows,cols)&&result[nr][nc]==original) { result[nr][nc]=replacement; q.add(new int[]{nr,nc}); } // Recolor matching unvisited neighbors exactly once.
            }
        }
        return Arrays.deepToString(result); // Unconnected colors remain untouched.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(new int[][]{{1,1},{1,0}},0,0,2)); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(new int[][]{{1,1},{1,0}},0,0,2), "[[2, 2], [2, 0]]"); // Recolor one connected region
        Check.equal(solve(new int[][]{{3}},0,0,3), "[[3]]"); // Replacement equals original color
        Check.equal(solve(new int[][]{{1,0,1}},0,0,9), "[[9, 0, 1]]"); // Disconnected same-color pixel stays unchanged
    }
}
