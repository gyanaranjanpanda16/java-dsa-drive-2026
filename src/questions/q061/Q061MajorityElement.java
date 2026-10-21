package questions.q061;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q061: Find a value occurring more than half the time.
 * Return the value occurring more than n/2 times, or empty. Cancellation preserves any strict majority, but a second pass is essential when existence is not guaranteed.
 * Complexity: O(n) time and O(1) space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q061MajorityElement { // Keep this question independently runnable and easy to find.
    private Q061MajorityElement() { } // This exercise exposes behavior through static methods.
    public static OptionalInt solve(int[] a) { // Define the input and result types of this particular problem.
        int candidate = 0, balance = 0; // Pairing unlike values cancels votes without storing all frequencies.
        for (int value : a) { // A genuine majority survives cancellation with every other value.
            if (balance == 0) candidate = value; // Start a new uncancelled group when the old group is exhausted.
            balance += value == candidate ? 1 : -1; // Equal candidates reinforce the group; other values cancel one vote.
        }
        int count = 0; // Voting finds only a candidate, not proof that a majority exists.
        for (int value : a) if (value == candidate) count++; // Verify the candidate against the actual required frequency.
        return count > a.length / 2 ? OptionalInt.of(candidate) : OptionalInt.empty(); // Reject candidates that do not form a strict majority.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[]{2,2,1,2}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[]{2,2,1,2}), OptionalInt.of(2)); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[]{1,2}), OptionalInt.empty()); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[]{}), OptionalInt.empty()); // Case 3: verify the expected result for this input.
    }
}
