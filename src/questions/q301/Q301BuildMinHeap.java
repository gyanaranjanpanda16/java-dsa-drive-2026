package questions.q301; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q301: Build a binary min-heap in an array.
 * Contract: Input is any non-null int array; the resulting array is a heap, not necessarily fully sorted.
 * Why: When an internal node is processed, both child subtrees are already heaps. Sifting its root down restores its local order without breaking either child heap. Processing parents in reverse index order eventually repairs the whole tree.
 * Complexity: O(n) time, because most nodes have short downward paths; O(n) returned copy and O(1) working space.
 */
public final class Q301BuildMinHeap { // One class contains this question, its answer, and reproducible checks.

    public static String solve(int[] input) { // Expose the answer as a reusable method as well as a runnable program.
        int[] a=input.clone(); // Heap construction should not modify the caller's input.
        for(int root=a.length/2-1;root>=0;root--) { // Leaves are already heaps; repair internal nodes bottom-up.
            int parent=root; // Follow the one branch whose parent order may be violated.
            while(parent<a.length/2) { // A node before length/2 has at least a left child.
                int child=2*parent+1; // Begin with the left child as the smaller-child candidate.
                if(child+1<a.length&&a[child+1]<a[child]) child++; // Swapping with the smaller child preserves both child comparisons.
                if(a[parent]<=a[child]) break; // A correctly ordered parent completes this subtree's repair.
                int saved=a[parent]; a[parent]=a[child]; a[child]=saved; // Move the smaller child upward and the displaced parent downward.
                parent=child; // Only the displaced value's new branch can still violate the invariant.
            }
        }
        return Arrays.toString(a); // The array encodes a complete tree with every parent no larger than its children.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(new int[]{5,3,8,1,2})); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(new int[]{5,3,8,1,2}), "[1, 2, 8, 3, 5]"); // Repair several internal nodes
        Check.equal(solve(new int[]{}), "[]"); // Empty heap
        Check.equal(solve(new int[]{7}), "[7]"); // Single-node heap
    }
}
