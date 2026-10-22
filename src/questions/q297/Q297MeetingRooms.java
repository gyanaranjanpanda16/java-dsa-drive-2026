package questions.q297; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q297: Find the minimum number of meeting rooms.
 * Contract: Rows are [start,end) with start<end; every row has two int values.
 * Why: Sorting by start time makes the heap contain precisely the intervals still active at each arrival. Their maximum count is both unavoidable and sufficient: released rooms can always be reused.
 * Complexity: O(n log(n+1)) time and O(n) space.
 */
public final class Q297MeetingRooms { // One class contains this question, its answer, and reproducible checks.

    public static int solve(int[][] meetings) { // Expose the answer as a reusable method as well as a runnable program.
        int[][] sorted=meetings.clone(); // Sort only row references, leaving caller order unchanged.
        Arrays.sort(sorted,Comparator.comparingInt(a->a[0])); // Process meetings by start time so past releases are known.
        PriorityQueue<Integer> endings=new PriorityQueue<>(); // The earliest finishing active meeting is released first.
        int rooms=0; // No room is needed before the first meeting.
        for(int[] meeting:sorted) { // Each row is a half-open interval with positive duration.
            while(!endings.isEmpty()&&endings.peek()<=meeting[0]) endings.poll(); // A room ending at this start is reusable.
            endings.offer(meeting[1]); // The current meeting occupies one room until its end.
            rooms=Math.max(rooms,endings.size()); // Maximum simultaneous occupancy is a necessary lower bound.
        }
        return rooms; // Allocating one room per overlapping meeting reaches that bound.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(new int[][]{{0,30},{5,10},{15,20}})); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(new int[][]{{0,30},{5,10},{15,20}}), 2); // Overlapping meetings
        Check.equal(solve(new int[][]{{1,2},{2,3}}), 1); // Touching intervals reuse a room
        Check.equal(solve(new int[][]{}), 0); // No meetings
    }
}
