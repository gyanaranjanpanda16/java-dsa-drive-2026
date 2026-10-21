package questions.q289; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q289: Merge k sorted integer feeds.
 * Contract: Every row is non-null and sorted nondecreasing; N is total elements.
 * Why: Because each feed is sorted, its head is its smallest unconsumed value. The minimum among all heads is therefore globally next; replacing only that head preserves the invariant.
 * Complexity: O(k+N log(k+1)) time; O(k) heap plus O(N) output.
 */
public final class Q289MergeSortedFeeds { // One class contains this question, its answer, and reproducible checks.

    public static String solve(int[][] feeds) { // Expose the answer as a reusable method as well as a runnable program.
        PriorityQueue<int[]> heap=new PriorityQueue<>(Comparator.comparingInt(c->feeds[c[0]][c[1]])); // Compare the value at each feed cursor.
        for(int f=0;f<feeds.length;f++) if(feeds[f].length>0) heap.offer(new int[]{f,0}); // Only nonempty feeds have a current head.
        List<Integer> result=new ArrayList<>(); // Append globally smallest remaining values in order.
        while(!heap.isEmpty()) { // Each iteration consumes exactly one feed element.
            int[] cursor=heap.poll(); // The minimum head is the smallest value in all remaining feeds.
            result.add(feeds[cursor[0]][cursor[1]]); // Emit that value before advancing its feed.
            cursor[1]++; // Advance only the feed whose head was consumed.
            if(cursor[1]<feeds[cursor[0]].length) heap.offer(cursor); // Replace the consumed head if this feed has more values.
        }
        return result.toString(); // Every input occurrence was emitted once.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(new int[][]{{1,4},{2,3},{}})); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(new int[][]{{1,4},{2,3},{}}), "[1, 2, 3, 4]"); // Multiple feeds and an empty feed
        Check.equal(solve(new int[][]{}), "[]"); // No feeds
        Check.equal(solve(new int[][]{{2,2},{2}}), "[2, 2, 2]"); // Equal timestamps remain distinct
    }
}
