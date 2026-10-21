package questions.q040;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q040: Find the sorted distinct union.
 * Return the sorted union of two arrays. A set includes a value when it occurs in either input and stores it only once.
 * Complexity: O((n+m) log(u+1)) time and O(u) space for u distinct output values.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q040DistinctUnion { // Keep this question independently runnable and easy to find.
    private Q040DistinctUnion() { } // This exercise exposes behavior through static methods.
    public static int[] solve(int[] a, int[] b) { // Define the input and result types of this particular problem.
        Set<Integer> union = new TreeSet<>(); // A sorted set combines uniqueness with increasing traversal order.
        for (int value : a) union.add(value); // Include every value from the first input.
        for (int value : b) union.add(value); // Include new values from the second input while collapsing duplicates.
        return union.stream().mapToInt(Integer::intValue).toArray(); // Convert the ordered set to the requested primitive result.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[]{3,1},new int[]{2,3}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[]{3,1},new int[]{2,3}), new int[]{1,2,3}); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[]{},new int[]{}), new int[]{}); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[]{5,5},new int[]{5}), new int[]{5}); // Case 3: verify the expected result for this input.
    }
}
