package questions.q236; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Search a sorted array by halving the candidate interval.
 * Contract: Return an index containing target in a nondecreasing int array, or-1 when absent. Any matching index is acceptable for duplicates.
 * Why: Maintain that any still-possible match lies between low and high inclusive. Comparing the middle value eliminates the half that ordering proves cannot contain the target. This is the core search operation on sorted indexes.
 * Cost: O(log(n+1)) time and O(1) space.
 */
public final class Q236IterativeBinarySearch { // Keep the algorithm, demonstration, and boundary checks together.
    private Q236IterativeBinarySearch() { } // Only static entry points are needed for this exercise.
    public static int solve(int[] a,int target) { // Assume ordering has already been established by the caller.
        int low=0,high=a.length-1; // The full array initially contains all candidate positions.
        while(low<=high) { // An empty candidate interval proves absence.
            int middle=low+(high-low)/2; // Avoid overflow from adding two large indices.
            if(a[middle]==target) return middle; // Equality identifies a valid answer immediately.
            if(a[middle]<target) low=middle+1; else high=middle-1; // Exclude the tested midpoint and the impossible sorted half.
        }
        return -1; // No candidate survived the comparisons.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new int[]{1,3,5,7,9},7))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new int[]{1,3,5,7,9},7), 3); // A target in the upper half.
        Check.equal(solve(new int[]{},1), -1); // Empty candidate interval.
        Check.equal(solve(new int[]{2,4},3), -1); // An absent value between entries.
    }
}
