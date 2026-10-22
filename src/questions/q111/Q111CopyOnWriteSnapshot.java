package questions.q111;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q111: Observe a CopyOnWriteArrayList iterator snapshot.
 * Return [old iterator values, current list] after one insertion. CopyOnWriteArrayList supports frequent reads and rare writes, such as listener registries. Iterators are snapshots of membership and do not support remove; snapshots are shallow, so referenced mutable objects remain shared.
 * Complexity: O(n) time and space for construction, copy-on-write insertion and snapshots; repeated writes are expensive.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q111CopyOnWriteSnapshot { // Keep this question independently runnable and easy to find.
    private Q111CopyOnWriteSnapshot() { } // This exercise exposes behavior through static methods.
    public static List<List<String>> solve(List<String> input, String appended) { // Define the input and result types of this particular problem.
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>(input); // Writes publish a fresh backing array for future readers.
        Iterator<String> before = list.iterator(); // This iterator retains the array that existed at creation time.
        list.add(appended); // A new array contains the appended element without modifying the iterator's array.
        List<String> seen = new ArrayList<>(); // Collect the old snapshot without any concurrent-modification exception.
        before.forEachRemaining(seen::add); // The iterator deliberately does not see the later insertion.
        return List.of(seen,new ArrayList<>(list)); // Contrast the iterator snapshot with current list membership.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(List.of("a"),"b"))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(List.of("a"),"b"), List.of(List.of("a"),List.of("a","b"))); // Case 1: verify the expected result for this input.
        Check.equal(solve(List.of(),"x"), List.of(List.of(),List.of("x"))); // Case 2: verify the expected result for this input.
        Check.equal(solve(List.of("a"),"a"), List.of(List.of("a"),List.of("a","a"))); // Case 3: verify the expected result for this input.
    }
}
