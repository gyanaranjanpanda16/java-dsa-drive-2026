package questions.q468;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q468: Minimum Refueling Stops.
 * Contract: Unlimited tank, stations[position,fuel], start at0; every selected station is used at most once.
 * Why it works: Whenever another stop is unavoidable, choosing the largest fuel among stations already reachable cannot reduce future options compared with any other one-stop choice. Deferred choices model stopping there earlier.
 * Cost: O(n log n) time and O(n) space; reach overflow throws.
 */
public final class Q468MinimumRefuelingStops { // Keep this question independently runnable and testable.
    private Q468MinimumRefuelingStops() { } // No object state is needed by this standalone exercise.
    public static int minimum(long target,long startFuel,int[][] stations) { // Expose the operation for an independent caller and the executable checks below.
        if(target<0||startFuel<0) throw new IllegalArgumentException("Nonnegative distances and fuel"); // One fuel unit covers one distance unit.
        int[][] sorted=Arrays.stream(stations).map(int[]::clone).toArray(int[][]::new); // Process reachable stations by their distance from the start.
        for(int[] s:sorted) if(s.length!=2||s[0]<0||s[1]<0) throw new IllegalArgumentException("Nonnegative station position and fuel"); // Negative fuel would break the greedy reach invariant.
        Arrays.sort(sorted,Comparator.comparingInt(s->s[0])); // A station becomes an available choice when the frontier reaches its position.
        PriorityQueue<Integer> available=new PriorityQueue<>(Comparator.reverseOrder()); long reach=startFuel; int next=0,stops=0; // Defer the decision to stop until fuel is actually needed.
        while(reach<target) { // Continue until the target belongs to the reachable distance prefix.
            while(next<sorted.length&&sorted[next][0]<=reach) available.offer(sorted[next++][1]); // Remember every station we could already have stopped at.
            if(available.isEmpty()) return -1; // No past reachable station can extend the frontier.
            reach=Math.addExact(reach,available.poll()); stops++; // The largest available refill maximizes reach for this additional stop.
        }
        return stops; // The first successful number of greedy extensions is minimal.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(minimum(100,10,new int[][]{{10,60},{20,30},{30,30},{60,40}})); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(minimum(1,1,new int[][]{})==0, "Q468 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(minimum(10,1,new int[][]{})==-1, "Q468 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(minimum(100,10,new int[][]{{10,60},{20,30},{30,30},{60,40}})==2, "Q468 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
