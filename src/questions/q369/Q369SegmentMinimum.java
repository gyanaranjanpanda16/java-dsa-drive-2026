package questions.q369; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Maintain range minima under point assignment.
 * Contract: Operations[0,index,value] assign one int;[1,left,right] query a nonempty valid half-open range minimum. Empty initial arrays are allowed only with no operations.
 * Why: Each internal node stores the minimum of its children. Point changes repair just the ancestor chain; a range is decomposed into disjoint canonical nodes. Unlike additive prefix sums, minimum cannot generally be recovered by subtracting two prefixes. Useful for live lowest-price or minimum-load queries.
 * Cost: O(n+q log(n+1)) time and O(n) tree space.
 */
public final class Q369SegmentMinimum { // Keep the algorithm, demonstration, and boundary checks together.
    private Q369SegmentMinimum() { } // Only static entry points are needed for this exercise.
    public static List<Integer> solve(int[] initial,int[][] operations) { // Store leaves in the second half of a compact iterative tree.
        int n=initial.length; int[] tree=new int[2*n]; System.arraycopy(initial,0,tree,n,n); // Leaf i+n represents original index i.
        for(int i=n-1;i>0;i--) tree[i]=Math.min(tree[2*i],tree[2*i+1]); // Build parent minima after both children are ready.
        List<Integer> result=new ArrayList<>(); // Queries append one aggregate while assignments only change the tree.
        for(int[] op:operations) { // All coordinates follow the stated valid-bounds contract.
            if(op[0]==0) { // Replace one leaf and rebuild the containing ranges.
                int at=op[1]+n; tree[at]=op[2]; // Update the exact represented value.
                while((at/=2)>0) tree[at]=Math.min(tree[2*at],tree[2*at+1]); // Only ancestors can have changed minima.
            } else if(op[0]==1) { // Decompose a half-open query from its leaf boundaries inward.
                if(op[1]>=op[2]) throw new IllegalArgumentException("nonempty range required"); // Minimum has no ordinary int answer for an empty range.
                int left=op[1]+n,right=op[2]+n,best=Integer.MAX_VALUE; // MAX_VALUE is the neutral element for minimum.
                while(left<right) { // Consume exposed boundary nodes before ascending their parents.
                    if((left&1)==1) best=Math.min(best,tree[left++]); if((right&1)==1) best=Math.min(best,tree[--right]); // Odd boundaries reveal complete covered segments.
                    left/=2; right/=2; // Remaining aligned boundaries refer to twice-as-large parent segments.
                }
                result.add(best); // The selected nodes partition exactly the original query interval.
            } else throw new IllegalArgumentException("unknown operation"); // Reject unsupported commands.
        }
        return result; // Query results reflect all preceding assignments.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new int[]{5,2,7},new int[][]{{1,0,3},{0,1,9},{1,0,3}}))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new int[]{5,2,7},new int[][]{{1,0,3},{0,1,9},{1,0,3}}), List.of(2,5)); // An updated former minimum changes the result.
        Check.equal(solve(new int[]{-3},new int[][]{{1,0,1}}), List.of(-3)); // A single negative leaf.
        Check.equal(solve(new int[]{4,1,8,2},new int[][]{{1,2,4}}), List.of(2)); // A proper subrange.
    }
}
