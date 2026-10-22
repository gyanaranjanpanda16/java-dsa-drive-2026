package questions.q484; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Enumerate every submask of a bitmask exactly once.
 * Contract: For a nonnegative int mask with at most20 set bits, return all submasks in descending numeric order, including zero.
 * Why: Subtracting one and ANDing with mask jumps to the next smaller subset using only allowed bits. Stop explicitly at zero before the subtraction would cycle back. Useful for subset DP and exhaustive choices over a small active feature set.
 * Cost: O(2^b) time and output space for b set bits.
 */
public final class Q484EnumerateSubmasks { // Keep the algorithm, demonstration, and boundary checks together.
    private Q484EnumerateSubmasks() { } // Only static entry points are needed for this exercise.
    public static List<Integer> solve(int mask) { // Treat only set positions of mask as selectable elements.
        if(mask<0||Integer.bitCount(mask)>20) throw new IllegalArgumentException("small nonnegative mask required"); // Bound exponential output and exclude a signed ordering ambiguity.
        List<Integer> result=new ArrayList<>(); int subset=mask; // The full mask is the greatest possible subset.
        while(true) { // Include the zero subset exactly once before stopping.
            result.add(subset); if(subset==0) break; // Without this guard, the next step would restart at mask.
            subset=(subset-1)&mask; // Move to the next lower selection that uses only allowed bits.
        }
        return result; // Each possible subset appears once in strictly decreasing order.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(5))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(5), List.of(5,4,1,0)); // Nonadjacent allowed bit positions.
        Check.equal(solve(0), List.of(0)); // Zero has exactly one submask.
        Check.equal(solve(1), List.of(1,0)); // One available feature.
    }
}
