package questions.q380; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Solve a Sudoku board using constraints and backtracking.
 * Contract: Accept exactly nine strings of nine characters, using digits1..9 or dot for an empty cell. Return one solved board as nine strings, or an empty list if no completion exists. Reject malformed boards or conflicting initial clues. The puzzle need not have a unique solution; input remains unchanged.
 * Why: Q223 checks clues only; solving requires choosing values for empty cells. Keep boolean tables for row, column, and box occupancy so a candidate can be checked immediately. Try a permitted digit, recurse, then undo all three occupancy entries if it leads to failure. Initial clues are never changed. This is constraint satisfaction, also used in small scheduling and assignment puzzles; arbitrary enumeration without pruning would try9 choices for every empty cell.
 * Cost: O(9^E) conservative worst-case time for E empty cells. Occupancy tables and board use fixed O(81) storage, and recursion is at most81 cells deep. Choosing the most constrained cell first is a useful further optimization.
 */
public final class Q380SolveSudoku { // Keep the algorithm, demonstration, and boundary checks together.
    private Q380SolveSudoku() { } // Only static entry points are needed for this exercise.
    public static List<String> solve(String[] input) { // Work on a private board so failed searches cannot damage the caller's puzzle.
        if(input.length!=9) throw new IllegalArgumentException("nine rows required"); // Sudoku uses a fixed board size.
        char[][] board=new char[9][]; boolean[][] rows=new boolean[9][9],cols=new boolean[9][9],boxes=new boolean[9][9]; // Each table records occupied digits.
        for(int r=0;r<9;r++) { // Validate and register all initial clues before searching.
            if(input[r].length()!=9) throw new IllegalArgumentException("nine columns required"); board[r]=input[r].toCharArray(); // Copy each immutable input row.
            for(int c=0;c<9;c++) { // A clue must satisfy all three overlapping constraints.
                char value=board[r][c]; if(value=='.') continue; // Empty cells will be assigned by the search.
                if(value<'1'||value>'9') throw new IllegalArgumentException("digits or dot required"); // Validate before indexing the tables.
                int d=value-'1',box=(r/3)*3+c/3; // Number digits and boxes from zero.
                if(rows[r][d]||cols[c][d]||boxes[box][d]) throw new IllegalArgumentException("conflicting clues"); // Reject a duplicate in any constraint group.
                rows[r][d]=cols[c][d]=boxes[box][d]=true; // Reserve this fixed clue in every group.
            }
        }
        if(!fill(board,0,rows,cols,boxes)) return List.of(); // Exhausted choices prove no completion exists.
        List<String> answer=new ArrayList<>(); for(char[] row:board) answer.add(new String(row)); // Return a detached solved board.
        return answer; // Search stops after the first complete solution.
    }
    private static boolean fill(char[][] board,int cell,boolean[][] rows,boolean[][] cols,boolean[][] boxes) { // Flatten the row-major cursor into one integer.
        if(cell==81) return true; // Every cell has a permitted value.
        int r=cell/9,c=cell%9,box=(r/3)*3+c/3; // Recover this cell's three constraint groups.
        if(board[r][c]!='.') return fill(board,cell+1,rows,cols,boxes); // Never alter an initial clue.
        for(int d=0;d<9;d++) { // Try candidates in deterministic ascending order.
            if(rows[r][d]||cols[c][d]||boxes[box][d]) continue; // Prune a locally invalid candidate immediately.
            board[r][c]=(char)('1'+d); rows[r][d]=cols[c][d]=boxes[box][d]=true; // Make the tentative assignment consistently.
            if(fill(board,cell+1,rows,cols,boxes)) return true; // Preserve assignments on the successful path.
            board[r][c]='.'; rows[r][d]=cols[c][d]=boxes[box][d]=false; // Restore all state before the next candidate.
        }
        return false; // The caller must revise its preceding choice.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new String[]{".34678912","672195348","198342567","859761423","426853791","713924856","961537284","287419635","345286179"}))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new String[]{".34678912","672195348","198342567","859761423","426853791","713924856","961537284","287419635","345286179"}), List.of("534678912","672195348","198342567","859761423","426853791","713924856","961537284","287419635","345286179")); // One missing clue.
        Check.equal(solve(new String[]{"534678912","672195348","198342567","859761423","426853791","713924856","961537284","287419635","345286179"}), List.of("534678912","672195348","198342567","859761423","426853791","713924856","961537284","287419635","345286179")); // Already solved.
        Check.equal(solve(new String[]{".34678912","672195348","198342567","859761423","426853791","713924856","961537284","287419635","34528617."}), List.of("534678912","672195348","198342567","859761423","426853791","713924856","961537284","287419635","345286179")); // Missing cells in different groups.
    }
}
