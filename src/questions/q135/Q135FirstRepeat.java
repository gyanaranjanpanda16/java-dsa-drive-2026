package questions.q135;

import java.util.*;
import questions.support.Check;

/**
 * Q135: Find the first repeated encounter.
 * Contract: Return the index of the first char encountered a second time; return -1 if all are distinct.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: A seen set holds exactly the chars in the earlier prefix, so failure to add means the current encounter repeats an earlier char.
 * Complexity: O(n) expected time and O(u) space.
 */
public final class Q135FirstRepeat { // Keep one question and its tests together for independent study.
    public static int solve(String text) { // Define first repeat by second-encounter position.
        Set<Character> seen = new HashSet<>(); // Remember earlier chars without tracking unnecessary counts.
        for (int i = 0; i < text.length(); i++) { // Encounter order determines which repeat is first.
            if (!seen.add(text.charAt(i))) return i; // Set insertion fails only when the char was already present.
        }
        return -1; // Every encounter added a new char.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve("abcbad")); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve("abcbad"), 3); // b repeats before a repeats.
    Check.equal(solve("abc"), -1); // Distinct chars never fail insertion.
    Check.equal(solve("aa"), 1); // The second occurrence is a repeat.
    }
}
