package questions.q242; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Compute the floor square root without floating point.
 * Contract: For nonnegative int n, return the largest integer r with r*r<=n.
 * Why: Feasibility of r is monotone. Compare r to n/r to avoid multiplying large candidates, and use an upper midpoint so the search advances when two candidates remain. Useful for exact integer bounds.
 * Cost: O(log(n+1)) time and O(1) space.
 */
public final class Q242IntegerSquareRoot { // Keep the algorithm, demonstration, and boundary checks together.
    private Q242IntegerSquareRoot() { } // Only static entry points are needed for this exercise.
    public static int solve(int n) { // Return an exact integer result rather than a rounded double approximation.
        if(n<0) throw new IllegalArgumentException("nonnegative input required"); // Real integer roots require nonnegative n here.
        int low=0,high=n; // Zero is always feasible and n is a safe inclusive upper bound.
        while(low<high) { // Converge to the largest feasible candidate.
            int middle=(int)(low+((long)high-low+1)/2); // Upper midpoint prevents retaining the same lower bound forever.
            if(middle<=n/middle) low=middle; else high=middle-1; // middle is positive in this loop, and division avoids square overflow.
        }
        return low; // No larger integer satisfies the square bound.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(8))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(8), 2); // A nonsquare rounds down.
        Check.equal(solve(0), 0); // Zero never enters the division loop.
        Check.equal(solve(Integer.MAX_VALUE), 46340); // Large input avoids multiplication overflow.
    }
}
