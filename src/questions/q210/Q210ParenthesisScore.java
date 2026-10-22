package questions.q210;

import java.util.*;
import questions.support.Check;

/**
 * Q210: Score balanced parentheses by nesting rules.
 * Contract: Valid parentheses input gives one pair () score 1, concatenation adds, and wrapping doubles. Empty has score0; arithmetic overflow throws.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: Each stack entry saves the score before entering a group. Closing contributes1 for an empty group or twice its inner score,then restores and adds the outer prefix.
 * Complexity: O(n) time and O(nesting depth) space.
 */
public final class Q210ParenthesisScore { // Keep one question and its tests together for independent study.
    public static long solve(String text) { // Evaluate the recursive scoring rules with an explicit stack.
        Deque<Long> outer=new ArrayDeque<>(); // Save the accumulated prefix score of each enclosing level.
        long score=0; // The current level starts with no completed groups.
        for(char c:text.toCharArray()) { // Valid input contains only properly balanced parentheses.
            if(c=='(') { outer.push(score);score=0; } // Enter a new group with an empty inner score.
            else { // A closing parenthesis completes the current group.
                long group=score==0?1:Math.multiplyExact(2,score); // Empty pairs score1;nonempty wrapping doubles its contents.
                score=Math.addExact(outer.pop(),group); // Concatenate the completed group with its outer preceding groups.
            }
        }
        return score; // Valid balance leaves the full outer-level score.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve("(()(()))")); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve("(()(()))"),6L); // Nested addition and doubling compose.
    Check.equal(solve("()()"),2L); // Concatenated simple pairs add.
    Check.equal(solve(""),0L); // Empty text has no scored pairs.
    }
}
