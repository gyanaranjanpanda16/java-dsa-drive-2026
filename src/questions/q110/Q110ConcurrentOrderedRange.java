package questions.q110;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q110: Read an ordered concurrent-map range.
 * Return non-null values whose non-null integer keys lie in [low,high], ordered by key. ConcurrentSkipListMap supports concurrent ordered lookup where ConcurrentHashMap offers no sorted traversal. This isolated example is deterministic; traversing during concurrent updates is weakly consistent, not an atomic snapshot.
 * Complexity: Expected O(n log(n+1)+k) time and O(n+k) space; a range read on an existing map is expected O(log n+k).
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q110ConcurrentOrderedRange { // Keep this question independently runnable and easy to find.
    private Q110ConcurrentOrderedRange() { } // This exercise exposes behavior through static methods.
    public static List<String> solve(Map<Integer,String> input, int low, int high) { // Define the input and result types of this particular problem.
        if (low > high) throw new IllegalArgumentException("reversed bounds"); // Define an inclusive ordered key interval.
        ConcurrentNavigableMap<Integer,String> ordered = new ConcurrentSkipListMap<>(input); // Combine sorted keys with concurrent access support.
        return new ArrayList<>(ordered.subMap(low,true,high,true).values()); // Copy values in ascending-key order from the bounded view.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(Map.of(3,"c",1,"a",2,"b"),2,3))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(Map.of(3,"c",1,"a",2,"b"),2,3), List.of("b","c")); // Case 1: verify the expected result for this input.
        Check.equal(solve(Map.of(),0,1), List.of()); // Case 2: verify the expected result for this input.
        Check.equal(solve(Map.of(1,"a"),2,3), List.of()); // Case 3: verify the expected result for this input.
    }
}
