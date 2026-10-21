package questions.q034;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q034: Rotate right by any signed distance.
 * Rotate right by k; negative k means left rotation. Modular index mapping is a bijection, so no two input positions overwrite the same output position.
 * Complexity: O(n) time and O(n) output space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q034RotateByK { // Keep this question independently runnable and easy to find.
    private Q034RotateByK() { } // This exercise exposes behavior through static methods.
    public static int[] solve(int[] a, int k) { // Define the input and result types of this particular problem.
        int n = a.length; // The array length is the modulus for cyclic positions.
        int[] rotated = new int[n]; // Use distinct storage so writes cannot destroy unread input.
        if (n == 0) return rotated; // Avoid a modulo operation with divisor zero.
        int shift = Math.floorMod(k, n); // Negative shifts consistently represent rotations to the left.
        for (int i = 0; i < n; i++) rotated[(int) (((long) i + shift) % n)] = a[i]; // Map every original position bijectively to its destination.
        return rotated; // Modulo wraparound preserves all elements exactly once.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[]{1,2,3,4},2))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[]{1,2,3,4},2), new int[]{3,4,1,2}); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[]{1,2,3},-1), new int[]{2,3,1}); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[]{},10), new int[]{}); // Case 3: verify the expected result for this input.
    }
}
