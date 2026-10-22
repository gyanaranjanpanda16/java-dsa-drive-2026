package questions.q218; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Count subarrays whose sum is divisible by k.
 * Contract: Return the number of nonempty contiguous subarrays with sum divisible by positive k, allowing negative int elements.
 * Why: Two prefix sums differ by a multiple of k exactly when their normalized remainders agree. Count earlier equal remainders before inserting the current prefix. This supports cyclic balance and periodic grouping without enumerating every interval.
 * Cost: Expected O(n) time and O(min(n,k)) space.
 */
public final class Q218DivisibleSubarrays { // Keep the algorithm, demonstration, and boundary checks together.
    private Q218DivisibleSubarrays() { } // Only static entry points are needed for this exercise.
    public static long solve(int[] a,int k) { // Count intervals, which may exceed int range.
        if(k<=0) throw new IllegalArgumentException("positive modulus required"); // Remainders need a positive normalization base.
        Map<Integer,Long> seen=new HashMap<>(); seen.put(0,1L); // The empty prefix allows intervals beginning at index zero.
        long answer=0; int residue=0; // Only the prefix residue, not its entire sum, is needed.
        for(int value:a) { // Extend the prefix one element at a time.
            residue=Math.floorMod((long)residue+value,k); // Normalize negative additions safely into 0..k-1.
            answer+=seen.getOrDefault(residue,0L); // Each earlier equal residue defines one valid nonempty interval.
            seen.merge(residue,1L,Long::sum); // Register the current prefix only after counting its predecessors.
        }
        return answer; // Every interval is counted at its unique ending position.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new int[]{4,5,0,-2,-3,1},5))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new int[]{4,5,0,-2,-3,1},5), 7L); // Negative elements and repeated residues.
        Check.equal(solve(new int[]{},3), 0L); // No nonempty intervals.
        Check.equal(solve(new int[]{0,0},2), 3L); // All three intervals have zero sum.
    }
}
