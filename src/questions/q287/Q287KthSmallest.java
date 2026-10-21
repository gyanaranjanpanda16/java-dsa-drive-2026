package questions.q287; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q287: Find the kth smallest array element.
 * Contract: Non-null array and valid one-based rank; duplicates count.
 * Why: The retained max-heap is the multiset of the k smallest processed occurrences. Its largest member is precisely the boundary value for rank k.
 * Complexity: O(n log(k+1)) time; O(k) auxiliary space.
 */
public final class Q287KthSmallest { // One class contains this question, its answer, and reproducible checks.

    public static int solve(int[] a,int k) { // Expose the answer as a reusable method as well as a runnable program.
        if(k<1||k>a.length) throw new IllegalArgumentException("invalid rank"); // Reject ranks without an answer.
        PriorityQueue<Integer> heap=new PriorityQueue<>(Comparator.reverseOrder()); // The largest retained small candidate is the eviction boundary.
        for(int value:a) { // Process each occurrence, including duplicates.
            heap.offer(value); // Give the arrival a chance to join the k smallest.
            if(heap.size()>k) heap.poll(); // Evict the largest of k+1 candidates.
        }
        return heap.element(); // The largest retained value is the kth smallest.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(new int[]{7,2,9,1},2)); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(new int[]{7,2,9,1},2), 2); // Unsorted input
        Check.equal(solve(new int[]{4,4,9},2), 4); // Repeated rank values
        Check.equal(solve(new int[]{-5},1), -5); // Singleton input
    }
}
