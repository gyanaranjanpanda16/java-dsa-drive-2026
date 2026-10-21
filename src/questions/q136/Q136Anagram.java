package questions.q136;

import java.util.*;
import questions.support.Check;

/**
 * Q136: Check exact anagrams.
 * Contract: Determine whether two non-null strings contain the same UTF-16 chars with the same multiplicities.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: Adding counts for one string and subtracting the other leaves all zeros exactly when the two multisets are equal.
 * Complexity: O(n + alphabet) time and O(alphabet) space for the fixed char universe.
 */
public final class Q136Anagram { // Keep one question and its tests together for independent study.
    public static boolean solve(String first, String second) { // Compare multisets, ignoring order but preserving case.
        if (first.length() != second.length()) return false; // Unequal total size makes equal multiplicities impossible.
        int[] counts = new int[Character.MAX_VALUE + 1]; // Index counters directly by char value.
        for (int i = 0; i < first.length(); i++) { // Pair visits only to share a loop, not to require positional equality.
            counts[first.charAt(i)]++; // Record one available char from the first string.
            counts[second.charAt(i)]--; // Consume one required char from the second string.
        }
        for (int count : counts) if (count != 0) return false; // Any remaining difference disproves multiset equality.
        return true; // Every char has identical multiplicity.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve("listen", "silent")); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve("listen","silent"), true); // Rearrangement preserves all counts.
    Check.equal(solve("aab","abb"), false); // Equal lengths alone are insufficient.
    Check.equal(solve("",""), true); // Both empty multisets are equal.
    }
}
