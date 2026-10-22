package questions.q186;

import java.util.*;
import questions.support.Check;

/**
 * Q186: Reverse values through a stack.
 * Contract: Return the reverse order of an integer array using a LIFO stack.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: Push visits values in forward order; popping returns the most recently pushed remaining value, so the entire order reverses.
 * Complexity: O(n) time and O(n) stack/output space.
 */
public final class Q186StackReverse { // Keep one question and its tests together for independent study.
    public static String solve(int[] values) { // Demonstrate LIFO using the recommended Deque interface.
        Deque<Integer> stack=new ArrayDeque<>(); // An array deque provides efficient push and pop at one end.
        for(int value:values) stack.push(value); // Later input values are placed above earlier values.
        List<Integer> result=new ArrayList<>(); // Record the resulting removal order.
        while(!stack.isEmpty()) result.add(stack.pop()); // Remove the newest remaining value each time.
        return result.toString(); // The output is the complete reverse of insertion order.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve(new int[]{1,2,3})); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve(new int[]{1,2,3}),"[3, 2, 1]"); // LIFO reverses arrival order.
    Check.equal(solve(new int[]{}),"[]"); // Empty stacks require no pop.
    Check.equal(solve(new int[]{7}),"[7]"); // A singleton reverses to itself.
    }
}
