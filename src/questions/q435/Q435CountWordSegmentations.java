package questions.q435;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q435: Count Word Segmentations.
 * Contract: Dictionary is a set of nonempty words; word reuse is allowed.
 * Why it works: Distinct final boundaries partition the segmentation set. Adding the counts of their preceding prefixes yields every segmentation exactly once.
 * Cost: O(n³) worst-case time with Java substring creation/hashing and O(n) DP space; count overflow throws.
 */
public final class Q435CountWordSegmentations { // Keep this question independently runnable and testable.
    private Q435CountWordSegmentations() { } // No object state is needed by this standalone exercise.
    public static long count(String text,Set<String> dictionary) { // Expose the operation for an independent caller and the executable checks below.
        if(dictionary.contains("")) throw new IllegalArgumentException("No empty words"); // Every chosen word must advance the boundary.
        long[] dp=new long[text.length()+1]; dp[0]=1; // One empty sequence of words forms empty text.
        for(int end=1;end<=text.length();end++) { // Count segmentations for increasing prefix lengths.
            for(int start=0;start<end;start++) if(dictionary.contains(text.substring(start,end))) dp[end]=Math.addExact(dp[end],dp[start]); // Append each possible final dictionary word to all preceding segmentations.
        }
        return dp[text.length()]; // Every segmentation has one unique final boundary and word.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(count("aaaa",Set.of("a","aa"))); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(count("",Set.of("a"))==1, "Q435 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(count("b",Set.of("a"))==0, "Q435 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(count("aa",Set.of("a","aa"))==2, "Q435 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
