package questions.q488; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Raise a value to a large power modulo m.
 * Contract: Return base^exponent mod modulus for any long base, nonnegative long exponent, and positive int modulus.
 * Why: Represent the exponent in binary. Maintain the product of selected powers while repeatedly squaring the current power; reduce modulo m after each multiplication. Residues below an int modulus have products that fit long. Useful for cyclic arithmetic and large-power calculations.
 * Cost: O(log(exponent+1)) modular multiplications and O(1) space.
 */
public final class Q488ModularExponentiation { // Keep the algorithm, demonstration, and boundary checks together.
    private Q488ModularExponentiation() { } // Only static entry points are needed for this exercise.
    public static long solve(long base,long exponent,int modulus) { // Bound the modulus so residue products fit signed long.
        if(exponent<0||modulus<=0) throw new IllegalArgumentException("invalid exponent or modulus"); // Negative powers need inverses rather than this algorithm.
        long power=Math.floorMod(base,(long)modulus),result=1L%modulus; // Normalize negative bases and handle modulus1 correctly.
        while(exponent>0) { // Consume one binary exponent digit each iteration.
            if((exponent&1)!=0) result=result*power%modulus; // Include this power exactly when its exponent bit is set.
            power=power*power%modulus; exponent>>>=1; // Square for the next binary place and discard the processed bit.
        }
        return result; // The invariant preserves the original modular power throughout the loop.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(2,10,1000))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(2,10,1000), 24L); // A power requiring multiple binary places.
        Check.equal(solve(-2,3,5), 2L); // Negative base normalizes to a nonnegative residue.
        Check.equal(solve(7,0,1), 0L); // Even the multiplicative identity is zero modulo1.
    }
}
