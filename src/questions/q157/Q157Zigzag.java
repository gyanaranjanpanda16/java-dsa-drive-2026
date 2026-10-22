package questions.q157;

import java.util.*;
import questions.support.Check;

/**
 * Q157: Read a zigzag layout row by row.
 * Contract: Arrange UTF-16 chars down then diagonally up across a positive number of rows and return row-wise text.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: A direction flag changes only at the top and bottom rows, so each character is assigned to exactly the row in its zigzag trajectory.
 * Complexity: O(n+rows) time and space.
 */
public final class Q157Zigzag { // Keep one question and its tests together for independent study.
    public static String solve(String text,int rows) { // Return the reading order, not a padded visual diagram.
        if(rows<1) throw new IllegalArgumentException("positive rows required"); // A layout needs at least one row.
        if(rows==1||rows>=text.length()) return text; // No upward turn can reorder these cases.
        StringBuilder[] lines=new StringBuilder[rows]; // Each row has its own growing output sequence.
        for(int i=0;i<rows;i++) lines[i]=new StringBuilder(); // Initialize buffers before appending characters.
        int row=0,direction=1; // Start at the top and move downward.
        for(char c:text.toCharArray()) { // Assign each input character exactly once.
            lines[row].append(c); // Preserve arrival order inside the chosen row.
            if(row==0) direction=1;else if(row==rows-1) direction=-1; // Turn only at the two boundaries.
            row+=direction; // Move to the row receiving the next character.
        }
        StringBuilder result=new StringBuilder(); // Concatenate the completed rows in top-to-bottom order.
        for(StringBuilder line:lines) result.append(line); // Row buffers already contain their correct horizontal order.
        return result.toString(); // Each original char appears once in the row-wise reading.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve("PAYPALISHIRING",3)); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve("PAYPALISHIRING",3),"PAHNAPLSIIGYIR"); // Exercise repeated turns.
    Check.equal(solve("abc",1),"abc"); // One row preserves original order.
    Check.equal(solve("",4),""); // Empty text needs no layout.
    }
}
