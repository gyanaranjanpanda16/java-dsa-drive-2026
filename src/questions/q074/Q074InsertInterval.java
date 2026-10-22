package questions.q074;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q074: Insert one interval into a disjoint sorted interval list.
 * Given valid closed intervals already sorted by start and pairwise disjoint, insert one valid interval and merge any overlaps. Partition the input into before, overlapping, and after regions; the original interval preconditions are assumed.
 * Complexity: O(n) time and O(n) output space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q074InsertInterval { // Keep this question independently runnable and easy to find.
    private Q074InsertInterval() { } // This exercise exposes behavior through static methods.
    public static int[][] solve(int[][] a, int[] added) { // Define the input and result types of this particular problem.
        if (added.length != 2 || added[0] > added[1]) throw new IllegalArgumentException("invalid added interval"); // The new interval must have valid ordered endpoints.
        List<int[]> result = new ArrayList<>(); // Build the resulting sorted disjoint union.
        int i = 0, start = added[0], end = added[1]; // Maintain the merged extent of the inserted interval.
        while (i < a.length && a[i][1] < start) result.add(a[i++].clone()); // Earlier intervals end before the new interval and remain unchanged.
        while (i < a.length && a[i][0] <= end) { // Every interval starting within the growing extent overlaps it.
            start = Math.min(start,a[i][0]); // Extend the merged beginning if necessary.
            end = Math.max(end,a[i][1]); // A later end can absorb additional intervals.
            i++; // Consume this overlapping original interval exactly once.
        }
        result.add(new int[]{start,end}); // Emit the single combined inserted component.
        while (i < a.length) result.add(a[i++].clone()); // All remaining intervals start after the merged component.
        return result.toArray(new int[0][]); // The three phases preserve increasing disjoint order.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[][]{{1,3},{6,9}},new int[]{2,5}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[][]{{1,3},{6,9}},new int[]{2,5}), new int[][]{{1,5},{6,9}}); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[][]{},new int[]{2,4}), new int[][]{{2,4}}); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[][]{{1,5}},new int[]{2,3}), new int[][]{{1,5}}); // Case 3: verify the expected result for this input.
    }
}
