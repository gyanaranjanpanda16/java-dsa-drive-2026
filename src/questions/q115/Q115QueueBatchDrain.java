package questions.q115;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q115: Take a bounded batch from a linked blocking queue.
 * Return [up to batchSize oldest items, remaining items] using drainTo. Batching can reduce per-item processing overhead. LinkedBlockingQueue also accepts an explicit capacity for bounded producer-consumer pipelines; this finite-input example does not wait or start threads.
 * Complexity: O(n) construction/result-copy time and O(n) space; draining k items takes O(k).
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q115QueueBatchDrain { // Keep this question independently runnable and easy to find.
    private Q115QueueBatchDrain() { } // This exercise exposes behavior through static methods.
    public static List<List<Integer>> solve(List<Integer> values, int batchSize) { // Define the input and result types of this particular problem.
        if (batchSize < 0) throw new IllegalArgumentException("negative batch size"); // Define zero as a valid request for no work.
        BlockingQueue<Integer> pending = new LinkedBlockingQueue<>(values); // Preserve the initial FIFO work order with linked nodes.
        List<Integer> batch = new ArrayList<>(); // Use a different collection as the drain destination.
        pending.drainTo(batch,batchSize); // Move at most the requested number without waiting for more arrivals.
        return List.of(batch,new ArrayList<>(pending)); // Show both the selected batch and the work still pending.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(List.of(1,2,3),2))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(List.of(1,2,3),2), List.of(List.of(1,2),List.of(3))); // Case 1: verify the expected result for this input.
        Check.equal(solve(List.of(1),0), List.of(List.of(),List.of(1))); // Case 2: verify the expected result for this input.
        Check.equal(solve(List.of(1,2),8), List.of(List.of(1,2),List.of())); // Case 3: verify the expected result for this input.
    }
}
