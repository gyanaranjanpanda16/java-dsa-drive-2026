package questions.q191;

import java.util.*;
import questions.support.Check;

/**
 * Q191: Find the nearest greater index on the left.
 * Contract: Return the closest earlier index with a strictly greater value, or -1.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: Discarding smaller or equal prior values is safe because current is newer and at least as large, making those values useless for any later greater-on-left query.
 * Complexity: O(n) time and O(n) space.
 */
public final class Q191PreviousGreater { // Keep one question and its tests together for independent study.
    public static String solve(int[] a) { // Output positions rather than ambiguous value sentinels.
        int[] result=new int[a.length]; // Preserve one answer per original index.
        Deque<Integer> stack=new ArrayDeque<>(); // Store useful earlier indices in decreasing value order.
        for(int i=0;i<a.length;i++) { // Earlier candidates are available before answering this position.
            while(!stack.isEmpty()&&a[stack.peek()]<=a[i]) stack.pop(); // Remove dominated candidates that are not strictly greater.
            result[i]=stack.isEmpty()?-1:stack.peek(); // The nearest surviving index is the required previous greater.
            stack.push(i); // Current may answer later queries.
        }
        return Arrays.toString(result); // Display answers in index order.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve(new int[]{10,4,2,20,12})); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve(new int[]{10,4,2,20,12}),"[-1, 0, 1, -1, 3]"); // Larger arrivals discard several prior candidates.
    Check.equal(solve(new int[]{2,2}),"[-1, -1]"); // Equal values are excluded.
    Check.equal(solve(new int[]{}),"[]"); // Empty input has no queries.
    }
}
