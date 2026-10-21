package questions.q007;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q007: Count even values.
 * Count even-valued elements, including zero and negative even numbers. The remainder test checks divisibility by two, not whether a value is positive.
 * Complexity: O(n) time and O(1) auxiliary space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q007CountEven { // Keep this question independently runnable and easy to find.
    private Q007CountEven() { } // This exercise exposes behavior through static methods.
    public static int solve(int[] a) { // Define the input and result types of this particular problem.
        int count = 0; // Count only elements satisfying the requested predicate.
        for (int value : a) { // Test every value because parity is independent of position.
            if (value % 2 == 0) count++; // A zero remainder includes negative even numbers and zero.
        }
        return count; // Each matching value was counted once.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[]{-2,0,3,4}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[]{-2,0,3,4}), 3); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[]{}), 0); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[]{1,3}), 0); // Case 3: verify the expected result for this input.
    }
}
