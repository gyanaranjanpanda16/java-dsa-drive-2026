package questions.q145;

import java.util.*;
import questions.support.Check;

/**
 * Q145: Find a substring using KMP failure links.
 * Contract: Return first exact pattern index or -1; empty pattern returns0.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: A failure link preserves the longest prefix already known to equal the suffix of matched text. It discards only impossible starts, so no text position needs to be re-read.
 * Complexity: O(n+m) time and O(m) space.
 */
public final class Q145KmpSubstring { // Keep one question and its tests together for independent study.
    public static int solve(String text,String pattern) { // Separate pattern preprocessing from the linear text scan.
        if (pattern.isEmpty()) return 0; // Avoid indexing an empty pattern.
        int[] lps=new int[pattern.length()]; // lps[i] stores the longest proper prefix equal to a suffix through i.
        for (int i=1,j=0;i<pattern.length();i++) { // Extend the prefix information one pattern position at a time.
            while (j>0 && pattern.charAt(i)!=pattern.charAt(j)) j=lps[j-1]; // Reuse the next shorter compatible border after a mismatch.
            if (pattern.charAt(i)==pattern.charAt(j)) j++; // A matching char extends the surviving border.
            lps[i]=j; // Save the border for future mismatch recovery.
        }
        for (int i=0,j=0;i<text.length();i++) { // Consume each text character once.
            while (j>0 && text.charAt(i)!=pattern.charAt(j)) j=lps[j-1]; // Shift the candidate without moving the text index backward.
            if (text.charAt(i)==pattern.charAt(j)) j++; // Extend the matched prefix when chars agree.
            if (j==pattern.length()) return i-j+1; // A full matched prefix identifies the earliest completed occurrence.
        }
        return -1; // The text ended before any complete pattern match.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve("ababcabcabababd","ababd")); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve("ababcabcabababd","ababd"),10); // Failure links skip repeated borders.
    Check.equal(solve("aaaaa","aaa"),0); // Overlaps do not hide the first match.
    Check.equal(solve("abc","z"),-1); // No matching char means absence.
    }
}
