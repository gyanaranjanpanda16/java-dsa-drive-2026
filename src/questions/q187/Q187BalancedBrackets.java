package questions.q187;

import java.util.*;
import questions.support.Check;

/**
 * Q187: Validate nesting of three bracket types.
 * Contract: Input contains only (),[],{}. Return whether pairs are correctly nested; other characters throw.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: The stack contains unmatched openings. A closing bracket must match the newest unmatched opener; otherwise the pairs would cross or be missing.
 * Complexity: O(n) time and O(n) space.
 */
public final class Q187BalancedBrackets { // Keep one question and its tests together for independent study.
    public static boolean solve(String text) { // Match nesting order, not just the number of each bracket type.
        Deque<Character> stack=new ArrayDeque<>(); // The top represents the opening that must close next.
        for(char c:text.toCharArray()) { // Process bracket events in input order.
            if(c=='('||c=='['||c=='{') stack.push(c); // An opening starts a new innermost unmatched pair.
            else { // A closing event must resolve the current innermost pair.
                if(c!=')'&&c!=']'&&c!='}') throw new IllegalArgumentException("brackets only"); // Reject characters outside the input grammar.
                if(stack.isEmpty()) return false; // There is no available opener for this closer.
                char opening=stack.pop(); // Remove exactly the pair that nesting requires.
                if((c==')'&&opening!='(')||(c==']'&&opening!='[')||(c=='}'&&opening!='{')) return false; // Different bracket types cannot form a pair.
            }
        }
        return stack.isEmpty(); // Unmatched openings also make the complete text invalid.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve("{[()]}")); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve("{[()]}"),true); // Nested types close in reverse order.
    Check.equal(solve("([)]"),false); // Equal counts do not imply proper nesting.
    Check.equal(solve(""),true); // Empty input has no unmatched pair.
    }
}
