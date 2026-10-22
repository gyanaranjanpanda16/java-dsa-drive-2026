package questions.q106;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q106: Compare value equality with object identity as map keys.
 * Return [HashMap size, IdentityHashMap size] after inserting a fresh String object for every non-null input value. Identity-based maps help track visited object instances in object-graph traversal or serialization; they intentionally differ from normal Map equality expectations and are unsuitable for usual textual lookup.
 * Complexity: Expected O(n) map operations plus O(total string hashing length) time and O(n) space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q106IdentityKeys { // Keep this question independently runnable and easy to find.
    private Q106IdentityKeys() { } // This exercise exposes behavior through static methods.
    public static int[] solve(String[] values) { // Define the input and result types of this particular problem.
        Map<String,Integer> byValue = new HashMap<>(); // Ordinary maps consider equal strings to be the same key.
        Map<String,Integer> byIdentity = new IdentityHashMap<>(); // Identity maps compare key references with == instead of equals.
        for (int i = 0; i < values.length; i++) { // Deliberately create one distinct object per input occurrence.
            String key = new String(values[i]); // Copy construction avoids reusing interned literal references.
            byValue.put(key,i); // Equal text replaces its previous value in the value-based map.
            byIdentity.put(key,i); // A distinct reference creates a distinct identity entry, even for equal text.
        }
        return new int[]{byValue.size(),byIdentity.size()}; // Report unique values and unique objects separately.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new String[]{"a","a","b"}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new String[]{"a","a","b"}), new int[]{2,3}); // Case 1: verify the expected result for this input.
        Check.equal(solve(new String[]{}), new int[]{0,0}); // Case 2: verify the expected result for this input.
        Check.equal(solve(new String[]{"x","y"}), new int[]{2,2}); // Case 3: verify the expected result for this input.
    }
}
