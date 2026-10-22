package questions.q431;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q431: Longest Increasing Subsequence.
 * Contract: Strictly increasing subsequence of integer values; duplicates do not increase its length.
 * Why it works: Replacing a tail with a smaller value cannot prevent a future extension. Lower-bound placement maintains minimal tails for every achievable length and extends the maximum only when warranted.
 * Cost: O(n log n) time and O(n) space.
 */
public final class Q431LongestIncreasingSubsequence { // Keep this question independently runnable and testable.
    private Q431LongestIncreasingSubsequence() { } // No object state is needed by this standalone exercise.
    public static int length(int[] values) { // Expose the operation for an independent caller and the executable checks below.
        int[] tails=new int[values.length]; int size=0; // tails[k] is the smallest possible final value of an increasing subsequence of length k+1.
        for(int value:values) { // Process positions in input order so subsequences preserve that order.
            int low=0,high=size; // Search only the populated increasing tail values.
            while(low<high) { int mid=low+(high-low)/2; if(tails[mid]<value) low=mid+1; else high=mid; } // Lower bound finds the first tail that value can improve.
            tails[low]=value; // A smaller tail leaves at least as much room for future extensions.
            if(low==size) size++; // Appending beyond all known tails creates a longer subsequence.
        }
        return size; // Tail values need not form one subsequence, but their populated count is the optimal length.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(length(new int[]{10,9,2,5,3,7,101,18})); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(length(new int[]{})==0, "Q431 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(length(new int[]{2,2,2})==1, "Q431 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(length(new int[]{3,2,1})==1, "Q431 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
