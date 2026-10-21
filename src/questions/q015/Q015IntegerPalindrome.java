package questions.q015;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q015: Check whether an integer is a palindrome.
 * Determine whether a nonnegative integer reads the same forwards and backwards; negative integers are defined as non-palindromes. Compare the original with its reversed magnitude.
 * Complexity: O(number of digits) time and O(1) auxiliary space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q015IntegerPalindrome { // Keep this question independently runnable and easy to find.
    private Q015IntegerPalindrome() { } // This exercise exposes behavior through static methods.
    public static boolean solve(int number) { // Define the input and result types of this particular problem.
        if (number < 0) return false; // A leading minus sign has no matching trailing minus sign.
        int remaining = number; // Keep the original number for the final comparison.
        long reversed = 0; // A long avoids overflow while reversing an int's digits.
        while (remaining > 0) { // Remove one original digit on each iteration.
            reversed = reversed * 10 + remaining % 10; // Append the next digit in reverse order.
            remaining /= 10; // Ensure the loop eventually finishes.
        }
        return reversed == number; // Equality means the original and reversed decimal forms agree.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(121))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(121), true); // Case 1: verify the expected result for this input.
        Check.equal(solve(10), false); // Case 2: verify the expected result for this input.
        Check.equal(solve(0), true); // Case 3: verify the expected result for this input.
    }
}
