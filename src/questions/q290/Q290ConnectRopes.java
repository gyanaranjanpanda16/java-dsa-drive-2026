package questions.q290; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q290: Minimize the cost of connecting ropes.
 * Contract: Nonnegative lengths; exact intermediate sums must fit long.
 * Why: A rope contributes its length once for every ancestor merge. In an optimal binary merge tree, two smallest weights can be siblings at greatest depth; exchanging larger leaves with them cannot increase cost. Contracting those siblings reduces to the same problem, justifying repeated smallest-pair merging.
 * Complexity: O(n log(n+1)) time; O(n) heap space.
 */
public final class Q290ConnectRopes { // One class contains this question, its answer, and reproducible checks.

    public static long solve(int[] lengths) { // Expose the answer as a reusable method as well as a runnable program.
        PriorityQueue<Long> heap=new PriorityQueue<>(); // Repeatedly expose the two shortest remaining ropes.
        for(int length:lengths) { // Validate and widen lengths before any sums are formed.
            if(length<0) throw new IllegalArgumentException("negative length"); // Physical rope lengths cannot be negative.
            heap.offer((long)length); // Preserve exact integer lengths in long arithmetic.
        }
        long cost=0; // No join has been performed initially.
        while(heap.size()>1) { // One remaining rope means all joins are finished.
            long joined=Math.addExact(heap.poll(),heap.poll()); // Merge the two cheapest candidates and detect overflow.
            cost=Math.addExact(cost,joined); // A merged rope's full length is the current join cost.
            heap.offer(joined); // The new rope participates in future joins.
        }
        return cost; // Empty and singleton inputs require no joins.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(new int[]{4,3,2,6})); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(new int[]{4,3,2,6}), 29L); // Several unequal ropes
        Check.equal(solve(new int[]{7}), 0L); // Already one rope
        Check.equal(solve(new int[]{}), 0L); // No ropes
    }
}
