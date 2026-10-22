package questions.q425;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q425: Distinct Target Subsequences.
 * Contract: Non-null strings; positional subsequences, UTF-16-unit equality.
 * Why it works: Selections forming a target prefix partition into those that skip the new source position and those using it to match the final target unit. Descending updates preserve the earlier-source counts.
 * Cost: O(sourceLength*targetLength) time and O(targetLength) space; overflow throws.
 */
public final class Q425DistinctTargetSubsequences { // Keep this question independently runnable and testable.
    private Q425DistinctTargetSubsequences() { } // No object state is needed by this standalone exercise.
    public static long count(String source,String target) { // Expose the operation for an independent caller and the executable checks below.
        long[] dp=new long[target.length()+1]; dp[0]=1; // Every source prefix has exactly one empty positional subsequence.
        for(int i=0;i<source.length();i++) { // Introduce each source position exactly once.
            for(int j=target.length();j>=1;j--) { // Descending target length prevents reusing the current source position.
                if(source.charAt(i)==target.charAt(j-1)) dp[j]=Math.addExact(dp[j],dp[j-1]); // Either skip this position or use it as the target prefix's final unit.
            }
        }
        return dp[target.length()]; // Count distinct selections of source indices, not distinct resulting text values.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(count("rabbbit","rabbit")); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(count("abc", "")==1, "Q425 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(count("", "a")==0, "Q425 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(count("aaa","aa")==3, "Q425 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
