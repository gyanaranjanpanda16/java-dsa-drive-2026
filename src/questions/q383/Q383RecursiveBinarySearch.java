package questions.q383;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q383: Recursive Binary Search.
 * Contract: Input sorted nondecreasing; return any matching index or -1.
 * Why it works: The sorted interval always contains every still-possible match. Comparing its midpoint eliminates half without discarding the target.
 * Cost: O(log n) time and O(log n) recursion space.
 */
public final class Q383RecursiveBinarySearch { // Keep this question independently runnable and testable.
    private Q383RecursiveBinarySearch() { } // No object state is needed by this standalone exercise.
    public static int search(int[] a, int target) { // Expose the operation for an independent caller and the executable checks below.
        return searchRange(a, target, 0, a.length - 1); // Begin with the complete sorted candidate interval.
    }
    private static int searchRange(int[] a, int t, int lo, int hi) { // Keep recursive state or a repeated sub-operation inside this solution.
        if (lo > hi) return -1; // No candidate index remains.
        int mid = lo + (hi - lo) / 2; // This midpoint avoids adding two potentially large indices.
        if (a[mid] == t) return mid; // Equality proves that this index is a valid answer.
        return a[mid] < t ? searchRange(a,t,mid+1,hi) : searchRange(a,t,lo,mid-1); // Sorted order rules out one entire half.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(search(new int[]{1,3,5,8,12},8)); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(search(new int[]{},1)==-1, "Q383 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(search(new int[]{5},5)==0, "Q383 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(search(new int[]{1,2,3},9)==-1, "Q383 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
