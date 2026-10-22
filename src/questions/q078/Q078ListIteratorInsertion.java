package questions.q078;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q078: Insert follow-up tasks during list traversal.
 * Insert a follow-up after every task equal to trigger using ListIterator. Cursor semantics ensure an inserted follow-up is not processed again, even when it equals the trigger.
 * Complexity: O(n) time and O(n) output space for a LinkedList iterator and constant-cost equality.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q078ListIteratorInsertion { // Keep this question independently runnable and easy to find.
    private Q078ListIteratorInsertion() { } // This exercise exposes behavior through static methods.
    public static List<String> solve(List<String> input, String trigger, String followUp) { // Define the input and result types of this particular problem.
        List<String> result = new LinkedList<>(input); // Iterator-local insertion avoids shifting array suffixes.
        ListIterator<String> cursor = result.listIterator(); // A list iterator supports insertion at its current gap.
        while (cursor.hasNext()) { // Process each original task once.
            String task = cursor.next(); // Advancing places the cursor immediately after this task.
            if (Objects.equals(task,trigger)) cursor.add(followUp); // Insert behind the visited task; next skips the newly inserted entry.
        }
        return result; // Original task order is preserved, with one follow-up per matching task.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(List.of("build","test","build"),"build","check"))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(List.of("build","test","build"),"build","check"), List.of("build","check","test","build","check")); // Case 1: verify the expected result for this input.
        Check.equal(solve(List.of("x"),"x","x"), List.of("x","x")); // Case 2: verify the expected result for this input.
        Check.equal(solve(List.of(),"x","y"), List.of()); // Case 3: verify the expected result for this input.
    }
}
