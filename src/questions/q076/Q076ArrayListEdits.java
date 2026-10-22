package questions.q076;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q076: Insert replace and remove ArrayList elements by position.
 * Insert a string at a valid position 0..size, uppercase it, and remove the following original element if present. This contrasts add (changes size), set (replaces), and remove(index) (shifts subsequent values). Strings must be non-null.
 * Complexity: O(n + value length) time and O(n + value length) space; positional ArrayList insertion/removal shifts elements.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q076ArrayListEdits { // Keep this question independently runnable and easy to find.
    private Q076ArrayListEdits() { } // This exercise exposes behavior through static methods.
    public static List<String> solve(List<String> input, int position, String value) { // Define the input and result types of this particular problem.
        List<String> result = new ArrayList<>(input); // Copy into a resizable list so the caller's sequence is preserved.
        result.add(position,value); // Insert before position, shifting later elements right; size is a valid append position.
        result.set(position,value.toUpperCase(Locale.ROOT)); // Replace the inserted entry without changing list size; ROOT gives stable case conversion.
        if (position+1 < result.size()) result.remove(position+1); // Remove the former occupant when one existed, demonstrating index-based removal.
        return result; // Return the edited sequence with predictable positional behavior.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(List.of("a","b","c"),1,"x"))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(List.of("a","b","c"),1,"x"), List.of("a","X","c")); // Case 1: verify the expected result for this input.
        Check.equal(solve(List.of(),0,"hi"), List.of("HI")); // Case 2: verify the expected result for this input.
        Check.equal(solve(List.of("a"),1,"z"), List.of("a","Z")); // Case 3: verify the expected result for this input.
    }
}
