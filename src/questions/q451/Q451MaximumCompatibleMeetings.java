package questions.q451;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q451: Maximum Compatible Meetings.
 * Contract: Half-open positive-length intervals; touching meetings are compatible.
 * Why it works: Replace the first meeting in any optimal schedule with the earliest-finishing compatible one. This cannot block any later meeting, so repeating the exchange proves the greedy count optimal.
 * Cost: O(n log n) time and O(n) copied storage.
 */
public final class Q451MaximumCompatibleMeetings { // Keep this question independently runnable and testable.
    private Q451MaximumCompatibleMeetings() { } // No object state is needed by this standalone exercise.
    public static int maximum(int[][] meetings) { // Expose the operation for an independent caller and the executable checks below.
        int[][] sorted=Arrays.stream(meetings).map(int[]::clone).toArray(int[][]::new); // Sort a copy so the caller retains the original order.
        for(int[] m:sorted) if(m.length!=2||m[0]>=m[1]) throw new IllegalArgumentException("Positive-length intervals required"); // Use unambiguous half-open meeting intervals.
        Arrays.sort(sorted,Comparator.comparingInt(m->m[1])); // Earliest finishing compatible meeting leaves the most future room.
        long finish=Long.MIN_VALUE; int count=0; // No meeting has yet consumed any time.
        for(int[] meeting:sorted) if(meeting[0]>=finish) { count++; finish=meeting[1]; } // Accept only meetings that do not overlap the current schedule.
        return count; // Every accepted meeting preserves the earliest available finishing frontier.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(maximum(new int[][]{{1,4},{2,3},{3,5},{5,7}})); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(maximum(new int[][]{})==0, "Q451 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(maximum(new int[][]{{1,2},{2,3}})==2, "Q451 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(maximum(new int[][]{{1,5},{2,4}})==1, "Q451 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
