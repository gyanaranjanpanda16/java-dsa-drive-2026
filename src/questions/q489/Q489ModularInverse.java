package questions.q489; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Find a modular inverse using extended Euclid.
 * Contract: For int value and modulus>=2, return OptionalLong x in[0,modulus) with value*x congruent1 modulo modulus, or empty if no inverse exists.
 * Why: Extended Euclid maintains coefficients expressing each remainder as a combination of the original value and modulus. An inverse exists exactly when their gcd is1; its coefficient gives the inverse after normalization. Useful for modular division when coprimality is guaranteed.
 * Cost: O(log modulus) Euclidean steps and O(1) space.
 */
public final class Q489ModularInverse { // Keep the algorithm, demonstration, and boundary checks together.
    private Q489ModularInverse() { } // Only static entry points are needed for this exercise.
    public static OptionalLong solve(int value,int modulus) { // Absence is a legitimate result for noncoprime inputs.
        if(modulus<2) throw new IllegalArgumentException("modulus at least two required"); // Define inverses in a nontrivial residue ring.
        long remainder=modulus,next=Math.floorMod((long)value,modulus),coefficient=0,nextCoefficient=1; // Track the value coefficient in each Euclidean remainder.
        while(next!=0) { // Euclid reduces positive remainders until the gcd remains.
            long quotient=remainder/next,saved=remainder-quotient*next; remainder=next; next=saved; // Apply one remainder reduction.
            saved=coefficient-quotient*nextCoefficient; coefficient=nextCoefficient; nextCoefficient=saved; // Apply the same linear combination to the associated coefficient.
        }
        if(remainder!=1) return OptionalLong.empty(); // Only gcd1 can express a modular multiplicative identity.
        return OptionalLong.of(Math.floorMod(coefficient,(long)modulus)); // Normalize the coefficient into the requested residue range.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(3,11))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(3,11), OptionalLong.of(4)); // A coprime pair has an inverse.
        Check.equal(solve(2,4), OptionalLong.empty()); // A shared factor prevents inversion.
        Check.equal(solve(-3,11), OptionalLong.of(7)); // Normalize a negative input and coefficient.
    }
}
