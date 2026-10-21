package questions.q127;

import java.util.*;
import questions.support.Check;

/**
 * Q127: Reverse text using two pointers.
 * Contract: Reverse a non-null string by UTF-16 char units; the caller string remains unchanged.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: After swapping symmetric ends, those positions are final. Moving both boundaries inward eventually fixes every position.
 * Complexity: O(n) time and O(n) space for the copied character array.
 */
public final class Q127Reverse { // Keep one question and its tests together for independent study.
    public static String solve(String text) { // Return a new string because String objects are immutable.
        char[] chars = text.toCharArray(); // Create mutable slots to swap.
        int left = 0, right = chars.length - 1; // Identify the first and last unresolved positions.
        while (left < right) { // A single middle char already belongs in place.
            char saved = chars[left]; // Save the left value before overwriting its slot.
            chars[left++] = chars[right]; // Move the right value to the left final position.
            chars[right--] = saved; // Complete the swap and shrink the unresolved interval.
        }
        return new String(chars); // Construct the reversed immutable result.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve("java")); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve("java"), "avaj"); // Check an even-length example.
    Check.equal(solve(""), ""); // Empty text needs no swaps.
    Check.equal(solve("abc"), "cba"); // The middle char remains fixed.
    }
}
