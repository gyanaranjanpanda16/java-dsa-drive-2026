package questions.q142;

import java.util.*;
import questions.support.Check;

/**
 * Q142: Find the longest substring without repeated chars.
 * Contract: Return the maximum contiguous substring length with unique UTF-16 chars.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: The left boundary never moves backwards. Moving it beyond the previous occurrence restores uniqueness while keeping the longest valid suffix ending at the current position.
 * Complexity: Expected O(n) time and O(u) space.
 */
public final class Q142LongestUnique { // Keep one question and its tests together for independent study.
    public static int solve(String text) { // Return a length, avoiding unnecessary substring copies.
        Map<Character,Integer> last = new HashMap<>(); // Remember the latest index of each char.
        int left = 0, best = 0; // The window starts empty before the first char.
        for (int right = 0; right < text.length(); right++) { // Extend the candidate window one char at a time.
            char c = text.charAt(right); // Identify the new char whose previous occurrence may conflict.
            left = Math.max(left,last.getOrDefault(c,-1)+1); // Exclude a duplicate only if it lies inside the current window.
            last.put(c,right); // Future windows must use this most recent occurrence.
            best = Math.max(best,right-left+1); // The restored window is the longest unique suffix ending here.
        }
        return best; // Every possible right endpoint has been considered.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve("abcabcbb")); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve("abcabcbb"),3); // Repeated pattern caps the window at3.
    Check.equal(solve("abba"),2); // The left boundary must not move backward.
    Check.equal(solve(""),0); // Empty input has length zero.
    }
}
