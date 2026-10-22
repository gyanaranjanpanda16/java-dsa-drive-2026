package questions.q244; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Sort with adjacent exchanges and an early-stop check.
 * Contract: Return a sorted copy of an int array using bubble sort; leave the input unchanged.
 * Why: An adjacent inverted pair can be swapped without disturbing any other pair order. Each pass moves the largest unresolved value to the right; positions after the final swap are already settled. This teaches local inversion repair and adaptive early stopping.
 * Cost: O(n²) worst-case and O(n) best-case time; O(n) returned copy and O(1) extra sorting state.
 */
public final class Q244BubbleSort { // Keep the algorithm, demonstration, and boundary checks together.
    private Q244BubbleSort() { } // Only static entry points are needed for this exercise.
    public static int[] solve(int[] input) { // Preserve caller-owned data by sorting a copy.
        int[] a=input.clone(); int end=a.length-1; // The suffix beyond end is already in final order.
        while(end>0) { // At least two unresolved positions are required for a comparison.
            int last=0; // Zero records that no out-of-order pair has been found yet.
            for(int i=0;i<end;i++) if(a[i]>a[i+1]) { // Swap only strict inversions, retaining stability for equal values.
                int saved=a[i]; a[i]=a[i+1]; a[i+1]=saved; last=i; // Move the larger value right and remember the final changed boundary.
            }
            end=last; // The remaining suffix had no later inversions and needs no further work.
        }
        return a; // Every adjacent pair is now ordered.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new int[]{3,1,2}))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new int[]{3,1,2}), new int[]{1,2,3}); // Several adjacent exchanges.
        Check.equal(solve(new int[]{}), new int[]{}); // Empty input.
        Check.equal(solve(new int[]{2,2,-1}), new int[]{-1,2,2}); // Duplicates and a negative value.
    }
}
