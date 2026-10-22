package questions.q070;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q070: Read a rectangular matrix in clockwise spiral order.
 * Return clockwise spiral traversal starting at the upper-left corner. Shrinking boundaries represent exactly the unvisited rectangle; guard the final row or column against duplicate visits.
 * Complexity: O(rows×columns + rows) time and O(rows×columns) output space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q070SpiralTraversal { // Keep this question independently runnable and easy to find.
    private Q070SpiralTraversal() { } // This exercise exposes behavior through static methods.
    public static List<Integer> solve(int[][] a) { // Define the input and result types of this particular problem.
        List<Integer> result = new ArrayList<>(); // Store the traversal without mutating the matrix.
        if (a.length == 0) return result; // No boundary exists for an empty matrix.
        int columns = a[0].length; // Fix the width used by all row traversals.
        for (int[] row : a) if (row.length != columns) throw new IllegalArgumentException("rectangular matrix required"); // Avoid ragged-row indexing.
        int top = 0, bottom = a.length-1, left = 0, right = columns-1; // These bounds enclose the unvisited rectangle.
        while (top <= bottom && left <= right) { // Stop when the unvisited rectangle is empty.
            for (int c = left; c <= right; c++) result.add(a[top][c]); // Consume the top boundary from left to right.
            top++; // Exclude that row from future passes.
            for (int r = top; r <= bottom; r++) result.add(a[r][right]); // Consume the right boundary downward.
            right--; // Exclude that column from future passes.
            if (top <= bottom) { // A single remaining row must not be visited twice.
                for (int c = right; c >= left; c--) result.add(a[bottom][c]); // Consume the bottom boundary in reverse.
                bottom--; // Remove the consumed bottom row.
            }
            if (left <= right) { // A single remaining column must not be visited twice.
                for (int r = bottom; r >= top; r--) result.add(a[r][left]); // Consume the left boundary upward.
                left++; // Remove the consumed left column.
            }
        }
        return result; // Each layer removes disjoint boundaries until every cell is emitted.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[][]{{1,2,3},{4,5,6}}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[][]{{1,2,3},{4,5,6}}), List.of(1,2,3,6,5,4)); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[][]{{1},{2}}), List.of(1,2)); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[][]{}), List.of()); // Case 3: verify the expected result for this input.
    }
}
