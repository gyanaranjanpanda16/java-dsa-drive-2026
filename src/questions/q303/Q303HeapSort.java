package questions.q303; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q303: Sort an integer array using an explicit heap.
 * Contract: Input is non-null; equal values may change relative identity because heapsort is not stable.
 * Why: A max-heap exposes the largest unsorted value at its root. Swapping it to the final available slot fixes that position forever; sifting the replacement down restores the invariant on the shorter prefix.
 * Complexity: O(n log(n+1)) time; O(n) result copy and O(1) additional working space.
 */
public final class Q303HeapSort { // One class contains this question, its answer, and reproducible checks.
    static void sink(int[] a,int parent,int size) { // Restore max-heap ordering below one possibly misplaced root.
        while(parent<size/2) { // Only internal nodes can violate ordering with a child.
            int child=2*parent+1; // Begin with the left child.
            if(child+1<size&&a[child+1]>a[child]) child++; // Promote the greater child so both child comparisons remain valid.
            if(a[parent]>=a[child]) return; // A correctly ordered root completes the repair.
            int saved=a[parent]; a[parent]=a[child]; a[child]=saved; // Exchange the misplaced value with its larger child.
            parent=child; // Continue along the only branch that may still violate heap order.
        }
    }
    public static String solve(int[] input) { // Expose the answer as a reusable method as well as a runnable program.
        int[] a=input.clone(); // Preserve the original while sorting the result storage.
        for(int root=a.length/2-1;root>=0;root--) sink(a,root,a.length); // Bottom-up repair creates a max-heap over the full array.
        for(int end=a.length-1;end>0;end--) { // Grow a sorted suffix by removing one maximum each iteration.
            int saved=a[0]; a[0]=a[end]; a[end]=saved; // Move the largest remaining value to its final position.
            sink(a,0,end); // Repair only the reduced unsorted prefix.
        }
        return Arrays.toString(a); // Successive maxima occupy the suffix in ascending final order.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(new int[]{4,1,3,2})); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(new int[]{4,1,3,2}), "[1, 2, 3, 4]"); // Unsorted input
        Check.equal(solve(new int[]{}), "[]"); // Empty array
        Check.equal(solve(new int[]{2,-1,2}), "[-1, 2, 2]"); // Negatives and duplicates
    }
}
