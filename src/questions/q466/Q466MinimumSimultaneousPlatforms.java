package questions.q466;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q466: Minimum Simultaneous Platforms.
 * Contract: Comparable integer times, positive-length half-open visits; equal-time departure precedes arrival.
 * Why it works: Every overlapping visit requires a different platform. Reusing a platform immediately after departure achieves exactly the maximum active interval count, so that count is optimal.
 * Cost: O(n log n) time and O(n) space.
 */
public final class Q466MinimumSimultaneousPlatforms { // Keep this question independently runnable and testable.
    private Q466MinimumSimultaneousPlatforms() { } // No object state is needed by this standalone exercise.
    public static int minimum(int[][] visits) { // Expose the operation for an independent caller and the executable checks below.
        int[][] sorted=Arrays.stream(visits).map(int[]::clone).toArray(int[][]::new); // Copy interval records before sorting arrivals.
        for(int[] v:sorted) if(v.length!=2||v[0]>=v[1]) throw new IllegalArgumentException("Positive-length visits required"); // Treat visits as half-open[arrival,departure).
        Arrays.sort(sorted,Comparator.comparingInt(v->v[0])); // Process arrivals in chronological order.
        PriorityQueue<Integer> departures=new PriorityQueue<>(); int best=0; // Active departures indicate currently occupied platforms.
        for(int[] visit:sorted) { // A new arrival must coexist with all visits not yet finished.
            while(!departures.isEmpty()&&departures.peek()<=visit[0]) departures.poll(); // A departure at the arrival time frees its platform immediately.
            departures.offer(visit[1]); best=Math.max(best,departures.size()); // Add the new occupancy and record peak overlap.
        }
        return best; // Peak simultaneous occupancy is both necessary and sufficient for interval platform allocation.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(minimum(new int[][]{{900,910},{940,1200},{950,1120},{1100,1130},{1500,1900},{1800,2000}})); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(minimum(new int[][]{})==0, "Q466 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(minimum(new int[][]{{1,2},{2,3}})==1, "Q466 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(minimum(new int[][]{{1,5},{2,4}})==2, "Q466 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
