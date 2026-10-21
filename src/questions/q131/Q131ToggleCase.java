package questions.q131;

import java.util.*;
import questions.support.Check;

/**
 * Q131: Toggle ASCII letter case.
 * Contract: Toggle a-z to A-Z and A-Z to a-z; preserve all other UTF-16 chars.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: The ASCII uppercase and lowercase ranges have a fixed offset, so mapping only those ranges changes case without touching punctuation.
 * Complexity: O(n) time and O(n) space.
 */
public final class Q131ToggleCase { // Keep one question and its tests together for independent study.
    public static String solve(String text) { // Work on a copied char array so the original String is preserved.
        char[] chars = text.toCharArray(); // Obtain mutable positions.
        for (int i = 0; i < chars.length; i++) { // Apply the rule independently at each index.
            char c = chars[i]; // Name the original char before selecting a branch.
            if (c >= 'a' && c <= 'z') chars[i] = (char)(c - 'a' + 'A'); // Translate lowercase ASCII offsets.
            else if (c >= 'A' && c <= 'Z') chars[i] = (char)(c - 'A' + 'a'); // Translate uppercase ASCII offsets.
        }
        return new String(chars); // Untouched nonletters retain their original values.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve("Java17!")); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve("Java17!"), "jAVA17!"); // Mix both cases and nonletters.
    Check.equal(solve(""), ""); // Empty input remains empty.
    Check.equal(solve("azAZ"), "AZaz"); // Check both ends of the accepted ranges.
    }
}
