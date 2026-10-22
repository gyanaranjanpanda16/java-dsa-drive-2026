package questions.q391; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Enumerate all simple paths through a maze.
 * Contract: Accept a rectangular binary grid of at most16 cells, where1 is open and0 blocked. Return sorted direction strings for all top-left to bottom-right paths using down D, left L, right R, and up U. A path cannot revisit a cell. A single open cell has one path, the empty string; a blocked endpoint or empty grid has no paths.
 * Why: Dynamic programming for right/down movement does not apply unchanged when moves may go in all four directions: cycles become possible. Maintain a visited grid for the current path, not a permanent global visited set. Otherwise finding one route would incorrectly forbid cells in other routes. This enumerates alternatives for a tiny maze; a shortest-route problem should use BFS instead of listing every possible route.
 * Cost: O(V*4^V) conservative time including storing and sorting path strings for V cells; O(V) recursion and visited storage plus potentially exponential output. The16-cell bound prevents presenting this as a scalable route planner.
 */
public final class Q391SimpleMazePaths { // Keep the algorithm, demonstration, and boundary checks together.
    private Q391SimpleMazePaths() { } // Only static entry points are needed for this exercise.
    public static List<String> solve(int[][] grid) { // Enumerate full direction strings rather than only a route count.
        int rows=grid.length,cols=rows==0?0:grid[0].length; // Empty dimensions contain no endpoints.
        if((long)rows*cols>16) throw new IllegalArgumentException("at most16 cells required"); // Bound exhaustive path enumeration.
        for(int[] row:grid) { if(row.length!=cols) throw new IllegalArgumentException("rectangular grid required"); for(int value:row) if(value!=0&&value!=1) throw new IllegalArgumentException("binary grid required"); } // Validate before searching.
        List<String> result=new ArrayList<>(); // No path is represented by an empty result list.
        if(rows==0||cols==0) return result; // Avoid indexing a missing start cell.
        paths(grid,0,0,new boolean[rows][cols],new StringBuilder(),result); // Begin with an empty route and no visited cells.
        Collections.sort(result); return result; // Stabilize the ordering independently of traversal choices.
    }
    private static void paths(int[][] grid,int r,int c,boolean[][] used,StringBuilder route,List<String> result) { // Each call considers one newly reached cell.
        if(r<0||r>=grid.length||c<0||c>=grid[0].length||grid[r][c]==0||used[r][c]) return; // Invalid or repeated cells cannot extend a simple path.
        if(r==grid.length-1&&c==grid[0].length-1) { result.add(route.toString()); return; } // Reaching the destination completes one route.
        used[r][c]=true; // Reserve this cell only for the current recursion branch.
        int[] dr={1,0,0,-1},dc={0,-1,1,0}; char[] letters={'D','L','R','U'}; // Pair each move with its output symbol.
        for(int i=0;i<4;i++) { // Consider every orthogonal direction.
            route.append(letters[i]); paths(grid,r+dr[i],c+dc[i],used,route,result); // Make a move and explore its continuation.
            route.setLength(route.length()-1); // Remove exactly the move added by this branch.
        }
        used[r][c]=false; // Other complete routes may use this cell again.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new int[][]{{1,1},{1,1}}))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new int[][]{{1,1},{1,1}}), List.of("DR","RD")); // Two routes share endpoints.
        Check.equal(solve(new int[][]{{1,0},{1,1}}), List.of("DR")); // A wall removes one route.
        Check.equal(solve(new int[][]{{1}}), List.of("")); // Start already equals destination.
        Check.equal(solve(new int[][]{{0}}), List.of()); // Blocked start.
    }
}
