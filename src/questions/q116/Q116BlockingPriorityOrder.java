package questions.q116;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q116: Consume a priority queue in sorted removal order.
 * Return values in priority removal order using PriorityBlockingQueue. This concurrent collection supports priority-based consumers, but equal-priority objects have no automatic FIFO guarantee and iteration is not sorted. Use a sequence tie-breaker when equal-priority arrival order matters.
 * Complexity: O(n log(n+1)) time and O(n) space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q116BlockingPriorityOrder { // Keep this question independently runnable and easy to find.
    private Q116BlockingPriorityOrder() { } // This exercise exposes behavior through static methods.
    public static List<Integer> solve(List<Integer> values) { // Define the input and result types of this particular problem.
        BlockingQueue<Integer> priority = new PriorityBlockingQueue<>(); // Natural order makes the smallest available integer highest priority.
        priority.addAll(values); // Insert all non-null tasks before this deterministic drain.
        List<Integer> result = new ArrayList<>(); // Store removal order, which differs from iterator order guarantees.
        Integer next; // Null marks the absence of a currently available item.
        while ((next = priority.poll()) != null) result.add(next); // Repeated removals expose priority order without blocking.
        return result; // PriorityBlockingQueue is logically unbounded; it does not provide capacity backpressure.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(List.of(3,1,2,1)))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(List.of(3,1,2,1)), List.of(1,1,2,3)); // Case 1: verify the expected result for this input.
        Check.equal(solve(List.of()), List.of()); // Case 2: verify the expected result for this input.
        Check.equal(solve(List.of(-2,0)), List.of(-2,0)); // Case 3: verify the expected result for this input.
    }
}
