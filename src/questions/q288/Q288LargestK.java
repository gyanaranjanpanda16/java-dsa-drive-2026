package questions.q288; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q288: Return the k largest values in descending order.
 * Contract: 0<=k<=length; input is not modified.
 * Why: A bounded min-heap retains the k largest occurrences. Repeated polling sorts those candidates ascending; reversing only the k outputs avoids sorting the entire input.
 * Complexity: O(n log(k+1)+k log(k+1)) time; O(k) auxiliary/output space.
 */
public final class Q288LargestK { // One class contains this question, its answer, and reproducible checks.

    public static String solve(int[] a,int k) { // Expose the answer as a reusable method as well as a runnable program.
        if(k<0||k>a.length) throw new IllegalArgumentException("invalid count"); // Selecting zero values is allowed.
        PriorityQueue<Integer> heap=new PriorityQueue<>(); // Keep the current top-k boundary at the root.
        for(int value:a) { // Every input occurrence remains eligible independently.
            heap.offer(value); // Add the next candidate before trimming excess capacity.
            if(heap.size()>k) heap.poll(); // Remove the least useful retained occurrence.
        }
        List<Integer> result=new ArrayList<>(); // Collect heap removals in a predictable order.
        while(!heap.isEmpty()) result.add(heap.poll()); // Polling emits ascending order, unlike heap iteration.
        Collections.reverse(result); // Reverse once to satisfy descending presentation.
        return result.toString(); // Expose the complete ordered selection.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(new int[]{4,1,9,3,9},3)); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(new int[]{4,1,9,3,9},3), "[9, 9, 4]"); // Top three with duplicates
        Check.equal(solve(new int[]{1,2},0), "[]"); // Select nothing
        Check.equal(solve(new int[]{-2,-5},2), "[-2, -5]"); // Select all negative values
    }
}
