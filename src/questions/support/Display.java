package questions.support;

import java.util.Arrays;

/** Deterministic formatting for the small values printed by question demonstrations. */
public final class Display { // Keep output formatting separate from the algorithms being learned.
    private Display() { } // No instances are required for a stateless utility.
    public static String of(Object value) { // Accept primitive arrays after Java boxes the reference as Object.
        if (value instanceof int[] a) return Arrays.toString(a); // Show element values instead of an array identity.
        if (value instanceof long[] a) return Arrays.toString(a); // Preserve wide integer answers.
        if (value instanceof boolean[] a) return Arrays.toString(a); // Display membership and predicate arrays.
        if (value instanceof char[] a) return Arrays.toString(a); // Make individual character values visible.
        if (value instanceof double[] a) return Arrays.toString(a); // Support numerical demonstrations.
        if (value instanceof Object[] a) return Arrays.deepToString(a); // Format matrices and nested arrays recursively.
        return String.valueOf(value); // Strings, numbers, collections, and null already have useful text forms.
    }
}
