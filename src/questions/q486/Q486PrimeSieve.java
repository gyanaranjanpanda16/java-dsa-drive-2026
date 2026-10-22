package questions.q486; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Generate every prime up to a limit.
 * Contract: Return primes in[2,limit] for0<=limit<=10000000.
 * Why: Every composite has a prime factor at most its square root. Mark multiples of each still-unmarked p beginning at p² because smaller multiples already have a smaller prime factor. This efficiently preprocesses many primality answers in a bounded universe.
 * Cost: O(limit log log(limit+2)) time and O(limit) storage plus output.
 */
public final class Q486PrimeSieve { // Keep the algorithm, demonstration, and boundary checks together.
    private Q486PrimeSieve() { } // Only static entry points are needed for this exercise.
    public static List<Integer> solve(int limit) { // Generate a bounded batch rather than test each candidate independently.
        if(limit<0||limit>10000000) throw new IllegalArgumentException("limit outside supported range"); // Prevent invalid or unintended huge arrays.
        boolean[] composite=new boolean[limit+1]; // False initially means no prime factor has yet disproved primality.
        for(int p=2;p<=limit/p;p++) if(!composite[p]) { // Only primes up to the square root need to mark multiples.
            for(int multiple=p*p;multiple<=limit;multiple+=p) composite[multiple]=true; // Smaller multiples were already handled by earlier factors.
        }
        List<Integer> primes=new ArrayList<>(); for(int value=2;value<=limit;value++) if(!composite[value]) primes.add(value); // Exclude0 and1 explicitly.
        return primes; // Increasing traversal yields sorted prime values.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(10))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(10), List.of(2,3,5,7)); // A range containing primes and composites.
        Check.equal(solve(1), List.of()); // No primes below2.
        Check.equal(solve(2), List.of(2)); // The first prime is included.
    }
}
