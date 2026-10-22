package questions.q192;

import java.util.*;
import questions.support.Check;

/**
 * Q192: Compute consecutive stock-price spans.
 * Contract: For each price return the count of consecutive days ending today whose prices are at most today, including today.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: After removing earlier prices at most current, the top is the nearest strictly higher barrier. The distance to that barrier is exactly the valid consecutive span.
 * Complexity: O(n) time and O(n) space.
 */
public final class Q192StockSpan { // Keep one question and its tests together for independent study.
    public static String solve(int[] prices) { // Each result counts days rather than reporting a previous index.
        int[] spans=new int[prices.length]; // Store one span per trading day.
        Deque<Integer> higher=new ArrayDeque<>(); // Retain possible strictly higher barriers.
        for(int i=0;i<prices.length;i++) { // Process days in chronological order.
            while(!higher.isEmpty()&&prices[higher.peek()]<=prices[i]) higher.pop(); // These days cannot block the current span.
            spans[i]=higher.isEmpty()?i+1:i-higher.peek(); // Count back to the first higher price or the beginning.
            higher.push(i); // Today's price may form a barrier for a later lower price.
        }
        return Arrays.toString(spans); // Preserve chronological result order.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve(new int[]{100,80,60,70,60,75,85})); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve(new int[]{100,80,60,70,60,75,85}),"[1, 1, 1, 2, 1, 4, 6]"); // Skip dominated days through the stack.
    Check.equal(solve(new int[]{5,5,5}),"[1, 2, 3]"); // Equal prices belong inside the span.
    Check.equal(solve(new int[]{}),"[]"); // No days means no spans.
    }
}
