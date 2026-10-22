package questions.q342; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q342: Find minimum dice rolls on a board with snakes and ladders.
 * Contract: Nonempty jump array; -1 means no jump, otherwise a valid destination index. Start at0; die outcomes1..6; exact landing required; apply one jump per roll, including on the final square.
 * Why: Board squares are states and each die outcome is an edge of cost one after its forced jump. BFS therefore finds the fewest rolls even when snakes form cycles. The visited array prevents looping; ArrayDeque supplies the FIFO frontier.
 * Complexity: O(N) time because each square has at most six outgoing moves; O(N) space.
 */
public final class Q342BoardJumps { // One class contains this question, its answer, and reproducible checks.
    static List<List<Integer>> graph(int n,int[][] edges,boolean directed) { // Build adjacency lists matching the declared edge direction.
        List<List<Integer>> g=new ArrayList<>(); // Allocate one neighbor container per vertex.
        for(int v=0;v<n;v++) g.add(new ArrayList<>()); // Isolated vertices still need empty adjacency lists.
        for(int[] e:edges) { // Each row describes one edge between valid vertex indices.
            g.get(e[0]).add(e[1]); // Preserve the forward connection.
            if(!directed) g.get(e[1]).add(e[0]); // Undirected relationships must be traversable both ways.
        }
        return g; // Neighbor order follows input edge order, making traversals deterministic.
    }
    public static int solve(int[] jump) { // Expose the answer as a reusable method as well as a runnable program.
        int n=jump.length; if(n==1) return 0; // The token starts on square zero, so a one-square board is already complete.
        int[] rolls=new int[n]; Arrays.fill(rolls,-1); // An unvisited landing square has no known minimum roll count.
        Deque<Integer> q=new ArrayDeque<>(); q.add(0); rolls[0]=0; // BFS begins before any die has been rolled.
        while(!q.isEmpty()) { // Unit-cost rolls make BFS layers equal minimum roll counts.
            int square=q.remove(); // Explore all legal outcomes of the next die roll.
            for(int die=1;die<=6&&square+die<n;die++) { // Overshooting the final square is not a legal move.
                int landed=square+die; int next=jump[landed]==-1?landed:jump[landed]; // Apply at most one snake or ladder after landing.
                if(rolls[next]!=-1) continue; // A previous BFS visit already used no more rolls.
                rolls[next]=rolls[square]+1; if(next==n-1) return rolls[next]; // Reaching the final square after its jump completes the game.
                q.add(next); // Defer all moves one roll farther away to the queue.
            }
        }
        return -1; // Every reachable landing state was exhausted without finishing.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(new int[]{-1,-1,8,-1,-1,-1,-1,-1,-1,-1})); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(new int[]{-1,-1,8,-1,-1,-1,-1,-1,-1,-1}), 2); // A ladder accelerates the route
        Check.equal(solve(new int[]{-1}), 0); // Already at the destination
        Check.equal(solve(new int[]{-1,0,0}), -1); // Every legal landing returns to the start
    }
}
