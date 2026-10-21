package questions.q398;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q398: Recursive Digit Decodings.
 * Contract: ASCII digits, short strings, count fits long; empty text has one empty decoding.
 * Why it works: A decoding begins with one digit or a valid pair. These disjoint choices lead to smaller suffixes; a leading zero invalidates that suffix immediately.
 * Cost: Exponential time without memoization, O(n) stack; later DP removes repeated suffix work.
 */
public final class Q398RecursiveDigitDecodings { // Keep this question independently runnable and testable.
    private Q398RecursiveDigitDecodings() { } // No object state is needed by this standalone exercise.
    public static long count(String s) { // Expose the operation for an independent caller and the executable checks below.
        for(char c:s.toCharArray()) if(c<'0'||c>'9') throw new IllegalArgumentException("Digits only"); // Keep numeric pair interpretation valid.
        return decode(s,0); // Count suffix decodings starting at the first digit.
    }
    private static long decode(String s,int i) { // Keep recursive state or a repeated sub-operation inside this solution.
        if(i==s.length()) return 1; // Consuming all digits completes one decoding.
        if(s.charAt(i)=='0') return 0; // Zero cannot represent a letter by itself.
        long ways=decode(s,i+1); // Decode one valid nonzero digit.
        if(i+1<s.length() && (s.charAt(i)-'0')*10+s.charAt(i+1)-'0'<=26) ways+=decode(s,i+2); // A pair from 10 through 26 is a separate valid first choice.
        return ways; // One-digit and two-digit first choices cannot overlap.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(count("226")); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(count("")==1, "Q398 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(count("0")==0, "Q398 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(count("10")==1, "Q398 case 3"); // Verify an independently expected ordinary or boundary result.
        Check.that(count("226")==3, "Q398 case 4"); // Verify an independently expected ordinary or boundary result.
    }
}
