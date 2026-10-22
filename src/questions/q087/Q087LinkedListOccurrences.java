package questions.q087;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q087: Remove the first and last matching occurrences.
 * Remove the first matching value, then the last remaining matching value, using LinkedList occurrence-removal operations. With only one match, it is removed once. This differs from removing endpoints or removing all equal values; useful when trimming duplicate markers around an ordered event sequence.
 * Complexity: O(n) time for two searches and the result copy; O(n) space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q087LinkedListOccurrences { // Keep this question independently runnable and easy to find.
    private Q087LinkedListOccurrences() { } // This exercise exposes behavior through static methods.
    public static List<String> solve(List<String> input, String target) { // Define the input and result types of this particular problem.
        Deque<String> remaining = new LinkedList<>(input); // A linked deque supports searching from either end as well as endpoint removal.
        remaining.removeFirstOccurrence(target); // Delete only the earliest matching node if one exists.
        remaining.removeLastOccurrence(target); // Search the remaining list from the other end and delete at most one more match.
        return new ArrayList<>(remaining); // Unmatched values and any middle matches retain their original order.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(List.of("x","a","x","b","x"),"x"))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(List.of("x","a","x","b","x"),"x"), List.of("a","x","b")); // Case 1: verify the expected result for this input.
        Check.equal(solve(List.of("a","x","b"),"x"), List.of("a","b")); // Case 2: verify the expected result for this input.
        Check.equal(solve(List.of("a"),"z"), List.of("a")); // Case 3: verify the expected result for this input.
    }
}
