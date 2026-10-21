package questions.q130;

import java.util.*;
import questions.support.Check;

/**
 * Q130: Count whitespace-delimited words.
 * Contract: Count maximal runs of non-whitespace chars according to Character.isWhitespace.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: A word starts exactly when a non-whitespace char follows a boundary. Tracking whether the scan is already inside a word counts each run once.
 * Complexity: O(n) time and O(1) space.
 */
public final class Q130Words { // Keep one question and its tests together for independent study.
    public static int solve(String text) { // Treat words as non-whitespace runs, not linguistic tokens.
        int count = 0; // No words have started yet.
        boolean inside = false; // Initially the scan is outside any word.
        for (int i = 0; i < text.length(); i++) { // Inspect each position without allocating token arrays.
            boolean letter = !Character.isWhitespace(text.charAt(i)); // Determine whether this char belongs to a run.
            if (letter && !inside) count++; // Count only a transition from boundary to word.
            inside = letter; // Remember the state needed for the next char.
        }
        return count; // Every nonempty run contributed exactly once.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve("  learn Java	DSA ")); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve("  learn Java	DSA "), 3); // Mixed whitespace separates three runs.
    Check.equal(solve("   "), 0); // Whitespace alone contains no word.
    Check.equal(solve("one"), 1); // A run can end at end-of-input.
    }
}
