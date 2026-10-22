package questions.q423;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q423: Edit Distance.
 * Contract: Unit-cost insertion, deletion, replacement on UTF-16 char units.
 * Why it works: Any final edit is an insertion, deletion, or replacement, and each leaves a smaller prefix conversion. Taking the cheapest exhaustive final operation yields an optimal result.
 * Cost: O(m*n) time and space.
 */
public final class Q423EditDistance { // Keep this question independently runnable and testable.
    private Q423EditDistance() { } // No object state is needed by this standalone exercise.
    public static int distance(String a,String b) { // Expose the operation for an independent caller and the executable checks below.
        int[][] dp=new int[a.length()+1][b.length()+1]; // Prefix states represent the minimum edits needed for conversion.
        for(int i=0;i<=a.length();i++) dp[i][0]=i; // Delete every unit to reach an empty target.
        for(int j=0;j<=b.length();j++) dp[0][j]=j; // Insert every target unit when the source is empty.
        for(int i=1;i<=a.length();i++) for(int j=1;j<=b.length();j++) { // Process shorter prefixes before longer conversions.
            if(a.charAt(i-1)==b.charAt(j-1)) dp[i][j]=dp[i-1][j-1]; // Matching ends need no extra edit.
            else dp[i][j]=1+Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1])); // Replace, delete, or insert the final unmatched unit.
        }
        return dp[a.length()][b.length()]; // The full prefix pair is the requested conversion.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(distance("horse","ros")); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(distance("", "abc")==3, "Q423 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(distance("same","same")==0, "Q423 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(distance("kitten","sitting")==3, "Q423 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
