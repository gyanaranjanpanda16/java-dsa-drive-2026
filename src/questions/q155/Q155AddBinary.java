package questions.q155;

import java.util.*;
import questions.support.Check;

/**
 * Q155: Add binary strings without numeric parsing.
 * Contract: Add nonempty binary strings and return canonical binary output without leading zeros except zero itself. Inputs contain only0/1.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: At each bit position, the two input bits plus carry determine the output remainder modulo2 and next carry; this is ordinary positional addition.
 * Complexity: O(n+m) time and O(max(n,m)) output space.
 */
public final class Q155AddBinary { // Keep one question and its tests together for independent study.
    public static String solve(String a,String b) { // Digit-by-digit addition supports strings larger than machine integers.
        int i=a.length()-1,j=b.length()-1,carry=0; // Start at the least-significant bits.
        StringBuilder reversed=new StringBuilder(); // Appending low-to-high bits is efficient before one final reversal.
        while(i>=0||j>=0||carry!=0) { // Include a final carry after both inputs end.
            int sum=carry; // Carry represents overflow from the previous lower position.
            if(i>=0) sum+=a.charAt(i--)-'0'; // Consume one available bit from the first operand.
            if(j>=0) sum+=b.charAt(j--)-'0'; // Consume one available bit from the second operand.
            reversed.append(sum%2);carry=sum/2; // Split the total into this bit and the next position's carry.
        }
        String answer=reversed.reverse().toString(); // Restore most-significant-first notation.
        return answer.replaceFirst("^0+(?!$)",""); // Canonicalize leading input zeros without deleting the final zero.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve("1010","1011")); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve("1010","1011"),"10101"); // Carries propagate between positions.
    Check.equal(solve("0","0"),"0"); // Zero retains one digit.
    Check.equal(solve("001","1"),"10"); // Ignore redundant leading zeros.
    }
}
