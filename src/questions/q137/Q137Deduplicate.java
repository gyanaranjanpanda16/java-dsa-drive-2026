package questions.q137;

import java.util.*;
import questions.support.Check;

/**
 * Q137: Remove repeated characters stably.
 * Contract: Keep only the first encounter of each UTF-16 char in its original order.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: The set records which chars already have a representative in output; a new char is appended exactly once.
 * Complexity: O(n) expected time and O(u) space.
 */
public final class Q137Deduplicate { // Keep one question and its tests together for independent study.
    public static String solve(String text) { // Preserve first-encounter order rather than sorting chars.
        Set<Character> seen = new HashSet<>(); // Track whether a char already appears in output.
        StringBuilder result = new StringBuilder(); // Append accepted chars in scan order.
        for (int i = 0; i < text.length(); i++) { // Every position is classified once.
            char c = text.charAt(i); // Name the current candidate.
            if (seen.add(c)) result.append(c); // Successful insertion means this is the first occurrence.
        }
        return result.toString(); // The result contains one representative per encountered char.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve("banana")); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve("banana"), "ban"); // Retain b, a, n in first-encounter order.
    Check.equal(solve(""), ""); // Empty input remains empty.
    Check.equal(solve("aaa"), "a"); // Repeated chars leave one representative.
    }
}
