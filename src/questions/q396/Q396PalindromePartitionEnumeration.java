package questions.q396;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q396: Palindrome Partition Enumeration.
 * Contract: Short non-null strings, exact UTF-16-unit equality.
 * Why it works: Each partition has a unique first cut. Trying every palindromic first piece and recursively partitioning the suffix covers all legal cut sequences.
 * Cost: O(n^2*2^n) conservative time bound including substring/palindrome work; output exponential and stack O(n).
 */
public final class Q396PalindromePartitionEnumeration { // Keep this question independently runnable and testable.
    private Q396PalindromePartitionEnumeration() { } // No object state is needed by this standalone exercise.
    public static List<List<String>> partitions(String s) { // Expose the operation for an independent caller and the executable checks below.
        List<List<String>> out=new ArrayList<>(); // Each answer lists consecutive palindromic pieces.
        split(s,0,new ArrayList<>(),out); // No characters have been consumed initially.
        return out; // Empty text yields one empty partition.
    }
    private static void split(String s,int start,List<String> path,List<List<String>> out) { // Keep recursive state or a repeated sub-operation inside this solution.
        if(start==s.length()) { out.add(new ArrayList<>(path)); return; } // The chosen pieces cover the complete input.
        for(int end=start+1;end<=s.length();end++) { // Try every nonempty next piece boundary.
            String part=s.substring(start,end); // Keep the consecutive segment represented by this choice.
            if(!isPalindrome(part)) continue; // Only palindromic pieces are legal.
            path.add(part); split(s,end,path,out); path.remove(path.size()-1); // Extend the partition, then restore its prefix.
        }
    }
    private static boolean isPalindrome(String s) { // Keep recursive state or a repeated sub-operation inside this solution.
        for(int l=0,r=s.length()-1;l<r;l++,r--) if(s.charAt(l)!=s.charAt(r)) return false; // Any unequal mirrored pair disproves symmetry.
        return true; // All mirrored pairs agree.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(partitions("aab")); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(partitions("").equals(List.of(List.of())), "Q396 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(partitions("a").equals(List.of(List.of("a"))), "Q396 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(partitions("aab").size()==2, "Q396 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
