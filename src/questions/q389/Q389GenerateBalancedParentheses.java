package questions.q389;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q389: Generate Balanced Parentheses.
 * Contract: Generate all balanced strings with n pairs; use small nonnegative n.
 * Why it works: Every explored prefix has close<=open<=n. These necessary conditions prevent invalid nesting, and every valid final string follows exactly one explored path.
 * Cost: O(n*C_n) output time/space for Catalan number C_n; O(n) recursion space.
 */
public final class Q389GenerateBalancedParentheses { // Keep this question independently runnable and testable.
    private Q389GenerateBalancedParentheses() { } // No object state is needed by this standalone exercise.
    public static List<String> generate(int n) { // Expose the operation for an independent caller and the executable checks below.
        if(n<0) throw new IllegalArgumentException("Nonnegative pair count"); // Pair counts cannot be negative.
        List<String> out=new ArrayList<>(); // Collect only prefixes that can remain valid.
        build(n,0,0,new StringBuilder(),out); // No opening or closing parenthesis has been used.
        return out; // Each string contains exactly n pairs.
    }
    private static void build(int n,int open,int close,StringBuilder p,List<String> out) { // Keep recursive state or a repeated sub-operation inside this solution.
        if(close==n) { out.add(p.toString()); return; } // All pairs have been closed legally.
        if(open<n) { p.append('('); build(n,open+1,close,p,out); p.deleteCharAt(p.length()-1); } // Add an opening only while capacity remains.
        if(close<open) { p.append(')'); build(n,open,close+1,p,out); p.deleteCharAt(p.length()-1); } // Never close a bracket before its opening.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(generate(3)); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(generate(0).equals(List.of("")), "Q389 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(generate(1).equals(List.of("()")), "Q389 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(generate(3).size()==5, "Q389 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
