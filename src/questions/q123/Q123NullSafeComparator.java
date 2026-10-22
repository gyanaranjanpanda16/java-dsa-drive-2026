package questions.q123;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q123: Sort nullable names with missing values last.
 * Sort names in Java natural string order with null values last. Wrapping a comparator with nullsLast defines missing-data behavior explicitly; String.compareTo alone throws when a compared value is null. Ordering is UTF-16 lexicographic, not locale-aware dictionary collation.
 * Complexity: O(n log n) comparisons and O(n) auxiliary/output storage; each string comparison can examine a shared prefix.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q123NullSafeComparator { // Keep this question independently runnable and easy to find.
    private Q123NullSafeComparator() { } // This exercise exposes behavior through static methods.
    public static List<String> solve(List<String> input) { // Define the input and result types of this particular problem.
        List<String> sorted = new ArrayList<>(input); // Copy input because sorting mutates list positions.
        Comparator<String> order = Comparator.nullsLast(Comparator.naturalOrder()); // Handle null before delegating non-null string comparisons.
        sorted.sort(order); // Apply a total ordering that places missing names after all present names.
        return sorted; // Null entries are preserved rather than silently removed or replaced.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(Arrays.asList("b",null,"a")))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(Arrays.asList("b",null,"a")), Arrays.asList("a","b",null)); // Case 1: verify the expected result for this input.
        Check.equal(solve(Arrays.asList(null,null)), Arrays.asList(null,null)); // Case 2: verify the expected result for this input.
        Check.equal(solve(List.of()), List.of()); // Case 3: verify the expected result for this input.
    }
}
