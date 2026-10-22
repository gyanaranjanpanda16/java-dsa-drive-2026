package questions.q487; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Factor a positive integer into prime factors.
 * Contract: Return the prime factors of positive int n in ascending order with multiplicity. The factorization of1 is an empty list.
 * Why: Repeatedly remove a factor before advancing the candidate. Once candidate² exceeds the remaining value, any remaining factor must be prime, otherwise a smaller factor would already have been found. Useful for divisor analysis and multiplicative arithmetic.
 * Cost: O(sqrt(n)) worst-case trial divisions and O(log n) output factors.
 */
public final class Q487PrimeFactorization { // Keep the algorithm, demonstration, and boundary checks together.
    private Q487PrimeFactorization() { } // Only static entry points are needed for this exercise.
    public static List<Integer> solve(int n) { // Factor a positive integer without treating1 as prime.
        if(n<1) throw new IllegalArgumentException("positive input required"); // Zero and negatives need different factorization conventions.
        List<Integer> factors=new ArrayList<>(); // Every division emits one prime occurrence.
        for(int candidate=2;candidate<=n/candidate;candidate++) { // Division expresses the square-root bound without multiplication overflow.
            while(n%candidate==0) { factors.add(candidate); n/=candidate; } // Strip all copies before trying the next candidate.
        }
        if(n>1) factors.add(n); // A surviving value larger than1 has no smaller factor and is prime.
        return factors; // Candidate order guarantees ascending factors.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(72))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(72), List.of(2,2,2,3,3)); // Repeated prime factors.
        Check.equal(solve(1), List.of()); // The empty product represents1.
        Check.equal(solve(97), List.of(97)); // A prime remains after trial division.
    }
}
