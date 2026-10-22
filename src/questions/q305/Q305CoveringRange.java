package questions.q305; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q305: Find the smallest range covering every sorted feed.
 * Contract: Rows are non-null and sorted nondecreasing; [] means no covering range; equal-width ties choose the earlier left endpoint.
 * Why: Each frontier holds one value from every feed. Advancing a representative other than the minimum cannot raise the lower endpoint and may only raise the maximum, so advancing the minimum is the only useful next choice. Enumerating these frontiers finds the optimum.
 * Complexity: O(N log(k+1)) time and O(k) working space for k feeds and N total values.
 */
public final class Q305CoveringRange { // One class contains this question, its answer, and reproducible checks.

    public static String solve(int[][] feeds) { // Expose the answer as a reusable method as well as a runnable program.
        if(feeds.length==0) return "[]"; // There is no required feed to cover.
        PriorityQueue<int[]> heap=new PriorityQueue<>(Comparator.comparingInt(c->feeds[c[0]][c[1]])); // The root identifies the smallest current representative.
        int maximum=Integer.MIN_VALUE; // Track the largest representative across all feeds.
        for(int i=0;i<feeds.length;i++) { // Initialize one representative per required feed.
            if(feeds[i].length==0) return "[]"; // An empty feed makes complete coverage impossible.
            heap.offer(new int[]{i,0}); maximum=Math.max(maximum,feeds[i][0]); // The initial frontier covers every feed.
        }
        int left=feeds[heap.peek()[0]][0],right=maximum; // Start with a real covering interval.
        while(true) { // Advance the minimum until one required feed is exhausted.
            int[] c=heap.poll(); int minimum=feeds[c[0]][c[1]]; // Only advancing the minimum can shrink the current range.
            if((long)maximum-minimum<(long)right-left) { left=minimum; right=maximum; } // Keep the narrowest range; earlier left endpoints win width ties.
            if(++c[1]==feeds[c[0]].length) break; // No later frontier can cover this exhausted feed.
            maximum=Math.max(maximum,feeds[c[0]][c[1]]); heap.offer(c); // Replace only the consumed representative and preserve complete coverage.
        }
        return "["+left+", "+right+"]"; // Return inclusive endpoints of an optimal covering range.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(new int[][]{{4,10,15,24,26},{0,9,12,20},{5,18,22,30}})); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(new int[][]{{4,10,15,24,26},{0,9,12,20},{5,18,22,30}}), "[20, 24]"); // Three sorted feeds
        Check.equal(solve(new int[][]{{1,2},{2,3}}), "[2, 2]"); // A shared value gives zero width
        Check.equal(solve(new int[][]{{},{1}}), "[]"); // Empty required feed
    }
}
