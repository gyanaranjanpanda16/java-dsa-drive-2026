package questions.q048;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q048: Find values greater than all values to their right.
 * A leader is strictly larger than every value to its right; the final element is always a leader. A suffix maximum makes each decision constant-time.
 * Complexity: O(n) time and O(n) output/storage space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q048Leaders { // Keep this question independently runnable and easy to find.
    private Q048Leaders() { } // This exercise exposes behavior through static methods.
    public static int[] solve(int[] a) { // Define the input and result types of this particular problem.
        List<Integer> reversed = new ArrayList<>(); // Discover leaders from right to left before restoring their original order.
        long maximum = Long.MIN_VALUE; // A wide sentinel lies below every valid int value.
        for (int i = a.length - 1; i >= 0; i--) { // Maintain the maximum strictly to the right of this position.
            if (a[i] > maximum) reversed.add(a[i]); // Strict comparison excludes equal values with an equal value to their right.
            maximum = Math.max(maximum, a[i]); // Extend the suffix maximum for the next position.
        }
        Collections.reverse(reversed); // Return leaders in the order they appeared in the input.
        return reversed.stream().mapToInt(Integer::intValue).toArray(); // Unbox the selected values.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[]{16,17,4,3,5,2}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[]{16,17,4,3,5,2}), new int[]{17,5,2}); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[]{5,5}), new int[]{5}); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[]{}), new int[]{}); // Case 3: verify the expected result for this input.
    }
}
