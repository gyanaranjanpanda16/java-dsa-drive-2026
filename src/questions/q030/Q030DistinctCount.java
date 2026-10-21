package questions.q030;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q030: Count distinct values.
 * Return the number of distinct int values. Set insertion collapses repetitions, making the final size the desired count.
 * Complexity: Expected O(n) time and O(u) space for u distinct values.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q030DistinctCount { // Keep this question independently runnable and easy to find.
    private Q030DistinctCount() { } // This exercise exposes behavior through static methods.
    public static int solve(int[] a) { // Define the input and result types of this particular problem.
        Set<Integer> seen = new HashSet<>(); // A set represents membership without duplicate entries.
        for (int value : a) seen.add(value); // Adding an existing value leaves the number of entries unchanged.
        return seen.size(); // One set entry corresponds to each distinct input value.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[]{1,2,1,3}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[]{1,2,1,3}), 3); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[]{}), 0); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[]{0,0}), 1); // Case 3: verify the expected result for this input.
    }
}
