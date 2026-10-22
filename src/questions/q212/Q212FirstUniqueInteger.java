package questions.q212;

import java.util.*;
import questions.support.Check;

/**
 * Q212: Find the first integer appearing exactly once.
 * Contract: Return an OptionalInt containing the earliest array value with total frequency one, or empty if none exists.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: The first pass obtains complete frequencies; the second pass respects original order. Its first frequency-one entry is therefore both unique globally and earliest. This models the first unpaired event identifier.
 * Complexity: O(n) expected time and O(u) space.
 */
public final class Q212FirstUniqueInteger { // Keep one question and its tests together for independent study.
    public static OptionalInt solve(int[] values) { // Distinguish no answer from every legitimate integer value.
        Map<Integer,Integer> counts=new HashMap<>(); // Store the final multiplicity of each observed value.
        for(int value:values) counts.merge(value,1,Integer::sum); // Count all occurrences before deciding global uniqueness.
        for(int value:values) if(counts.get(value)==1) return OptionalInt.of(value); // Original traversal order selects the earliest qualifying value.
        return OptionalInt.empty(); // Every value was repeated,or the array was empty.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve(new int[]{4,5,4,6,5})); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve(new int[]{4,5,4,6,5}),OptionalInt.of(6)); // Earlier candidates can be invalidated by later repeats.
    Check.equal(solve(new int[]{2,2}),OptionalInt.empty()); // All repeated values produce no answer.
    Check.equal(solve(new int[]{-1,3}),OptionalInt.of(-1)); // Negative one is a valid answer rather than a sentinel.
    }
}
