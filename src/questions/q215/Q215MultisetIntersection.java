package questions.q215;

import java.util.*;
import questions.support.Check;

/**
 * Q215: Intersect arrays including matched duplicate occurrences.
 * Contract: Return min(leftCount,rightCount) copies of each shared value, in first-array encounter order.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: A frequency map represents unused occurrences in the second array. Each accepted first-array value consumes exactly one, so emitted multiplicity cannot exceed either input and reaches their minimum. This reconciles duplicate records or inventory units.
 * Complexity: O(n+m) expected time and O(u) auxiliary space.
 */
public final class Q215MultisetIntersection { // Keep one question and its tests together for independent study.
    public static String solve(int[] first,int[] second) { // Match individual occurrences rather than only membership.
        Map<Integer,Integer> available=new HashMap<>(); // Count unmatched copies supplied by the second array.
        for(int value:second) available.merge(value,1,Integer::sum); // Repeated values each contribute one available copy.
        List<Integer> result=new ArrayList<>(); // Preserve the encounter order of matched first-array occurrences.
        for(int value:first) { // Attempt one match for each first-array occurrence.
            int count=available.getOrDefault(value,0); // A missing key contributes no available copy.
            if(count>0) { result.add(value);available.put(value,count-1); } // Emit only when one second-array copy can be consumed.
        }
        return result.toString(); // Each shared value appears with the smaller input multiplicity.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve(new int[]{2,1,2,2},new int[]{2,2,3})); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve(new int[]{2,1,2,2},new int[]{2,2,3}),"[2, 2]"); // Match only the two available copies.
    Check.equal(solve(new int[]{},new int[]{1}),"[]"); // An empty first input supplies no matches.
    Check.equal(solve(new int[]{1,2,1},new int[]{1,2,1}),"[1, 2, 1]"); // Equal multisets preserve every first-array occurrence.
    }
}
