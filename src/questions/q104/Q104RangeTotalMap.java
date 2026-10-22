package questions.q104;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q104: Total map values within an inclusive key range.
 * Sum values whose integer keys lie in [low,high]. A TreeMap range view is useful for timestamp windows or score bands, but it does not store aggregate sums; repeated range-total workloads may justify a Fenwick or segment tree instead.
 * Complexity: O(n log n + k) time including construction, and O(n) space; querying a prebuilt map takes O(log n+k).
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q104RangeTotalMap { // Keep this question independently runnable and easy to find.
    private Q104RangeTotalMap() { } // This exercise exposes behavior through static methods.
    public static long solve(Map<Integer,Integer> input, int low, int high) { // Define the input and result types of this particular problem.
        if (low > high) throw new IllegalArgumentException("reversed bounds"); // Both endpoints must define an ordered interval.
        NavigableMap<Integer,Integer> ordered = new TreeMap<>(input); // Tree order supports locating the first matching key.
        long total = 0L; // Widen aggregation so sums of int values need not fit in int.
        for (int value : ordered.subMap(low,true,high,true).values()) total += value; // Traverse only values with keys inside the inclusive bounds.
        return total; // Missing keys contribute nothing because a map stores only actual entries.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(Map.of(1,4,3,5,5,6),1,3))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(Map.of(1,4,3,5,5,6),1,3), 9L); // Case 1: verify the expected result for this input.
        Check.equal(solve(Map.of(),0,0), 0L); // Case 2: verify the expected result for this input.
        Check.equal(solve(Map.of(2,Integer.MAX_VALUE,3,Integer.MAX_VALUE),2,3), 4294967294L); // Case 3: verify the expected result for this input.
    }
}
