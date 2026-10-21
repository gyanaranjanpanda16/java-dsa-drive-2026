package questions.q392;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q392: Word Search Backtracking.
 * Contract: Rectangular board, four-way movement, no reuse within a path; input board is preserved.
 * Why it works: The recursion represents one simple board path matching the consumed prefix. Marking and restoring cells enforces no reuse while allowing other paths to use them later.
 * Cost: O(rows*cols*4^L) upper-bound time, O(rows*cols+L) working space.
 */
public final class Q392WordSearchBacktracking { // Keep this question independently runnable and testable.
    private Q392WordSearchBacktracking() { } // No object state is needed by this standalone exercise.
    public static boolean exists(char[][] board,String word) { // Expose the operation for an independent caller and the executable checks below.
        if(word.isEmpty()) return true; // The empty word uses no cells.
        if(board.length==0 || board[0].length==0) return false; // Nonempty text needs at least one board cell.
        boolean[][] used=new boolean[board.length][board[0].length]; // A path cannot reuse a cell.
        for(int r=0;r<board.length;r++) for(int c=0;c<board[0].length;c++) if(find(board,word,0,r,c,used)) return true; // Try every possible starting cell.
        return false; // Every starting cell has been disproved.
    }
    private static boolean find(char[][] b,String w,int i,int r,int c,boolean[][] used) { // Keep recursive state or a repeated sub-operation inside this solution.
        if(i==w.length()) return true; // Every required character has been matched.
        if(r<0||c<0||r==b.length||c==b[0].length||used[r][c]||b[r][c]!=w.charAt(i)) return false; // Reject illegal or mismatching steps.
        used[r][c]=true; // Reserve this cell for the current path only.
        boolean found=find(b,w,i+1,r+1,c,used)||find(b,w,i+1,r-1,c,used)||find(b,w,i+1,r,c+1,used)||find(b,w,i+1,r,c-1,used); // Try four-way adjacency.
        used[r][c]=false; // Restore the board state even after finding an answer.
        return found; // Report whether any continuation completed the word.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(exists(new char[][]{{'A','B'},{'C','D'}},"ABD")); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(exists(new char[][]{},""), "Q392 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(exists(new char[][]{{'A'}},"A"), "Q392 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(!exists(new char[][]{{'A'}},"AA"), "Q392 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
