package questions.q062;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q062: Count all values in first-appearance order.
 * Build a frequency table and retain first-appearance key order. A LinkedHashMap combines counting by key with a predictable traversal order.
 * Complexity: Expected O(n) time and O(u) space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q062FrequencyTable { // Keep this question independently runnable and easy to find.
    private Q062FrequencyTable() { } // This exercise exposes behavior through static methods.
    public static Map<Integer,Integer> solve(int[] a) { // Define the input and result types of this particular problem.
        Map<Integer,Integer> counts = new LinkedHashMap<>(); // Preserve the order in which distinct values first appear.
        for (int value : a) { // Every occurrence contributes one to its key's count.
            counts.merge(value,1,Integer::sum); // Insert one for a new key or add one to its existing frequency.
        }
        return counts; // Map values record multiplicity while keys retain first-seen order.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[]{3,1,3}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[]{3,1,3}), Map.of(3,2,1,1)); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[]{}), Map.of()); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[]{5,5,5}), Map.of(5,3)); // Case 3: verify the expected result for this input.
    }
}
