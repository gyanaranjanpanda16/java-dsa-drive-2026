package questions.q247; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Sort by dividing and stably merging halves.
 * Contract: Return a sorted copy using merge sort with a reusable auxiliary buffer.
 * Why: Recursively sort disjoint halves, then repeatedly select the smaller front value. The unconsumed fronts are the smallest remaining candidates, so each merged output is the next sorted value. Stable merging is useful when secondary record order must survive.
 * Cost: O(n log(n+1)) time and O(n) buffer/output space plus O(log(n+1)) call stack.
 */
public final class Q247MergeSort { // Keep the algorithm, demonstration, and boundary checks together.
    private Q247MergeSort() { } // Only static entry points are needed for this exercise.
    public static int[] solve(int[] input) { // Clone once and share one scratch buffer across recursive calls.
        int[] a=input.clone(); sort(a,new int[a.length],0,a.length); return a; // Half-open bounds naturally represent empty input.
    }
    private static void sort(int[] a,int[] buffer,int from,int to) { // Sort exactly the range [from,to).
        if(to-from<2) return; // Zero or one element is already ordered.
        int middle=from+(to-from)/2; sort(a,buffer,from,middle); sort(a,buffer,middle,to); // Establish the two sorted prerequisites.
        int left=from,right=middle; // Track the unconsumed front of each half.
        for(int out=from;out<to;out++) { // Fill every destination slot with the smallest remaining candidate.
            if(right==to || (left<middle && a[left]<=a[right])) buffer[out]=a[left++]; // Prefer left on equality to preserve stability.
            else buffer[out]=a[right++]; // The right front is smaller or the left half is exhausted.
        }
        System.arraycopy(buffer,from,a,from,to-from); // Commit the fully merged range back to the working array.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new int[]{4,1,3,2}))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new int[]{4,1,3,2}), new int[]{1,2,3,4}); // Merge interleaving sorted halves.
        Check.equal(solve(new int[]{}), new int[]{}); // Empty half-open range.
        Check.equal(solve(new int[]{2,1,2,1}), new int[]{1,1,2,2}); // Repeated keys survive merging.
    }
}
