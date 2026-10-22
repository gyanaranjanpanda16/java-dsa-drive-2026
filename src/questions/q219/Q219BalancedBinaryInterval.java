package questions.q219; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Find the longest interval with equal zeros and ones.
 * Contract: For a binary array, return the longest contiguous interval containing equally many zeros and ones.
 * Why: Treat zero as -1 and one as +1. Equal prefix balances bound a zero-balance interval; storing the earliest occurrence maximizes its length. This measures balanced two-category stretches in a sequence.
 * Cost: Expected O(n) time and O(n) space.
 */
public final class Q219BalancedBinaryInterval { // Keep the algorithm, demonstration, and boundary checks together.
    private Q219BalancedBinaryInterval() { } // Only static entry points are needed for this exercise.
    public static int solve(int[] a) { // Compare counts through a signed running balance.
        Map<Integer,Integer> earliest=new HashMap<>(); earliest.put(0,-1); // Balance zero exists before the array starts.
        int balance=0,best=0; // Track the current prefix balance and best interval length.
        for(int i=0;i<a.length;i++) { // Each new endpoint can close a balanced interval.
            if(a[i]!=0 && a[i]!=1) throw new IllegalArgumentException("binary values required"); // The two-category encoding must be exact.
            balance+=a[i]==1?1:-1; // Ones and zeros cancel when their counts agree.
            Integer start=earliest.putIfAbsent(balance,i); // Preserve the earliest index for the largest future gap.
            if(start!=null) best=Math.max(best,i-start); // Equal balances mean the intervening sum is zero.
        }
        return best; // Zero also represents no nonempty balanced interval.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new int[]{0,1,0}))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new int[]{0,1,0}), 2); // A proper subinterval is optimal.
        Check.equal(solve(new int[]{0,0}), 0); // Only one category cannot balance.
        Check.equal(solve(new int[]{0,1,1,0}), 4); // The entire array balances.
    }
}
