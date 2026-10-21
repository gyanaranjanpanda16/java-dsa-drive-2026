package questions.q055;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q055: Calculate water trapped between bars.
 * For nonnegative unit-width bar heights, return trapped water. Resolve the side with the smaller known maximum because the opposite boundary is already high enough to support that level.
 * Complexity: O(n) time and O(1) auxiliary space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q055TrappedRainwater { // Keep this question independently runnable and easy to find.
    private Q055TrappedRainwater() { } // This exercise exposes behavior through static methods.
    public static long solve(int[] height) { // Define the input and result types of this particular problem.
        int left = 0, right = height.length - 1, leftMax = 0, rightMax = 0; // Bound the still-unresolved region and its walls.
        long water = 0; // The total trapped volume may exceed int range.
        while (left <= right) { // Resolve exactly one position each iteration.
            if (leftMax <= rightMax) { // A sufficient right wall is known, so the left wall limits this position.
                leftMax = Math.max(leftMax,height[left]); // A taller current bar raises the left boundary.
                water += leftMax - height[left++]; // Add only the gap above the resolved bar.
            } else { // The right boundary is now the smaller known wall.
                rightMax = Math.max(rightMax,height[right]); // Incorporate the current right bar into that boundary.
                water += rightMax - height[right--]; // Resolve the symmetric gap and shrink the region.
            }
        }
        return water; // Each position was resolved with a limiting wall justified by the opposite side.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}), 6L); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[]{}), 0L); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[]{3,0,3}), 3L); // Case 3: verify the expected result for this input.
    }
}
