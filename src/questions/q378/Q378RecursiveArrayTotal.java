package questions.q378;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q378: Recursive Array Total.
 * Contract: Return the sum of a non-null integer array using long; keep recursive examples small.
 * Why it works: The suffix sum beginning at i consists of a[i] plus the suffix beginning at i+1. The index increases until the empty suffix.
 * Cost: O(n) time and O(n) stack; recursion is educational and a loop avoids stack overflow for large arrays.
 */
public final class Q378RecursiveArrayTotal { // Keep this question independently runnable and testable.
    private Q378RecursiveArrayTotal() { } // No object state is needed by this standalone exercise.
    public static long total(int[] a) { // Expose the operation for an independent caller and the executable checks below.
        return totalFrom(a, 0); // Start before consuming any array position.
    }
    private static long totalFrom(int[] a, int i) { // Keep recursive state or a repeated sub-operation inside this solution.
        if (i == a.length) return 0; // An empty suffix contributes no values.
        return (long)a[i] + totalFrom(a, i + 1); // Combine the current value with the remaining suffix.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(total(new int[]{4,-2,7})); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(total(new int[]{})==0, "Q378 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(total(new int[]{-3})==-3, "Q378 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(total(new int[]{Integer.MAX_VALUE,1})==2147483648L, "Q378 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
