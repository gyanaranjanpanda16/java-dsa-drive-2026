package questions.q083;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q083: Sort words by length while preserving equal-length order.
 * Sort non-null strings by UTF-16 length, retaining original order among equal lengths. List.sort is stable, so an unnecessary alphabetical tie-breaker would change the requested behavior.
 * Complexity: O(n log n) comparisons and O(n) copy/sorting space; String.length is O(1).
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q083StableLengthSort { // Keep this question independently runnable and easy to find.
    private Q083StableLengthSort() { } // This exercise exposes behavior through static methods.
    public static List<String> solve(List<String> input) { // Define the input and result types of this particular problem.
        List<String> ordered = new ArrayList<>(input); // Preserve encounter order before the stable sort begins.
        ordered.sort(Comparator.comparingInt(String::length)); // Compare only length so stability determines the order of ties.
        return ordered; // Equal-length words remain in their original relative order.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(List.of("bb","a","cc","ddd")))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(List.of("bb","a","cc","ddd")), List.of("a","bb","cc","ddd")); // Case 1: verify the expected result for this input.
        Check.equal(solve(List.of("b","a")), List.of("b","a")); // Case 2: verify the expected result for this input.
        Check.equal(solve(List.of()), List.of()); // Case 3: verify the expected result for this input.
    }
}
