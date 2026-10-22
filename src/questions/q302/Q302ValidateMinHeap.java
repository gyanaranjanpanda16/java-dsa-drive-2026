package questions.q302; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q302: Check the binary min-heap property.
 * Contract: An ordinary array has no missing internal positions; duplicate values are allowed.
 * Why: An array already represents a complete binary-tree shape. Therefore checking the heap property only requires checking every parent-child edge; transitivity then makes the root no larger than every descendant.
 * Complexity: O(n) time and O(1) auxiliary space.
 */
public final class Q302ValidateMinHeap { // One class contains this question, its answer, and reproducible checks.

    public static boolean solve(int[] heap) { // Expose the answer as a reusable method as well as a runnable program.
        for(int child=1;child<heap.length;child++) { // Every non-root node has exactly one parent to compare.
            int parent=(child-1)/2; // Array layout identifies the parent without building node objects.
            if(heap[parent]>heap[child]) return false; // One violated edge disproves the min-heap invariant.
        }
        return true; // All parent-child edges satisfy the required ordering.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(new int[]{1,3,2,7,4})); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(new int[]{1,3,2,7,4}), true); // Valid heap with unsorted siblings
        Check.equal(solve(new int[]{2,1,3}), false); // Child smaller than parent
        Check.equal(solve(new int[]{}), true); // Empty array vacuously satisfies the invariant
    }
}
