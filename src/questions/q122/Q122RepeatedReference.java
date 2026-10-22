package questions.q122;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q122: See why nCopies does not clone mutable elements.
 * Create count references with Collections.nCopies, mutate the shared StringBuilder, and return each observed string. An unmodifiable list can still contain mutable objects; repeated references are useful for immutable defaults but can accidentally share mutable state.
 * Complexity: O(initial.length + suffix.length + count * finalTextLength) time/output storage; nCopies itself uses O(1) storage.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q122RepeatedReference { // Keep this question independently runnable and easy to find.
    private Q122RepeatedReference() { } // This exercise exposes behavior through static methods.
    public static List<String> solve(int count, String initial, String suffix) { // Define the input and result types of this particular problem.
        if (count < 0) throw new IllegalArgumentException("negative count"); // A repetition count must describe a valid list size.
        StringBuilder shared = new StringBuilder(initial); // Create one mutable element object.
        List<StringBuilder> repeated = Collections.nCopies(count,shared); // Repeat the same reference rather than allocating count independent builders.
        shared.append(suffix); // Mutating that object is visible through every stored reference.
        List<String> observed = new ArrayList<>(); // Freeze the observed content into immutable strings for the result.
        for (StringBuilder value : repeated) observed.add(value.toString()); // Every entry points to the one changed builder.
        return observed; // Use a loop constructing new objects when independent mutable elements are required.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(3,"a","b"))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(3,"a","b"), List.of("ab","ab","ab")); // Case 1: verify the expected result for this input.
        Check.equal(solve(0,"a","b"), List.of()); // Case 2: verify the expected result for this input.
        Check.equal(solve(1,"","x"), List.of("x")); // Case 3: verify the expected result for this input.
    }
}
