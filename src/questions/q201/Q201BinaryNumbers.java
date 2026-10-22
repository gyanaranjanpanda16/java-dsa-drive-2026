package questions.q201;

import java.util.*;
import questions.support.Check;

/**
 * Q201: Generate the first n positive binary representations.
 * Contract: For n>=0 return binary representations of1 through n using a queue.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: Starting from1, appending0 and1 generates the two next-level binary strings. Breadth-first order is increasing length and then lexicographic order, which equals positive numeric order.
 * Complexity: O(n log(n+1)) output time/space.
 */
public final class Q201BinaryNumbers { // Keep one question and its tests together for independent study.
    public static List<String> solve(int n) { // Return text so representations are not limited by machine integer parsing.
        if(n<0) throw new IllegalArgumentException("nonnegative count"); // Negative output counts are undefined.
        Queue<String> queue=new ArrayDeque<>();queue.offer("1"); // The first positive binary number is the root of the generation tree.
        List<String> result=new ArrayList<>(); // Collect exactly the requested number of representations.
        for(int i=0;i<n;i++) { // One queue removal produces one successive number.
            String current=queue.remove();result.add(current); // FIFO visits shorter representations before their children.
            queue.offer(current+"0");queue.offer(current+"1"); // Append children in numeric order within the next length level.
        }
        return result; // The result contains every positive integer representation through n once.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve(5)); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve(5),List.of("1","10","11","100","101")); // Breadth-first generation follows numeric order.
    Check.equal(solve(0),List.of()); // Zero requested values yields an empty list.
    Check.equal(solve(1),List.of("1")); // The root is the first answer.
    }
}
