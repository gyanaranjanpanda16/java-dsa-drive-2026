package questions.q377; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Generate subsets without duplicate combinations.
 * Contract: Given int values, return all distinct subsets, including the empty subset. Sort each subset ascending and emit subsets in depth-first order. Equal input values are separate available copies, but equal output combinations appear only once. Input is not mutated; output grows exponentially.
 * Why: Generating every position subset and deduplicating later wastes memory. Sort a copy first, then skip equal choices at the same recursion depth. Equal values are still allowed at deeper levels, so [2,2] is retained when two copies exist. Each recursion state is one already valid subset; extending only with later positions prevents permutations of the same choice. This models selecting distinct inventory combinations when items have repeated values.
 * Cost: O(n log n + n*2^n) worst-case time, O(n) working space plus O(n*2^n) output. Duplicate skipping can reduce the actual output greatly.
 */
public final class Q377UniqueSubsets { // Keep the algorithm, demonstration, and boundary checks together.
    private Q377UniqueSubsets() { } // Only static entry points are needed for this exercise.
    public static List<List<Integer>> solve(int[] values) { // Return combinations rather than subsets of position identifiers.
        int[] sorted=values.clone(); Arrays.sort(sorted); // Adjacent equal values make duplicate choices detectable.
        List<List<Integer>> output=new ArrayList<>(); // Each stored subset must be an independent snapshot.
        subsets(sorted,0,new ArrayList<>(),output); // Initially no position has been chosen.
        return output; // Even empty input has the empty subset.
    }
    private static void subsets(int[] values,int start,List<Integer> path,List<List<Integer>> output) { // Only positions at or after start remain available.
        output.add(new ArrayList<>(path)); // Copy before later backtracking changes the path.
        for(int i=start;i<values.length;i++) { // Try each possible next value.
            if(i>start&&values[i]==values[i-1]) continue; // Skip equivalent sibling branches, not repeated values at deeper levels.
            path.add(values[i]); // Choose one available occurrence.
            subsets(values,i+1,path,output); // This occurrence cannot be selected again.
            path.remove(path.size()-1); // Restore the parent's subset for the next choice.
        }
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new int[]{1,2,2}))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new int[]{1,2,2}), List.of(List.of(),List.of(1),List.of(1,2),List.of(1,2,2),List.of(2),List.of(2,2))); // Duplicate input does not duplicate output.
        Check.equal(solve(new int[]{}), List.of(List.of())); // The empty subset is still present.
        Check.equal(solve(new int[]{2,2}), List.of(List.of(),List.of(2),List.of(2,2))); // Both copies may be selected.
    }
}
