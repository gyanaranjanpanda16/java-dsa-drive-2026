package questions.q081;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q081: Rotate a list with the Collections utility.
 * Return a list rotated by any signed distance using Collections.rotate. This exercises the standard-library operation on mutable storage; immutable List.of input must first be copied.
 * Complexity: O(n) time and O(n) output copy for this ArrayList implementation.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q081RotateList { // Keep this question independently runnable and easy to find.
    private Q081RotateList() { } // This exercise exposes behavior through static methods.
    public static List<Integer> solve(List<Integer> input, int distance) { // Define the input and result types of this particular problem.
        List<Integer> rotated = new ArrayList<>(input); // Supply mutable random-access storage for the rotation utility.
        Collections.rotate(rotated,distance); // Positive distance shifts right; negative distance shifts left, wrapping cyclically.
        return rotated; // The original list is unchanged because only the copy was rotated.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(List.of(1,2,3,4),1))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(List.of(1,2,3,4),1), List.of(4,1,2,3)); // Case 1: verify the expected result for this input.
        Check.equal(solve(List.of(1,2,3),-1), List.of(2,3,1)); // Case 2: verify the expected result for this input.
        Check.equal(solve(List.of(),7), List.of()); // Case 3: verify the expected result for this input.
    }
}
