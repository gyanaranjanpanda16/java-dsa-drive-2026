package questions.q485; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Find a minimum round trip with subset dynamic programming.
 * Contract: Given a complete directed nonnegative int cost matrix with at most15 vertices, start at0, visit every other vertex once and return to0 at minimum cost. Empty and singleton inputs cost0.
 * Why: dp[mask][last] stores the best route from0 visiting exactly mask and ending at last. Extending by one unvisited vertex considers every possible final transition. The final return edge closes the tour. This is exact optimization for small route sets, where factorial enumeration becomes too costly.
 * Cost: O(n²*2^n) time and O(n*2^n) space; this exact method is only practical for small n.
 */
public final class Q485TravelingSalesperson { // Keep the algorithm, demonstration, and boundary checks together.
    private Q485TravelingSalesperson() { } // Only static entry points are needed for this exercise.
    public static long solve(int[][] cost) { // Keep all tour costs in long because many int edges are added.
        int n=cost.length; if(n>15) throw new IllegalArgumentException("at most fifteen vertices"); if(n<2) return 0; // Bound exponential storage and handle trivial tours.
        int states=1<<n; long unreachable=Long.MAX_VALUE/4; long[][] best=new long[states][n]; // A bitmask records which vertices have already been visited.
        for(long[] row:best) Arrays.fill(row,unreachable); best[1][0]=0; // Initially only vertex0 is visited and no edge cost is paid.
        for(int mask=1;mask<states;mask++) for(int last=0;last<n;last++) { // Every transition adds a bit, so smaller masks are processed before their extensions.
            if(best[mask][last]==unreachable) continue; // Do not extend nonexistent partial routes.
            for(int next=1;next<n;next++) if((mask&(1<<next))==0) { // Vertex0 is reserved for the final closing edge.
                int extended=mask|(1<<next); best[extended][next]=Math.min(best[extended][next],best[mask][last]+cost[last][next]); // Try each possible next destination once.
            }
        }
        long answer=unreachable; for(int last=1;last<n;last++) answer=Math.min(answer,best[states-1][last]+cost[last][0]); // Close every complete candidate route back to0.
        return answer; // The minimum covers every ordering through its sequence of subset extensions.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new int[][]{{0,10,15},{10,0,20},{15,20,0}}))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new int[][]{{0,10,15},{10,0,20},{15,20,0}}), 45L); // A symmetric three-vertex tour.
        Check.equal(solve(new int[][]{{0}}), 0L); // A singleton requires no travel.
        Check.equal(solve(new int[][]{{0,1,9},{9,0,1},{1,9,0}}), 3L); // Directed costs favor one orientation.
    }
}
