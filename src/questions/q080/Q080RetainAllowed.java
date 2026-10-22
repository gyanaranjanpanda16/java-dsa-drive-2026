package questions.q080;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q080: Filter a list using set membership while preserving duplicates.
 * Keep only allowed values while preserving input order and multiplicity. A set supplies membership checks, while ArrayList retains list semantics; unlike a set intersection, duplicates survive.
 * Complexity: Expected O(n+m) time and O(n+u) space for n input values and m allowed values, u distinct allowed.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q080RetainAllowed { // Keep this question independently runnable and easy to find.
    private Q080RetainAllowed() { } // This exercise exposes behavior through static methods.
    public static List<String> solve(List<String> input, Collection<String> allowed) { // Define the input and result types of this particular problem.
        Set<String> membership = new HashSet<>(allowed); // Avoid repeated linear searches through the allowed collection.
        List<String> result = new ArrayList<>(input); // Preserve the original sequence and its duplicates.
        result.retainAll(membership); // Remove each occurrence whose value is absent from the allowed set.
        return result; // This is list filtering, so repeated allowed values remain repeated.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(List.of("a","b","a"),List.of("a")))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(List.of("a","b","a"),List.of("a")), List.of("a","a")); // Case 1: verify the expected result for this input.
        Check.equal(solve(List.of("a"),List.of()), List.of()); // Case 2: verify the expected result for this input.
        Check.equal(solve(List.of(),List.of("a")), List.of()); // Case 3: verify the expected result for this input.
    }
}
