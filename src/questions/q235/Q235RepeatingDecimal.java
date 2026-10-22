package questions.q235; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Format a rational number with a repeating decimal cycle.
 * Contract: For int numerator and nonzero int denominator, return the exact decimal representation with the repeating part in parentheses. Omit a decimal point for integers.
 * Why: Long division is determined entirely by its current remainder. When a remainder repeats, the digits produced since its first appearance repeat forever; recording that output position identifies the cycle precisely. Useful for exact rational formatting instead of rounded floating-point output.
 * Cost: O(d) expected time and space for d emitted digits; d can be very large for a large denominator.
 */
public final class Q235RepeatingDecimal { // Keep the algorithm, demonstration, and boundary checks together.
    private Q235RepeatingDecimal() { } // Only static entry points are needed for this exercise.
    public static String solve(int numerator,int denominator) { // Preserve exact rational value throughout formatting.
        if(denominator==0) throw new IllegalArgumentException("zero denominator"); // Division by zero has no decimal expansion.
        if(numerator==0) return "0"; // Avoid a negative sign or decimal suffix for zero.
        StringBuilder out=new StringBuilder(); if((numerator<0)!=(denominator<0)) out.append('-'); // Opposite signs produce a negative quotient.
        long n=Math.abs((long)numerator),d=Math.abs((long)denominator); // Widen before abs so Integer.MIN_VALUE is representable.
        out.append(n/d); long remainder=n%d; // Emit the integer part and retain the exact fractional remainder.
        if(remainder==0) return out.toString(); // An exact integer needs no decimal point.
        out.append('.'); Map<Long,Integer> position=new HashMap<>(); // Record where each remainder begins generating digits.
        while(remainder!=0) { // A zero remainder terminates a finite expansion.
            Integer repeated=position.putIfAbsent(remainder,out.length()); // Preserve the earliest position of this state.
            if(repeated!=null) { out.insert(repeated.intValue(),'('); out.append(')'); break; } // Enclose exactly the recurring digit suffix.
            remainder*=10; out.append(remainder/d); remainder%=d; // Perform one long-division step using safe long arithmetic.
        }
        return out.toString(); // The result is either finite or has one explicitly marked cycle.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(1,6))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(1,6), "0.1(6)"); // A nonrepeating prefix precedes the cycle.
        Check.equal(solve(-50,8), "-6.25"); // A negative terminating expansion.
        Check.equal(solve(Integer.MIN_VALUE,-1), "2147483648"); // The quotient exceeds int but formats safely.
        Check.equal(solve(0,5), "0"); // Zero has no fractional part.
    }
}
