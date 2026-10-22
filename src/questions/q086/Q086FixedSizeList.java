package questions.q086;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q086: Observe a fixed-size list backed by an array.
 * Return [backing-array text, whether add was rejected] after replacing its first element through Arrays.asList. This view is useful when APIs need a List over an existing array; fixed size does not mean immutable. Inputs and replacement are non-null.
 * Complexity: O(n) copy/formatting time and space; view creation and set are O(1).
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q086FixedSizeList { // Keep this question independently runnable and easy to find.
    private Q086FixedSizeList() { } // This exercise exposes behavior through static methods.
    public static List<String> solve(String[] input, String replacement) { // Define the input and result types of this particular problem.
        String[] backing = input.clone(); // Isolate the caller while retaining a real backing array for the view.
        List<String> view = Arrays.asList(backing); // This list permits replacement but cannot grow or shrink.
        if (!view.isEmpty()) view.set(0,replacement); // set writes into the corresponding backing-array slot.
        boolean rejected = false; // Record the structural restriction without crashing the demonstration.
        try { view.add(replacement); } catch (UnsupportedOperationException expected) { rejected = true; } // add cannot resize the array.
        return List.of(Arrays.toString(backing),Boolean.toString(rejected)); // Expose both aliasing and the fixed-size contract.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new String[]{"a","b"},"x"))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new String[]{"a","b"},"x"), List.of("[x, b]","true")); // Case 1: verify the expected result for this input.
        Check.equal(solve(new String[]{},"x"), List.of("[]","true")); // Case 2: verify the expected result for this input.
        Check.equal(solve(new String[]{"a"},"a"), List.of("[a]","true")); // Case 3: verify the expected result for this input.
    }
}
