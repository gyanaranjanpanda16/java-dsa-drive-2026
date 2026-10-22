package questions.q427;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q427: Longest Palindromic Subsequence.
 * Contract: Non-null string; subsequence positions need not be contiguous; UTF-16 units.
 * Why it works: Equal endpoints can extend an optimal interior palindrome. If endpoints differ, a palindromic subsequence must omit at least one, so the better of the two exclusions is exhaustive.
 * Cost: O(n²) time and space.
 */
public final class Q427LongestPalindromicSubsequence { // Keep this question independently runnable and testable.
    private Q427LongestPalindromicSubsequence() { } // No object state is needed by this standalone exercise.
    public static int length(String s) { // Expose the operation for an independent caller and the executable checks below.
        if(s.isEmpty()) return 0; // Avoid requesting an interval with a negative right endpoint.
        int[][] dp=new int[s.length()][s.length()]; // States cover inclusive source intervals.
        for(int left=s.length()-1;left>=0;left--) { // Every smaller inner interval is known before its enclosing interval.
            dp[left][left]=1; // One unit is a palindrome of length one.
            for(int right=left+1;right<s.length();right++) { // Extend intervals toward the right.
                dp[left][right]=s.charAt(left)==s.charAt(right)?2+(right-left==1?0:dp[left+1][right-1]):Math.max(dp[left+1][right],dp[left][right-1]); // Match both ends or discard an unmatched end.
            }
        }
        return dp[0][s.length()-1]; // The final interval contains every available source position.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(length("bbbab")); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(length("")==0, "Q427 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(length("cbbd")==2, "Q427 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(length("abc")==1, "Q427 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
