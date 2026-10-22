package questions.q221; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Count distinct values in every fixed-size window.
 * Contract: Return distinct counts for each length-k contiguous window, with k>0. If k exceeds the input length, return an empty array.
 * Why: A frequency map contains exactly the current window. Remove keys when their count becomes zero so map size equals the distinct count. This supports rolling unique-user or category statistics.
 * Cost: Expected O(n) time and O(k) auxiliary space plus output.
 */
public final class Q221DistinctWindowCounts { // Keep the algorithm, demonstration, and boundary checks together.
    private Q221DistinctWindowCounts() { } // Only static entry points are needed for this exercise.
    public static int[] solve(int[] a,int k) { // Return one count per complete window.
        if(k<=0) throw new IllegalArgumentException("positive window required"); // A window must contain at least one element.
        if(k>a.length) return new int[0]; // No complete window exists.
        Map<Integer,Integer> counts=new HashMap<>(); // Store multiplicities rather than only membership.
        int[] result=new int[a.length-k+1]; // Consecutive starting positions determine the output size.
        for(int i=0;i<a.length;i++) { // Add the new right endpoint before evicting the old left endpoint.
            counts.merge(a[i],1,Integer::sum); // Repeated elements increase multiplicity without increasing distinct size.
            if(i>=k) { // This prefix contains one element beyond the desired window.
                int old=a[i-k],left=counts.get(old)-1; // Remove one occurrence, not every equal value.
                if(left==0) counts.remove(old); else counts.put(old,left); // Keep exactly the positive-frequency keys.
            }
            if(i>=k-1) result[i-k+1]=counts.size(); // Only full windows contribute an answer.
        }
        return result; // Each window has been updated from its predecessor in constant expected work.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new int[]{1,2,1,3},3))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new int[]{1,2,1,3},3), new int[]{2,3}); // Distinct membership changes as one value leaves.
        Check.equal(solve(new int[]{2,2,2},2), new int[]{1,1}); // Repeated copies stay one distinct value.
        Check.equal(solve(new int[]{},1), new int[]{}); // No full window.
    }
}
