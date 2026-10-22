package questions.q245; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Sort by repeatedly selecting the smallest remaining value.
 * Contract: Return a sorted copy using selection sort.
 * Why: At step i, choose the minimum of the unresolved suffix and place it at i. The fixed prefix then contains exactly the i+1 smallest values in order. This teaches selection and minimizes swaps, but the standard swap version is not stable.
 * Cost: O(n²) comparisons in all cases, at most O(n) swaps; O(n) output copy.
 */
public final class Q245SelectionSort { // Keep the algorithm, demonstration, and boundary checks together.
    private Q245SelectionSort() { } // Only static entry points are needed for this exercise.
    public static int[] solve(int[] input) { // Make mutations local to the returned copy.
        int[] a=input.clone(); // Every later swap preserves the original multiset of values.
        for(int i=0;i<a.length;i++) { // Grow the finalized sorted prefix one element at a time.
            int smallest=i; // Initially consider the first unresolved value the minimum.
            for(int j=i+1;j<a.length;j++) if(a[j]<a[smallest]) smallest=j; // Search the complete unresolved suffix.
            int saved=a[i]; a[i]=a[smallest]; a[smallest]=saved; // Place its minimum at the next final position.
        }
        return a; // The finalized prefix eventually spans the entire array.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new int[]{3,1,2}))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new int[]{3,1,2}), new int[]{1,2,3}); // The minimum moves into each prefix slot.
        Check.equal(solve(new int[]{}), new int[]{}); // No selection needed.
        Check.equal(solve(new int[]{4,4,1}), new int[]{1,4,4}); // Equal values remain present.
    }
}
