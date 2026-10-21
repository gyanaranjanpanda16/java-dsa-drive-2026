package questions.q143;

import java.util.*;
import questions.support.Check;

/**
 * Q143: Find the longest common prefix of strings.
 * Contract: Return the shared leading chars of non-null strings; empty array returns empty text.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: A candidate prefix is repeatedly shortened until it prefixes every processed string. Once empty it cannot grow again.
 * Complexity: O(total compared characters) time and O(m) substring space.
 */
public final class Q143CommonPrefix { // Keep one question and its tests together for independent study.
    public static String solve(String[] words) { // Accept an array of non-null words.
        if (words.length == 0) return ""; // With no words, use the explicit empty-prefix convention.
        String prefix = words[0]; // Any common prefix must prefix the first word.
        for (int i=1; i<words.length; i++) { // Intersect the candidate with each remaining word.
            int length=0; // Count equal leading chars without repeated startsWith calls.
            while (length<prefix.length() && length<words[i].length() && prefix.charAt(length)==words[i].charAt(length)) length++; // Stop at the first mismatch or boundary.
            prefix=prefix.substring(0,length); // Keep exactly the common part established by this comparison.
        }
        return prefix; // The final prefix belongs to every input word.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve(new String[]{"flower","flow","flight"})); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve(new String[]{"flower","flow","flight"}),"fl"); // Shrink across several words.
    Check.equal(solve(new String[]{"dog","cat"}),""); // A first-char mismatch eliminates every nonempty prefix.
    Check.equal(solve(new String[]{}),""); // No words follows the empty convention.
    }
}
