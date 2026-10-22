package questions.q436;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q436: Decode Digit Strings.
 * Contract: Map1..26 to letters; reject non-digits; empty message returns zero.
 * Why it works: The final encoded letter uses either one valid nonzero digit or a valid pair10..26. These token lengths partition all decodings without overlap.
 * Cost: O(n) time and O(1) space; count overflow throws.
 */
public final class Q436DecodeDigitStrings { // Keep this question independently runnable and testable.
    private Q436DecodeDigitStrings() { } // No object state is needed by this standalone exercise.
    public static long ways(String digits) { // Expose the operation for an independent caller and the executable checks below.
        if(digits.isEmpty()) return 0; // This exercise defines an empty message as having no decoding.
        for(char c:digits.toCharArray()) if(c<'0'||c>'9') throw new IllegalArgumentException("Digits only"); // Numeric pair parsing assumes decimal units.
        long previous=1,current=digits.charAt(0)=='0'?0:1; // Empty prefix is an internal base case; a leading zero cannot decode.
        for(int i=1;i<digits.length();i++) { // Each new digit may stand alone or pair with its predecessor.
            long next=digits.charAt(i)=='0'?0:current; // Values1..9 extend every decoding of the previous prefix.
            int pair=(digits.charAt(i-1)-'0')*10+digits.charAt(i)-'0'; // Build the only possible two-digit final token.
            if(pair>=10&&pair<=26) next=Math.addExact(next,previous); // A valid10..26 token extends decodings from two positions earlier.
            previous=current; current=next; // Advance both prerequisite prefix counts.
        }
        return current; // Invalid zero arrangements naturally leave the count at zero.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(ways("226")); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(ways("")==0, "Q436 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(ways("06")==0, "Q436 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(ways("10")==1, "Q436 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
