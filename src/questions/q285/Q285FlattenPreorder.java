package questions.q285; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q285: Flatten a tree into a preorder right-child chain.
 * Contract: A null root denotes an empty tree. Values are int; aggregate results use long where needed.
 * Why: Flatten left and right subtrees first. Place the flattened left chain after the root, connect its tail to the original right chain, and clear the left link. Returning tails avoids repeated tail scans.
 * Complexity: O(n) time; O(h) recursion plus output.
 */
public final class Q285FlattenPreorder { // One class contains this question, its answer, and reproducible checks.

    static final class Node { // A node models one value and its two possible child links.
        int value; Node left, right; // Child references encode hierarchy; null means no child.
        Node(int value) { this.value = value; } // A fresh node starts as a leaf.
        Node(int value, Node left, Node right) { this.value=value; this.left=left; this.right=right; } // Connect a complete local subtree.
    }
    static Node sample() { // Build a fresh tree so mutation in one demonstration cannot affect another.
        return new Node(4,new Node(2,new Node(1),new Node(3)),new Node(6,new Node(5),new Node(7))); // A seven-node balanced BST also supplies a general binary-tree example.
    }

    static Node flatten(Node n) { // Return the tail of the flattened chain rooted at n.
        if(n==null) return null; // Empty subtrees have no tail.
        Node leftTail=flatten(n.left),rightTail=flatten(n.right); // Flatten both child chains before reconnecting them.
        if(leftTail!=null) { leftTail.right=n.right; n.right=n.left; n.left=null; } // Splice left preorder before right preorder without losing either chain.
        return rightTail!=null?rightTail:(leftTail!=null?leftTail:n); // The last existing subtree determines the combined tail.
    }
    public static String solve(Node root) { // Expose the answer as a reusable method as well as a runnable program.
        flatten(root); // Mutate the tree so every left child becomes null.
        List<Integer> out=new ArrayList<>(); // Display the resulting linked chain.
        while(root!=null) { out.add(root.value); root=root.right; } // Right links now follow preorder successor order.
        return out.toString(); // Each original node appears exactly once.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(sample())); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(sample()), "[4, 2, 1, 3, 6, 5, 7]"); // Balanced seven-node input
        Check.equal(solve(null), "[]"); // Empty tree
        Check.equal(solve(new Node(9)), "[9]"); // Single-node tree
    }
}
