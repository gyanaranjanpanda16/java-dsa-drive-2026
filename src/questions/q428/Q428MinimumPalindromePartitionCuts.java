package questions.q428;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q428: Minimum Palindrome Partition Cuts.
 * Contract: Cut a UTF-16 string into nonempty palindromic blocks; return the number of cuts.
 * Why it works: Any valid partition has a final palindromic block. Choosing its start and combining it with the best partition of the preceding prefix explores all optimal possibilities.
 * Cost: O(n²) time and space.
 */
public final class Q428MinimumPalindromePartitionCuts { // Keep this question independently runnable and testable.
    private Q428MinimumPalindromePartitionCuts() { } // No object state is needed by this standalone exercise.
    public static int cuts(String s) { // Expose the operation for an independent caller and the executable checks below.
        int n=s.length(); if(n==0) return 0; // Empty text needs no cuts.
        boolean[][] palindrome=new boolean[n][n]; int[] best=new int[n+1]; best[0]=-1; // Minus one makes a first palindromic block add zero cuts.
        for(int end=1;end<=n;end++) { // Solve the minimum cuts for each prefix.
            best[end]=end-1; // Splitting into single units is always a valid upper bound.
            for(int start=0;start<end;start++) { // Try every possible start for the final palindrome block.
                palindrome[start][end-1]=s.charAt(start)==s.charAt(end-1)&&(end-start<=2||palindrome[start+1][end-2]); // Inner intervals ended earlier and are already known.
                if(palindrome[start][end-1]) best[end]=Math.min(best[end],best[start]+1); // Attach one palindromic block to an optimal preceding partition.
            }
        }
        return best[n]; // Every final block of every full partition was considered.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(cuts("aab")); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(cuts("")==0, "Q428 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(cuts("racecar")==0, "Q428 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(cuts("abc")==2, "Q428 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
