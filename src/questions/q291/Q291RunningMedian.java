package questions.q291; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q291: Report the median after each stream value.
 * Contract: All int values are accepted; medians are exact integers or half-integers representable in double.
 * Why: Maintain all lower-half values <= all upper-half values and sizes differing by at most one. Rebalancing moves only the boundary value, preserving order; the heap roots then expose the median.
 * Complexity: O(n log(n+1)) time and O(n) total storage.
 */
public final class Q291RunningMedian { // One class contains this question, its answer, and reproducible checks.

    public static String solve(int[] stream) { // Expose the answer as a reusable method as well as a runnable program.
        PriorityQueue<Integer> low=new PriorityQueue<>(Comparator.reverseOrder()); // The lower half exposes its greatest value.
        PriorityQueue<Integer> high=new PriorityQueue<>(); // The upper half exposes its least value.
        List<Double> medians=new ArrayList<>(); // Record a median for each processed prefix.
        for(int value:stream) { // Insert each arrival without sorting the whole prefix.
            if(low.isEmpty()||value<=low.peek()) low.offer(value); else high.offer(value); // Preserve ordering between the two halves.
            if(low.size()>high.size()+1) high.offer(low.poll()); // Move the largest lower value when the lower half is too large.
            if(high.size()>low.size()) low.offer(high.poll()); // Keep the lower half equal in size or one larger.
            double median=low.size()==high.size()?((long)low.peek()+high.peek())/2.0:low.peek(); // Widen before averaging to avoid int addition overflow.
            medians.add(median); // The two boundary roots determine the middle value or pair.
        }
        return medians.toString(); // Present medians in arrival order.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(new int[]{5,1,9,2})); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(new int[]{5,1,9,2}), "[5.0, 3.0, 5.0, 3.5]"); // Odd and even prefix sizes
        Check.equal(solve(new int[]{}), "[]"); // Empty stream
        Check.equal(solve(new int[]{2,2}), "[2.0, 2.0]"); // Duplicate medians
    }
}
