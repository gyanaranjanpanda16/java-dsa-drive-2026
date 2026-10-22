package questions.q343; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q343: Find the fewest obstacles to remove across a grid.
 * Contract: Nonempty rectangular binary grid with grid[0][0]=0; four-direction moves; each entered1 costs one removal; input is unchanged.
 * Why: When weights are only zero or one, a deque maintains the same distance ordering needed by Dijkstra without a heap. A zero-cost relaxation belongs at the front; a cost-one relaxation belongs at the back. This is useful when route planning counts paid transitions rather than physical steps.
 * Complexity: O(RC) time and O(RC) space for the grid graph.
 */
public final class Q343ObstacleRemoval { // One class contains this question, its answer, and reproducible checks.
    static List<List<Integer>> graph(int n,int[][] edges,boolean directed) { // Build adjacency lists matching the declared edge direction.
        List<List<Integer>> g=new ArrayList<>(); // Allocate one neighbor container per vertex.
        for(int v=0;v<n;v++) g.add(new ArrayList<>()); // Isolated vertices still need empty adjacency lists.
        for(int[] e:edges) { // Each row describes one edge between valid vertex indices.
            g.get(e[0]).add(e[1]); // Preserve the forward connection.
            if(!directed) g.get(e[1]).add(e[0]); // Undirected relationships must be traversable both ways.
        }
        return g; // Neighbor order follows input edge order, making traversals deterministic.
    }    static final int[][] DIRECTIONS={{1,0},{-1,0},{0,1},{0,-1}}; // Four-way adjacency excludes diagonal contact.
    static boolean inside(int r,int c,int rows,int cols) { // Centralize the grid-boundary condition used by every neighbor.
        return r>=0&&r<rows&&c>=0&&c<cols; // Only valid coordinates may index an array.
    }
    public static int solve(int[][] grid) { // Expose the answer as a reusable method as well as a runnable program.
        int rows=grid.length,cols=grid[0].length; // Nonempty rectangular dimensions define the state space.
        int[][] distance=new int[rows][cols]; for(int[] row:distance) Arrays.fill(row,Integer.MAX_VALUE); // No cell has a known finite removal cost initially.
        Deque<int[]> deque=new ArrayDeque<>(); distance[0][0]=0; deque.add(new int[]{0,0,0}); // The free start costs zero removals.
        while(!deque.isEmpty()) { // Zero-cost edges stay ahead of one-cost edges in the deque.
            int[] current=deque.removeFirst(); int r=current[0],c=current[1]; // Each entry carries the distance at insertion for stale-entry detection.
            if(current[2]!=distance[r][c]) continue; // A later relaxation may already have improved this queued cell.
            for(int[] direction:DIRECTIONS) { // A move enters one orthogonally adjacent cell.
                int nr=r+direction[0],nc=c+direction[1]; if(!inside(nr,nc,rows,cols)) continue; // Ignore positions outside the board.
                int candidate=distance[r][c]+grid[nr][nc]; // Entering an obstacle costs one; an empty cell costs zero.
                if(candidate>=distance[nr][nc]) continue; // Only a strict improvement can change a shortest route.
                distance[nr][nc]=candidate; int[] next={nr,nc,candidate}; // Record the improved cost before scheduling further relaxations.
                if(grid[nr][nc]==0) deque.addFirst(next); else deque.addLast(next); // Process cost-zero continuations before routes one removal farther away.
            }
        }
        return distance[rows-1][cols-1]; // The final cell stores the minimum removal budget.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(new int[][]{{0,1,1},{1,1,0},{1,1,0}})); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(new int[][]{{0,1,1},{1,1,0},{1,1,0}}), 2); // Two removals open the shortest-cost route
        Check.equal(solve(new int[][]{{0,0},{1,0}}), 0); // A free detour avoids an obstacle
        Check.equal(solve(new int[][]{{0}}), 0); // One free cell
    }
}
