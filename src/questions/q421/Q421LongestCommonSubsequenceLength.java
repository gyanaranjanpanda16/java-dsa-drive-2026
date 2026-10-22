package questions.q421;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q421: Longest Common Subsequence Length.
 * Contract: Non-null strings; compare UTF-16 char units; a subsequence need not be contiguous.
 * Why it works: When final units match an optimum can pair them; otherwise a common subsequence must skip at least one unmatched end. These cases yield the recurrence over prefix pairs.
 * Cost: O(m*n) time and space.
 */
public final class Q421LongestCommonSubsequenceLength { // Keep this question independently runnable and testable.
    private Q421LongestCommonSubsequenceLength() { } // No object state is needed by this standalone exercise.
    public static int length(String a,String b) { // Expose the operation for an independent caller and the executable checks below.
        int[][] dp=new int[a.length()+1][b.length()+1]; // Empty-prefix subsequences have length zero.
        for(int i=1;i<=a.length();i++) { // Solve progressively longer prefixes of the first string.
            for(int j=1;j<=b.length();j++) { // Each dependency is in an earlier row or column.
                if(a.charAt(i-1)==b.charAt(j-1)) dp[i][j]=1+dp[i-1][j-1]; // Matching final units extend a common shorter subsequence.
                else dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]); // A mismatch forces at least one final unit to be excluded.
            }
        }
        return dp[a.length()][b.length()]; // This prefix pair covers both complete strings.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(length("abcde","ace")); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(length("", "abc")==0, "Q421 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(length("abc","def")==0, "Q421 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(length("abc","abc")==3, "Q421 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
