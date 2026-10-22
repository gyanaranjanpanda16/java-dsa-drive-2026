package questions.q093;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q093: Deduplicate values while preserving first-seen order.
 * Return one copy of each value in first-appearance order. LinkedHashSet is useful for de-duplicating event identifiers or search results while keeping their original ranking; HashSet alone does not promise this order.
 * Complexity: Expected O(n) time and O(u) result/working space for u unique values.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q093FirstSeenOrder { // Keep this question independently runnable and easy to find.
    private Q093FirstSeenOrder() { } // This exercise exposes behavior through static methods.
    public static List<String> solve(List<String> input) { // Define the input and result types of this particular problem.
        Set<String> unique = new LinkedHashSet<>(); // Combine hash-based membership with insertion-order traversal.
        for (String value : input) unique.add(value); // Re-adding an existing value does not move its original position.
        return new ArrayList<>(unique); // Copy encounter order into a list for callers that need positional access.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(List.of("b","a","b","c")))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(List.of("b","a","b","c")), List.of("b","a","c")); // Case 1: verify the expected result for this input.
        Check.equal(solve(List.of("x","x")), List.of("x")); // Case 2: verify the expected result for this input.
        Check.equal(solve(List.of()), List.of()); // Case 3: verify the expected result for this input.
    }
}
