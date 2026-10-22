package questions.q105;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q105: Count fixed categories with EnumMap.
 * Count NEW, RUNNING and DONE events, returning every category including those with zero occurrences. EnumMap is designed for enum keys and avoids a general-purpose hash table for a small fixed domain; useful for state-machine metrics.
 * Complexity: O(n+e) time and O(e) space for e enum constants (three here).
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q105EnumCounters { // Keep this question independently runnable and easy to find.
    private Q105EnumCounters() { } // This exercise exposes behavior through static methods.
    public static Map<Status,Integer> solve(Status[] events) { // Define the input and result types of this particular problem.
        EnumMap<Status,Integer> counts = new EnumMap<>(Status.class); // The enum class supplies a compact fixed key universe even for empty input.
        for (Status status : Status.values()) counts.put(status,0); // Include zero counts so the report contains every declared category.
        for (Status status : events) counts.merge(status,1,Integer::sum); // Increment the counter associated with each non-null event category.
        return counts; // EnumMap iterates in enum declaration order, making the output stable.
    }
    public enum Status { NEW, RUNNING, DONE } // Give the allowed metric categories explicit, type-safe names.

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new Status[]{Status.NEW,Status.DONE,Status.NEW}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new Status[]{Status.NEW,Status.DONE,Status.NEW}), Map.of(Status.NEW,2,Status.RUNNING,0,Status.DONE,1)); // Case 1: verify the expected result for this input.
        Check.equal(solve(new Status[]{}), Map.of(Status.NEW,0,Status.RUNNING,0,Status.DONE,0)); // Case 2: verify the expected result for this input.
        Check.equal(solve(new Status[]{Status.RUNNING}), Map.of(Status.NEW,0,Status.RUNNING,1,Status.DONE,0)); // Case 3: verify the expected result for this input.
    }
}
