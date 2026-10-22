package questions.q414;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q414: Target Sum Sign Assignments.
 * Contract: Integer inputs, long target; counts must fit long and reachable states must fit memory.
 * Why it works: Assignments of a prefix are partitioned by their sum. Extending each with plus and minus enumerates every new assignment exactly once, and merging equal sums retains their multiplicity.
 * Cost: O(n*S) expected time and O(S) space where S is the maximum number of distinct reachable totals; S may be exponential.
 */
public final class Q414TargetSumSignAssignments { // Keep this question independently runnable and testable.
    private Q414TargetSumSignAssignments() { } // No object state is needed by this standalone exercise.
    public static long count(int[] values,long target) { // Expose the operation for an independent caller and the executable checks below.
        Map<Long,Long> counts=new HashMap<>(); counts.put(0L,1L); // The empty prefix has one assignment with total zero.
        for(int value:values) { // Assign one sign to each input position, including zeros.
            Map<Long,Long> next=new HashMap<>(); // New states must not reuse the same position more than once.
            for(Map.Entry<Long,Long> entry:counts.entrySet()) { // Expand every achievable prefix total.
                next.merge(entry.getKey()+value,entry.getValue(),Math::addExact); // Appending plus preserves all assignments leading to this total.
                next.merge(entry.getKey()-value,entry.getValue(),Math::addExact); // Appending minus is a distinct sign choice even for zero.
            }
            counts=next; // Move to assignments of the entire processed prefix.
        }
        return counts.getOrDefault(target,0L); // An absent total has no valid sign assignment.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(count(new int[]{1,1,1,1,1},3)); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(count(new int[]{},0)==1, "Q414 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(count(new int[]{0,0},0)==4, "Q414 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(count(new int[]{2},1)==0, "Q414 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
