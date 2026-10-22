package questions.q203;

import java.util.*;
import questions.support.Check;

/**
 * Q203: Reverse the first k queue values only.
 * Contract: Require0<=k<=length. Reverse the first k values and preserve the order of the remaining suffix.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: A stack reverses exactly the removed prefix. Rotating the untouched suffix behind that reversed prefix restores its original relative placement.
 * Complexity: O(n) time and O(n+k) representation space.
 */
public final class Q203ReverseQueuePrefix { // Keep one question and its tests together for independent study.
    public static String solve(int[] values,int k) { // Work through FIFO operations to expose the queue transformation.
        if(k<0||k>values.length) throw new IllegalArgumentException("invalid prefix length"); // A prefix cannot extend beyond the queue.
        Queue<Integer> queue=new ArrayDeque<>(); // Preserve arrival order during construction.
        for(int value:values) queue.offer(value); // The first array value becomes the queue front.
        Deque<Integer> stack=new ArrayDeque<>(); // Reversing a prefix requires LIFO order for those values.
        for(int i=0;i<k;i++) stack.push(queue.remove()); // Remove only the prefix selected for reversal.
        while(!stack.isEmpty()) queue.offer(stack.pop()); // Append the reversed prefix after the untouched suffix.
        for(int i=0;i<values.length-k;i++) queue.offer(queue.remove()); // Rotate the untouched suffix behind the now-reversed prefix.
        return queue.toString(); // The suffix order is unchanged while only the first k values reverse.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve(new int[]{1,2,3,4,5},3)); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve(new int[]{1,2,3,4,5},3),"[3, 2, 1, 4, 5]"); // Preserve the suffix4,5.
    Check.equal(solve(new int[]{1,2},0),"[1, 2]"); // Reversing no values is a no-op.
    Check.equal(solve(new int[]{},0),"[]"); // Empty input has one valid prefix length.
    }
}
