package questions.q124;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q124: Preserve arrival order among equal-priority tasks.
 * Return task names ordered by ascending priority and then arrival order. PriorityQueue does not guarantee stability for comparator ties; adding a sequence key creates the desired fairness rule. Inputs must remain unchanged during this method because the comparator reads their priority array.
 * Complexity: O(n log(n+1)) time and O(n) space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q124StablePriorityQueue { // Keep this question independently runnable and easy to find.
    private Q124StablePriorityQueue() { } // This exercise exposes behavior through static methods.
    public static List<String> solve(String[] names, int[] priorities) { // Define the input and result types of this particular problem.
        if (names.length != priorities.length) throw new IllegalArgumentException("parallel arrays differ"); // Each task needs exactly one priority.
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt((Integer i) -> priorities[i]).thenComparingInt(Integer::intValue)); // Compare immutable priorities first and original arrival indices second.
        for (int i = 0; i < names.length; i++) queue.add(i); // Store indices so names need not be unique or comparable.
        List<String> result = new ArrayList<>(); // Collect tasks in execution order.
        while (!queue.isEmpty()) result.add(names[queue.remove()]); // Repeated head removal respects both priority and the explicit tie-break.
        return result; // Lower numeric priority wins; equal-priority tasks remain FIFO.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new String[]{"a","b","c"},new int[]{2,1,2}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new String[]{"a","b","c"},new int[]{2,1,2}), List.of("b","a","c")); // Case 1: verify the expected result for this input.
        Check.equal(solve(new String[]{"x","y"},new int[]{1,1}), List.of("x","y")); // Case 2: verify the expected result for this input.
        Check.equal(solve(new String[]{},new int[]{}), List.of()); // Case 3: verify the expected result for this input.
    }
}
