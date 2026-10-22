package questions.q223; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Validate partial Sudoku constraints.
 * Contract: Given exactly nine strings of nine characters, using digits1..9 or dot for empty cells, return whether filled cells obey row, column, and3x3-box uniqueness. This checks consistency, not solvability.
 * Why: Encode each digit occurrence in three independent namespaces: row, column and box. A repeated key identifies exactly one violated constraint. This generalizes to detecting conflicting assignments across overlapping groups.
 * Cost: O(81) time and space for this fixed board.
 */
public final class Q223SudokuValidity { // Keep the algorithm, demonstration, and boundary checks together.
    private Q223SudokuValidity() { } // Only static entry points are needed for this exercise.
    public static boolean solve(String[] board) { // Validate current entries without attempting to solve missing cells.
        if(board.length!=9) throw new IllegalArgumentException("nine rows required"); // Fix the Sudoku board shape.
        Set<String> seen=new HashSet<>(); // Namespace prefixes prevent unrelated constraint types from colliding.
        for(int r=0;r<9;r++) { // Inspect every row and its nine cells.
            if(board[r].length()!=9) throw new IllegalArgumentException("nine columns required"); // Reject ragged rows.
            for(int c=0;c<9;c++) { // Each filled cell supplies three uniqueness claims.
                char d=board[r].charAt(c); if(d=='.') continue; // Empty cells impose no digit constraint.
                if(d<'1'||d>'9') throw new IllegalArgumentException("digit or dot required"); // Restrict the valid alphabet.
                int box=(r/3)*3+c/3; // Map row/column coordinates into one of nine3x3 regions.
                if(!seen.add("r"+r+d)||!seen.add("c"+c+d)||!seen.add("b"+box+d)) return false; // Any repeated claim violates the board rules.
            }
        }
        return true; // Every filled cell passed all three constraints.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new String[]{"53.......","6........",".........",".........",".........",".........",".........",".........","........."}))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new String[]{"53.......","6........",".........",".........",".........",".........",".........",".........","........."}), true); // A consistent partial board.
        Check.equal(solve(new String[]{"55.......",".........",".........",".........",".........",".........",".........",".........","........."}), false); // Duplicate in one row.
        Check.equal(solve(new String[]{"5........",".5.......",".........",".........",".........",".........",".........",".........","........."}), false); // Distinct rows and columns still share a box.
    }
}
