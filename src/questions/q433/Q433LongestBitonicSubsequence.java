package questions.q433;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q433: Longest Bitonic Subsequence.
 * Contract: Strictly increase then strictly decrease; either side may contain only its peak.
 * Why it works: For a fixed peak, the increasing and decreasing choices occupy disjoint index ranges and can be combined. Maximizing over all peak positions covers every possible bitonic subsequence.
 * Cost: O(n²) time and O(n) space.
 */
public final class Q433LongestBitonicSubsequence { // Keep this question independently runnable and testable.
    private Q433LongestBitonicSubsequence() { } // No object state is needed by this standalone exercise.
    public static int length(int[] a) { // Expose the operation for an independent caller and the executable checks below.
        int[] rising=new int[a.length],falling=new int[a.length]; Arrays.fill(rising,1); Arrays.fill(falling,1); // Singleton peaks form valid degenerate bitonic subsequences.
        for(int i=0;i<a.length;i++) for(int j=0;j<i;j++) if(a[j]<a[i]) rising[i]=Math.max(rising[i],rising[j]+1); // Build strict increases ending at each peak.
        for(int i=a.length-1;i>=0;i--) for(int j=i+1;j<a.length;j++) if(a[j]<a[i]) falling[i]=Math.max(falling[i],falling[j]+1); // Build strict decreases beginning at each peak.
        int best=0; // Empty input has length zero.
        for(int i=0;i<a.length;i++) best=Math.max(best,rising[i]+falling[i]-1); // Both halves count the peak, so subtract it once.
        return best; // Every bitonic subsequence has some peak considered by this scan.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(length(new int[]{1,11,2,10,4,5,2,1})); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(length(new int[]{})==0, "Q433 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(length(new int[]{1,2,3})==3, "Q433 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(length(new int[]{2,2})==1, "Q433 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
