package questions.q211;

import java.util.*;
import questions.support.Check;

/**
 * Q211: Count words in sorted dictionary order.
 * Contract: Given case-sensitive word tokens, return each word and its count in natural String order; empty tokens are ordinary words.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: A TreeMap maintains one entry per word. After processing a prefix, each value equals that word count in the prefix; increasing one entry preserves this invariant. Sorted iteration is useful for a reproducible word-index report.
 * Complexity: O(n log u) map operations and O(u) entries for n tokens and u distinct words; string comparisons also inspect characters.
 */
public final class Q211WordFrequencies { // Keep one question and its tests together for independent study.
    public static String solve(String[] words) { // Aggregate repeated tokens into a deterministic dictionary report.
        Map<String,Integer> counts=new TreeMap<>(); // Natural key order gives stable,alphabetically ordered output.
        for(String word:words) counts.merge(word,1,Integer::sum); // A first occurrence starts at one;later occurrences add one.
        return counts.toString(); // TreeMap iteration exposes every distinct word and its final count.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve(new String[]{"pear","apple","pear"})); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve(new String[]{"pear","apple","pear"}),"{apple=1, pear=2}"); // Repeated words share one counter.
    Check.equal(solve(new String[]{}),"{}"); // No tokens create no entries.
    Check.equal(solve(new String[]{"A","a","A"}),"{A=2, a=1}"); // Case-sensitive keys remain separate.
    }
}
