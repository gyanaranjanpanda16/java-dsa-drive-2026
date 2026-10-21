package questions.q292; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q292: Report the kth largest after every eligible prefix.
 * Contract: k positive; duplicates are occurrences; output omits prefixes with fewer than k elements.
 * Why: Each prefix reuses the previous top-k heap rather than recomputing a rank from scratch. Removing the minimum after overflow preserves exactly the k largest processed occurrences.
 * Complexity: O(n log(k+1)) time; O(k) working storage plus output.
 */
public final class Q292StreamingKth { // One class contains this question, its answer, and reproducible checks.

    public static String solve(int[] stream,int k) { // Expose the answer as a reusable method as well as a runnable program.
        if(k<1) throw new IllegalArgumentException("positive rank required"); // A zero rank has no meaning.
        PriorityQueue<Integer> heap=new PriorityQueue<>(); // The root is the current kth boundary once k values exist.
        List<Integer> answers=new ArrayList<>(); // Prefixes shorter than k intentionally produce no answer.
        for(int value:stream) { // Update the retained candidates with every arrival.
            heap.offer(value); // Include the new occurrence.
            if(heap.size()>k) heap.poll(); // Retain only the k largest occurrences.
            if(heap.size()==k) answers.add(heap.peek()); // A complete candidate set makes rank k defined.
        }
        return answers.toString(); // Output follows the order of eligible prefixes.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(new int[]{4,1,7,2,9},2)); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(new int[]{4,1,7,2,9},2), "[1, 4, 4, 7]"); // Boundary changes over time
        Check.equal(solve(new int[]{1},3), "[]"); // Insufficient arrivals
        Check.equal(solve(new int[]{5,5},1), "[5, 5]"); // Rank one with duplicates
    }
}
