package questions.q432;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q432: Count Longest Increasing Subsequences.
 * Contract: Count positional strictly increasing subsequences having maximum length.
 * Why it works: Length and multiplicity must be maintained together. Better extensions replace counts; equally long extensions add counts because they differ in their preceding indices.
 * Cost: O(n²) time and O(n) space; overflow throws.
 */
public final class Q432CountLongestIncreasingSubsequences { // Keep this question independently runnable and testable.
    private Q432CountLongestIncreasingSubsequences() { } // No object state is needed by this standalone exercise.
    public static long count(int[] a) { // Expose the operation for an independent caller and the executable checks below.
        if(a.length==0) return 0; // This convention counts no nonempty subsequences in empty input.
        int[] length=new int[a.length]; long[] count=new long[a.length]; Arrays.fill(length,1); Arrays.fill(count,1); // Every position starts one singleton subsequence.
        int best=1; // The optimal nonempty length is at least one.
        for(int i=0;i<a.length;i++) { // Count subsequences by their unique final index.
            for(int j=0;j<i;j++) if(a[j]<a[i]) { // Only strictly smaller earlier values may precede the current value.
                if(length[j]+1>length[i]) { length[i]=length[j]+1; count[i]=count[j]; } // A better length replaces all previously shorter alternatives.
                else if(length[j]+1==length[i]) count[i]=Math.addExact(count[i],count[j]); // Different preceding endpoints contribute disjoint positional subsequences.
            }
            best=Math.max(best,length[i]); // Track the globally longest endpoint state.
        }
        long answer=0; for(int i=0;i<a.length;i++) if(length[i]==best) answer=Math.addExact(answer,count[i]); // Sum only endpoint counts achieving the global length.
        return answer; // Each counted subsequence has exactly one final index.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(count(new int[]{1,3,5,4,7})); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(count(new int[]{})==0, "Q432 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(count(new int[]{2,2,2})==3, "Q432 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(count(new int[]{1,2,3})==1, "Q432 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
