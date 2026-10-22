package questions.q084;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q084: Insert into a sorted ArrayList using binarySearch.
 * Given a nondecreasing non-null integer list, insert a value without re-sorting. Decode Collections.binarySearch's negative insertion-point result; a found equal index is acceptable because duplicate ordering is unspecified.
 * Complexity: O(log n) search plus O(n) insertion/copy time; O(n) output space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q084SortedListInsertion { // Keep this question independently runnable and easy to find.
    private Q084SortedListInsertion() { } // This exercise exposes behavior through static methods.
    public static List<Integer> solve(List<Integer> sorted, int value) { // Define the input and result types of this particular problem.
        List<Integer> result = new ArrayList<>(sorted); // Obtain mutable random-access storage for search and insertion.
        int position = Collections.binarySearch(result,value); // A negative result encodes -(insertionPoint)-1 rather than just absence.
        if (position < 0) position = -position-1; // Decode the gap that keeps the sequence sorted.
        result.add(position,value); // An equal-value index is also a valid insertion position when duplicates are allowed.
        return result; // Binary search locates the position; shifting preserves sorted order after insertion.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(List.of(1,3,5),4))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(List.of(1,3,5),4), List.of(1,3,4,5)); // Case 1: verify the expected result for this input.
        Check.equal(solve(List.of(2,2),2), List.of(2,2,2)); // Case 2: verify the expected result for this input.
        Check.equal(solve(List.of(),7), List.of(7)); // Case 3: verify the expected result for this input.
    }
}
