package questions.q052;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q052: Compute products excluding each position.
 * Return the product of all values except the current index, without division. Prefix and suffix products partition the included positions. Empty output is allowed; a singleton returns one. All intermediate products must fit long.
 * Complexity: O(n) time, O(n) output, and O(1) additional working space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q052ProductExceptSelf { // Keep this question independently runnable and easy to find.
    private Q052ProductExceptSelf() { } // This exercise exposes behavior through static methods.
    public static long[] solve(int[] a) { // Define the input and result types of this particular problem.
        long[] result = new long[a.length]; // Each position will combine the product before it and after it.
        long prefix = 1; // The empty prefix contributes multiplicative identity one.
        for (int i = 0; i < a.length; i++) { // Store the product strictly before each index.
            result[i] = prefix; // Exclude a[i] by storing before multiplying it in.
            prefix = Math.multiplyExact(prefix,a[i]); // Advance the prefix; overflow violates the exact-arithmetic contract.
        }
        long suffix = 1; // The empty suffix also has product one.
        for (int i = a.length - 1; i >= 0; i--) { // Walk backwards so suffix represents values strictly after i.
            result[i] = Math.multiplyExact(result[i],suffix); // Prefix and suffix together include every position except i.
            suffix = Math.multiplyExact(suffix,a[i]); // Extend the suffix for the next position to the left.
        }
        return result; // No division is needed, so zero values are handled naturally.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[]{1,2,3,4}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[]{1,2,3,4}), new long[]{24,12,8,6}); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[]{0,2,3}), new long[]{6,0,0}); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[]{7}), new long[]{1}); // Case 3: verify the expected result for this input.
    }
}
