package questions.q129;

import java.util.*;
import questions.support.Check;

/**
 * Q129: Count ASCII vowels and consonants.
 * Contract: Return [vowels, consonants] for ASCII English letters, ignoring other characters and case.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: Every English letter belongs to exactly one of the vowel and consonant categories; ignoring nonletters keeps counts scoped to the contract.
 * Complexity: O(n) time and O(1) auxiliary space.
 */
public final class Q129Letters { // Keep one question and its tests together for independent study.
    public static int[] solve(String text) { // Count only the explicitly defined ASCII alphabet.
        int vowels = 0, consonants = 0; // Keep the two disjoint category totals.
        for (char raw : text.toCharArray()) { // Inspect every char in the text.
            char c = Character.toLowerCase(raw); // Make uppercase ASCII letters comparable with lowercase vowels.
            if (c < 'a' || c > 'z') continue; // Ignore punctuation, digits, and non-ASCII letters.
            if ("aeiou".indexOf(c) >= 0) vowels++; // Membership in the five-letter vowel set selects this count.
            else consonants++; // Every remaining accepted letter is a consonant.
        }
        return new int[]{vowels, consonants}; // Return totals in the contract's specified order.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(Arrays.toString(solve("Java 17!"))); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.array(solve("Java 17!"), new int[]{2,2}); // Ignore digits and punctuation.
    Check.array(solve("AEIOU"), new int[]{5,0}); // Normalize upper case.
    Check.array(solve("123"), new int[]{0,0}); // No accepted letters means zero counts.
    }
}
