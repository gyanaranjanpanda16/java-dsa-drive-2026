package questions.q045;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q045: Build a prefix-sum array.
 * Return n+1 prefix sums with prefix[0]=0. This convention makes later half-open range sums a subtraction without a special case for the first position.
 * Complexity: O(n) time and O(n) output space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q045PrefixTotals { // Keep this question independently runnable and easy to find.
    private Q045PrefixTotals() { } // This exercise exposes behavior through static methods.
    public static long[] solve(int[] a) { // Define the input and result types of this particular problem.
        long[] prefix = new long[a.length + 1]; // Include an initial zero for the empty prefix.
        for (int i = 0; i < a.length; i++) { // Extend the known prefix one element at a time.
            prefix[i + 1] = prefix[i] + a[i]; // The first i+1 values equal the first i values plus a[i].
        }
        return prefix; // prefix[k] is the sum of the first k elements.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[]{2,-1,4}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[]{2,-1,4}), new long[]{0,2,1,5}); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[]{}), new long[]{0}); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[]{5}), new long[]{0,5}); // Case 3: verify the expected result for this input.
    }
}
