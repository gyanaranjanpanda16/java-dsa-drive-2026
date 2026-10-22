package questions.q381; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Select the kth lexicographic permutation.
 * Contract: Return the kth permutation of digits1 through n in lexicographic order. Require1<=n<=9 and1<=k<=n!. Ranking starts at1. Return a string of the digits.
 * Why: Generating and sorting n! permutations is unnecessary for selecting one rank. With m available digits, each possible first digit begins a block of (m-1)! permutations. Divide the zero-based rank by that block size to choose the next digit, then keep the remainder as the rank inside its block. This is positional ranking with factorial place values, useful for indexing arrangements without enumerating them.
 * Cost: O(n^2) time because removing a digit shifts the ArrayList, and O(n) storage. Factorials through9! fit int; the bound also keeps each symbol one character.
 */
public final class Q381KthPermutation { // Keep the algorithm, demonstration, and boundary checks together.
    private Q381KthPermutation() { } // Only static entry points are needed for this exercise.
    public static String solve(int n,int k) { // Select directly from factorial-sized blocks.
        if(n<1||n>9) throw new IllegalArgumentException("1..9 required"); // Keep factorial arithmetic and digit spelling unambiguous.
        int[] factorial=new int[n+1]; factorial[0]=1; // The empty suffix has one arrangement.
        List<Integer> available=new ArrayList<>(); // Maintain remaining digits in lexical order.
        for(int i=1;i<=n;i++) { factorial[i]=factorial[i-1]*i; available.add(i); } // Build factorials and the initial candidate list.
        if(k<1||k>factorial[n]) throw new IllegalArgumentException("rank outside permutations"); // A rank must identify an existing arrangement.
        StringBuilder answer=new StringBuilder(); int rank=k-1; // Zero-based ranks make division select the correct block.
        for(int remaining=n;remaining>0;remaining--) { // Fix one leading digit per iteration.
            int block=factorial[remaining-1]; // Every candidate digit owns the same number of suffixes.
            answer.append(available.remove(rank/block)); // Remove the selected digit so it cannot be reused.
            rank%=block; // Continue at the corresponding position within this candidate's suffix block.
        }
        return answer.toString(); // All n digits have been selected exactly once.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(3,4))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(3,4), "231"); // Rank inside a middle block.
        Check.equal(solve(1,1), "1"); // Smallest valid input.
        Check.equal(solve(3,6), "321"); // Last permutation.
    }
}
