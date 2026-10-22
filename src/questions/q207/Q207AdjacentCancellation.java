package questions.q207;

import java.util.*;
import questions.support.Check;

/**
 * Q207: Repeatedly remove adjacent equal character pairs.
 * Contract: Delete adjacent equal UTF-16 char pairs until none remain; return the unique stack-reduction result.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: The buffer is always the reduced form of the processed prefix. A new char either cancels its equal top or appends without creating another reducible boundary.
 * Complexity: O(n) time and O(n) space.
 */
public final class Q207AdjacentCancellation { // Keep one question and its tests together for independent study.
    public static String solve(String text) { // Model cancellations with a mutable character stack.
        StringBuilder stack=new StringBuilder(); // The final character acts as the stack top.
        for(char c:text.toCharArray()) { // Extend the reduced prefix by one input character.
            int size=stack.length(); // Cache the top position before possibly removing it.
            if(size>0&&stack.charAt(size-1)==c) stack.setLength(size-1); // The incoming char cancels with the equal top pair.
            else stack.append(c); // A different char becomes the new unmatched top.
        }
        return stack.toString(); // The entire input prefix is now reduced with no equal adjacent pair.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve("abbaca")); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve("abbaca"),"ca"); // Removing bb exposes aa for another cancellation.
    Check.equal(solve("aaaa"),""); // Two independent pairs cancel completely.
    Check.equal(solve("abc"),"abc"); // No equal adjacent pair changes nothing.
    }
}
