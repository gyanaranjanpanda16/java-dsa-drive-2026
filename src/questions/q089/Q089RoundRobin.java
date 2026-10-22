package questions.q089;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q089: Simulate fair round-robin task scheduling.
 * Return completion indices for positive-duration tasks arriving together, with a positive time quantum. A FIFO queue gives each unfinished task a turn before it can run again; this models the core of time-sliced scheduling without threads or wall-clock timing.
 * Complexity: O(n + sum ceil(duration[i]/quantum)) time and O(n) space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q089RoundRobin { // Keep this question independently runnable and easy to find.
    private Q089RoundRobin() { } // This exercise exposes behavior through static methods.
    public static List<Integer> solve(int[] duration, int quantum) { // Define the input and result types of this particular problem.
        if (quantum <= 0) throw new IllegalArgumentException("positive quantum required"); // A nonpositive slice cannot make progress.
        int[] remaining = duration.clone(); // Track work without altering caller-owned durations.
        Deque<Integer> ready = new ArrayDeque<>(); // Store task indices so ties follow arrival order.
        for (int i = 0; i < remaining.length; i++) { // All tasks arrive at time zero in index order.
            if (remaining[i] <= 0) throw new IllegalArgumentException("positive durations required"); // This model excludes zero-work tasks.
            ready.addLast(i); // Place each task at the rear of the initial ready queue.
        }
        List<Integer> completed = new ArrayList<>(); // Record only completions, not every time slice.
        while (!ready.isEmpty()) { // Each turn reduces some positive remaining duration.
            int task = ready.removeFirst(); // The longest-waiting ready task receives the next slice.
            remaining[task] -= Math.min(remaining[task],quantum); // Consume at most one quantum without making work negative.
            if (remaining[task] == 0) completed.add(task); // Finished tasks leave the scheduler permanently.
            else ready.addLast(task); // Unfinished tasks yield to every task already waiting.
        }
        return completed; // Completion order can differ from arrival order while service remains cyclic.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[]{5,2,3},2))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[]{5,2,3},2), List.of(1,2,0)); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[]{1,1},5), List.of(0,1)); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[]{},1), List.of()); // Case 3: verify the expected result for this input.
    }
}
