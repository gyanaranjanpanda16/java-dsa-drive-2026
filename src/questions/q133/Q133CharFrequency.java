package questions.q133;

import java.util.*;
import questions.support.Check;

/**
 * Q133: Count characters in sorted order.
 * Contract: Return a TreeMap from UTF-16 char to occurrence count.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: Incrementing the map entry on each visit makes it equal to occurrences in the scanned prefix; the tree orders keys for deterministic output.
 * Complexity: O(n log u) time and O(u) space, where u is the number of distinct chars.
 */
public final class Q133CharFrequency { // Keep one question and its tests together for independent study.
    public static Map<Character,Integer> solve(String text) { // Return ordered counts rather than relying on hash iteration order.
        Map<Character,Integer> counts = new TreeMap<>(); // Sorted keys make printed results deterministic.
        for (int i = 0; i < text.length(); i++) { // Count each char occurrence, including repeated positions.
            char c = text.charAt(i); // Use the char value as the lookup key.
            counts.put(c, counts.getOrDefault(c, 0) + 1); // Start absent keys at zero before incrementing.
        }
        return counts; // Each key now holds its complete frequency.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve("banana")); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve("banana"), Map.of('a',3,'b',1,'n',2)); // Verify repeated counts.
    Check.equal(solve(""), Map.of()); // No chars produce no entries.
    Check.equal(solve("Aa"), Map.of('A',1,'a',1)); // Case remains significant.
    }
}
