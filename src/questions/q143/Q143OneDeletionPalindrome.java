package questions.q143; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Palindrome after at most one deletion.
 * Contract: Return whether deleting zero or one UTF-16 char makes the entire string a palindrome. Case and spaces are significant; use a code-point representation for full Unicode characters.
 * Why: A baseline deletes each position and checks the result, taking quadratic time. Matching outer characters can be kept. At the first mismatch, any valid repair must delete either the left or the right mismatching character: deleting elsewhere leaves that mismatch. Check those two remaining ranges without allocating substrings. This models validation with a single missing or extra symbol, rather than arbitrary typo correction.
 * Cost: O(n) time and O(1) extra space: one outer scan and at most two additional scans.
 */
public final class Q143OneDeletionPalindrome { // Keep the algorithm, demonstration, and boundary checks together.
    private Q143OneDeletionPalindrome() { } // Only static entry points are needed for this exercise.
    public static boolean solve(String text) { // Keep indices into the original immutable string.
        int left=0,right=text.length()-1; // Start with the complete range.
        while(left<right) { // A zero- or one-character range is already a palindrome.
            if(text.charAt(left)!=text.charAt(right)) return palindrome(text,left+1,right)||palindrome(text,left,right-1); // Spend the only deletion at one mismatching endpoint.
            left++; right--; // Equal outer characters can remain in the answer.
        }
        return true; // No deletion was needed.
    }
    private static boolean palindrome(String text,int left,int right) { // This check has no deletion budget left.
        while(left<right) if(text.charAt(left++)!=text.charAt(right--)) return false; // Every remaining mirrored pair must agree.
        return true; // All pairs matched, including an empty range.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve("abca"))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve("abca"), true); // Delete the middle b or c.
        Check.equal(solve("abc"), false); // One deletion is insufficient.
        Check.equal(solve(""), true); // Empty string is symmetric.
        Check.equal(solve("deeee"), true); // Deletion at the beginning.
    }
}
