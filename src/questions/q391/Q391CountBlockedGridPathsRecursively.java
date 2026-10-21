package questions.q391;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q391: Count Blocked Grid Paths Recursively.
 * Contract: Rectangular non-null boolean grid; true means blocked; down/right only; small grids and counts fitting long.
 * Why it works: Every route begins with a down or right move. Adding those disjoint recursive route counts is correct, though repeated subproblems motivate later memoization.
 * Cost: Exponential O(2^(rows+columns)) upper bound and O(rows+columns) stack.
 */
public final class Q391CountBlockedGridPathsRecursively { // Keep this question independently runnable and testable.
    private Q391CountBlockedGridPathsRecursively() { } // No object state is needed by this standalone exercise.
    public static long paths(boolean[][] blocked) { // Expose the operation for an independent caller and the executable checks below.
        if(blocked.length==0 || blocked[0].length==0) return 0; // An empty grid has no destination cell.
        return visit(blocked,0,0); // Begin at the top-left position.
    }
    private static long visit(boolean[][] b,int r,int c) { // Keep recursive state or a repeated sub-operation inside this solution.
        if(r==b.length || c==b[0].length || b[r][c]) return 0; // Outside or blocked cells cannot belong to a path.
        if(r==b.length-1 && c==b[0].length-1) return 1; // Reaching the destination completes one path.
        return visit(b,r+1,c)+visit(b,r,c+1); // Every valid first move is down or right, so the sets are disjoint.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(paths(new boolean[][]{{false,false},{false,false}})); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(paths(new boolean[][]{})==0, "Q391 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(paths(new boolean[][]{{false}})==1, "Q391 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(paths(new boolean[][]{{true}})==0, "Q391 case 3"); // Verify an independently expected ordinary or boundary result.
        Check.that(paths(new boolean[][]{{false,true},{false,false}})==1, "Q391 case 4"); // Verify an independently expected ordinary or boundary result.
    }
}
