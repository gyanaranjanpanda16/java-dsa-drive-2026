package questions.q430;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q430: Wildcard Pattern Match.
 * Contract: Full-string wildcard matching: ? means one UTF-16 unit, * means any number; other units are literals.
 * Why it works: A star match splits into empty consumption or at least one unit, leaving the same star to consume more. Other tokens must consume one matching unit; these cases are exhaustive.
 * Cost: O(textLength*patternLength) time and space.
 */
public final class Q430WildcardPatternMatch { // Keep this question independently runnable and testable.
    private Q430WildcardPatternMatch() { } // No object state is needed by this standalone exercise.
    public static boolean matches(String text,String pattern) { // Expose the operation for an independent caller and the executable checks below.
        boolean[][] dp=new boolean[text.length()+1][pattern.length()+1]; dp[0][0]=true; // Empty text matches empty pattern.
        for(int j=1;j<=pattern.length();j++) dp[0][j]=pattern.charAt(j-1)=='*'&&dp[0][j-1]; // Only an all-star prefix can match empty text.
        for(int i=1;i<=text.length();i++) for(int j=1;j<=pattern.length();j++) { // Solve shorter text/pattern prefix pairs first.
            char token=pattern.charAt(j-1); // Interpret only the final token of this pattern prefix.
            if(token=='*') dp[i][j]=dp[i][j-1]||dp[i-1][j]; // Star matches nothing or absorbs one more text unit.
            else dp[i][j]=(token=='?'||token==text.charAt(i-1))&&dp[i-1][j-1]; // A literal or question mark consumes exactly one unit.
        }
        return dp[text.length()][pattern.length()]; // Require a full match, not a matching substring.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(matches("adceb","*a*b")); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(matches("","***"), "Q430 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(!matches("abc","a?d"), "Q430 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(matches("abc","a?c"), "Q430 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
