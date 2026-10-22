package questions.q456;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q456: Minimum Forward Jumps.
 * Contract: Nonnegative maximum forward jump lengths; return-1 when unreachable or empty.
 * Why it works: Scanning all positions of one reachable layer before extending the boundary simulates breadth-first search over jump counts without storing every edge. Each new layer adds one necessary jump.
 * Cost: O(n) time and O(1) space.
 */
public final class Q456MinimumForwardJumps { // Keep this question independently runnable and testable.
    private Q456MinimumForwardJumps() { } // No object state is needed by this standalone exercise.
    public static int minimum(int[] jumps) { // Expose the operation for an independent caller and the executable checks below.
        if(jumps.length==0) return -1; // Empty input has no reachable starting point.
        if(jumps.length==1) return 0; // The start already is the destination.
        int count=0,end=0; long farthest=0; // end closes the current BFS-like layer of positions reachable in count jumps.
        for(int i=0;i<jumps.length-1;i++) { // The destination itself never needs to initiate another jump.
            if(i>farthest) return -1; // An unreachable gap prevents any later progress.
            if(jumps[i]<0) throw new IllegalArgumentException("Nonnegative jumps"); // Forward ranges rely on nonnegative lengths.
            farthest=Math.max(farthest,(long)i+jumps[i]); // Gather the farthest next-layer reach from every current-layer position.
            if(i==end) { if(farthest<=end) return -1; count++; end=(int)Math.min(farthest,jumps.length-1L); } // Finish a layer and commit exactly one additional jump.
        }
        return count; // Each completed frontier represents the minimum possible jump count.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(minimum(new int[]{2,3,1,1,4})); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(minimum(new int[]{0})==0, "Q456 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(minimum(new int[]{0,1})==-1, "Q456 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(minimum(new int[]{1,1,1})==2, "Q456 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
