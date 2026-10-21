package questions.q043;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q043: Find distinct indices adding to a target.
 * Return one pair of distinct indices with the requested sum, or an empty array. Remembering previous values replaces repeated linear searches for a complement.
 * Complexity: Expected O(n) time and O(n) space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q043TwoSumIndices { // Keep this question independently runnable and easy to find.
    private Q043TwoSumIndices() { } // This exercise exposes behavior through static methods.
    public static int[] solve(int[] a, int target) { // Define the input and result types of this particular problem.
        Map<Long,Integer> previous = new HashMap<>(); // Associate each seen value with an earlier index.
        for (int i = 0; i < a.length; i++) { // Restrict the stored candidates to positions before i.
            Integer partner = previous.get((long) target - a[i]); // Widen before subtraction so complement arithmetic cannot overflow int.
            if (partner != null) return new int[]{partner,i}; // The stored partner is necessarily a different earlier element.
            previous.putIfAbsent((long) a[i], i); // Insert after querying to prevent reusing the current element.
        }
        return new int[0]; // No valid pair exists under the chosen contract.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[]{2,7,11},9))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[]{2,7,11},9), new int[]{0,1}); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[]{3,3},6), new int[]{0,1}); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[]{1},2), new int[]{}); // Case 3: verify the expected result for this input.
    }
}
