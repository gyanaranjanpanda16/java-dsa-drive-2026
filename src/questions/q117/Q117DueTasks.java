package questions.q117;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q117: Poll due tasks from a DelayQueue using a logical clock.
 * Return indices of tasks whose deadline is at or before now, sorted by deadline then input index. DelayQueue combines ordering with a readiness condition. This educational nonblocking snapshot uses a frozen logical clock; a production scheduler needs continuously updated remaining delays and must handle shutdown/interruption.
 * Complexity: O(n log(n+1)) time and O(n) space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q117DueTasks { // Keep this question independently runnable and easy to find.
    private Q117DueTasks() { } // This exercise exposes behavior through static methods.
    public static List<Integer> solve(int[] deadlines, int now) { // Define the input and result types of this particular problem.
        DelayQueue<Task> scheduled = new DelayQueue<>(); // DelayQueue exposes only entries whose remaining delay is nonpositive.
        for (int i = 0; i < deadlines.length; i++) scheduled.add(new Task(deadlines[i],i,now)); // Use a frozen logical time to make nonblocking examples reproducible.
        List<Integer> due = new ArrayList<>(); // Report task indices, ordering equal deadlines by original index.
        Task task; // Null means the head is not due, even when future tasks remain in the queue.
        while ((task = scheduled.poll()) != null) due.add(task.index); // Poll removes expired entries and immediately stops at the earliest future entry.
        return due; // No sleeping or wall-clock assumptions are needed for this snapshot simulation.
    }
    private static final class Task implements Delayed { // Model one scheduled item with a deterministic snapshot of logical time.
        final int deadline, index, now; // Keep ordering keys and the query time immutable.
        Task(int deadline, int index, int now) { this.deadline = deadline; this.index = index; this.now = now; } // Bind an item to this query.
        @Override public long getDelay(TimeUnit unit) { return unit.convert((long) deadline-now,TimeUnit.MILLISECONDS); } // Widen subtraction and express remaining logical delay in the requested unit.
        @Override public int compareTo(Delayed other) { // DelayQueue compares items to locate the earliest deadline.
            Task task = (Task) other; // This private queue contains only Task instances.
            int order = Integer.compare(deadline,task.deadline); // Earlier deadlines must be removed first.
            return order != 0 ? order : Integer.compare(index,task.index); // Give equal deadlines a reproducible FIFO tie-break.
        }
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[]{5,2,5,9},5))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[]{5,2,5,9},5), List.of(1,0,2)); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[]{2,3},1), List.of()); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[]{Integer.MIN_VALUE,Integer.MAX_VALUE},0), List.of(0)); // Case 3: verify the expected result for this input.
    }
}
