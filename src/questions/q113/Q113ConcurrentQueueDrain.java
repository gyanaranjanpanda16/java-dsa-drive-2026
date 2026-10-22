package questions.q113;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q113: Drain a concurrent FIFO with poll.
 * Drain a ConcurrentLinkedQueue in FIFO order. poll avoids a separate check-then-remove race and returns null immediately when empty; with active producers, observing empty does not prove production has finished, so real pipelines need a completion protocol.
 * Complexity: O(n) total queue operations and O(n) space in this single-consumer demonstration.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q113ConcurrentQueueDrain { // Keep this question independently runnable and easy to find.
    private Q113ConcurrentQueueDrain() { } // This exercise exposes behavior through static methods.
    public static List<Integer> solve(List<Integer> values) { // Define the input and result types of this particular problem.
        Queue<Integer> queue = new ConcurrentLinkedQueue<>(values); // Enqueue non-null items in their encounter order.
        List<Integer> drained = new ArrayList<>(); // Capture the order observed by this single consumer.
        Integer next; // Null is reserved by the queue as the empty result sentinel.
        while ((next = queue.poll()) != null) drained.add(next); // Poll combines retrieval and removal without a separate size or empty check.
        return drained; // With no concurrent producers, every original value is consumed exactly once.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(List.of(3,1,2)))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(List.of(3,1,2)), List.of(3,1,2)); // Case 1: verify the expected result for this input.
        Check.equal(solve(List.of()), List.of()); // Case 2: verify the expected result for this input.
        Check.equal(solve(List.of(7,7)), List.of(7,7)); // Case 3: verify the expected result for this input.
    }
}
