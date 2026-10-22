package questions.q103;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q103: Look up the latest effective price at a timestamp.
 * Return the price active at each queried integer timestamp, or null before the first change. Prices and times are non-null map entries. floorEntry models versioned configuration, tax tables or historical rates where a value remains effective until replaced.
 * Complexity: O(n log n + q log(n+1)) time and O(n+q) space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q103EffectivePrice { // Keep this question independently runnable and easy to find.
    private Q103EffectivePrice() { } // This exercise exposes behavior through static methods.
    public static List<Integer> solve(Map<Integer,Integer> prices, int[] times) { // Define the input and result types of this particular problem.
        NavigableMap<Integer,Integer> timeline = new TreeMap<>(prices); // Order prices by their effective start time.
        List<Integer> result = new ArrayList<>(); // Null indicates that no price existed yet.
        for (int time : times) { // Each query is independent and need not arrive in sorted order.
            Map.Entry<Integer,Integer> active = timeline.floorEntry(time); // Find the latest change whose time is no greater than the query.
            result.add(active == null ? null : active.getValue()); // Preserve absent history without confusing it with a zero price.
        }
        return result; // A later change does not affect queries before its timestamp.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(Map.of(10,5,20,7),new int[]{9,10,19,20}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(Map.of(10,5,20,7),new int[]{9,10,19,20}), Arrays.asList(null,5,5,7)); // Case 1: verify the expected result for this input.
        Check.equal(solve(Map.of(),new int[]{1}), Arrays.asList((Integer) null)); // Case 2: verify the expected result for this input.
        Check.equal(solve(Map.of(0,0),new int[]{5}), List.of(0)); // Case 3: verify the expected result for this input.
    }
}
