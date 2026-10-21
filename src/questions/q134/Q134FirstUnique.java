package questions.q134;

import java.util.*;
import questions.support.Check;

/**
 * Q134: Find the first non-repeating character index.
 * Contract: Return the first UTF-16 index whose char appears once, or -1 if absent.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: A frequency pass establishes global multiplicity; a second ordered pass selects the first position with count one.
 * Complexity: O(n) time and O(1) alphabet storage for the fixed 65,536-char universe.
 */
public final class Q134FirstUnique { // Keep one question and its tests together for independent study.
    public static int solve(String text) { // Return an index so every char value remains a valid answer.
        int[] counts = new int[Character.MAX_VALUE + 1]; // Reserve a counter for every possible Java char.
        for (int i = 0; i < text.length(); i++) counts[text.charAt(i)]++; // Learn total multiplicity before deciding uniqueness.
        for (int i = 0; i < text.length(); i++) { // Restore original order when selecting the first unique char.
            if (counts[text.charAt(i)] == 1) return i; // This is the earliest globally unique position.
        }
        return -1; // No index satisfied uniqueness.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve("swiss")); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve("swiss"), 1); // w is the first unique char.
    Check.equal(solve("aabb"), -1); // Every char repeats.
    Check.equal(solve(""), -1); // Empty text has no position.
    }
}
