package questions.q293; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q293: Select the k most frequent integers.
 * Contract: k>=0; returns at most k distinct values; input remains unchanged.
 * Why: Counting converts repeated input into distinct candidates with priorities. A priority queue orders frequency descending and value ascending, so each poll is the best remaining candidate.
 * Complexity: O(n+u log(u+1)) time and O(u) storage for u distinct values.
 */
public final class Q293FrequentValues { // One class contains this question, its answer, and reproducible checks.

    public static String solve(int[] a,int k) { // Expose the answer as a reusable method as well as a runnable program.
        if(k<0) throw new IllegalArgumentException("negative count"); // Zero requested values is valid.
        Map<Integer,Integer> counts=new HashMap<>(); // Frequency is separate from the numeric value itself.
        for(int value:a) counts.merge(value,1,Integer::sum); // Accumulate one count per occurrence.
        PriorityQueue<Integer> heap=new PriorityQueue<>((x,y)->counts.get(x).equals(counts.get(y))?Integer.compare(x,y):Integer.compare(counts.get(y),counts.get(x))); // Higher frequency wins; smaller value breaks ties.
        heap.addAll(counts.keySet()); // Store each distinct candidate only once.
        List<Integer> result=new ArrayList<>(); // Produce a deterministic ranking.
        while(result.size()<k&&!heap.isEmpty()) result.add(heap.poll()); // Stop if fewer than k distinct values exist.
        return result.toString(); // Output is ranked by the documented priority.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(new int[]{3,3,1,1,2},2)); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(new int[]{3,3,1,1,2},2), "[1, 3]"); // Equal counts use numeric tie-break
        Check.equal(solve(new int[]{},2), "[]"); // No candidates
        Check.equal(solve(new int[]{9},0), "[]"); // Request zero results
    }
}
