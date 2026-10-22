package questions.q066;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q066: Find a longest interval with a target sum.
 * Return the longest nonempty contiguous length summing to target, or zero. Keep the earliest index for each prefix sum because a fixed endpoint is farthest from its earliest valid start.
 * Complexity: Expected O(n) time and O(n) space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q066LongestTargetSubarray { // Keep this question independently runnable and easy to find.
    private Q066LongestTargetSubarray() { } // This exercise exposes behavior through static methods.
    public static int solve(int[] a, int target) { // Define the input and result types of this particular problem.
        Map<Long,Integer> first = new HashMap<>(); // Earliest prefix positions produce the longest matching interval.
        first.put(0L,-1); // A prefix before the array lets a valid interval start at index zero.
        long prefix = 0; // Wide sums avoid overflow of int aggregates.
        int best = 0; // Zero means no nonempty matching interval was found.
        for (int i = 0; i < a.length; i++) { // Treat each position as a possible right endpoint.
            prefix += a[i]; // Extend the running prefix through this element.
            Integer before = first.get(prefix-target); // Subtracting a matching earlier prefix leaves exactly target.
            if (before != null) best = Math.max(best,i-before); // The earliest matching prefix gives the longest interval ending here.
            first.putIfAbsent(prefix,i); // Never replace an earlier occurrence with a less useful later one.
        }
        return best; // All endpoints have been checked, including those involving negative values.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[]{1,-1,5,-2,3},3))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[]{1,-1,5,-2,3},3), 4); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[]{2,2},3), 0); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[]{0,0,0},0), 3); // Case 3: verify the expected result for this input.
    }
}
