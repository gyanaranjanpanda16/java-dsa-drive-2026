package questions.q188;

import java.util.*;
import questions.support.Check;

/**
 * Q188: Evaluate a valid postfix arithmetic expression.
 * Contract: Tokens are signed long integers or +,-,*,/. The postfix expression is valid, division uses Java truncation, divisors are nonzero, and results fit long.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: Each operator follows its two completed operand expressions; their values are therefore the top two stack entries. Replacing them by the result preserves evaluated-expression values.
 * Complexity: O(tokens) time and O(tokens) space.
 */
public final class Q188Postfix { // Keep one question and its tests together for independent study.
    public static long solve(String[] tokens) { // Postfix order removes any need for precedence parsing.
        Deque<Long> stack=new ArrayDeque<>(); // Hold values of already completed subexpressions.
        for(String token:tokens) { // Read operands and operators in their evaluation order.
            if(token.equals("+")||token.equals("-")||token.equals("*")||token.equals("/")) { // Only these one-character tokens are operators.
                long right=stack.pop(),left=stack.pop(); // Pop right first because it was evaluated after the left operand.
                long value=switch(token) { case "+"->left+right;case "-"->left-right;case "*"->left*right;default->left/right; }; // Preserve operand order for subtraction and division.
                stack.push(value); // The completed expression replaces its two operand entries.
            } else stack.push(Long.parseLong(token)); // Signed numeric tokens become completed leaf expressions.
        }
        return stack.pop(); // Valid postfix input leaves exactly one complete expression value.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve(new String[]{"2","1","+","3","*"})); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve(new String[]{"2","1","+","3","*"}),9L); // Evaluate a composed expression.
    Check.equal(solve(new String[]{"4","13","5","/","+"}),6L); // Division truncates toward zero.
    Check.equal(solve(new String[]{"-7"}),-7L); // A single signed operand is valid.
    }
}
