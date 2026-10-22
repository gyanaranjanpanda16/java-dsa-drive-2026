package questions.q071;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q071: Search a matrix sorted by rows and columns.
 * Search a rectangular matrix whose rows and columns are independently nondecreasing. Starting in the upper-right corner permits eliminating one full row or column per comparison.
 * Complexity: O(rows+columns) time, including shape validation; O(1) auxiliary space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q071SortedMatrixSearch { // Keep this question independently runnable and easy to find.
    private Q071SortedMatrixSearch() { } // This exercise exposes behavior through static methods.
    public static boolean solve(int[][] a, int target) { // Define the input and result types of this particular problem.
        if (a.length == 0) return false; // Empty matrices contain no values.
        int columns = a[0].length; // A fixed width is required by the staircase walk.
        for (int[] row : a) if (row.length != columns) throw new IllegalArgumentException("rectangular matrix required"); // Validate shape, while sortedness remains an input precondition.
        int row = 0, column = columns-1; // The upper-right value is largest in its row and smallest in its column.
        while (row < a.length && column >= 0) { // Continue while a candidate rectangle remains.
            if (a[row][column] == target) return true; // A matching value answers membership immediately.
            if (a[row][column] > target) column--; // Every value below in this column is also too large.
            else row++; // Every value to the left in this row is also too small.
        }
        return false; // Each step eliminated an impossible row or column until none remained.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[][]{{1,4,7},{2,5,8},{3,6,9}},6))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[][]{{1,4,7},{2,5,8},{3,6,9}},6), true); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[][]{{1,4},{2,5}},3), false); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[][]{},2), false); // Case 3: verify the expected result for this input.
    }
}
