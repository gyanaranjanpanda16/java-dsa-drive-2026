package questions.q248; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Sort with three-way quicksort partitions.
 * Contract: Return a sorted copy using a middle-value pivot and less/equal/greater partitions. An explicit stack avoids recursive call-stack overflow.
 * Why: Partitioning fixes all pivot-equal values together; only the less and greater regions remain unsorted. This avoids repeatedly partitioning equal keys and exposes the invariant behind quicksort. Deterministic pivots still permit adversarial quadratic time.
 * Cost: O(n log n) on balanced partitions, O(n²) worst-case; O(n) copy and worst-case work-stack space.
 */
public final class Q248ThreeWayQuickSort { // Keep the algorithm, demonstration, and boundary checks together.
    private Q248ThreeWayQuickSort() { } // Only static entry points are needed for this exercise.
    private static void swap(int[] a,int i,int j) { // Exchange two slots without changing the multiset.
        int saved=a[i]; a[i]=a[j]; a[j]=saved; // Preserve the overwritten value until the exchange completes.
    }
    public static int[] solve(int[] input) { // Keep the original array intact.
        int[] a=input.clone(); Deque<int[]> work=new ArrayDeque<>(); work.push(new int[]{0,a.length-1}); // Store unresolved inclusive ranges explicitly.
        while(!work.isEmpty()) { // Process ranges until every value belongs to a finalized partition.
            int[] range=work.pop(); int low=range[0],high=range[1]; if(low>=high) continue; // Empty and singleton ranges are already sorted.
            int pivot=a[low+(high-low)/2],less=low,scan=low,greater=high; // Regions are less-than, equal, unknown, and greater-than.
            while(scan<=greater) { // Inspect every still-unknown value in this range.
                if(a[scan]<pivot) swap(a,less++,scan++); // Extend the smaller region and preserve the equal region behind it.
                else if(a[scan]>pivot) swap(a,scan,greater--); // Move a larger value out but inspect the newly swapped-in value next.
                else scan++; // An equal value already belongs in the middle region.
            }
            if(low<less-1) work.push(new int[]{low,less-1}); // Only strictly smaller values need further ordering.
            if(greater+1<high) work.push(new int[]{greater+1,high}); // Likewise schedule the strictly larger region.
        }
        return a; // Pivot-equal regions and recursively ordered sides establish global order.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new int[]{3,1,3,2,3}))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new int[]{3,1,3,2,3}), new int[]{1,2,3,3,3}); // One large equal partition.
        Check.equal(solve(new int[]{}), new int[]{}); // Empty range is skipped.
        Check.equal(solve(new int[]{3,2,1}), new int[]{1,2,3}); // Reverse order.
    }
}
