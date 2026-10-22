package questions.q073;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q073: Merge overlapping closed intervals.
 * Merge the union of closed integer intervals, including intervals that touch at an endpoint. Sorting by start ensures an interval can only join the final merged component.
 * Complexity: O(n log n) time and O(n) working/output space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q073MergeIntervals { // Keep this question independently runnable and easy to find.
    private Q073MergeIntervals() { } // This exercise exposes behavior through static methods.
    public static int[][] solve(int[][] intervals) { // Define the input and result types of this particular problem.
        int[][] sorted = new int[intervals.length][2]; // Copy endpoints so sorting and merging preserve the caller's data.
        for (int i = 0; i < intervals.length; i++) { // Validate each interval independently.
            if (intervals[i].length != 2 || intervals[i][0] > intervals[i][1]) throw new IllegalArgumentException("invalid interval"); // A closed interval needs ordered start/end endpoints.
            sorted[i] = intervals[i].clone(); // Separate mutable interval records from the input.
        }
        Arrays.sort(sorted, Comparator.comparingInt(pair -> pair[0])); // Increasing starts make overlaps form consecutive groups.
        List<int[]> merged = new ArrayList<>(); // Retain only the union intervals constructed so far.
        for (int[] interval : sorted) { // Only the last merged interval can overlap the next start.
            if (merged.isEmpty() || merged.get(merged.size()-1)[1] < interval[0]) merged.add(interval); // A gap begins a new union component.
            else merged.get(merged.size()-1)[1] = Math.max(merged.get(merged.size()-1)[1],interval[1]); // Extend the current component through the farthest overlapping endpoint.
        }
        return merged.toArray(new int[0][]); // Closed intervals sharing an endpoint are merged.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[][]{{1,3},{2,6},{8,10}}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[][]{{1,3},{2,6},{8,10}}), new int[][]{{1,6},{8,10}}); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[][]{{1,2},{2,3}}), new int[][]{{1,3}}); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[][]{}), new int[][]{}); // Case 3: verify the expected result for this input.
    }
}
