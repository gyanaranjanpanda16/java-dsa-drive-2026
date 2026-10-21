package questions.q286; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q286: Find the kth largest array element.
 * Contract: Non-null int array; 1<=k<=length; duplicates count separately.
 * Why: After every insertion the heap contains the largest k values seen so far, or the entire shorter prefix. Discarding its minimum cannot eliminate a final top-k value without keeping k at least-as-large alternatives.
 * Complexity: O(n log(k+1)) time and O(k) space.
 */
public final class Q286KthLargest { // One class contains this question, its answer, and reproducible checks.

    public static int solve(int[] a,int k) { // Expose the answer as a reusable method as well as a runnable program.
        if(k<1||k>a.length) throw new IllegalArgumentException("invalid rank"); // A rank must select an existing occurrence.
        PriorityQueue<Integer> heap=new PriorityQueue<>(); // The smallest retained candidate is easiest to discard.
        for(int value:a) { // Each arrival competes for one of the k retained positions.
            heap.offer(value); // Temporarily include the new candidate.
            if(heap.size()>k) heap.poll(); // Discard the smallest so only the k largest remain.
        }
        return heap.element(); // The smallest of the k largest is exactly rank k.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(new int[]{3,2,1,5,6,4},2)); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(new int[]{3,2,1,5,6,4},2), 5); // Ordinary rank
        Check.equal(solve(new int[]{5,5,1},2), 5); // Equal values count as separate occurrences
        Check.equal(solve(new int[]{-2},1), -2); // Singleton rank
    }
}
