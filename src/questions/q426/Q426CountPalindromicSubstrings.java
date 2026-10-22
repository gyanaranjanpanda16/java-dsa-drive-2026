package questions.q426;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q426: Count Palindromic Substrings.
 * Contract: Count nonempty positional substrings using UTF-16-unit equality.
 * Why it works: An interval is a palindrome exactly when its ends agree and its interior is palindromic. Processing intervals after their interior makes this a direct inductive proof.
 * Cost: O(n²) time and space.
 */
public final class Q426CountPalindromicSubstrings { // Keep this question independently runnable and testable.
    private Q426CountPalindromicSubstrings() { } // No object state is needed by this standalone exercise.
    public static long count(String s) { // Expose the operation for an independent caller and the executable checks below.
        boolean[][] palindrome=new boolean[s.length()][s.length()]; long total=0; // A state records whether one particular inclusive interval is symmetric.
        for(int left=s.length()-1;left>=0;left--) { // Larger left endpoints solve the inner interval first.
            for(int right=left;right<s.length();right++) { // Include every nonempty interval exactly once.
                palindrome[left][right]=s.charAt(left)==s.charAt(right)&&(right-left<2||palindrome[left+1][right-1]); // Equal ends enclose either a trivial or already-proven palindromic interior.
                if(palindrome[left][right]) total++; // Different positions count as different substrings even when their text matches.
            }
        }
        return total; // Every possible pair of interval endpoints was considered.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(count("aaa")); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(count("")==0, "Q426 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(count("abc")==3, "Q426 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(count("aaa")==6, "Q426 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
