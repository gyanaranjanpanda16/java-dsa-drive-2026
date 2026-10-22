package questions.q067;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q067: Rotate a square matrix clockwise.
 * Return a clockwise 90-degree rotation of a square matrix without changing the input. Original coordinate (r,c) maps to (c,n-1-r).
 * Complexity: O(n²) time and output space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q067RotateSquare { // Keep this question independently runnable and easy to find.
    private Q067RotateSquare() { } // This exercise exposes behavior through static methods.
    public static int[][] solve(int[][] a) { // Define the input and result types of this particular problem.
        int n = a.length; // A square matrix has the same row and column count.
        for (int[] row : a) if (row.length != n) throw new IllegalArgumentException("square matrix required"); // Validate before mapping coordinates.
        int[][] rotated = new int[n][n]; // Separate output storage avoids overwriting unread values.
        for (int row = 0; row < n; row++) { // Visit every original row.
            for (int column = 0; column < n; column++) { // Visit every original column.
                rotated[column][n-1-row] = a[row][column]; // A clockwise turn converts row to reversed output column and column to output row.
            }
        }
        return rotated; // The coordinate mapping is a bijection, so every value appears exactly once.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[][]{{1,2},{3,4}}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[][]{{1,2},{3,4}}), new int[][]{{3,1},{4,2}}); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[][]{}), new int[][]{}); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[][]{{7}}), new int[][]{{7}}); // Case 3: verify the expected result for this input.
    }
}
