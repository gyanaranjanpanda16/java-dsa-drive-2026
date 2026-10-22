package questions.q068;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q068: Transpose a rectangular matrix.
 * Return the transpose of a rectangular matrix. Unlike rotation, transposition only swaps coordinates and changes an r×c matrix into c×r.
 * Complexity: O(rows×columns) time and output space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q068Transpose { // Keep this question independently runnable and easy to find.
    private Q068Transpose() { } // This exercise exposes behavior through static methods.
    public static int[][] solve(int[][] a) { // Define the input and result types of this particular problem.
        int rows = a.length, columns = rows == 0 ? 0 : a[0].length; // Empty input has no columns under this representation.
        for (int[] row : a) if (row.length != columns) throw new IllegalArgumentException("rectangular matrix required"); // Ragged rows cannot be transposed with a single fixed width.
        int[][] result = new int[columns][rows]; // Transposition exchanges the two dimensions.
        for (int r = 0; r < rows; r++) { // Read every original row.
            for (int c = 0; c < columns; c++) result[c][r] = a[r][c]; // Exchange the row and column coordinates without reversing either.
        }
        return result; // Original rows have become output columns.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[][]{{1,2,3},{4,5,6}}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[][]{{1,2,3},{4,5,6}}), new int[][]{{1,4},{2,5},{3,6}}); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[][]{}), new int[][]{}); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[][]{{9}}), new int[][]{{9}}); // Case 3: verify the expected result for this input.
    }
}
