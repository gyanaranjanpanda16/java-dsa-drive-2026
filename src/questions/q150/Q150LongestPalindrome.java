package questions.q150;

import java.util.*;
import questions.support.Check;

/**
 * Q150: Find a longest palindromic substring.
 * Contract: Return the earliest longest palindromic substring under UTF-16 char equality; empty text returns empty text.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: Every palindrome has a center at a character or between characters. Expanding each center finds its largest palindrome; comparing all centers therefore includes an optimal answer.
 * Complexity: O(n²) time and O(1) auxiliary space besides the returned substring.
 */
public final class Q150LongestPalindrome { // Keep one question and its tests together for independent study.
    public static String solve(String text) { // Return the actual contiguous palindrome, not a subsequence.
        int start=0,best=0; // The empty substring is the initial valid candidate.
        for(int center=0;center<2*text.length()-1;center++) { // Alternate character centers and gaps to cover odd and even lengths.
            int left=center/2,right=left+center%2; // Map the center number to its initial symmetric positions.
            while(left>=0&&right<text.length()&&text.charAt(left)==text.charAt(right)) { // Expand only while both boundaries match.
                if(right-left+1>best) { start=left;best=right-left+1; } // Strict improvement preserves the earliest equal-length result.
                left--;right++; // Test the next outer symmetric pair.
            }
        }
        return text.substring(start,start+best); // Extract exactly the winning contiguous interval.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve("babad")); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve("babad"),"bab"); // Earlier equal-length optimum wins.
    Check.equal(solve("cbbd"),"bb"); // A gap center finds an even-length answer.
    Check.equal(solve(""),""); // No centers produces the empty substring.
    }
}
