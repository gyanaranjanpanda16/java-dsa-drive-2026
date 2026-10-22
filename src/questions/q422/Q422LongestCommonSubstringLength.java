package questions.q422;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q422: Longest Common Substring Length.
 * Contract: Non-null strings; exact UTF-16-unit substring comparison.
 * Why it works: A shared contiguous block ending at a pair of positions extends only when those units match. Taking the maximum of all endpoint states finds every possible block.
 * Cost: O(m*n) time and space.
 */
public final class Q422LongestCommonSubstringLength { // Keep this question independently runnable and testable.
    private Q422LongestCommonSubstringLength() { } // No object state is needed by this standalone exercise.
    public static int length(String a,String b) { // Expose the operation for an independent caller and the executable checks below.
        int[][] suffix=new int[a.length()+1][b.length()+1]; int best=0; // Each state counts matching contiguous suffixes ending at these positions.
        for(int i=1;i<=a.length();i++) { // Consider every possible first-string endpoint.
            for(int j=1;j<=b.length();j++) { // Consider the corresponding second-string endpoint independently.
                if(a.charAt(i-1)==b.charAt(j-1)) suffix[i][j]=1+suffix[i-1][j-1]; // Equal units extend the immediately preceding matching suffix.
                best=Math.max(best,suffix[i][j]); // A mismatch leaves zero, enforcing contiguity rather than skipping characters.
            }
        }
        return best; // The answer may end anywhere, not just at both final positions.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(length("xabcdz","yabcw")); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(length("", "a")==0, "Q422 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(length("abc","def")==0, "Q422 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(length("abc","abc")==3, "Q422 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
