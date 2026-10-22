package questions.q204;

import java.util.*;
import questions.support.Check;

/**
 * Q204: Interleave the two halves of an even queue.
 * Contract: Input length must be even. Return first-half[0],second-half[0],first-half[1],second-half[1],and so on.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: Separating the first half into another queue leaves two ordered halves. Alternating removals preserves relative order inside each half.
 * Complexity: O(n) time and O(n) space.
 */
public final class Q204InterleaveHalves { // Keep one question and its tests together for independent study.
    public static String solve(int[] values) { // Equal-sized halves are required for complete pairwise interleaving.
        if(values.length%2!=0) throw new IllegalArgumentException("even length required"); // Reject an unmatched final half element.
        Queue<Integer> second=new ArrayDeque<>(),first=new ArrayDeque<>(); // Two FIFO queues preserve each half's internal order.
        for(int value:values) second.offer(value); // Initially the second queue holds the whole sequence.
        for(int i=0;i<values.length/2;i++) first.offer(second.remove()); // Move exactly the first half into its own queue.
        List<Integer> result=new ArrayList<>(); // Record alternating values without modifying either order rule.
        while(!first.isEmpty()) { result.add(first.remove());result.add(second.remove()); } // Equal half lengths guarantee both removals exist.
        return result.toString(); // Each pair contains corresponding positions from the two halves.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve(new int[]{1,2,3,4,5,6})); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve(new int[]{1,2,3,4,5,6}),"[1, 4, 2, 5, 3, 6]"); // Interleave corresponding half positions.
    Check.equal(solve(new int[]{}),"[]"); // Two empty halves interleave to empty.
    Check.throwsType(IllegalArgumentException.class,()->solve(new int[]{1,2,3})); // Reject unequal halves.
    }
}
