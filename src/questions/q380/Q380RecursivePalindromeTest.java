package questions.q380;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q380: Recursive Palindrome Test.
 * Contract: Exact case-sensitive UTF-16-unit palindrome test; input non-null.
 * Why it works: A string is a palindrome exactly when its ends match and its interior is a palindrome. A mismatched pair is a complete counterexample.
 * Cost: O(n) time and O(n) stack; two pointers iteratively avoid stack space.
 */
public final class Q380RecursivePalindromeTest { // Keep this question independently runnable and testable.
    private Q380RecursivePalindromeTest() { } // No object state is needed by this standalone exercise.
    public static boolean palindrome(String s) { // Expose the operation for an independent caller and the executable checks below.
        return match(s, 0, s.length() - 1); // Compare the outermost unmatched units first.
    }
    private static boolean match(String s, int left, int right) { // Keep recursive state or a repeated sub-operation inside this solution.
        if (left >= right) return true; // Empty and single-unit interiors are symmetric.
        return s.charAt(left) == s.charAt(right) && match(s, left + 1, right - 1); // Both outer equality and inner symmetry are necessary.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(palindrome("level")); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(palindrome(""), "Q380 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(palindrome("aa"), "Q380 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(!palindrome("abca"), "Q380 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
