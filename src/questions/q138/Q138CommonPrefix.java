package questions.q138;

import java.util.*;
import questions.support.Check;

/**
 * Q138: Find the longest common prefix.
 * Contract: Return the common starting sequence of all non-null strings; an empty array yields an empty string.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: The candidate length never grows: shortening at each mismatch preserves exactly the prefix shared by all strings processed so far.
 * Complexity: O(total inspected chars) time and O(prefix length) result space.
 */
public final class Q138CommonPrefix { // Keep one question and its tests together for independent study.
    public static String solve(String[] words) { // Inputs and their string entries must be non-null.
        if (words.length == 0) return ""; // No words supply a nonempty common prefix.
        int length = words[0].length(); // Start with the largest possible prefix of the first word.
        for (int i = 1; i < words.length; i++) { // Intersect the current prefix with each additional word.
            length = Math.min(length, words[i].length()); // A prefix cannot be longer than the current word.
            int j = 0; // Compare the candidate from its first char.
            while (j < length && words[0].charAt(j) == words[i].charAt(j)) j++; // Stop at the first mismatch.
            length = j; // Everything after the mismatch is no longer common.
        }
        return words[0].substring(0, length); // Materialize only the verified common portion.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve(new String[]{"flower","flow","flight"})); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve(new String[]{"flower","flow","flight"}), "fl"); // Common chars stop before differing third chars.
    Check.equal(solve(new String[]{}), ""); // Empty collection is defined explicitly.
    Check.equal(solve(new String[]{"a",""}), ""); // An empty member forces an empty prefix.
    }
}
