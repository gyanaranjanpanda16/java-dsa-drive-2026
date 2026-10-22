package questions.q378; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Insert arithmetic operators to reach a target.
 * Contract: Given 1..10 decimal digits and a long target, insert +, -, or * between some digits to form expressions evaluating to target. Joining adjacent digits is allowed; multi-digit operands cannot start with zero. Multiplication has precedence; parentheses and unary operators are not inserted. Return expressions in lexicographic order.
 * Why: Generating strings and building a full parser for each expression repeats work. Carry the current total and the signed last multiplicative term. For multiplication, replace that last term with its product: total-last+last*operand. This preserves normal precedence without an expression tree. Enumerating choices is useful in expression synthesis and puzzle solvers; it remains exponential rather than a general symbolic-algebra engine.
 * Cost: O(n*4^n) conservative time including expression construction and result sorting, with O(n^2) working string storage along recursion plus output. At most10 digits keeps evaluated intermediate terms safely inside long.
 */
public final class Q378ExpressionOperators { // Keep the algorithm, demonstration, and boundary checks together.
    private Q378ExpressionOperators() { } // Only static entry points are needed for this exercise.
    public static List<String> solve(String digits,long target) { // The target can be outside the reachable range without causing arithmetic overflow.
        if(digits.isEmpty()||digits.length()>10) throw new IllegalArgumentException("1..10 digits required"); // Bound enumeration and arithmetic.
        for(char c:digits.toCharArray()) if(c<'0'||c>'9') throw new IllegalArgumentException("decimal digits required"); // Operands contain no signs or separators.
        List<String> result=new ArrayList<>(); // Store only complete expressions meeting the target.
        build(digits,target,0,0,0,"",result); Collections.sort(result); // Search first, then stabilize display order.
        return result; // No expression is a valid empty result.
    }
    private static void build(String digits,long target,int start,long total,long last,String expression,List<String> result) { // last is the signed trailing multiplicative term.
        if(start==digits.length()) { if(total==target) result.add(expression); return; } // Accept only expressions consuming all digits.
        long operand=0; // Extend the next operand one digit at a time.
        for(int end=start;end<digits.length();end++) { // Each endpoint chooses where the next operator may appear.
            if(end>start&&digits.charAt(start)=='0') break; // Zero is valid alone, but not as a leading digit.
            operand=operand*10+digits.charAt(end)-'0'; String token=digits.substring(start,end+1); // Build the numeric value and its exact spelling.
            if(start==0) build(digits,target,end+1,operand,operand,token,result); // The first operand has no preceding operator.
            else { // Later operands offer three distinct operator choices.
                build(digits,target,end+1,total+operand,operand,expression+"+"+token,result); // Addition starts a positive final term.
                build(digits,target,end+1,total-operand,-operand,expression+"-"+token,result); // Subtraction starts a negative final term.
                build(digits,target,end+1,total-last+last*operand,last*operand,expression+"*"+token,result); // Replace only the final term to preserve precedence.
            }
        }
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve("123",6))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve("123",6), List.of("1*2*3","1+2+3")); // Two different expressions reach the target.
        Check.equal(solve("105",5), List.of("1*0+5","10-5")); // Zero handling and concatenation.
        Check.equal(solve("5",5), List.of("5")); // No operator is necessary.
        Check.equal(solve("00",0), List.of("0*0","0+0","0-0")); // Leading-zero operands are excluded.
    }
}
