package questions.q198;

import java.util.*;
import questions.support.Check;

/**
 * Q198: Implement LIFO behavior with queue rotation.
 * Contract: Push input values using a FIFO queue, then remove all in stack order.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: After appending a new value, rotating all older values behind it makes the newest value the queue front. The front therefore behaves as the stack top.
 * Complexity: O(n²) total push time and O(n) space; each final pop is O(1).
 */
public final class Q198StackFromQueue { // Keep one question and its tests together for independent study.
    public static String solve(int[] values) { // Show the cost of implementing a stack with one queue.
        Queue<Integer> queue=new ArrayDeque<>(); // Only FIFO offer and remove are used for the representation.
        for(int value:values) { // Each new value must become the next value removed.
            int older=queue.size(); // Remember how many earlier values need to move behind the new value.
            queue.offer(value); // Append the new value at the FIFO tail.
            for(int i=0;i<older;i++) queue.offer(queue.remove()); // Rotate exactly the old values so the new value reaches the front.
        }
        List<Integer> result=new ArrayList<>(); // Observe the resulting LIFO removals.
        while(!queue.isEmpty()) result.add(queue.remove()); // The front now represents the newest remaining insertion.
        return result.toString(); // Report removal order after all pushes.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve(new int[]{1,2,3})); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve(new int[]{1,2,3}),"[3, 2, 1]"); // Queue rotation establishes LIFO order.
    Check.equal(solve(new int[]{4}),"[4]"); // A first push needs no rotation.
    Check.equal(solve(new int[]{}),"[]"); // Empty input has no removals.
    }
}
