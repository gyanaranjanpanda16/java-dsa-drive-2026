package questions.q056;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q056: Find the maximum water-container area.
 * Choose two nonnegative vertical walls maximizing width times the shorter height. Moving the shorter wall is safe because moving only the taller wall cannot improve the current limiting height.
 * Complexity: O(n) time and O(1) space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q056ContainerArea { // Keep this question independently runnable and easy to find.
    private Q056ContainerArea() { } // This exercise exposes behavior through static methods.
    public static long solve(int[] height) { // Define the input and result types of this particular problem.
        int left = 0, right = height.length - 1; // Start with the widest possible pair of walls.
        long best = 0; // Fewer than two walls cannot enclose positive area.
        while (left < right) { // Only distinct positions form a container.
            best = Math.max(best, (long) Math.min(height[left],height[right]) * (right-left)); // Height is limited by the shorter wall.
            if (height[left] <= height[right]) left++; // Keeping the shorter wall while reducing width cannot improve this pair.
            else right--; // Apply the same dominance argument to the right wall.
        }
        return best; // Every discarded endpoint was proven unable to yield a better remaining container.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[]{1,8,6,2,5,4,8,3,7}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[]{1,8,6,2,5,4,8,3,7}), 49L); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[]{1,1}), 1L); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[]{}), 0L); // Case 3: verify the expected result for this input.
    }
}
