package questions.q424;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q424: Minimum Deletions To Equal Strings.
 * Contract: Only deletions are allowed; non-null UTF-16 strings.
 * Why it works: After deletions the surviving text must be a subsequence of both inputs. Keeping a longest common subsequence maximizes retained units and therefore minimizes deleted units.
 * Cost: O(m*n) time and space.
 */
public final class Q424MinimumDeletionsToEqualStrings { // Keep this question independently runnable and testable.
    private Q424MinimumDeletionsToEqualStrings() { } // No object state is needed by this standalone exercise.
    public static int deletions(String a,String b) { // Expose the operation for an independent caller and the executable checks below.
        int[][] dp=new int[a.length()+1][b.length()+1]; // Track retained common subsequence lengths instead of constructing deletion plans.
        for(int i=1;i<=a.length();i++) for(int j=1;j<=b.length();j++) { // Build answers for all prefix pairs.
            dp[i][j]=a.charAt(i-1)==b.charAt(j-1)?1+dp[i-1][j-1]:Math.max(dp[i-1][j],dp[i][j-1]); // Keep matching ends or skip one unmatched end.
        }
        return a.length()+b.length()-2*dp[a.length()][b.length()]; // Each retained common unit saves one deletion in each string.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(deletions("sea","eat")); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(deletions("", "abc")==3, "Q424 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(deletions("abc","abc")==0, "Q424 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(deletions("a","b")==2, "Q424 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
