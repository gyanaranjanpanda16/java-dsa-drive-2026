package questions.q241; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Find a strict local peak without scanning the whole array.
 * Contract: Adjacent values must differ. Return an index whose value exceeds its existing neighbors, treating missing endpoints as negative infinity; return-1 for empty input.
 * Why: If the slope rises from middle to middle+1, some peak exists on the right: a rising walk must end at a descent or the array endpoint. A falling slope guarantees one on the left including middle. This locates a local optimum, not necessarily the global maximum.
 * Cost: O(log(n+1)) time and O(1) space.
 */
public final class Q241PeakElement { // Keep the algorithm, demonstration, and boundary checks together.
    private Q241PeakElement() { } // Only static entry points are needed for this exercise.
    public static int solve(int[] a) { // Find any strict local peak under the unequal-neighbor contract.
        if(a.length==0) return -1; // An empty array has no peak position.
        int low=0,high=a.length-1; // The retained interval always contains at least one peak.
        while(low<high) { // middle+1 is valid because middle is strictly below high.
            int middle=low+(high-low)/2; // Probe the local slope near the interval center.
            if(a[middle]<a[middle+1]) low=middle+1; else high=middle; // Follow the side that is guaranteed to contain a peak.
        }
        return low; // Both bounds agree on a locally maximal position.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new int[]{1,2,3,1}))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new int[]{1,2,3,1}), 2); // An interior peak.
        Check.equal(solve(new int[]{5}), 0); // A lone element is a peak.
        Check.equal(solve(new int[]{3,2,1}), 0); // A decreasing array peaks at its first endpoint.
    }
}
