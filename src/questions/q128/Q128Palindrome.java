package questions.q128;

import java.util.*;
import questions.support.Check;

/**
 * Q128: Check an exact palindrome.
 * Contract: Return whether a non-null string equals its reverse under exact UTF-16 char comparison.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: A palindrome requires every symmetric pair to match. One mismatch is sufficient to disprove it; all pairs matching proves it.
 * Complexity: O(n) time and O(1) auxiliary space.
 */
public final class Q128Palindrome { // Keep one question and its tests together for independent study.
    public static boolean solve(String text) { // Check symmetry without allocating a reversed copy.
        int left = 0, right = text.length() - 1; // Start with the outermost pair.
        while (left < right) { // Stop once all distinct symmetric pairs have been checked.
            if (text.charAt(left++) != text.charAt(right--)) return false; // Reject immediately on unequal mirrored chars.
        }
        return true; // No symmetric pair contradicted the palindrome property.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve("level")); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve("level"), true); // Check an odd palindrome.
    Check.equal(solve("ab"), false); // Unequal ends disprove symmetry.
    Check.equal(solve(""), true); // Empty text satisfies every pair condition vacuously.
    }
}
