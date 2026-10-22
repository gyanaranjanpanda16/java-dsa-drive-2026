package questions.q356; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Maximize the unsigned XOR of two array values.
 * Contract: Return the largest unsigned32-bit XOR of two distinct array positions as a long; return0 when fewer than two positions exist.
 * Why: At each bit from most to least significant, prefer the opposite stored bit because its contribution outweighs every lower bit combined. Query only previously inserted values to guarantee distinct indices. This is a bitwise optimization technique for encoded identifiers and numeric matching.
 * Cost: O(32n) time and O(32n) worst-case nodes.
 */
public final class Q356MaximumPairXor { // Keep the algorithm, demonstration, and boundary checks together.
    private Q356MaximumPairXor() { } // Only static entry points are needed for this exercise.
    private static final class Node { // Each edge fixes one bit of a stored32-bit value.
        final Node[] child=new Node[2]; // Binary branching avoids hash lookup for bit keys.
    }
    public static long solve(int[] values) { // Long can represent every unsigned32-bit XOR result.
        Node root=new Node(); long best=0; // No pair exists before the first insertion.
        for(int i=0;i<values.length;i++) { // Compare the current value only with earlier positions.
            if(i>0) { // The trie is nonempty exactly after at least one value was inserted.
                Node node=root; long xor=0; // Build the optimal XOR from high bits to low bits.
                for(int bit=31;bit>=0;bit--) { // A higher bit dominates all possible lower-bit differences.
                    int digit=(values[i]>>>bit)&1,preferred=digit^1; // Opposite bits contribute one to XOR.
                    if(node.child[preferred]!=null) { xor|=1L<<bit; node=node.child[preferred]; } // Take a profitable branch whenever it exists.
                    else node=node.child[digit]; // Some stored value always continues along the remaining branch.
                }
                best=Math.max(best,xor); // Compare unsigned magnitudes represented as nonnegative longs.
            }
            Node node=root; // Insert the current value after its query to prevent self-pairing.
            for(int bit=31;bit>=0;bit--) { int digit=(values[i]>>>bit)&1; if(node.child[digit]==null) node.child[digit]=new Node(); node=node.child[digit]; } // Share identical bit prefixes.
        }
        return best; // Every pair was eligible when its later member was queried.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new int[]{3,10,5,25,2,8}))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new int[]{3,10,5,25,2,8}), 28L); // A nonadjacent pair maximizes XOR.
        Check.equal(solve(new int[]{-1,0}), 4294967295L); // All32 unsigned bits are counted.
        Check.equal(solve(new int[]{7}), 0L); // No pair of distinct indices.
    }
}
