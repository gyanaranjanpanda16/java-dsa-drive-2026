package questions.q148;

import java.util.*;
import questions.support.Check;

/**
 * Q148: Parse and clamp a signed decimal prefix.
 * Contract: Skip leading ASCII spaces, accept one sign, consume decimal digits, stop at first nondigit, and clamp outside int range. No digits means zero.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: Accumulating against the sign-specific limit permits detecting overflow before multiplication; the consumed prefix uniquely determines the signed value.
 * Complexity: O(n) time and O(1) auxiliary space.
 */
public final class Q148ParseInteger { // Keep one question and its tests together for independent study.
    public static int solve(String text) { // Define a deterministic decimal-prefix parser rather than relying on exceptions.
        int i=0,sign=1; // Begin before the optional whitespace and sign.
        while(i<text.length()&&text.charAt(i)==' ') i++; // Only leading ASCII spaces are skipped by this contract.
        if(i<text.length()&&(text.charAt(i)=='+'||text.charAt(i)=='-')) sign=text.charAt(i++)=='-'?-1:1; // Consume at most one optional sign.
        long value=0,limit=sign<0?2147483648L:2147483647L; // The negative int magnitude is one larger than the positive limit.
        while(i<text.length()&&text.charAt(i)>='0'&&text.charAt(i)<='9') { // Stop as soon as the decimal prefix ends.
            int digit=text.charAt(i++)-'0'; // Convert the accepted ASCII digit into its numeric value.
            if(value>(limit-digit)/10) return sign<0?Integer.MIN_VALUE:Integer.MAX_VALUE; // Clamp before the next multiply could exceed the allowed magnitude.
            value=value*10+digit; // Append one decimal digit to the accumulated magnitude.
        }
        return (int)(sign*value); // Apply the sign after constructing its safe magnitude.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve("  -42tail")); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve("  -42tail"),-42); // Stop at a trailing nondigit.
    Check.equal(solve("99999999999999999999"),Integer.MAX_VALUE); // Clamp without overflowing a long accumulator.
    Check.equal(solve("words 12"),0); // No leading digits means zero.
    }
}
