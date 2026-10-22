package questions.q119;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q119: Distinguish direct transfer from ordinary queueing.
 * Try to transfer each value directly; when no consumer is waiting, enqueue it and return the final buffered FIFO sequence. LinkedTransferQueue distinguishes consumer handoff from plain acceptance into storage. transfer waits for consumption, while this tryTransfer/offer example stays nonblocking.
 * Complexity: O(n) total operations and O(n) space in this isolated scenario.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q119TransferVsEnqueue { // Keep this question independently runnable and easy to find.
    private Q119TransferVsEnqueue() { } // This exercise exposes behavior through static methods.
    public static List<Integer> solve(int[] values) { // Define the input and result types of this particular problem.
        TransferQueue<Integer> queue = new LinkedTransferQueue<>(); // This queue supports both buffering and producer-to-consumer transfer.
        for (int value : values) { // No consumer is waiting in this single-threaded example.
            if (!queue.tryTransfer(value)) queue.offer(value); // Failed tryTransfer does not enqueue, so explicitly buffer the value as a fallback.
        }
        List<Integer> result = new ArrayList<>(); // Capture buffered work after all transfer attempts.
        queue.drainTo(result); // Drain the FIFO buffer without waiting for future items.
        return result; // Every value appears once because a failed transfer left no duplicate behind.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[]{3,2,1}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[]{3,2,1}), List.of(3,2,1)); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[]{}), List.of()); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[]{4,4}), List.of(4,4)); // Case 3: verify the expected result for this input.
    }
}
