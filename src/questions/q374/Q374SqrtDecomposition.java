package questions.q374; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Maintain range totals with square-root-sized blocks.
 * Contract: Operations[0,index,value] assign an int;[1,left,right] query a valid half-open range sum. Return long totals; empty ranges are allowed.
 * Why: Cache the sum of each block. A range query scans only its unaligned ends and consumes entire middle blocks at once; an assignment adjusts one block by a difference. This offers a simpler compromise between a plain array and a tree.
 * Cost: O(n+u+q*sqrt(n+1)) time for u assignments and q queries; O(n) storage.
 */
public final class Q374SqrtDecomposition { // Keep the algorithm, demonstration, and boundary checks together.
    private Q374SqrtDecomposition() { } // Only static entry points are needed for this exercise.
    public static List<Long> solve(int[] initial,int[][] operations) { // Maintain direct access to values together with coarser cached totals.
        int n=initial.length,size=Math.max(1,(int)Math.sqrt(n)+1); long[] values=new long[n],blocks=new long[(n+size-1)/size]; // A positive block size also handles empty arrays.
        for(int i=0;i<n;i++) { values[i]=initial[i]; blocks[i/size]+=initial[i]; } // Build every block total in one pass.
        List<Long> result=new ArrayList<>(); // Preserve query outputs while assignments remain silent.
        for(int[] op:operations) { // All positions and half-open bounds are valid by contract.
            if(op[0]==0) { blocks[op[1]/size]+=(long)op[2]-values[op[1]]; values[op[1]]=op[2]; } // Assignment changes exactly one cached block by the value difference.
            else if(op[0]==1) { // Walk the range using whole blocks whenever alignment permits.
                long total=0; int at=op[1]; // Begin at the inclusive left endpoint.
                while(at<op[2]) { // The current index always marks the next uncovered element.
                    if(at%size==0 && at+size<=op[2]) { total+=blocks[at/size]; at+=size; } // Consume a complete aligned block in one step.
                    else total+=values[at++]; // Scan only boundary elements that cannot form a full covered block.
                }
                result.add(total); // Every requested element was covered exactly once.
            } else throw new IllegalArgumentException("unknown operation"); // Reject unsupported command codes.
        }
        return result; // This layout favors simple implementation over logarithmic query bounds.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new int[]{1,2,3,4,5},new int[][]{{1,1,5},{0,2,10},{1,0,5}}))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new int[]{1,2,3,4,5},new int[][]{{1,1,5},{0,2,10},{1,0,5}}), List.of(14L,22L)); // Partial blocks and a corrected element.
        Check.equal(solve(new int[]{},new int[][]{{1,0,0}}), List.of(0L)); // Empty query on empty input.
        Check.equal(solve(new int[]{Integer.MAX_VALUE,Integer.MAX_VALUE},new int[][]{{1,0,2}}), List.of(4294967294L)); // Block totals use long.
    }
}
