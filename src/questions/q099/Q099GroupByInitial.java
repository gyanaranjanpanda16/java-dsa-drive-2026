package questions.q099;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q099: Group names with computeIfAbsent.
 * Group non-null nonempty names by their first UTF-16 character, preserving order inside groups. computeIfAbsent expresses lazy bucket creation for indexes and aggregation; the lambda only allocates an empty list and does not modify the map recursively.
 * Complexity: O(n log u) map operations and O(n+u) space for u distinct initials.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q099GroupByInitial { // Keep this question independently runnable and easy to find.
    private Q099GroupByInitial() { } // This exercise exposes behavior through static methods.
    public static Map<Character,List<String>> solve(List<String> names) { // Define the input and result types of this particular problem.
        Map<Character,List<String>> grouped = new TreeMap<>(); // Sort group keys while retaining insertion order inside each list.
        for (String name : names) { // Process names in their encounter order.
            if (name.isEmpty()) throw new IllegalArgumentException("empty name has no initial"); // The grouping key requires a first UTF-16 code unit.
            char initial = name.charAt(0); // This example is case-sensitive and groups by UTF-16 char, not full Unicode code point.
            grouped.computeIfAbsent(initial,key -> new ArrayList<>()).add(name); // Create a bucket only on its first occurrence, then append to the existing bucket.
        }
        return grouped; // Each name belongs to exactly one initial group and duplicates remain visible.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(List.of("Ana","Bob","Amy")))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(List.of("Ana","Bob","Amy")), Map.of('A',List.of("Ana","Amy"),'B',List.of("Bob"))); // Case 1: verify the expected result for this input.
        Check.equal(solve(List.of("a","A")), Map.of('a',List.of("a"),'A',List.of("A"))); // Case 2: verify the expected result for this input.
        Check.equal(solve(List.of()), Map.of()); // Case 3: verify the expected result for this input.
    }
}
