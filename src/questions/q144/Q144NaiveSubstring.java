package questions.q144;

import java.util.*;
import questions.support.Check;

/**
 * Q144: Find the first exact substring match.
 * Contract: Return the first pattern start or -1; the empty pattern matches at zero.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: Every legal starting position is tried. Comparing all pattern chars rules a start in or out, so the first successful start is minimal.
 * Complexity: O(nm) time and O(1) auxiliary space.
 */
public final class Q144NaiveSubstring { // Keep one question and its tests together for independent study.
    public static int solve(String text,String pattern) { // Use direct comparison as a baseline for KMP.
        for (int start=0; start<=text.length()-pattern.length(); start++) { // Only starts with enough remaining characters can match.
            int matched=0; // Reset the match length for this independent candidate start.
            while (matched<pattern.length() && text.charAt(start+matched)==pattern.charAt(matched)) matched++; // Stop at the first mismatch.
            if (matched==pattern.length()) return start; // Every pattern char matched at this earliest eligible start.
        }
        return -1; // Every legal candidate start failed.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve("mississippi","issip")); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve("mississippi","issip"),4); // Restart after an almost-match.
    Check.equal(solve("abc",""),0); // Empty pattern matches immediately.
    Check.equal(solve("ab","abcd"),-1); // A longer pattern cannot fit.
    }
}
