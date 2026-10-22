package questions.q189;

import java.util.*;
import questions.support.Check;

/**
 * Q189: Find the next strictly greater value to the right.
 * Contract: Return a list of OptionalInt values, one per position; absence is explicit and does not collide with negative inputs.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: A decreasing candidate stack removes values that are no greater than the current value, because they cannot answer this or any earlier smaller-distance query better than current.
 * Complexity: O(n) time and O(n) space.
 */
public final class Q189NextGreater { // Keep one question and its tests together for independent study.
    public static List<OptionalInt> solve(int[] a) { // Preserve absence separately from all valid integer values.
        OptionalInt[] answer=new OptionalInt[a.length]; // Store answers in original index order.
        Deque<Integer> stack=new ArrayDeque<>(); // Candidate values are kept in decreasing order from bottom to top traversal context.
        for(int i=a.length-1;i>=0;i--) { // Right-to-left processing makes every stacked value lie to the right.
            while(!stack.isEmpty()&&stack.peek()<=a[i]) stack.pop(); // Smaller or equal candidates are dominated by the current value.
            answer[i]=stack.isEmpty()?OptionalInt.empty():OptionalInt.of(stack.peek()); // The nearest surviving greater candidate answers this index.
            stack.push(a[i]); // Make the current value available to earlier positions.
        }
        return Arrays.asList(answer); // Return one explicit optional answer per input position.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve(new int[]{2,1,4,3})); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve(new int[]{2,1,4,3}),List.of(OptionalInt.of(4),OptionalInt.of(4),OptionalInt.empty(),OptionalInt.empty())); // Resolve multiple waiting values.
    Check.equal(solve(new int[]{2,2}),List.of(OptionalInt.empty(),OptionalInt.empty())); // Equal is not strictly greater.
    Check.equal(solve(new int[]{}),List.of()); // Empty input has no answer positions.
    }
}
