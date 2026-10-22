package questions.q146;

import java.util.*;
import questions.support.Check;

/**
 * Q146: Encode runs as character-count pairs.
 * Contract: Encode runs of lowercase English letters as letter followed by positive decimal count. Empty text encodes to empty text.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: Every maximal equal-character run is found once, and its length equals the difference between its end and start indices.
 * Complexity: O(n) time and O(n) output space.
 */
public final class Q146RunLength { // Keep one question and its tests together for independent study.
    public static String solve(String text) { // Restrict the alphabet so digits in counts remain unambiguous.
        StringBuilder result=new StringBuilder(); // Collect each run without repeatedly copying prior output.
        for(int start=0;start<text.length();) { // Advance by entire runs rather than by arbitrary boundaries.
            char c=text.charAt(start); // This character labels the next run.
            if(c<'a'||c>'z') throw new IllegalArgumentException("lowercase only"); // Keep the encoding contract unambiguous.
            int end=start+1; // The first character already belongs to this run.
            while(end<text.length()&&text.charAt(end)==c) end++; // Extend until the first different character.
            result.append(c).append(end-start); // Emit the run label and exact multiplicity.
            start=end; // The next scan begins at the first unencoded character.
        }
        return result.toString(); // All original positions belong to exactly one emitted run.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve("aaabbc")); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve("aaabbc"),"a3b2c1"); // Adjacent runs remain separate.
    Check.equal(solve(""),""); // Empty input has no runs.
    Check.equal(solve("aba"),"a1b1a1"); // Equal nonadjacent chars cannot merge.
    }
}
