package questions.q379;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q379: Recursive String Reversal.
 * Contract: Reverse UTF-16 char units of a non-null string; this is not Unicode grapheme reversal.
 * Why it works: Swapping matching ends fixes both final positions. Recursive calls operate only on the untouched interior, preserving the fixed outside region.
 * Cost: O(n) time, O(n) copied-character storage and O(n) stack.
 */
public final class Q379RecursiveStringReversal { // Keep this question independently runnable and testable.
    private Q379RecursiveStringReversal() { } // No object state is needed by this standalone exercise.
    public static String reverse(String s) { // Expose the operation for an independent caller and the executable checks below.
        char[] a = s.toCharArray(); // Mutable slots allow symmetric swaps without repeated concatenation.
        swapEnds(a, 0, a.length - 1); // The first call covers every UTF-16 unit.
        return new String(a); // Preserve the caller's immutable input string.
    }
    private static void swapEnds(char[] a, int left, int right) { // Keep recursive state or a repeated sub-operation inside this solution.
        if (left >= right) return; // Zero or one middle unit is already reversed.
        char temp = a[left]; a[left] = a[right]; a[right] = temp; // Place both boundary units at their final positions.
        swapEnds(a, left + 1, right - 1); // Only the inner interval remains unresolved.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(reverse("java")); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(reverse("").equals(""), "Q379 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(reverse("x").equals("x"), "Q379 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(reverse("abcd").equals("dcba"), "Q379 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
