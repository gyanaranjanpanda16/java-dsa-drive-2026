package questions.q059;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q059: Sort an array containing only zero one and two.
 * Sort values restricted to 0,1,2 in one partition pass after validation. The unknown region shrinks on every iteration; a swapped-in high-end value must be rechecked.
 * Complexity: O(n) time, O(n) output copy and O(1) working space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q059SortThreeValues { // Keep this question independently runnable and easy to find.
    private Q059SortThreeValues() { } // This exercise exposes behavior through static methods.
    public static int[] solve(int[] input) { // Define the input and result types of this particular problem.
        int[] a = input.clone(); // Return sorted data without changing the original array.
        for (int value : a) if (value < 0 || value > 2) throw new IllegalArgumentException("only 0,1,2 allowed"); // Validate the three-category contract first.
        int low = 0, current = 0, high = a.length - 1; // Keep zero-prefix, one-middle, unknown-region, and two-suffix boundaries.
        while (current <= high) { // Stop when no unclassified positions remain.
            if (a[current] == 0) { // Move a zero into the next position of the zero-prefix.
                int saved = a[low]; a[low++] = a[current]; a[current++] = saved; // The displaced value is already classified as one or is the same slot.
            } else if (a[current] == 2) { // Move a two into the next position of the two-suffix.
                int saved = a[high]; a[high--] = a[current]; a[current] = saved; // Reinspect the swapped-in value because it came from the unknown region.
            } else current++; // A one already belongs in the middle region.
        }
        return a; // All four invariant regions collapse into sorted zero, one, and two sections.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[]{2,0,2,1,1,0}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[]{2,0,2,1,1,0}), new int[]{0,0,1,1,2,2}); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[]{}), new int[]{}); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[]{1,1}), new int[]{1,1}); // Case 3: verify the expected result for this input.
    }
}
