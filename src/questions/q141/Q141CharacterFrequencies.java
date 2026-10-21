package questions.q141;

import java.util.*;
import questions.support.Check;

/**
 * Q141: Count characters in sorted order.
 * Contract: Return a sorted map of UTF-16 chars to occurrence counts.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: A map associates each distinct char with the number of scanned occurrences; merging one on each visit maintains this counting invariant.
 * Complexity: O(n log(u+1)) time and O(u) space.
 */
public final class Q141CharacterFrequencies { // Keep one question and its tests together for independent study.
    public static Map<Character,Integer> solve(String text) { // Return counts in predictable character order.
        Map<Character,Integer> counts = new TreeMap<>(); // A tree map combines exact lookup with sorted keys.
        for (char c : text.toCharArray()) counts.merge(c,1,Integer::sum); // Increment exactly the entry belonging to the current char.
        return counts; // Every input occurrence contributed once to its key.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve("banana")); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve("banana"), Map.of('a',3,'b',1,'n',2)); // Preserve multiplicity.
    Check.equal(solve(""), Map.of()); // Empty text has no keys.
    Check.equal(solve("AA"), Map.of('A',2)); // Case is significant.
    }
}
