package questions.q114;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q114: Handle backpressure with nonblocking queue offers.
 * Return [accepted FIFO values, rejected values] while offering into a fixed-capacity queue with no concurrent consumer. Bounded queues express resource limits; offer exposes overload, while put would wait and could deadlock this single-threaded example.
 * Complexity: O(n) time and O(n+capacity) storage including returned rejection records.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q114BoundedQueueOffers { // Keep this question independently runnable and easy to find.
    private Q114BoundedQueueOffers() { } // This exercise exposes behavior through static methods.
    public static List<List<Integer>> solve(int capacity, int[] values) { // Define the input and result types of this particular problem.
        if (capacity <= 0) throw new IllegalArgumentException("positive capacity required"); // ArrayBlockingQueue cannot have zero capacity.
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(capacity); // Fix storage capacity rather than growing without a bound.
        List<Integer> rejected = new ArrayList<>(); // Make overload visible to the producer.
        for (int value : values) if (!queue.offer(value)) rejected.add(value); // offer returns false immediately when full instead of blocking.
        List<Integer> accepted = new ArrayList<>(); // Collect accepted values in FIFO order.
        queue.drainTo(accepted); // Remove all currently queued values into a separate destination.
        return List.of(accepted,rejected); // The caller can decide whether rejected work should be retried, dropped or persisted.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(2,new int[]{1,2,3}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(2,new int[]{1,2,3}), List.of(List.of(1,2),List.of(3))); // Case 1: verify the expected result for this input.
        Check.equal(solve(1,new int[]{}), List.of(List.of(),List.of())); // Case 2: verify the expected result for this input.
        Check.equal(solve(1,new int[]{4,4,5}), List.of(List.of(4),List.of(4,5))); // Case 3: verify the expected result for this input.
    }
}
