package questions.q046;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q046: Answer multiple half-open range-sum queries.
 * For valid query pairs [left,right), return each interval total. Prefix subtraction reuses the same preprocessing across many questions; right is excluded and an empty range sums to zero.
 * Complexity: O(n+q) time and O(n+q) space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q046BatchRangeSums { // Keep this question independently runnable and easy to find.
    private Q046BatchRangeSums() { } // This exercise exposes behavior through static methods.
    public static long[] solve(int[] a, int[][] queries) { // Define the input and result types of this particular problem.
        long[] prefix = new long[a.length + 1]; // Precompute reusable sums to avoid rescanning overlapping ranges.
        for (int i = 0; i < a.length; i++) prefix[i + 1] = prefix[i] + a[i]; // Extend each prefix by one value.
        long[] answers = new long[queries.length]; // Keep query results in their original order.
        for (int i = 0; i < queries.length; i++) { // Each query is represented as [left,right), with two endpoints.
            int left = queries[i][0], right = queries[i][1]; // Name the endpoints so their inclusion convention stays explicit.
            if (left < 0 || right < left || right > a.length) throw new IllegalArgumentException("invalid range"); // Reject ranges outside the original array.
            answers[i] = prefix[right] - prefix[left]; // Cancel the prefix before left, leaving exactly the requested interval.
        }
        return answers; // Each query took constant work after preprocessing.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[]{2,4,1},new int[][]{{0,2},{1,3}}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[]{2,4,1},new int[][]{{0,2},{1,3}}), new long[]{6,5}); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[]{},new int[][]{{0,0}}), new long[]{0}); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[]{5},new int[][]{{1,1}}), new long[]{0}); // Case 3: verify the expected result for this input.
    }
}
