package questions.q082;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q082: Sort integers by magnitude with numeric tie-breaking.
 * Sort non-null integers by increasing absolute value, breaking ties by the actual integer value. Integer.MIN_VALUE requires long magnitude; comparator subtraction can overflow and produce inconsistent ordering.
 * Complexity: O(n log n) comparisons and O(n) copy/sorting space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q082AbsoluteComparator { // Keep this question independently runnable and easy to find.
    private Q082AbsoluteComparator() { } // This exercise exposes behavior through static methods.
    public static List<Integer> solve(List<Integer> input) { // Define the input and result types of this particular problem.
        List<Integer> ordered = new ArrayList<>(input); // Sort a copy because caller-owned order may still be needed.
        Comparator<Integer> order = Comparator.comparingLong((Integer value) -> Math.abs((long) value)); // Widen before abs so Integer.MIN_VALUE gets its correct positive magnitude.
        ordered.sort(order.thenComparingInt(Integer::intValue)); // Use numeric order to make equal-magnitude ties deterministic.
        return ordered; // Comparison helpers avoid subtraction overflow and maintain comparator transitivity.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(List.of(-3,2,-2,1)))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(List.of(-3,2,-2,1)), List.of(1,-2,2,-3)); // Case 1: verify the expected result for this input.
        Check.equal(solve(List.of(Integer.MIN_VALUE,0,Integer.MAX_VALUE)), List.of(0,Integer.MAX_VALUE,Integer.MIN_VALUE)); // Case 2: verify the expected result for this input.
        Check.equal(solve(List.of()), List.of()); // Case 3: verify the expected result for this input.
    }
}
