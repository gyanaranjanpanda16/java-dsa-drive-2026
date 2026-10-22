package questions.q112;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q112: Deduplicate a rarely changed observer registry.
 * Return [old observer snapshot, current unique observers] after registration. CopyOnWriteArraySet fits small read-heavy registries where callbacks can traverse a stable membership snapshot; membership checks are linear, so it is not a high-volume HashSet replacement.
 * Complexity: O(n²) worst-case construction from duplicate-checking insertions and O(n) extra insertion/snapshot work; O(n) space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q112CopyOnWriteSet { // Keep this question independently runnable and easy to find.
    private Q112CopyOnWriteSet() { } // This exercise exposes behavior through static methods.
    public static List<List<String>> solve(List<String> input, String registered) { // Define the input and result types of this particular problem.
        CopyOnWriteArraySet<String> observers = new CopyOnWriteArraySet<>(input); // Collapse equal observers using a copy-on-write array-backed set.
        Iterator<String> before = observers.iterator(); // Keep a snapshot of membership before registration.
        observers.add(registered); // Duplicate registration changes nothing; a new observer triggers a copy.
        List<String> oldMembers = new ArrayList<>(); // Materialize the previously captured membership.
        before.forEachRemaining(oldMembers::add); // Snapshot iteration is insulated from later membership updates.
        return List.of(oldMembers,new ArrayList<>(observers)); // Expose insertion-order membership before and after registration.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(List.of("a","a","b"),"c"))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(List.of("a","a","b"),"c"), List.of(List.of("a","b"),List.of("a","b","c"))); // Case 1: verify the expected result for this input.
        Check.equal(solve(List.of("a"),"a"), List.of(List.of("a"),List.of("a"))); // Case 2: verify the expected result for this input.
        Check.equal(solve(List.of(),"x"), List.of(List.of(),List.of("x"))); // Case 3: verify the expected result for this input.
    }
}
