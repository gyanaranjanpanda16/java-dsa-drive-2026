package questions.q490; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Compute an exact combination count without factorial overflow.
 * Contract: Return n choose k exactly for0<=k<=n<=10000.
 * Why: Use the recurrence C(n-k+i,i)=C(n-k+i-1,i-1)*(n-k+i)/i. Each division is exact, and symmetry reduces work to min(k,n-k) steps. BigInteger preserves results beyond long, useful for exact combinatorial counts.
 * Cost: O(min(k,n-k)) BigInteger multiplication/division steps and result-sized storage; bit-operation costs grow with the answer.
 */
public final class Q490BinomialCoefficient { // Keep the algorithm, demonstration, and boundary checks together.
    private Q490BinomialCoefficient() { } // Only static entry points are needed for this exercise.
    public static BigInteger solve(int n,int k) { // Compute an exact integer rather than a floating-point approximation.
        if(n<0||n>10000||k<0||k>n) throw new IllegalArgumentException("invalid bounded combination"); // Bound demonstration size and enforce a valid selection count.
        k=Math.min(k,n-k); BigInteger result=BigInteger.ONE; // Choosing a subset or its complement gives the same count.
        for(int i=1;i<=k;i++) result=result.multiply(BigInteger.valueOf(n-k+i)).divide(BigInteger.valueOf(i)); // The binomial recurrence guarantees exact division at every step.
        return result; // Selecting zero elements returns the multiplicative identity1.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(52,5))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(52,5), new BigInteger("2598960")); // A familiar finite combination count.
        Check.equal(solve(0,0), BigInteger.ONE); // One empty selection.
        Check.equal(solve(100,50), new BigInteger("100891344545564193334812497256")); // The exact answer is much larger than long.
    }
}
