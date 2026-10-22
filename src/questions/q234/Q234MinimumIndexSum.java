package questions.q234; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Find shared preferences with minimum combined rank.
 * Contract: Each input is a list of distinct non-null names in preference order. Return all shared names minimizing the sum of their zero-based indices, sorted lexically.
 * Why: Index the first ranking, then scan the second. Each shared name has one uniquely determined rank sum, so keeping only the smallest sum and its ties is sufficient. This combines two ordered preferences without comparing every pair.
 * Cost: Expected O(n+m+t log t) time and O(n+t) space for t tied answers, excluding string comparison costs.
 */
public final class Q234MinimumIndexSum { // Keep the algorithm, demonstration, and boundary checks together.
    private Q234MinimumIndexSum() { } // Only static entry points are needed for this exercise.
    public static List<String> solve(String[] first,String[] second) { // Return every tied optimum under the explicit rank-sum rule.
        Map<String,Integer> index=new HashMap<>(); for(int i=0;i<first.length;i++) index.put(first[i],i); // Constant-time lookup replaces repeated first-list scans.
        long best=Long.MAX_VALUE; List<String> result=new ArrayList<>(); // Use a wider sum even though individual indices are ints.
        for(int j=0;j<second.length;j++) { // Inspect each second-list preference exactly once.
            Integer i=index.get(second[j]); if(i==null) continue; // Nonshared names cannot be chosen.
            long sum=(long)i+j; // Combine ranks without integer overflow.
            if(sum<best) { best=sum; result.clear(); } // A better rank sum invalidates every older candidate.
            if(sum==best) result.add(second[j]); // Keep all ties at the best known sum.
        }
        Collections.sort(result); return result; // Lexical order makes tie presentation deterministic.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new String[]{"a","b","c"},new String[]{"c","b","a"}))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new String[]{"a","b","c"},new String[]{"c","b","a"}), List.of("a","b","c")); // All three names tie.
        Check.equal(solve(new String[]{"a"},new String[]{"b"}), List.of()); // No shared name.
        Check.equal(solve(new String[]{"a","b"},new String[]{"b","x"}), List.of("b")); // One shared optimum.
    }
}
