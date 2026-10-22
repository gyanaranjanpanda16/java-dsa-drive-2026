package questions.q246; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Sort by inserting each value into a sorted prefix.
 * Contract: Return a sorted copy using stable insertion sort.
 * Why: Before each iteration, the prefix is sorted. Shift only values larger than the new value, then fill the resulting gap. Equal values are not moved past each other, making the algorithm stable and useful for small or nearly sorted inputs.
 * Cost: O(n²) worst-case, O(n) on sorted input; O(n) output copy and O(1) working state.
 */
public final class Q246InsertionSort { // Keep the algorithm, demonstration, and boundary checks together.
    private Q246InsertionSort() { } // Only static entry points are needed for this exercise.
    public static int[] solve(int[] input) { // Return new storage instead of modifying the supplied array.
        int[] a=input.clone(); // A prefix of length one is already sorted.
        for(int i=1;i<a.length;i++) { // Extend the sorted prefix to include the next value.
            int value=a[i],j=i-1; // Save the value before shifting overwrites its original slot.
            while(j>=0 && a[j]>value) { a[j+1]=a[j]; j--; } // Move only larger values right, preserving equal-value order.
            a[j+1]=value; // The first nonlarger predecessor defines the insertion gap.
        }
        return a; // Induction on prefix length establishes complete sorted order.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new int[]{3,1,2}))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new int[]{3,1,2}), new int[]{1,2,3}); // Insert into two growing prefixes.
        Check.equal(solve(new int[]{1,2,3}), new int[]{1,2,3}); // Already sorted needs no shifts.
        Check.equal(solve(new int[]{2,-1,2}), new int[]{-1,2,2}); // Keep duplicates while moving a smaller value.
    }
}
