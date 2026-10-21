package questions.q132;

import java.util.*;
import questions.support.Check;

/**
 * Q132: Remove whitespace characters.
 * Contract: Remove every char for which Character.isWhitespace returns true; retain order of all other chars.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: The output is always exactly the accepted subsequence of the processed prefix. Appending only non-whitespace chars preserves this invariant.
 * Complexity: O(n) time and O(n) output space.
 */
public final class Q132RemoveWhitespace { // Keep one question and its tests together for independent study.
    public static String solve(String text) { // Filter by Java's explicit char-level whitespace predicate.
        StringBuilder result = new StringBuilder(); // Store retained chars in encounter order.
        for (int i = 0; i < text.length(); i++) { // Read every char exactly once.
            char c = text.charAt(i); // Reuse the current value for classification and output.
            if (!Character.isWhitespace(c)) result.append(c); // Keep only values outside the removal category.
        }
        return result.toString(); // Return the stable filtered text.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve(" a	b c\n")); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve(" a	b c\n"), "abc"); // Remove multiple whitespace types.
    Check.equal(solve(""), ""); // Empty input needs no output buffer content.
    Check.equal(solve("x!"), "x!"); // Preserve accepted punctuation and letters.
    }
}
