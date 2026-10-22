package questions.q460;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q460: Partition Labels.
 * Contract: Partition a UTF-16 string into as many contiguous blocks as possible, with each unit appearing in only one block.
 * Why it works: A partition containing a unit must include its last occurrence. Expanding to every forced endpoint yields the earliest possible valid cut, and taking that cut cannot reduce the number of later partitions.
 * Cost: Expected O(n) time and O(u) map space plus output.
 */
public final class Q460PartitionLabels { // Keep this question independently runnable and testable.
    private Q460PartitionLabels() { } // No object state is needed by this standalone exercise.
    public static List<Integer> lengths(String text) { // Expose the operation for an independent caller and the executable checks below.
        Map<Character,Integer> last=new HashMap<>(); // Last occurrence tells how far a partition must extend to contain a seen unit.
        for(int i=0;i<text.length();i++) last.put(text.charAt(i),i); // Later positions replace earlier ones for the same unit.
        List<Integer> result=new ArrayList<>(); int start=0,end=0; // Track the start and forced endpoint of the current partition.
        for(int i=0;i<text.length();i++) { // Include every unit that lies inside the currently forced interval.
            end=Math.max(end,last.get(text.charAt(i))); // Any newly encountered unit may force a later boundary.
            if(i==end) { result.add(end-start+1); start=i+1; } // All units inside have no later occurrences, so cut at the earliest legal boundary.
        }
        return result; // Earliest legal cuts maximize the number of partitions.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(lengths("ababcbacadefegdehijhklij")); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(lengths("").isEmpty(), "Q460 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(lengths("abc").equals(List.of(1,1,1)), "Q460 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(lengths("aaaa").equals(List.of(4)), "Q460 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
