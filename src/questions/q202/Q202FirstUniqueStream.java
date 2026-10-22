package questions.q202;

import java.util.*;
import questions.support.Check;

/**
 * Q202: Track the earliest nonrepeating character after each arrival.
 * Contract: Input is lowercase English letters. Return one character per prefix: earliest currently unique letter, or # if none.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: Frequency counts identify repetitions; the queue preserves first-occurrence order. Removing repeated front candidates leaves the earliest unique character at the front.
 * Complexity: O(n) time and O(alphabet) live queue space plus output.
 */
public final class Q202FirstUniqueStream { // Keep one question and its tests together for independent study.
    public static String solve(String stream) { // The # marker is unambiguous because inputs contain only lowercase letters.
        int[] frequency=new int[26]; // Store exact multiplicities for the bounded alphabet.
        Queue<Character> candidates=new ArrayDeque<>(); // First appearances enter in chronological order.
        StringBuilder answer=new StringBuilder(); // Emit one observation after every arrival.
        for(char c:stream.toCharArray()) { // Update the state of each successive prefix.
            if(c<'a'||c>'z') throw new IllegalArgumentException("lowercase only"); // Keep direct indexing and the absence marker valid.
            if(++frequency[c-'a']==1) candidates.offer(c); // Only first appearances can become new earliest-unique candidates.
            while(!candidates.isEmpty()&&frequency[candidates.peek()-'a']>1) candidates.remove(); // Repeated front candidates can never become unique again.
            answer.append(candidates.isEmpty()?'#':candidates.peek()); // The front is the earliest remaining unique arrival.
        }
        return answer.toString(); // Output length equals the number of processed prefixes.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve("aabc")); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve("aabc"),"a#bb"); // A repeated first candidate exposes later unique values.
    Check.equal(solve("zz"),"z#"); // The only candidate can become repeated.
    Check.equal(solve(""),""); // No arrivals produce no observations.
    }
}
