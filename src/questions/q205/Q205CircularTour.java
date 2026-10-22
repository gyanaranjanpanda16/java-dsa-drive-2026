package questions.q205;

import java.util.*;
import questions.support.Check;

/**
 * Q205: Find a feasible start for a circular fuel tour.
 * Contract: Gas and cost arrays have equal length and nonnegative values. Return a valid start index or -1; empty returns-1.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: If a candidate runs out at i, every start between that candidate and i also fails by the accumulated deficit argument. Resetting to i+1 skips only impossible starts; nonnegative total balance guarantees the surviving start succeeds.
 * Complexity: O(n) time and O(1) space.
 */
public final class Q205CircularTour { // Keep one question and its tests together for independent study.
    public static int solve(int[] gas,int[] cost) { // Treat each station's supply minus outgoing travel cost as its net contribution.
        if(gas.length!=cost.length) throw new IllegalArgumentException("matching lengths required"); // Every station needs both supply and travel cost.
        if(gas.length==0) return -1; // No station can serve as a start.
        long total=0,tank=0;int start=0; // Track global feasibility and the current candidate segment separately.
        for(int i=0;i<gas.length;i++) { // Consider each station once in circular order before choosing the wrap point.
            long change=(long)gas[i]-cost[i]; // Widen before subtraction to avoid int overflow.
            total+=change;tank+=change; // Update both the whole-route balance and current candidate's balance.
            if(tank<0) { start=i+1;tank=0; } // Every start in the failed candidate segment is invalid,so try the next station.
        }
        return total<0?-1:start; // A negative route total is impossible from every start.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve(new int[]{1,2,3,4,5},new int[]{3,4,5,1,2})); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve(new int[]{1,2,3,4,5},new int[]{3,4,5,1,2}),3); // Skip failed prefixes to find a feasible wrap point.
    Check.equal(solve(new int[]{2,3,4},new int[]{3,4,3}),-1); // Negative total balance proves impossibility.
    Check.equal(solve(new int[]{},new int[]{}),-1); // Empty route has no start.
    }
}
