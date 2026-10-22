package questions.q085;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q085: Create a detached immutable List.copyOf snapshot.
 * Return [changed source, immutable snapshot] after appending one non-null string to a mutable copy. List.copyOf captures membership independently; this is a shallow snapshot, so mutable element objects would still be shared.
 * Complexity: O(n) time and O(n) space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q085ImmutableSnapshot { // Keep this question independently runnable and easy to find.
    private Q085ImmutableSnapshot() { } // This exercise exposes behavior through static methods.
    public static List<List<String>> solve(List<String> input, String appended) { // Define the input and result types of this particular problem.
        List<String> working = new ArrayList<>(input); // Create a mutable source owned by this operation.
        List<String> snapshot = List.copyOf(working); // Capture the current elements in an unmodifiable list that rejects nulls.
        working.add(appended); // Later structural changes to the source must not alter the snapshot.
        return List.of(working,snapshot); // Return both states so their independence is visible.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(List.of("a","b"),"c"))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(List.of("a","b"),"c"), List.of(List.of("a","b","c"),List.of("a","b"))); // Case 1: verify the expected result for this input.
        Check.equal(solve(List.of(),"x"), List.of(List.of("x"),List.of())); // Case 2: verify the expected result for this input.
        Check.equal(solve(List.of("q"),"q"), List.of(List.of("q","q"),List.of("q"))); // Case 3: verify the expected result for this input.
    }
}
