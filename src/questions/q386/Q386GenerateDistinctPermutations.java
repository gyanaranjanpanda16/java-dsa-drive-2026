package questions.q386;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q386: Generate Distinct Permutations.
 * Contract: Distinct integers, small n; return all orderings without mutating input.
 * Why it works: Fixing the next position partitions permutations by which remaining value occupies it. Swapping back restores the same unresolved suffix for the next branch.
 * Cost: O(n*n!) time/output and O(n) stack besides the copied input.
 */
public final class Q386GenerateDistinctPermutations { // Keep this question independently runnable and testable.
    private Q386GenerateDistinctPermutations() { } // No object state is needed by this standalone exercise.
    public static List<List<Integer>> permutations(int[] a) { // Expose the operation for an independent caller and the executable checks below.
        List<List<Integer>> out = new ArrayList<>(); // Own a snapshot for every completed ordering.
        permute(a.clone(), 0, out); // Work on a copy so the input stays unchanged.
        return out; // Values must be distinct to guarantee unique output lists.
    }
    private static void permute(int[] a, int at, List<List<Integer>> out) { // Keep recursive state or a repeated sub-operation inside this solution.
        if (at == a.length) { List<Integer> row=new ArrayList<>(); for(int v:a) row.add(v); out.add(row); return; } // A fixed prefix of length n is a full permutation.
        for (int i=at;i<a.length;i++) { // Try each remaining value in the current slot.
            int t=a[at]; a[at]=a[i]; a[i]=t; // Commit the selected value to this prefix position.
            permute(a, at+1, out); // Arrange the remaining suffix independently.
            t=a[at]; a[at]=a[i]; a[i]=t; // Undo the choice before trying the next candidate.
        }
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(permutations(new int[]{1,2})); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(permutations(new int[]{}).equals(List.of(List.of())), "Q386 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(permutations(new int[]{7}).equals(List.of(List.of(7))), "Q386 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(permutations(new int[]{1,2,3}).size()==6, "Q386 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
