package questions.q072;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q072: Zero rows and columns containing an original zero.
 * Return a copy where every row and column containing an original zero is cleared. Separate marking and writing prevents newly introduced zeros from incorrectly cascading.
 * Complexity: O(rows×columns+rows+columns) time; O(rows+columns) working markers plus output.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q072ZeroMatrixRowsColumns { // Keep this question independently runnable and easy to find.
    private Q072ZeroMatrixRowsColumns() { } // This exercise exposes behavior through static methods.
    public static int[][] solve(int[][] a) { // Define the input and result types of this particular problem.
        int rows = a.length, columns = rows == 0 ? 0 : a[0].length; // Establish rectangular dimensions before allocating markers.
        boolean[] zeroRow = new boolean[rows], zeroColumn = new boolean[columns]; // Remember original zero locations without contaminating later decisions.
        int[][] result = new int[rows][columns]; // Keep the original input available throughout both passes.
        for (int r = 0; r < rows; r++) { // First pass records only zeros from the original matrix.
            if (a[r].length != columns) throw new IllegalArgumentException("rectangular matrix required"); // Every row must match the chosen width.
            for (int c = 0; c < columns; c++) if (a[r][c] == 0) { // A zero requires both its row and column to be cleared.
                zeroRow[r] = true; // Mark the affected row.
                zeroColumn[c] = true; // Mark the affected column.
            }
        }
        for (int r = 0; r < rows; r++) { // Second pass applies the fully collected decisions.
            for (int c = 0; c < columns; c++) result[r][c] = zeroRow[r] || zeroColumn[c] ? 0 : a[r][c]; // Preserve values only when neither dimension was marked.
        }
        return result; // Newly written zeros never trigger additional unrelated rows or columns.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[][]{{1,2,3},{4,0,6},{7,8,9}}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[][]{{1,2,3},{4,0,6},{7,8,9}}), new int[][]{{1,0,3},{0,0,0},{7,0,9}}); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[][]{{1,2}}), new int[][]{{1,2}}); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[][]{}), new int[][]{}); // Case 3: verify the expected result for this input.
    }
}
