package questions.q101;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q101: Build a reverse lookup index for shared values.
 * Invert non-null string mappings into value-to-sorted-keys lists. A plain swapped map would overwrite duplicate values; a multi-value index supports department-to-employees or tag-to-items lookups without losing relationships.
 * Complexity: O(n log n) time in total for grouping and sorting, and O(n) space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q101InvertIndex { // Keep this question independently runnable and easy to find.
    private Q101InvertIndex() { } // This exercise exposes behavior through static methods.
    public static Map<String,List<String>> solve(Map<String,String> input) { // Define the input and result types of this particular problem.
        Map<String,List<String>> inverse = new TreeMap<>(); // A value can belong to many original keys, so each result bucket is a list.
        for (Map.Entry<String,String> entry : input.entrySet()) { // Visit each original relationship once.
            inverse.computeIfAbsent(entry.getValue(),ignored -> new ArrayList<>()).add(entry.getKey()); // Preserve every key even when several share a value.
        }
        inverse.values().forEach(Collections::sort); // Input-map traversal order is unspecified, so explicitly order each bucket.
        return inverse; // Both value groups and their member keys have deterministic natural order.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(Map.of("amy","dev","bob","ops","cat","dev")))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(Map.of("amy","dev","bob","ops","cat","dev")), Map.of("dev",List.of("amy","cat"),"ops",List.of("bob"))); // Case 1: verify the expected result for this input.
        Check.equal(solve(Map.of()), Map.of()); // Case 2: verify the expected result for this input.
        Check.equal(solve(Map.of("x","x")), Map.of("x",List.of("x"))); // Case 3: verify the expected result for this input.
    }
}
