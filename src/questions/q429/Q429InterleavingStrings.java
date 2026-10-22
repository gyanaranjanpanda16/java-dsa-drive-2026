package questions.q429;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q429: Interleaving Strings.
 * Contract: Use all characters of both non-null sources, preserving order within each; UTF-16 units.
 * Why it works: The final target unit must come from the next unit of one source available unit. Prefix states capture exactly how much of each source has been consumed while preserving order.
 * Cost: O(m*n) time and space.
 */
public final class Q429InterleavingStrings { // Keep this question independently runnable and testable.
    private Q429InterleavingStrings() { } // No object state is needed by this standalone exercise.
    public static boolean possible(String a,String b,String target) { // Expose the operation for an independent caller and the executable checks below.
        if(a.length()+b.length()!=target.length()) return false; // Interleaving uses every unit exactly once.
        boolean[][] dp=new boolean[a.length()+1][b.length()+1]; dp[0][0]=true; // Empty prefixes form the empty target prefix.
        for(int i=0;i<=a.length();i++) for(int j=0;j<=b.length();j++) { // Each state consumes i units from a and j from b.
            if(i>0&&a.charAt(i-1)==target.charAt(i+j-1)) dp[i][j]|=dp[i-1][j]; // Take the final target unit from the end of the a prefix.
            if(j>0&&b.charAt(j-1)==target.charAt(i+j-1)) dp[i][j]|=dp[i][j-1]; // Or take it from the end of the b prefix.
        }
        return dp[a.length()][b.length()]; // Both complete sources must have been consumed in order.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(possible("aabcc","dbbca","aadbbcbcac")); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(possible("","",""), "Q429 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(possible("ab","cd","acbd"), "Q429 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(!possible("ab","cd","adbc"), "Q429 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
