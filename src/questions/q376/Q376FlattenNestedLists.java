package questions.q376; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Flatten nested integer lists in order.
 * Contract: Accept a finite, acyclic List whose elements are Integer or further Lists of the same form. Return all integers in left-to-right depth-first order. Empty nested lists contribute nothing; other element types and null elements are rejected. Nesting must fit the Java call stack.
 * Why: A fixed number of nested loops only handles a fixed depth. Recursion handles the same contract at every depth: flatten a child list completely before visiting its next sibling. The output accumulated so far is always the flattened prefix already visited. This is the core traversal idea behind nested configuration values and document trees; cycles would require a separate visited-object policy.
 * Cost: O(N) time for N visited elements, O(D) call-stack space for depth D, plus O(K) output for K integers.
 */
public final class Q376FlattenNestedLists { // Keep the algorithm, demonstration, and boundary checks together.
    private Q376FlattenNestedLists() { } // Only static entry points are needed for this exercise.
    public static List<Integer> solve(List<?> nested) { // Wildcards allow lists nested to different depths.
        List<Integer> output=new ArrayList<>(); // One accumulator avoids repeatedly copying child results.
        flatten(nested,output); // Visit the whole outer list in encounter order.
        return output; // The caller receives a new flat list.
    }
    private static void flatten(List<?> nested,List<Integer> output) { // Each call handles one list completely.
        for(Object value:nested) { // Preserve the source order among siblings.
            if(value instanceof Integer number) output.add(number); // Integers are leaves and contribute one output value.
            else if(value instanceof List<?> child) flatten(child,output); // Lists contribute their recursively flattened contents.
            else throw new IllegalArgumentException("only integers and lists allowed"); // Do not silently drop unsupported values.
        }
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(List.of(1,List.of(2,List.of(),List.of(3)),4)))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(List.of(1,List.of(2,List.of(),List.of(3)),4)), List.of(1,2,3,4)); // Multiple depths and an empty nested list.
        Check.equal(solve(List.of()), List.of()); // Empty outer list.
        Check.equal(solve(List.of(List.of(-1),List.of(-1))), List.of(-1,-1)); // Keep repeated values and signs.
    }
}
