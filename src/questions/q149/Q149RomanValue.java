package questions.q149;

import java.util.*;
import questions.support.Check;

/**
 * Q149: Evaluate a valid Roman numeral.
 * Contract: Convert a valid canonical uppercase Roman numeral using I,V,X,L,C,D,M; empty text returns0. Canonical validity is assumed.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: A smaller symbol before a larger one is subtracted; otherwise it is added. This partitions valid Roman notation into its additive and subtractive contributions.
 * Complexity: O(n) time and O(1) auxiliary space.
 */
public final class Q149RomanValue { // Keep one question and its tests together for independent study.
    static int value(char c) { // Map symbols to their numeric values in one place.
        return switch(c) { case 'I'->1; case 'V'->5; case 'X'->10; case 'L'->50; case 'C'->100; case 'D'->500; case 'M'->1000; default->throw new IllegalArgumentException("invalid symbol"); }; // Reject characters outside the defined numeral alphabet.
    }
    public static int solve(String text) { // The caller promises canonical Roman ordering.
        int total=0; // Accumulate signed symbol contributions.
        for(int i=0;i<text.length();i++) { // Decide each symbol using only its immediate successor.
            int current=value(text.charAt(i)); // Read the current Roman magnitude.
            total+=(i+1<text.length()&&current<value(text.charAt(i+1)))?-current:current; // Subtract exactly the symbols introducing a subtractive pair.
        }
        return total; // Every symbol has contributed once according to its role.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve("MCMXCIV")); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve("MCMXCIV"),1994); // Combine additive and subtractive groups.
    Check.equal(solve("III"),3); // Repeated additive symbols accumulate.
    Check.equal(solve(""),0); // Empty input follows the stated convention.
    }
}
