package questions.q044;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q044: Count index pairs with a target sum.
 * Count unordered index pairs i<j whose values sum to target. Repeated values create distinct pairs, so a frequency map is required.
 * Complexity: Expected O(n) time and O(u) space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q044PairCount { // Keep this question independently runnable and easy to find.
    private Q044PairCount() { } // This exercise exposes behavior through static methods.
    public static long solve(int[] a, int target) { // Define the input and result types of this particular problem.
        Map<Long,Long> frequencies = new HashMap<>(); // Store multiplicity rather than just membership.
        long pairs = 0; // The number of index pairs may exceed int range.
        for (int value : a) { // Every pair is counted when its later element is processed.
            pairs += frequencies.getOrDefault((long) target - value, 0L); // Every previous complement forms a distinct index pair.
            frequencies.merge((long) value, 1L, Long::sum); // Make this value available only to later elements.
        }
        return pairs; // No pair was double-counted because its later index is unique.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[]{1,1,1},2))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[]{1,1,1},2), 3L); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[]{1,2,3,4},5), 2L); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[]{},0), 0L); // Case 3: verify the expected result for this input.
    }
}
