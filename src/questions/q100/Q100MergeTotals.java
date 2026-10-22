package questions.q100;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q100: Aggregate transaction totals with Map.merge.
 * Sum integer transaction amounts by non-null category into long totals. Map.merge combines absent-key insertion and present-key accumulation; use long because multiple int amounts can exceed int range. This is useful for reports and counters.
 * Complexity: O(n log u) time and O(u) space for u categories.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q100MergeTotals { // Keep this question independently runnable and easy to find.
    private Q100MergeTotals() { } // This exercise exposes behavior through static methods.
    public static Map<String,Long> solve(String[] categories, int[] amounts) { // Define the input and result types of this particular problem.
        if (categories.length != amounts.length) throw new IllegalArgumentException("parallel arrays differ"); // Each category needs exactly one amount.
        Map<String,Long> totals = new TreeMap<>(); // Use ordered keys so the returned report prints deterministically.
        for (int i = 0; i < categories.length; i++) { // Read each transaction once.
            totals.merge(categories[i],(long) amounts[i],Long::sum); // Insert the first amount or combine it with the existing total.
        }
        return totals; // Refunds may be negative and zero totals remain present.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new String[]{"food","travel","food"},new int[]{10,4,-3}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new String[]{"food","travel","food"},new int[]{10,4,-3}), Map.of("food",7L,"travel",4L)); // Case 1: verify the expected result for this input.
        Check.equal(solve(new String[]{"x","x"},new int[]{Integer.MAX_VALUE,1}), Map.of("x",2147483648L)); // Case 2: verify the expected result for this input.
        Check.equal(solve(new String[]{},new int[]{}), Map.of()); // Case 3: verify the expected result for this input.
    }
}
