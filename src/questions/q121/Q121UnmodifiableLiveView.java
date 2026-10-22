package questions.q121;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q121: Contrast a read-only live view with a detached snapshot.
 * Return [unmodifiable live view, detached immutable snapshot] after appending to their original source. Unmodifiable describes what a reference allows, not whether the underlying data can ever change. This distinction matters when exposing internal state through public APIs. Require non-null elements.
 * Complexity: O(n) time and O(n) space for copies; wrapping an unmodifiable view alone is O(1).
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q121UnmodifiableLiveView { // Keep this question independently runnable and easy to find.
    private Q121UnmodifiableLiveView() { } // This exercise exposes behavior through static methods.
    public static List<List<String>> solve(List<String> input, String appended) { // Define the input and result types of this particular problem.
        List<String> source = new ArrayList<>(input); // Keep a mutable backing list owned by this example.
        List<String> view = Collections.unmodifiableList(source); // Block mutations through this reference while retaining the backing list relationship.
        List<String> snapshot = List.copyOf(source); // Capture current non-null membership independently of future structural changes.
        source.add(appended); // Mutation through the original mutable reference is still permitted.
        return List.of(view,snapshot); // The view sees the appended value; the snapshot retains its earlier membership.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(List.of("a"),"b"))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(List.of("a"),"b"), List.of(List.of("a","b"),List.of("a"))); // Case 1: verify the expected result for this input.
        Check.equal(solve(List.of(),"x"), List.of(List.of("x"),List.of())); // Case 2: verify the expected result for this input.
        Check.equal(solve(List.of("x","y"),"x"), List.of(List.of("x","y","x"),List.of("x","y"))); // Case 3: verify the expected result for this input.
    }
}
