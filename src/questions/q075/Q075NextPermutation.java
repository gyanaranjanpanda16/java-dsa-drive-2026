package questions.q075;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q075: Find the next lexicographic permutation.
 * Return the next larger lexicographic permutation, wrapping the largest arrangement to the smallest. Change the rightmost possible pivot minimally, then minimize everything after it; duplicates are allowed.
 * Complexity: O(n) time, O(n) output copy, O(1) extra working space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q075NextPermutation { // Keep this question independently runnable and easy to find.
    private Q075NextPermutation() { } // This exercise exposes behavior through static methods.
    public static int[] solve(int[] input) { // Define the input and result types of this particular problem.
        int[] a = input.clone(); // Produce a new permutation without changing the supplied array.
        int pivot = a.length-2; // Look for the rightmost position that can be increased.
        while (pivot >= 0 && a[pivot] >= a[pivot+1]) pivot--; // The suffix after pivot is nonincreasing and already maximal for its current prefix.
        if (pivot >= 0) { // A pivot exists unless the whole sequence is the final permutation.
            int successor = a.length-1; // The smallest larger suffix value is found scanning from the right.
            while (a[successor] <= a[pivot]) successor--; // Skip values that cannot increase the pivot.
            int saved = a[pivot]; a[pivot] = a[successor]; a[successor] = saved; // Make the smallest possible increase to the earliest changed position.
        }
        for (int left = pivot+1, right = a.length-1; left < right; left++,right--) { // Reverse the nonincreasing suffix to obtain its minimum arrangement.
            int saved = a[left]; a[left] = a[right]; a[right] = saved; // Swap opposite endpoints while shrinking the unresolved middle.
        }
        return a; // The final permutation wraps to ascending order when no pivot exists.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[]{1,2,3}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[]{1,2,3}), new int[]{1,3,2}); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[]{3,2,1}), new int[]{1,2,3}); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[]{1,1,5}), new int[]{1,5,1}); // Case 3: verify the expected result for this input.
    }
}
