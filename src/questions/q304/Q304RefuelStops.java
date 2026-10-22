package questions.q304; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q304: Minimize refueling stops to reach a target.
 * Contract: Nonnegative target and start fuel; stations sorted by position with nonnegative positions/fuel; fuel consumption is one unit per distance, tank capacity unlimited.
 * Why: Delay committing to a refill until progress requires it. Among previously reachable stations, choosing the largest supply gives at least as much future reach as any other single stop; exchanging another choice for this one cannot hurt feasibility.
 * Complexity: O(n log(n+1)) time and O(n) heap space.
 */
public final class Q304RefuelStops { // One class contains this question, its answer, and reproducible checks.

    public static int solve(int target,int startFuel,int[][] stations) { // Expose the answer as a reusable method as well as a runnable program.
        long reachable=startFuel; // Track the farthest position attainable with fuel already chosen.
        PriorityQueue<Integer> available=new PriorityQueue<>(Comparator.reverseOrder()); // The largest passed fuel supply extends reach most per stop.
        int next=0,stops=0; // Stations are consumed once in positional order.
        while(reachable<target) { // Stop as soon as the target is reachable without another refill.
            while(next<stations.length&&stations[next][0]<=reachable) available.offer(stations[next++][1]); // Every passed station becomes an available earlier stopping choice.
            if(available.isEmpty()) return -1; // No reachable supply can extend the journey.
            reachable+=available.poll(); // Choose the largest available contribution to maximize reach for this stop count.
            stops++; // Taking one supply corresponds to one actual refueling stop.
        }
        return stops; // The greedy reach dominates any plan with fewer chosen supplies at each step.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(100,10,new int[][]{{10,60},{20,30},{30,30},{60,40}})); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(100,10,new int[][]{{10,60},{20,30},{30,30},{60,40}}), 2); // Two large refills suffice
        Check.equal(solve(10,10,new int[][]{}), 0); // Target already reachable
        Check.equal(solve(20,5,new int[][]{{10,20}}), -1); // First station cannot be reached
    }
}
