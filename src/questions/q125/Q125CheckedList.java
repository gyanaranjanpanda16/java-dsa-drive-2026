package questions.q125;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q125: Reject a raw-type insertion at the collection boundary.
 * Pass a checked string-list view to a simulated raw-type legacy API and return the unchanged strings after it tries to insert an integer. checkedList catches heap pollution near the bad write instead of a later read; callers with direct access to the backing list can still bypass the wrapper.
 * Complexity: O(n) copying time and space; runtime type checking of one insertion is O(1).
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q125CheckedList { // Keep this question independently runnable and easy to find.
    private Q125CheckedList() { } // This exercise exposes behavior through static methods.
    public static List<String> solve(String[] input, int invalidValue) { // Define the input and result types of this particular problem.
        List<String> backing = new ArrayList<>(Arrays.asList(input)); // Begin with valid string elements in mutable storage.
        List<String> checked = Collections.checkedList(backing,String.class); // Retain a runtime element-type guard despite generic type erasure.
        boolean rejected = false; // Track whether the deliberately unsafe legacy call was caught.
        try { legacyInsert(checked,invalidValue); } catch (ClassCastException expected) { rejected = true; } // The checked view rejects the Integer before it contaminates backing storage.
        if (!rejected) throw new AssertionError("runtime guard did not reject a non-string"); // A silently accepted wrong type would violate this exercise's guarantee.
        return new ArrayList<>(backing); // All original string values survive unchanged after the rejected operation.
    }
    @SuppressWarnings({"rawtypes", "unchecked"}) // Confine deliberate legacy raw-type misuse to this teaching helper.
    private static void legacyInsert(List target, Object value) { // Model an old API that lacks compile-time generic safety.
        target.add(value); // The checked wrapper, not a generic cast at a later read, must reject the value.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new String[]{"a","b"},7))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new String[]{"a","b"},7), List.of("a","b")); // Case 1: verify the expected result for this input.
        Check.equal(solve(new String[]{},0), List.of()); // Case 2: verify the expected result for this input.
        Check.equal(solve(new String[]{"x"},-1), List.of("x")); // Case 3: verify the expected result for this input.
    }
}
