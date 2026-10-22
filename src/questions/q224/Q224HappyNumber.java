package questions.q224; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Detect whether repeated digit-square sums reach one.
 * Contract: Return whether positive n eventually reaches1 by repeatedly replacing it with the sum of the squares of its decimal digits.
 * Why: The transformation is deterministic and eventually enters a bounded finite set. Reaching1 succeeds; repeating a previous state proves a cycle that can never newly reach1. This is a simple model of cycle detection in deterministic state transitions.
 * Cost: O(s*d) time and O(s) space for s visited states and at most d decimal digits per state.
 */
public final class Q224HappyNumber { // Keep the algorithm, demonstration, and boundary checks together.
    private Q224HappyNumber() { } // Only static entry points are needed for this exercise.
    public static boolean solve(int n) { // Accept only the positive-number version of the problem.
        if(n<=0) throw new IllegalArgumentException("positive input required"); // Zero and negative values are outside this domain.
        Set<Integer> seen=new HashSet<>(); // Remember states before following their deterministic successor.
        while(n!=1 && seen.add(n)) { // Stop either at success or at a repeated state.
            int next=0; // Accumulate the squared digits of the current number.
            while(n>0) { int digit=n%10; next+=digit*digit; n/=10; } // Extract and remove one decimal digit each iteration.
            n=next; // Advance to the newly computed state.
        }
        return n==1; // A repeated non-one state traps the sequence in a cycle.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(19))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(19), true); // A multi-step happy chain.
        Check.equal(solve(2), false); // An unhappy cycle.
        Check.equal(solve(1), true); // Already at the terminal value.
    }
}
