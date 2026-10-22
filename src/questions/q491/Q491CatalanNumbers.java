package questions.q491; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Count binary-tree shapes with exact Catalan numbers.
 * Contract: Return the nth Catalan number for0<=n<=500, counting ordered binary-tree shapes with n nodes, including the empty shape for n=0.
 * Why: Choose how many nodes belong to the left subtree; the rest belong to the right. Their independent shape counts multiply, and summing over all possible splits covers every root shape exactly once. The same sequence counts balanced-parenthesis shapes and several other recursive structures.
 * Cost: O(n²) BigInteger multiply/add operations and O(n) stored BigIntegers; actual bit costs grow with the counts.
 */
public final class Q491CatalanNumbers { // Keep the algorithm, demonstration, and boundary checks together.
    private Q491CatalanNumbers() { } // Only static entry points are needed for this exercise.
    public static BigInteger solve(int n) { // Keep exponentially growing combinatorial counts exact.
        if(n<0||n>500) throw new IllegalArgumentException("require zero to five hundred"); // Bound this quadratic teaching implementation.
        BigInteger[] count=new BigInteger[n+1]; count[0]=BigInteger.ONE; // One empty subtree is required for multiplicative combinations.
        for(int nodes=1;nodes<=n;nodes++) { // Smaller subtree counts are ready before larger shapes are constructed.
            count[nodes]=BigInteger.ZERO; // Sum disjoint possibilities for the left-subtree size.
            for(int left=0;left<nodes;left++) count[nodes]=count[nodes].add(count[left].multiply(count[nodes-1-left])); // Reserve one node for the root and combine both subtrees independently.
        }
        return count[n]; // The recurrence enumerates shapes, not key permutations or labeled trees.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(3))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(3), BigInteger.valueOf(5)); // All three root splits contribute.
        Check.equal(solve(0), BigInteger.ONE); // The empty tree is one shape.
        Check.equal(solve(20), new BigInteger("6564120420")); // The count exceeds int.
    }
}
