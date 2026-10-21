package questions.q387;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q387: Choose K Numbers.
 * Contract: Choose k distinct values from 1..n; n,k nonnegative and output small enough to enumerate.
 * Why it works: Strictly increasing choices represent each k-element subset exactly once. The upper loop bound prunes prefixes that cannot be completed.
 * Cost: O(k*C(n,k)) output-sensitive time and space, with O(k) stack.
 */
public final class Q387ChooseKNumbers { // Keep this question independently runnable and testable.
    private Q387ChooseKNumbers() { } // No object state is needed by this standalone exercise.
    public static List<List<Integer>> combinations(int n, int k) { // Expose the operation for an independent caller and the executable checks below.
        if(n<0 || k<0) throw new IllegalArgumentException("Nonnegative sizes"); // Reject meaningless selection sizes.
        List<List<Integer>> out=new ArrayList<>(); // Collect increasing selections only.
        choose(1,n,k,new ArrayList<>(),out); // Start from the smallest allowed number.
        return out; // Choosing more than n numbers naturally returns no selections.
    }
    private static void choose(int start,int n,int left,List<Integer> path,List<List<Integer>> out) { // Keep recursive state or a repeated sub-operation inside this solution.
        if(left==0) { out.add(new ArrayList<>(path)); return; } // A full selection must be copied before restoration.
        for(int v=start;v<=n-left+1;v++) { // Stop when too few larger numbers would remain.
            path.add(v); // Reserve v as the next increasing selected value.
            choose(v+1,n,left-1,path,out); // Larger-only continuation prevents duplicate orders.
            path.remove(path.size()-1); // Release v for the following candidate.
        }
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(combinations(4,2)); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(combinations(3,0).equals(List.of(List.of())), "Q387 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(combinations(2,3).isEmpty(), "Q387 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(combinations(4,2).size()==6, "Q387 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
