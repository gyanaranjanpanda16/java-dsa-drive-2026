package questions.q039;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q039: Find the sorted distinct intersection.
 * Return shared distinct values in increasing order. Membership and output ordering are different requirements, handled by a hash set and a sorted set.
 * Complexity: Expected O(n + m log(u+1)) time and O(n+u) space, where u is the result size.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q039DistinctIntersection { // Keep this question independently runnable and easy to find.
    private Q039DistinctIntersection() { } // This exercise exposes behavior through static methods.
    public static int[] solve(int[] a, int[] b) { // Define the input and result types of this particular problem.
        Set<Integer> first = new HashSet<>(); // Remember membership in the first input efficiently.
        for (int value : a) first.add(value); // Duplicate occurrences do not change membership.
        Set<Integer> common = new TreeSet<>(); // Store each shared value once in increasing order.
        for (int value : b) if (first.contains(value)) common.add(value); // A value belongs in the intersection exactly when both inputs contain it.
        return common.stream().mapToInt(Integer::intValue).toArray(); // Unbox the sorted result into a primitive array.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[]{3,1,2,2},new int[]{2,3,4}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[]{3,1,2,2},new int[]{2,3,4}), new int[]{2,3}); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[]{},new int[]{1}), new int[]{}); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[]{5},new int[]{5,5}), new int[]{5}); // Case 3: verify the expected result for this input.
    }
}
