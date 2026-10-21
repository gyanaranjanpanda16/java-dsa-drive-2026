package questions.q017;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q017: Find a Fibonacci number iteratively.
 * Return Fn with F0=0 and F1=1 for 0≤n≤92. Keeping two consecutive terms avoids the repeated work of naive recursion and the storage of a full table.
 * Complexity: O(n) time and O(1) auxiliary space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q017Fibonacci { // Keep this question independently runnable and easy to find.
    private Q017Fibonacci() { } // This exercise exposes behavior through static methods.
    public static long solve(int n) { // Define the input and result types of this particular problem.
        if (n < 0 || n > 92) throw new IllegalArgumentException("require 0..92"); // F92 is the last Fibonacci value fitting long.
        if (n == 0) return 0; // F0 is a base case requiring no addition.
        long previous = 0, current = 1; // Track consecutive Fibonacci values instead of an entire table.
        for (int i = 1; i < n; i++) { // Compute only the terms needed to reach Fn.
            long next = previous + current; // The recurrence combines the preceding two terms.
            previous = current; // Shift the older term forward without losing the saved sum.
            current = next; // The new term becomes the current result.
        }
        return current; // The maintained pair now ends at Fn.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(10))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(10), 55L); // Case 1: verify the expected result for this input.
        Check.equal(solve(0), 0L); // Case 2: verify the expected result for this input.
        Check.equal(solve(1), 1L); // Case 3: verify the expected result for this input.
    }
}
