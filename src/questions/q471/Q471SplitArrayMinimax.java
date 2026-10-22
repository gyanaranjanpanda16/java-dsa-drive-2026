package questions.q471; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Minimize the largest sum among k contiguous groups.
 * Contract: Split a nonempty nonnegative int array into exactly k nonempty contiguous groups,1<=k<=length. Return the smallest possible largest group sum as long.
 * Why: For a proposed maximum, greedily extend each group until the next value would exceed it. This minimizes the number of groups under that maximum. Feasibility is monotone; if at most k groups suffice, nonnegative groups can be split further without increasing their maximum. Useful for ordered workload partitioning.
 * Cost: O(n log(total+1)) time and O(1) space.
 */
public final class Q471SplitArrayMinimax { // Keep the algorithm, demonstration, and boundary checks together.
    private Q471SplitArrayMinimax() { } // Only static entry points are needed for this exercise.
    public static long solve(int[] a,int k) { // Preserve order and disallow empty output groups.
        if(k<1||k>a.length) throw new IllegalArgumentException("invalid group count"); // Exactly k nonempty groups require enough elements.
        long low=0,high=0; for(int value:a) { if(value<0) throw new IllegalArgumentException("nonnegative values required"); low=Math.max(low,value); high+=value; } // A maximum must hold every element and need not exceed the full sum.
        while(low<high) { // Keep the optimum inside an inclusive feasible-bound search interval.
            long limit=low+(high-low)/2,sum=0; int groups=1; // Test a proposed largest group total.
            for(int value:a) { if(sum+value>limit) { groups++; sum=0; } sum+=value; } // Delay starting a new group until it is necessary.
            if(groups<=k) high=limit; else low=limit+1; // Increasing the limit cannot require more groups.
        }
        return low; // The first feasible maximum is optimal.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new int[]{7,2,5,10,8},2))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new int[]{7,2,5,10,8},2), 18L); // Two groups with unequal lengths.
        Check.equal(solve(new int[]{1,2,3},3), 3L); // Every group contains one element.
        Check.equal(solve(new int[]{0,0},1), 0L); // All-zero workload.
    }
}
