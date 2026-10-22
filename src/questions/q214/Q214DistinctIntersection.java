package questions.q214;

import java.util.*;
import questions.support.Check;

/**
 * Q214: Return the distinct intersection in first-array order.
 * Contract: Return each integer present in both arrays once, ordered by its first appearance in the first array.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: A set stores unmatched second-array membership. Removing a value upon its first successful match both proves membership and prevents duplicate output. Useful when intersecting user cohorts while retaining a preferred display order.
 * Complexity: O(n+m) expected time and O(u) space excluding output.
 */
public final class Q214DistinctIntersection { // Keep one question and its tests together for independent study.
    public static String solve(int[] first,int[] second) { // Combine membership with a stable first-array ordering rule.
        Set<Integer> available=new HashSet<>(); // Membership ignores duplicates in the second cohort.
        for(int value:second) available.add(value); // Record every possible intersection value.
        List<Integer> result=new ArrayList<>(); // Append matches in first-array encounter order.
        for(int value:first) if(available.remove(value)) result.add(value); // Successful removal both matches and marks the value as already emitted.
        return result.toString(); // Every common distinct value appears exactly once.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve(new int[]{3,1,3,2},new int[]{2,3,3})); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve(new int[]{3,1,3,2},new int[]{2,3,3}),"[3, 2]"); // Preserve first-array order while discarding duplicate matches.
    Check.equal(solve(new int[]{1},new int[]{2}),"[]"); // Disjoint cohorts have no intersection.
    Check.equal(solve(new int[]{1,1},new int[]{1}),"[1]"); // A common value is emitted once.
    }
}
