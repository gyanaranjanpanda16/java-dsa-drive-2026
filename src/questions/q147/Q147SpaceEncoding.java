package questions.q147;

import java.util.*;
import questions.support.Check;

/**
 * Q147: Encode ASCII spaces as percent-20.
 * Contract: Replace each ASCII space with %20 while preserving every other UTF-16 character.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: Each character is copied once or replaced by the required fixed sequence, preserving the order of all positions.
 * Complexity: O(n) time and O(n) output space.
 */
public final class Q147SpaceEncoding { // Keep one question and its tests together for independent study.
    public static String solve(String text) { // This is space escaping only, not a full URL encoder.
        StringBuilder result=new StringBuilder(); // Expand the output as replacements add characters.
        for(char c:text.toCharArray()) { // Visit the source without overwriting unread characters.
            if(c==' ') result.append("%20"); // ASCII space has the specified three-character representation.
            else result.append(c); // Preserve punctuation and other characters exactly.
        }
        return result.toString(); // Return a new immutable representation.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve("Mr John Smith")); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve("Mr John Smith"),"Mr%20John%20Smith"); // Interior spaces expand.
    Check.equal(solve(" a "),"%20a%20"); // Preserve leading and trailing spaces as encodings.
    Check.equal(solve(""),""); // Empty input remains empty.
    }
}
