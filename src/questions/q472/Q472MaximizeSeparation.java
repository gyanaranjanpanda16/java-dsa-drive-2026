package questions.q472; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Place k items to maximize their minimum separation.
 * Contract: Choose k positions from the supplied int coordinates,2<=k<=length, maximizing the minimum pairwise distance. Duplicate coordinates are permitted; input order is irrelevant. Return long distance.
 * Why: After sorting, placing each next item at the earliest position at least d from the last leaves maximal room for future items. This greedy feasibility test is monotone in d, enabling a search for the greatest feasible separation. Useful for spacing resources along a line.
 * Cost: O(n log n+n log(coordinateSpan+1)) time and O(n) copied storage.
 */
public final class Q472MaximizeSeparation { // Keep the algorithm, demonstration, and boundary checks together.
    private Q472MaximizeSeparation() { } // Only static entry points are needed for this exercise.
    public static long solve(int[] positions,int k) { // Distances can span the entire signed-int coordinate domain.
        if(k<2||k>positions.length) throw new IllegalArgumentException("invalid item count"); // At least two items are needed to define separation.
        int[] sorted=positions.clone(); Arrays.sort(sorted); // Greedy placement requires increasing coordinates.
        long low=0,high=(long)sorted[sorted.length-1]-sorted[0]; // Zero is always feasible, while the full span is an upper bound.
        while(low<high) { // Search for the last feasible integer distance.
            long distance=low+(high-low+1)/2,last=sorted[0]; int placed=1; // An upper midpoint guarantees progress on a two-value interval.
            for(int i=1;i<sorted.length;i++) if((long)sorted[i]-last>=distance) { placed++; last=sorted[i]; } // Choose the earliest usable position each time.
            if(placed>=k) low=distance; else high=distance-1; // A failed spacing rules out every larger spacing.
        }
        return low; // This greatest feasible threshold equals the optimal minimum separation.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new int[]{1,2,4,8,9},3))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new int[]{1,2,4,8,9},3), 3L); // Greedy feasibility inside a binary search.
        Check.equal(solve(new int[]{Integer.MIN_VALUE,Integer.MAX_VALUE},2), 4294967295L); // Distance subtraction is widened.
        Check.equal(solve(new int[]{2,2,2},2), 0L); // Duplicate positions allow only zero separation.
    }
}
