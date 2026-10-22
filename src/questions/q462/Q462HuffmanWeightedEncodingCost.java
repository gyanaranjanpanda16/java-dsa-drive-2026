package questions.q462;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q462: Huffman Weighted Encoding Cost.
 * Contract: Weighted prefix-code path length; ignore zero-frequency symbols; a single symbol has zero-bit depth in this mathematical model.
 * Why it works: In an optimal prefix tree the two least frequent symbols can be deepest siblings. Merging them reduces the problem while adding exactly their combined weight, yielding an exchange-based optimal greedy recursion.
 * Cost: O(n log n) time and O(n) space; overflow throws.
 */
public final class Q462HuffmanWeightedEncodingCost { // Keep this question independently runnable and testable.
    private Q462HuffmanWeightedEncodingCost() { } // No object state is needed by this standalone exercise.
    public static long cost(int[] frequency) { // Expose the operation for an independent caller and the executable checks below.
        PriorityQueue<Long> heap=new PriorityQueue<>(); // Repeatedly retrieve the two least frequent current subtrees.
        for(int f:frequency) { if(f<0) throw new IllegalArgumentException("Nonnegative frequencies"); if(f>0) heap.offer((long)f); } // Zero-frequency symbols need no representation in this cost model.
        long cost=0; // Each merge adds one code bit to every symbol beneath the merged node.
        while(heap.size()>1) { // Stop when all used symbols belong to one prefix-code tree.
            long merged=Math.addExact(heap.poll(),heap.poll()); // Combine the two lowest-weight subtrees.
            cost=Math.addExact(cost,merged); heap.offer(merged); // Add their depth increase and return the merged subtree to future decisions.
        }
        return cost; // Summed merge weights equal total frequency times codeword depth.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(cost(new int[]{5,9,12,13,16,45})); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(cost(new int[]{})==0, "Q462 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(cost(new int[]{7})==0, "Q462 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(cost(new int[]{1,1,1})==5, "Q462 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
