package questions.q079;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q079: Delete a half-open range through a backed list view.
 * Remove positions [from,to) using subList(...).clear on a copy. subList is a backed view, not an independent list, so changes through the view update its backing list. Require 0≤from≤to≤size.
 * Complexity: O(n) time and O(n) output copy; creating the view itself takes O(1).
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q079SubListDeletion { // Keep this question independently runnable and easy to find.
    private Q079SubListDeletion() { } // This exercise exposes behavior through static methods.
    public static List<Integer> solve(List<Integer> input, int from, int to) { // Define the input and result types of this particular problem.
        List<Integer> result = new ArrayList<>(input); // Preserve the input while creating a mutable backing list.
        List<Integer> selected = result.subList(from,to); // The view represents positions from inclusive to exclusive, without copying them.
        selected.clear(); // Clearing the backed view removes that range from the original backing list.
        return result; // Values outside the selected range retain their relative order.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(List.of(1,2,3,4),1,3))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(List.of(1,2,3,4),1,3), List.of(1,4)); // Case 1: verify the expected result for this input.
        Check.equal(solve(List.of(1,2),0,0), List.of(1,2)); // Case 2: verify the expected result for this input.
        Check.equal(solve(List.of(),0,0), List.of()); // Case 3: verify the expected result for this input.
    }
}
