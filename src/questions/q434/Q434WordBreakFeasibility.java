package questions.q434;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q434: Word Break Feasibility.
 * Contract: Non-null text and dictionary of nonempty words; words may be reused.
 * Why it works: Any full segmentation has a final word. Trying its start boundary reduces the problem to an already-solved prefix, yielding an exhaustive existence test.
 * Cost: O(n³) worst-case time including Java substring copying/hashing and O(n) DP space, excluding transient substrings.
 */
public final class Q434WordBreakFeasibility { // Keep this question independently runnable and testable.
    private Q434WordBreakFeasibility() { } // No object state is needed by this standalone exercise.
    public static boolean possible(String text,Set<String> dictionary) { // Expose the operation for an independent caller and the executable checks below.
        if(dictionary.contains("")) throw new IllegalArgumentException("Empty dictionary word not allowed"); // Empty words would not advance a segmentation.
        boolean[] dp=new boolean[text.length()+1]; dp[0]=true; // Empty text is segmented using zero words.
        for(int end=1;end<=text.length();end++) { // Solve all prefixes before extending them.
            for(int start=0;start<end;start++) { // Try each possible boundary of the final word.
                if(dp[start]&&dictionary.contains(text.substring(start,end))) { dp[end]=true; break; } // One valid preceding segmentation and dictionary suffix suffices.
            }
        }
        return dp[text.length()]; // Require every text unit to belong to a dictionary word.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(possible("leetcode",Set.of("leet","code"))); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(possible("",Set.of("a")), "Q434 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(!possible("catsandog",Set.of("cats","dog","sand","and","cat")), "Q434 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(possible("aaaa",Set.of("aa")), "Q434 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
