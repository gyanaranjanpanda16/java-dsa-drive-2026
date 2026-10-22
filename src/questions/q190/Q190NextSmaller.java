package questions.q190;

import java.util.*;
import questions.support.Check;

/**
 * Q190: Find the next strictly smaller index to the right.
 * Contract: Return next strictly smaller element indices; -1 means absent.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: An increasing candidate stack discards values at least as large as current, leaving the nearest smaller value as the top. Indices preserve distance and avoid sentinel collisions.
 * Complexity: O(n) time and O(n) space.
 */
public final class Q190NextSmaller { // Keep one question and its tests together for independent study.
    public static String solve(int[] a) { // Return indices so any input value, including -1, remains unambiguous.
        int[] answer=new int[a.length]; // Each input index receives one index result.
        Deque<Integer> stack=new ArrayDeque<>(); // Store indices of still-useful right-side candidates.
        for(int i=a.length-1;i>=0;i--) { // Already processed candidates all lie to the right.
            while(!stack.isEmpty()&&a[stack.peek()]>=a[i]) stack.pop(); // A newer no-larger value dominates these candidates for earlier positions.
            answer[i]=stack.isEmpty()?-1:stack.peek(); // The surviving top is the nearest strictly smaller element.
            stack.push(i); // Earlier positions may use the current index as a candidate.
        }
        return Arrays.toString(answer); // Show index answers in their original position order.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve(new int[]{4,8,5,2})); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve(new int[]{4,8,5,2}),"[3, 2, 3, -1]"); // Find nearest smaller positions.
    Check.equal(solve(new int[]{1,1}),"[-1, -1]"); // Equal values do not qualify.
    Check.equal(solve(new int[]{}),"[]"); // No positions means no answers.
    }
}
