package questions.q390;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q390: Count N Queens Placements.
 * Contract: Count boards with n nonattacking queens; empty board counts as one placement.
 * Why it works: Placing one queen per row removes row conflicts. Three occupancy arrays detect exactly the remaining column and diagonal attacks, so each accepted complete board is legal.
 * Cost: O(n*n!) upper-bound search time, O(n) working space.
 */
public final class Q390CountNQueensPlacements { // Keep this question independently runnable and testable.
    private Q390CountNQueensPlacements() { } // No object state is needed by this standalone exercise.
    public static long count(int n) { // Expose the operation for an independent caller and the executable checks below.
        if(n<0 || n>14) throw new IllegalArgumentException("Use 0..14"); // Bound exhaustive search and long output.
        return place(0,n,new boolean[n],new boolean[Math.max(0,2*n-1)],new boolean[Math.max(0,2*n-1)]); // Track occupied columns and both diagonals.
    }
    private static long place(int row,int n,boolean[] col,boolean[] down,boolean[] up) { // Keep recursive state or a repeated sub-operation inside this solution.
        if(row==n) return 1; // One queen per row has been placed without conflicts.
        long total=0; // Sum independent valid choices for this row.
        for(int c=0;c<n;c++) { // Test each column as this row's queen position.
            int d=row-c+n-1,u=row+c; // Equal values identify shared diagonals.
            if(col[c]||down[d]||up[u]) continue; // An attacked square cannot extend a legal placement.
            col[c]=down[d]=up[u]=true; // Reserve all attack lines before descending.
            total+=place(row+1,n,col,down,up); // Count every valid completion beneath this queen.
            col[c]=down[d]=up[u]=false; // Remove the queen's reservations for the next column.
        }
        return total; // Branches use different columns for this row and cannot duplicate a board.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(count(4)); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(count(0)==1, "Q390 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(count(1)==1, "Q390 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(count(2)==0, "Q390 case 3"); // Verify an independently expected ordinary or boundary result.
        Check.that(count(4)==2, "Q390 case 4"); // Verify an independently expected ordinary or boundary result.
    }
}
