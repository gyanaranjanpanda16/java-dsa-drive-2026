package questions.q126;

import java.util.*;
import questions.support.Check;

/**
 * Q126: Print every character with its index.
 * Contract: Return one index:character entry per UTF-16 char of a non-null string. Empty input returns an empty string.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: Every loop index visits exactly one char; appending a separator only after the first entry prevents a leading comma.
 * Complexity: O(n) time and O(n) output space.
 */
public final class Q126Characters { // Keep one question and its tests together for independent study.
    public static String solve(String text) { // Accept text without changing it.
        StringBuilder result = new StringBuilder(); // Grow one output buffer instead of repeatedly copying strings.
        for (int i = 0; i < text.length(); i++) { // Visit every valid zero-based index once.
            if (i > 0) result.append(", "); // Separate entries while avoiding a leading delimiter.
            result.append(i).append(':').append(text.charAt(i)); // Associate each char with its position.
        }
        return result.toString(); // Freeze the built output into an immutable String.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve("Java")); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve("Java"), "0:J, 1:a, 2:v, 3:a"); // Check repeated chars retain distinct positions.
    Check.equal(solve(""), ""); // No indices exist for empty text.
    Check.equal(solve("x"), "0:x"); // The only valid index is zero.
    }
}
