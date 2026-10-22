package questions.q118;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q118: Observe that a SynchronousQueue has no storage buffer.
 * Count rejected nonblocking offers to a SynchronousQueue when no consumer exists. A rendezvous queue transfers directly between producer and consumer, useful when work should start only if a worker is ready; replacing offer with put here would wait indefinitely.
 * Complexity: O(n) operations and O(1) storage in this no-consumer scenario.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q118RendezvousQueue { // Keep this question independently runnable and easy to find.
    private Q118RendezvousQueue() { } // This exercise exposes behavior through static methods.
    public static int solve(int[] values) { // Define the input and result types of this particular problem.
        BlockingQueue<Integer> handoff = new SynchronousQueue<>(); // A successful insertion requires a consumer waiting for a direct handoff.
        int rejected = 0; // Count attempts that cannot be accepted immediately.
        for (int value : values) { // This example intentionally has no consumer thread.
            if (!handoff.offer(value)) rejected++; // Nonblocking offer fails because there is no receiver and no internal capacity.
        }
        return rejected; // No values were buffered for a later consumer.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[]{1,2,3}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[]{1,2,3}), 3); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[]{}), 0); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[]{7}), 1); // Case 3: verify the expected result for this input.
    }
}
