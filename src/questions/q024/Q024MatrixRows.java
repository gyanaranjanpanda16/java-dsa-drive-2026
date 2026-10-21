package questions.q024;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q024: Print a rectangular matrix row by row.
 * Format each matrix row as space-separated values, separating rows with newlines. Java int[][] is an array of row arrays, so nested iteration follows its representation.
 * Complexity: O(total elements plus output length) time and O(output length) space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q024MatrixRows { // Keep this question independently runnable and easy to find.
    private Q024MatrixRows() { } // This exercise exposes behavior through static methods.
    public static String solve(int[][] matrix) { // Define the input and result types of this particular problem.
        StringJoiner rows = new StringJoiner("\n"); // Newlines preserve the matrix's row structure.
        for (int[] row : matrix) { // Visit each row; this also supports rows of different lengths.
            StringJoiner values = new StringJoiner(" "); // Separate columns within the current row.
            for (int value : row) values.add(Integer.toString(value)); // Preserve the column order of this row.
            rows.add(values.toString()); // Finish the row before starting the next one.
        }
        return rows.toString(); // No matrix mutation is needed to format it.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[][]{{1,2},{3,4}}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[][]{{1,2},{3,4}}), "1 2\n3 4"); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[][]{}), ""); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[][]{{5}}), "5"); // Case 3: verify the expected result for this input.
    }
}
