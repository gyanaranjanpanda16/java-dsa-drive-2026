package questions.q069;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q069: Sum both diagonals without double-counting the center.
 * Return the total of the principal and secondary diagonals of a square matrix, counting the middle cell once. Their only possible intersection is the center of an odd-sized square.
 * Complexity: O(n) time including row-length validation and O(1) space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q069DiagonalTotal { // Keep this question independently runnable and easy to find.
    private Q069DiagonalTotal() { } // This exercise exposes behavior through static methods.
    public static long solve(int[][] a) { // Define the input and result types of this particular problem.
        int n = a.length; // Both diagonals are defined on the same square grid.
        for (int[] row : a) if (row.length != n) throw new IllegalArgumentException("square matrix required"); // Check the indexing assumption.
        long sum = 0; // Accumulate int values using a wider total.
        for (int i = 0; i < n; i++) { // Each row contributes its principal-diagonal position.
            sum += a[i][i]; // Principal diagonal has equal row and column indices.
            if (i != n-1-i) sum += a[i][n-1-i]; // Skip the central overlap when n is odd.
        }
        return sum; // Every diagonal cell contributes once.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[][]{{1,2,3},{4,5,6},{7,8,9}}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[][]{{1,2,3},{4,5,6},{7,8,9}}), 25L); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[][]{{5}}), 5L); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[][]{}), 0L); // Case 3: verify the expected result for this input.
    }
}
