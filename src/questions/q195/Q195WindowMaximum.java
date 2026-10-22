package questions.q195;

import java.util.*;
import questions.support.Check;

/**
 * Q195: Find every fixed-window maximum with a deque.
 * Contract: Require1<=k<=array length. Return maxima for every length-k contiguous window.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: The deque retains unexpired indices in decreasing value order. A newer at-least-as-large value dominates an older candidate and lasts longer, so removing it cannot change any future maximum.
 * Complexity: O(n) time and O(k) auxiliary space plus output.
 */
public final class Q195WindowMaximum { // Keep one question and its tests together for independent study.
    public static String solve(int[] a,int k) { // Window boundaries require indices rather than only stored values.
        if(k<1||k>a.length) throw new IllegalArgumentException("invalid window"); // Require at least one complete nonempty window.
        Deque<Integer> candidates=new ArrayDeque<>(); // Front is the maximum among still-useful window values.
        int[] result=new int[a.length-k+1]; // There is one result for each legal window start.
        for(int i=0;i<a.length;i++) { // Add one incoming position at a time.
            while(!candidates.isEmpty()&&candidates.peekFirst()<=i-k) candidates.removeFirst(); // Expire indices outside the current window.
            while(!candidates.isEmpty()&&a[candidates.peekLast()]<=a[i]) candidates.removeLast(); // Remove older candidates dominated by the incoming value.
            candidates.addLast(i); // Retain the new candidate after restoring decreasing order.
            if(i>=k-1) result[i-k+1]=a[candidates.peekFirst()]; // Emit only after the first full window exists.
        }
        return Arrays.toString(result); // Output order follows increasing window start.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve(new int[]{1,3,-1,-3,5,3,6,7},3)); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve(new int[]{1,3,-1,-3,5,3,6,7},3),"[3, 3, 5, 5, 6, 7]"); // Remove expired and dominated indices independently.
    Check.equal(solve(new int[]{2,2},1),"[2, 2]"); // Unit windows expose every value.
    Check.throwsType(IllegalArgumentException.class,()->solve(new int[]{1},2)); // Reject oversized windows.
    }
}
