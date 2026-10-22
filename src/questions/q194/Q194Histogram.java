package questions.q194;

import java.util.*;
import questions.support.Check;

/**
 * Q194: Find the largest rectangle in a histogram.
 * Contract: Heights are nonnegative unit-width bars. Return maximum rectangle area as long; empty returns0.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: An increasing stack keeps bars without a known smaller right boundary. When a smaller bar arrives, each popped bar has both nearest smaller boundaries known, defining its widest valid rectangle.
 * Complexity: O(n) time and O(n) space.
 */
public final class Q194Histogram { // Keep one question and its tests together for independent study.
    public static long solve(int[] heights) { // Use long for height multiplied by width.
        Deque<Integer> stack=new ArrayDeque<>(); // Retain increasing-height indices awaiting their right boundary.
        long best=0; // An empty histogram has area zero.
        for(int i=0;i<=heights.length;i++) { // A virtual zero at the end flushes all unresolved positive bars.
            int current=i==heights.length?0:heights[i]; // The virtual boundary is read without indexing beyond the array.
            while(!stack.isEmpty()&&heights[stack.peek()]>=current) { // Current supplies a no-higher boundary for each popped bar.
                int height=heights[stack.pop()]; // This bar determines the rectangle's limiting height.
                int left=stack.isEmpty()?-1:stack.peek(); // The surviving predecessor bounds how far left that height extends.
                best=Math.max(best,(long)height*(i-left-1)); // Count only positions strictly between the two boundaries.
            }
            if(i<heights.length) stack.push(i); // Do not store the virtual boundary as a real array index.
        }
        return best; // Every bar was considered once as a limiting rectangle height.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve(new int[]{2,1,5,6,2,3})); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve(new int[]{2,1,5,6,2,3}),10L); // Bars5 and6 form area10.
    Check.equal(solve(new int[]{2,2}),4L); // Equal bars can form a wider rectangle.
    Check.equal(solve(new int[]{}),0L); // Empty histogram has no positive rectangle.
    }
}
