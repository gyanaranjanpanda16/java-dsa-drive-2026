package questions.q038;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q038: Merge two sorted arrays.
 * Merge two nondecreasing arrays without sorting the concatenation. The smallest unconsumed element must be one of the two current heads.
 * Complexity: O(n+m) time and O(n+m) output space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q038MergeSortedArrays { // Keep this question independently runnable and easy to find.
    private Q038MergeSortedArrays() { } // This exercise exposes behavior through static methods.
    public static int[] solve(int[] a, int[] b) { // Define the input and result types of this particular problem.
        int[] result = new int[a.length + b.length]; // Every input occurrence must appear in the output.
        int i = 0, j = 0, write = 0; // Each pointer identifies the smallest unconsumed value in its sequence.
        while (i < a.length || j < b.length) { // Continue until both sequences are exhausted.
            if (j == b.length || (i < a.length && a[i] <= b[j])) result[write++] = a[i++]; // Choose the smaller available head, preferring a on ties.
            else result[write++] = b[j++]; // The head of b is the next smallest remaining value.
        }
        return result; // Choosing the smallest remaining head preserves sorted output.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[]{1,4},new int[]{2,3}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[]{1,4},new int[]{2,3}), new int[]{1,2,3,4}); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[]{},new int[]{2}), new int[]{2}); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[]{1},new int[]{1}), new int[]{1,1}); // Case 3: verify the expected result for this input.
    }
}
