package questions.q400;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q400: Josephus Survivor.
 * Contract: Return a zero-based survivor index for positive n and step; keep n small enough for recursion.
 * Why it works: After removing one person, the reduced circle has the same problem with n-1 participants and rotated labels. Adding step maps its survivor back to the original labels.
 * Cost: O(n) time and O(n) stack; an iterative recurrence reduces working space to O(1).
 */
public final class Q400JosephusSurvivor { // Keep this question independently runnable and testable.
    private Q400JosephusSurvivor() { } // No object state is needed by this standalone exercise.
    public static int survivor(int n,int step) { // Expose the operation for an independent caller and the executable checks below.
        if(n<1||step<1) throw new IllegalArgumentException("Positive n and step"); // A nonempty circle and positive elimination interval are required.
        if(n==1) return 0; // The only remaining person's zero-based position is zero.
        return (int)((survivor(n-1,step)+(long)step)%n); // Translate the smaller circle's labels back after one elimination.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(survivor(5,2)); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(survivor(1,7)==0, "Q400 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(survivor(5,2)==2, "Q400 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(survivor(7,3)==3, "Q400 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
