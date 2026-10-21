package questions.q042;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q042: Find the first value repeated during a scan.
 * Return the value whose second occurrence is encountered first, or no result. This differs from the repeated value with the smallest original index.
 * Complexity: Expected O(n) time and O(u) space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q042FirstRepeated { // Keep this question independently runnable and easy to find.
    private Q042FirstRepeated() { } // This exercise exposes behavior through static methods.
    public static OptionalInt solve(int[] a) { // Define the input and result types of this particular problem.
        Set<Integer> seen = new HashSet<>(); // Remember the values already passed by the scan.
        for (int value : a) { // The scan order defines which repetition is first.
            if (!seen.add(value)) return OptionalInt.of(value); // add returns false exactly when this value was already present.
        }
        return OptionalInt.empty(); // Every value was new, so no repetition occurred.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[]{2,1,1,2}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[]{2,1,1,2}), OptionalInt.of(1)); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[]{1,2}), OptionalInt.empty()); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[]{0,0}), OptionalInt.of(0)); // Case 3: verify the expected result for this input.
    }
}
