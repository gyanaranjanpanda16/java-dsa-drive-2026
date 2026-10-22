package questions.q077;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q077: Remove even values safely while iterating a list.
 * Remove even values using Iterator.remove, with no null elements. Calling the list's structural remove directly during this traversal can invalidate its iterator; iterator.remove is the supported mutation path. For bulk ArrayList filtering, removeIf is usually more efficient.
 * Complexity: O(n²) worst-case time because ArrayList deletions shift suffixes; O(n) output copy.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q077IteratorRemoval { // Keep this question independently runnable and easy to find.
    private Q077IteratorRemoval() { } // This exercise exposes behavior through static methods.
    public static List<Integer> solve(List<Integer> input) { // Define the input and result types of this particular problem.
        List<Integer> result = new ArrayList<>(input); // Work on a mutable copy, even when the input is immutable.
        Iterator<Integer> iterator = result.iterator(); // Let the iterator track its position and structural modification count.
        while (iterator.hasNext()) { // Never call next beyond the available elements.
            int value = iterator.next(); // next establishes the element that remove is allowed to delete.
            if (value % 2 == 0) iterator.remove(); // Removing through this iterator keeps its state consistent with the list.
        }
        return result; // Odd values retain their encounter order.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(List.of(1,2,3,4)))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(List.of(1,2,3,4)), List.of(1,3)); // Case 1: verify the expected result for this input.
        Check.equal(solve(List.of(2,2)), List.of()); // Case 2: verify the expected result for this input.
        Check.equal(solve(List.of()), List.of()); // Case 3: verify the expected result for this input.
    }
}
