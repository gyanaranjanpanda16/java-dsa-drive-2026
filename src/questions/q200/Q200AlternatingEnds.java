package questions.q200;

import java.util.*;
import questions.support.Check;

/**
 * Q200: Read a deque by alternating front and back.
 * Contract: Return first,last,second,second-last,and so on, emitting each input value once.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: Removing from alternating ends shrinks the unresolved interval without changing its internal order. A final odd middle value is removed only once.
 * Complexity: O(n) time and O(n) deque/output space.
 */
public final class Q200AlternatingEnds { // Keep one question and its tests together for independent study.
    public static String solve(int[] values) { // Demonstrate a workload that requires both deque ends.
        Deque<Integer> deque=new ArrayDeque<>(); // Store the unresolved input interval in its original order.
        for(int value:values) deque.addLast(value); // Appending preserves the sequence's front-to-back arrangement.
        List<Integer> result=new ArrayList<>(); // Collect the alternating endpoint sequence.
        boolean front=true; // The first removal uses the original first value.
        while(!deque.isEmpty()) { // Stop immediately after the final remaining value is removed.
            result.add(front?deque.removeFirst():deque.removeLast()); // Select the required endpoint without shifting array elements.
            front=!front; // Alternate the endpoint for the next step.
        }
        return result.toString(); // Every input position was removed exactly once.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve(new int[]{1,2,3,4,5})); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve(new int[]{1,2,3,4,5}),"[1, 5, 2, 4, 3]"); // The odd middle is emitted once.
    Check.equal(solve(new int[]{1,2}),"[1, 2]"); // Two endpoints exhaust the input.
    Check.equal(solve(new int[]{}),"[]"); // Empty deque has no endpoint.
    }
}
