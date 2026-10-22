package questions.q094;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q094: Select a sorted half-open set range.
 * Return distinct values in [low,high), sorted ascending. TreeSet supports ordered range views for score bands or identifier ranges; subSet is backed by the original set, so this method copies it before returning.
 * Complexity: O(n log n + k) time and O(u+k) space for u unique values and k matches.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q094BoundedSetRange { // Keep this question independently runnable and easy to find.
    private Q094BoundedSetRange() { } // This exercise exposes behavior through static methods.
    public static List<Integer> solve(Collection<Integer> values, int low, int high) { // Define the input and result types of this particular problem.
        if (low > high) throw new IllegalArgumentException("reversed bounds"); // A half-open interval must have ordered endpoints.
        NavigableSet<Integer> sorted = new TreeSet<>(values); // Store unique values in numeric order.
        NavigableSet<Integer> range = sorted.subSet(low,true,high,false); // Include the lower bound and exclude the upper bound.
        return new ArrayList<>(range); // Materialize a detached sorted result instead of exposing a mutable backed view.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(List.of(5,1,3,3,7),3,7))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(List.of(5,1,3,3,7),3,7), List.of(3,5)); // Case 1: verify the expected result for this input.
        Check.equal(solve(List.of(1,2),2,2), List.of()); // Case 2: verify the expected result for this input.
        Check.equal(solve(List.of(),0,4), List.of()); // Case 3: verify the expected result for this input.
    }
}
