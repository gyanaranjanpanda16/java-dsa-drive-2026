package questions.q139;

import java.util.*;
import questions.support.Check;

/**
 * Q139: Reverse word order and normalize spaces.
 * Contract: Reverse whitespace-delimited words; output one space between words and no boundary spaces.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: Collecting tokens and visiting them backward reverses word order while preserving each word itself.
 * Complexity: O(n) time and O(n) space.
 */
public final class Q139ReverseWords { // Keep one question and its tests together for independent study.
    public static String solve(String text) { // Whitespace follows the regular-expression \s definition for this exercise.
        String stripped = text.trim(); // Remove ASCII boundary whitespace before tokenization.
        if (stripped.isEmpty()) return ""; // Avoid treating empty input as a word.
        String[] words = stripped.split("\s+"); // Collapse consecutive whitespace into one delimiter.
        StringBuilder result = new StringBuilder(); // Build the normalized output efficiently.
        for (int i = words.length - 1; i >= 0; i--) { // Visit words from last to first.
            if (result.length() > 0) result.append(' '); // Add exactly one separator between output words.
            result.append(words[i]); // Preserve internal letter order of this word.
        }
        return result.toString(); // Return normalized reversed word order.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve("  Java   is fun  ")); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve("  Java   is fun  "), "fun is Java"); // Normalize repeated spaces.
    Check.equal(solve(""), ""); // Empty input produces no words.
    Check.equal(solve("one"), "one"); // One token is its own reversed order.
    }
}
