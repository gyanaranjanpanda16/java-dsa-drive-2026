package questions.q296; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q296: Simulate repeatedly smashing the two heaviest stones.
 * Contract: Nonnegative int weights; input is not changed.
 * Why: A max-heap implements the required choice rule directly. After removing the two maxima, reinserting only their positive difference makes the heap exactly the next simulation state.
 * Complexity: O(n log(n+1)) time and O(n) space.
 */
public final class Q296LastStone { // One class contains this question, its answer, and reproducible checks.

    public static int solve(int[] stones) { // Expose the answer as a reusable method as well as a runnable program.
        PriorityQueue<Integer> heap=new PriorityQueue<>(Comparator.reverseOrder()); // The next two removals must be the heaviest stones.
        for(int stone:stones) { // Load each physical stone as a separate occurrence.
            if(stone<0) throw new IllegalArgumentException("negative stone"); // Negative weights do not model the problem.
            heap.offer(stone); // Preserve equal weights rather than collapsing them in a set.
        }
        while(heap.size()>1) { // A smash requires two remaining stones.
            int largest=heap.poll(),second=heap.poll(); // Priority ordering guarantees largest>=second.
            if(largest!=second) heap.offer(largest-second); // Equal stones disappear; otherwise their difference survives.
        }
        return heap.isEmpty()?0:heap.peek(); // No remaining stone has weight zero by convention.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(new int[]{2,7,4,1,8,1})); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(new int[]{2,7,4,1,8,1}), 1); // Several smashes
        Check.equal(solve(new int[]{3,3}), 0); // Equal stones disappear
        Check.equal(solve(new int[]{}), 0); // Empty input
    }
}
