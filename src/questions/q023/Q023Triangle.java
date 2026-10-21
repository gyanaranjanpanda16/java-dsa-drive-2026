package questions.q023;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q023: Print a growing star triangle.
 * Return a left-aligned triangle whose row r has r stars. Total work depends on the total number of output characters, not just the number of rows.
 * Complexity: O(rows²) time and output space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q023Triangle { // Keep this question independently runnable and easy to find.
    private Q023Triangle() { } // This exercise exposes behavior through static methods.
    public static String solve(int rows) { // Define the input and result types of this particular problem.
        if (rows < 0) throw new IllegalArgumentException("rows cannot be negative"); // Negative height has no defined drawing.
        StringJoiner lines = new StringJoiner("\n"); // Separate rows without adding a final internal newline.
        for (int row = 1; row <= rows; row++) { // Row numbers equal the required number of stars.
            lines.add("*".repeat(row)); // Construct exactly the width prescribed for this row.
        }
        return lines.toString(); // The caller prints the complete drawing in one operation.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(3))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(3), "*\n**\n***"); // Case 1: verify the expected result for this input.
        Check.equal(solve(0), ""); // Case 2: verify the expected result for this input.
        Check.equal(solve(1), "*"); // Case 3: verify the expected result for this input.
    }
}
