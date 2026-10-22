package questions.q299; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q299: Sort values displaced by at most k positions.
 * Contract: Input must already satisfy the displacement guarantee; arbitrary input with small k need not be sorted by this method.
 * Why: If every value is within k positions of a sorted placement, the next required value must occur among the next k+1 unconsumed candidates. Removing their minimum and admitting the next arrival maintains that fact.
 * Complexity: O(n log(k+2)) time; O(min(n,k+1)) heap plus output.
 */
public final class Q299SortNearlySorted { // One class contains this question, its answer, and reproducible checks.

    public static String solve(int[] a,int k) { // Expose the answer as a reusable method as well as a runnable program.
        if(k<0) throw new IllegalArgumentException("negative displacement"); // Displacement is a nonnegative distance.
        PriorityQueue<Integer> heap=new PriorityQueue<>(); // Candidate next values come from at most k+1 nearby positions.
        List<Integer> result=new ArrayList<>(); // Emit the sorted sequence without changing the input.
        for(int value:a) { // Add arrivals to the bounded candidate window.
            heap.offer(value); // The next answer cannot lie farther right than this candidate boundary.
            if(heap.size()>k) result.add(heap.poll()); // Once k+1 candidates exist, their minimum is the next sorted value.
        }
        while(!heap.isEmpty()) result.add(heap.poll()); // Drain the final incomplete candidate window.
        return result.toString(); // Under the displacement contract, all output positions are sorted.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(new int[]{2,1,4,3},1)); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(new int[]{2,1,4,3},1), "[1, 2, 3, 4]"); // Each element is displaced one position
        Check.equal(solve(new int[]{1,2},0), "[1, 2]"); // Already sorted input
        Check.equal(solve(new int[]{},4), "[]"); // Empty input
    }
}
