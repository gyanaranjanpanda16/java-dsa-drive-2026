package questions.q450;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q450: Longest Arithmetic Subsequence.
 * Contract: Integer sequence; return longest ordered subsequence with a constant mathematical difference.
 * Why it works: An arithmetic subsequence is determined for extension by its final index and common difference. Any earlier endpoint with that difference can be extended; retaining the longest such history is sufficient.
 * Cost: Expected O(n²) time and O(n²) space.
 */
public final class Q450LongestArithmeticSubsequence { // Keep this question independently runnable and testable.
    private Q450LongestArithmeticSubsequence() { } // No object state is needed by this standalone exercise.
    public static int length(int[] values) { // Expose the operation for an independent caller and the executable checks below.
        List<Map<Long,Integer>> dp=new ArrayList<>(); int best=values.length==0?0:1; // Each endpoint maps a difference to its best subsequence length.
        for(int i=0;i<values.length;i++) { // Later endpoints may extend only earlier positions.
            Map<Long,Integer> current=new HashMap<>(); // Separate states for sequences ending exactly at i.
            for(int j=0;j<i;j++) { // Choose the preceding element of a possible arithmetic subsequence.
                long difference=(long)values[i]-values[j]; // Widen before subtraction to preserve extreme int differences.
                int candidate=dp.get(j).getOrDefault(difference,1)+1; // A missing prior pair starts a new length-two subsequence.
                current.merge(difference,candidate,Math::max); // Keep the best history for this endpoint and difference.
                best=Math.max(best,candidate); // Compare all endpoints and all differences.
            }
            dp.add(current); // Publish this endpoint only after its predecessors have been processed.
        }
        return best; // Empty and singleton inputs retain their defined lengths.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(length(new int[]{3,6,9,12})); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(length(new int[]{})==0, "Q450 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(length(new int[]{5})==1, "Q450 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(length(new int[]{9,4,7,2,10})==3, "Q450 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
