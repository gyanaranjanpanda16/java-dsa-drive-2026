package questions.q270; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q270: Find the maximum sum of a nonempty tree path.
 * Contract: A null root denotes an empty tree. Values are int; aggregate results use long where needed.
 * Why: A path turning at a node may use both positive child gains. A path returned to the parent may use only one branch, otherwise it would fork. Discard negative extensions because they decrease the sum.
 * Complexity: O(n) time; O(h) recursion.
 */
public final class Q270MaximumPathSum { // One class contains this question, its answer, and reproducible checks.

    static final class Node { // A node models one value and its two possible child links.
        int value; Node left, right; // Child references encode hierarchy; null means no child.
        Node(int value) { this.value = value; } // A fresh node starts as a leaf.
        Node(int value, Node left, Node right) { this.value=value; this.left=left; this.right=right; } // Connect a complete local subtree.
    }
    static Node sample() { // Build a fresh tree so mutation in one demonstration cannot affect another.
        return new Node(4,new Node(2,new Node(1),new Node(3)),new Node(6,new Node(5),new Node(7))); // A seven-node balanced BST also supplies a general binary-tree example.
    }

    static long gain(Node n,long[] best) { // Return the best downward extension available to the parent.
        if(n==null) return 0; // An absent extension adds nothing.
        long a=Math.max(0,gain(n.left,best)),b=Math.max(0,gain(n.right,best)); // Skip harmful negative branches.
        best[0]=Math.max(best[0],n.value+a+b); // Complete a path whose highest node is n.
        return n.value+Math.max(a,b); // The parent can connect to only one branch below n.
    }
    public static long solve(Node root) { // Expose the answer as a reusable method as well as a runnable program.
        if(root==null) throw new IllegalArgumentException("nonempty tree required"); // No valid nonempty path exists in an empty tree.
        long[] best={Long.MIN_VALUE}; gain(root,best); // Negative-only trees must retain their largest negative node.
        return best[0]; // The best completed path may start and end anywhere.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(sample())); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(sample()), 22L); // Path 3→2→4→6→7
        Check.equal(solve(new Node(-9)), -9L); // Negative-only tree still chooses one node
        Check.equal(solve(new Node(2,new Node(-1),new Node(3))), 5L); // Discard negative extension
    }
}
