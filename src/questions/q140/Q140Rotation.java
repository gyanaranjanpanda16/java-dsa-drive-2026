package questions.q140;

import java.util.*;
import questions.support.Check;

/**
 * Q140: Recognize a rotation of a string.
 * Contract: Return whether second is a cyclic rotation of first, with exact char matching and equal lengths.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: Every cut of first appears as a length-n contiguous substring of first+first; equal length excludes shorter accidental matches.
 * Complexity: O(n²) worst-case time with library substring search; O(n) concatenation space.
 */
public final class Q140Rotation { // Keep one question and its tests together for independent study.
    public static boolean solve(String first, String second) { // Compare only strings of equal length.
        if (first.length() != second.length()) return false; // Rotation cannot add or remove chars.
        String doubled = first + first; // Place every possible wraparound cut in one linear string.
        return doubled.contains(second); // A full-length match corresponds to a cyclic shift.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve("waterbottle", "erbottlewat")); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve("waterbottle","erbottlewat"), true); // A cut can wrap around the original end.
    Check.equal(solve("abc","acb"), false); // Arbitrary permutations are not rotations.
    Check.equal(solve("",""), true); // The empty string rotates to itself.
    }
}
