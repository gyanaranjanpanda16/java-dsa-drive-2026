package questions.q120;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q120: Traverse a legacy Vector with Enumeration.
 * Copy Vector contents through Enumeration in insertion order. This teaches how to read legacy Java APIs; Enumeration has no remove operation. New unsynchronized list code usually uses ArrayList, and concurrent traversal requires an explicit consistency strategy.
 * Complexity: O(n) amortized time and O(n) space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q120VectorEnumeration { // Keep this question independently runnable and easy to find.
    private Q120VectorEnumeration() { } // This exercise exposes behavior through static methods.
    public static List<Integer> solve(int[] values) { // Define the input and result types of this particular problem.
        Vector<Integer> vector = new Vector<>(); // Vector is a legacy resizable array with synchronized individual methods.
        for (int value : values) vector.add(value); // Store values in insertion order.
        Enumeration<Integer> enumeration = vector.elements(); // Older APIs expose Enumeration instead of Iterator.
        List<Integer> result = new ArrayList<>(); // Materialize the sequence without changing the vector during traversal.
        while (enumeration.hasMoreElements()) result.add(enumeration.nextElement()); // Test availability before advancing the legacy cursor.
        return result; // Individual synchronized calls do not turn this multi-step traversal into one atomic operation.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[]{5,2,5}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[]{5,2,5}), List.of(5,2,5)); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[]{}), List.of()); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[]{-1}), List.of(-1)); // Case 3: verify the expected result for this input.
    }
}
