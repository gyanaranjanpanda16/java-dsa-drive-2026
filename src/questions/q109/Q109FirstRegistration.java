package questions.q109;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q109: Keep the first registered value with putIfAbsent.
 * Keep the first value for each repeated non-null key. putIfAbsent is useful for idempotent registration and atomic initialization; in actual concurrent calls the winning insertion depends on scheduling, whereas this example supplies a deterministic sequence.
 * Complexity: Expected O(n) updates plus O(u log u) report construction and O(u) space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q109FirstRegistration { // Keep this question independently runnable and easy to find.
    private Q109FirstRegistration() { } // This exercise exposes behavior through static methods.
    public static Map<String,String> solve(String[] keys, String[] values) { // Define the input and result types of this particular problem.
        if (keys.length != values.length) throw new IllegalArgumentException("parallel arrays differ"); // Every registration needs a key and a value.
        ConcurrentMap<String,String> registered = new ConcurrentHashMap<>(); // Expose the interface that promises atomic conditional insertion.
        for (int i = 0; i < keys.length; i++) registered.putIfAbsent(keys[i],values[i]); // A later registration cannot overwrite the first successfully inserted value.
        return new TreeMap<>(registered); // Return a detached ordered report once the sequential demonstration finishes.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new String[]{"a","b","a"},new String[]{"one","two","late"}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new String[]{"a","b","a"},new String[]{"one","two","late"}), Map.of("a","one","b","two")); // Case 1: verify the expected result for this input.
        Check.equal(solve(new String[]{},new String[]{}), Map.of()); // Case 2: verify the expected result for this input.
        Check.equal(solve(new String[]{"x","x"},new String[]{"first","second"}), Map.of("x","first")); // Case 3: verify the expected result for this input.
    }
}
