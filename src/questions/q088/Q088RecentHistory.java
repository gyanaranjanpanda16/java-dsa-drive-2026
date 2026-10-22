package questions.q088;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q088: Retain only the most recent events.
 * Keep the last capacity non-null events, including duplicates, in chronological order. A bounded history is useful for recent actions or log previews; ArrayDeque itself is resizable, so this method enforces the retention bound explicitly.
 * Complexity: O(n) amortized time and O(min(n,capacity)+1) working storage.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q088RecentHistory { // Keep this question independently runnable and easy to find.
    private Q088RecentHistory() { } // This exercise exposes behavior through static methods.
    public static List<String> solve(List<String> events, int capacity) { // Define the input and result types of this particular problem.
        if (capacity < 0) throw new IllegalArgumentException("negative capacity"); // A history length must be nonnegative.
        Deque<String> history = new ArrayDeque<>(); // Store events in chronological order with efficient endpoint changes.
        for (String event : events) { // Process events in arrival order.
            history.addLast(event); // The newest event belongs at the tail; null is unsupported by ArrayDeque.
            if (history.size() > capacity) history.removeFirst(); // Evict exactly the oldest event when the window overflows.
        }
        return new ArrayList<>(history); // Return a stable oldest-to-newest snapshot of retained events.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(List.of("a","b","c"),2))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(List.of("a","b","c"),2), List.of("b","c")); // Case 1: verify the expected result for this input.
        Check.equal(solve(List.of("a"),0), List.of()); // Case 2: verify the expected result for this input.
        Check.equal(solve(List.of(),3), List.of()); // Case 3: verify the expected result for this input.
    }
}
