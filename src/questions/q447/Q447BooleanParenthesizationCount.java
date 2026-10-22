package questions.q447;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q447: Boolean Parenthesization Count.
 * Contract: Alternating T/F and binary &,|,^ operators, at most31 UTF-16 units; count fully parenthesized true results.
 * Why it works: Each parenthesization has one final binary operator. Counts for independent left and right truth values multiply, and summing over splits and truth-table cases counts each parse tree once.
 * Cost: O(n³) time and O(n²) space for n operands; n<=16 keeps counts within long.
 */
public final class Q447BooleanParenthesizationCount { // Keep this question independently runnable and testable.
    private Q447BooleanParenthesizationCount() { } // No object state is needed by this standalone exercise.
    public static long countTrue(String expression) { // Expose the operation for an independent caller and the executable checks below.
        if(expression.isEmpty()) return 0; // There is no truth-valued expression to parenthesize.
        if(expression.length()>31||expression.length()%2==0) throw new IllegalArgumentException("Odd expression length at most31"); // Bound Catalan growth and require alternating operands/operators.
        int n=(expression.length()+1)/2; long[][][] dp=new long[n][n][2]; // Final dimension separates false and true evaluation counts.
        for(int i=0;i<n;i++) { char c=expression.charAt(2*i); if(c!='T'&&c!='F') throw new IllegalArgumentException("T/F operands required"); dp[i][i][c=='T'?1:0]=1; } // A single operand has exactly one evaluation.
        for(int len=2;len<=n;len++) for(int left=0;left+len<=n;left++) { // Combine fully solved shorter operand intervals.
            int right=left+len-1; // This interval includes every operand from left through right.
            for(int split=left;split<right;split++) { // The final operator uniquely partitions a binary parenthesization.
                char op=expression.charAt(2*split+1); if(op!='&'&&op!='|'&&op!='^') throw new IllegalArgumentException("Use &,|,^"); // Validate the supported truth-table operation.
                for(int a=0;a<2;a++) for(int b=0;b<2;b++) { boolean value=op=='&'?(a==1&&b==1):op=='|'?(a==1||b==1):(a!=b); dp[left][right][value?1:0]+=dp[left][split][a]*dp[split+1][right][b]; } // Multiply independent subexpression counts and route them through the operator truth table.
            }
        }
        return dp[0][n-1][1]; // Request only evaluations of the complete expression that are true.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(countTrue("T|F&T")); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(countTrue("")==0, "Q447 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(countTrue("F")==0, "Q447 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(countTrue("T^T^F")==0, "Q447 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
