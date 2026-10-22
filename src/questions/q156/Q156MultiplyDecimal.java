package questions.q156;

import java.util.*;
import questions.support.Check;

/**
 * Q156: Multiply decimal strings by positional arithmetic.
 * Contract: Multiply nonempty unsigned decimal strings; return canonical decimal text. Valid digits assumed.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: Each digit pair contributes to position i+j+1; normalization carries tens into the preceding position, reproducing long multiplication.
 * Complexity: O(nm) time and O(n+m) space.
 */
public final class Q156MultiplyDecimal { // Keep one question and its tests together for independent study.
    public static String solve(String a,String b) { // Work on decimal digits so operand length is not limited by long.
        int[] digits=new int[a.length()+b.length()]; // The product needs at most n+m decimal places.
        for(int i=a.length()-1;i>=0;i--) { // Process each first-operand digit from low significance upward.
            for(int j=b.length()-1;j>=0;j--) { // Combine it with every second-operand digit.
                int sum=digits[i+j+1]+(a.charAt(i)-'0')*(b.charAt(j)-'0'); // Add this pair's positional contribution to the current slot.
                digits[i+j+1]=sum%10;digits[i+j]+=sum/10; // Keep one decimal digit and carry excess into the next higher slot.
            }
        }
        StringBuilder result=new StringBuilder(); // Emit canonical notation after arithmetic is complete.
        for(int digit:digits) if(result.length()>0||digit!=0) result.append(digit); // Skip only leading zeros, preserving internal zeros.
        return result.length()==0?"0":result.toString(); // A zero product still needs one digit.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve("123","456")); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve("123","456"),"56088"); // Multiple digit-pair contributions combine.
    Check.equal(solve("000","99"),"0"); // Canonicalize zero products.
    Check.equal(solve("9","9"),"81"); // Carry creates an extra leading digit.
    }
}
