package questions.q092;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q092: Find values present in exactly one set.
 * Return the sorted symmetric difference of two non-null integer collections. This models mismatched permissions or inventory identifiers; duplicate input values do not change set membership.
 * Complexity: Expected O(n+m+k log k) time and O(n+m) space, where k is the result size.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q092SymmetricDifference { // Keep this question independently runnable and easy to find.
    private Q092SymmetricDifference() { } // This exercise exposes behavior through static methods.
    public static List<Integer> solve(Collection<Integer> left, Collection<Integer> right) { // Define the input and result types of this particular problem.
        Set<Integer> a = new HashSet<>(left); // Collapse duplicates because the problem is about membership, not frequency.
        Set<Integer> b = new HashSet<>(right); // Separate right-side membership from changes to the result.
        Set<Integer> result = new HashSet<>(a); // Begin with every distinct left value.
        for (int value : b) { // Toggle each distinct right-side value exactly once.
            if (!result.add(value)) result.remove(value); // A shared value cancels; a right-only value remains.
        }
        List<Integer> sorted = new ArrayList<>(result); // HashSet iteration has no promised numeric order.
        Collections.sort(sorted); // Explicitly sort so the public result is deterministic.
        return sorted; // Return precisely the values belonging to one set but not both.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(List.of(1,2,2),List.of(2,3)))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(List.of(1,2,2),List.of(2,3)), List.of(1,3)); // Case 1: verify the expected result for this input.
        Check.equal(solve(List.of(1),List.of(1)), List.of()); // Case 2: verify the expected result for this input.
        Check.equal(solve(List.of(),List.of(2,2)), List.of(2)); // Case 3: verify the expected result for this input.
    }
}
