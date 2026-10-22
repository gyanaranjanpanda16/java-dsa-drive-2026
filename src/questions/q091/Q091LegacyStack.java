package questions.q091;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q091: Trace legacy Stack push peek and pop.
 * Return integer values in Stack pop order, showing that peek observes while pop removes. Stack is a legacy collection; its synchronized methods do not make arbitrary multi-step operations atomic. Deque is the usual modern stack abstraction.
 * Complexity: O(n) amortized time and O(n) space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q091LegacyStack { // Keep this question independently runnable and easy to find.
    private Q091LegacyStack() { } // This exercise exposes behavior through static methods.
    public static List<Integer> solve(int[] input) { // Define the input and result types of this particular problem.
        Stack<Integer> stack = new Stack<>(); // Use the legacy Vector-derived class to understand existing Java code.
        for (int value : input) stack.push(value); // Push makes each new value the top of the stack.
        List<Integer> result = new ArrayList<>(); // Store the pop order for inspection.
        while (!stack.empty()) { // Both peek and pop require a nonempty stack.
            int top = stack.peek(); // Observe the top without removing it.
            stack.pop(); // Remove the same top value after observing it.
            result.add(top); // Last-in values appear first in the result.
        }
        return result; // New stack-oriented application code usually chooses Deque instead.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[]{1,2,3}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[]{1,2,3}), List.of(3,2,1)); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[]{}), List.of()); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[]{4,4}), List.of(4,4)); // Case 3: verify the expected result for this input.
    }
}
