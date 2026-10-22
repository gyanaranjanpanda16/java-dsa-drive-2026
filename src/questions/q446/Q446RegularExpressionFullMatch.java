package questions.q446;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q446: Regular Expression Full Match.
 * Contract: Subset of regex: dot means any UTF-16 unit, star repeats preceding atom; other units are literals, with no escaping.
 * Why it works: An atom-star suffix either contributes nothing or consumes a matching final character while retaining the same pattern. Ordinary atoms consume one character, so the recurrence covers all full matches.
 * Cost: O(textLength*patternLength) time and space.
 */
public final class Q446RegularExpressionFullMatch { // Keep this question independently runnable and testable.
    private Q446RegularExpressionFullMatch() { } // No object state is needed by this standalone exercise.
    public static boolean matches(String text,String pattern) { // Expose the operation for an independent caller and the executable checks below.
        for(int j=0;j<pattern.length();j++) if(pattern.charAt(j)=='*'&&(j==0||pattern.charAt(j-1)=='*')) throw new IllegalArgumentException("Star needs one preceding atom"); // Reject malformed repetition operators.
        boolean[][] dp=new boolean[text.length()+1][pattern.length()+1]; dp[0][0]=true; // Empty input matches an empty pattern.
        for(int j=2;j<=pattern.length();j++) if(pattern.charAt(j-1)=='*') dp[0][j]=dp[0][j-2]; // An atom-star pair may contribute zero characters.
        for(int i=1;i<=text.length();i++) for(int j=1;j<=pattern.length();j++) { // Shorter prefix dependencies are ready before this state.
            char token=pattern.charAt(j-1); // Read the final token of the current pattern prefix.
            if(token=='*') { char atom=pattern.charAt(j-2); dp[i][j]=dp[i][j-2]||((atom=='.'||atom==text.charAt(i-1))&&dp[i-1][j]); } // Use zero repeats or one matching character plus more repeats.
            else dp[i][j]=(token=='.'||token==text.charAt(i-1))&&dp[i-1][j-1]; // A literal or dot consumes one character from both prefixes.
        }
        return dp[text.length()][pattern.length()]; // Matching is anchored at both ends.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(matches("aab","c*a*b")); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(matches("","a*"), "Q446 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(!matches("mississippi","mis*is*p*."), "Q446 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(matches("ab",".*"), "Q446 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
