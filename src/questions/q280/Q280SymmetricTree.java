package questions.q280; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q280: Check mirror symmetry around the root.
 * Contract: A null root denotes an empty tree. Values are int; aggregate results use long where needed.
 * Why: Symmetry requires equal opposing values and recursively matching outer and inner child pairs. Comparing ordinary left-to-left positions would test equality rather than reflection.
 * Complexity: O(n) time; O(h) recursion.
 */
public final class Q280SymmetricTree { // One class contains this question, its answer, and reproducible checks.

    static final class Node { // A node models one value and its two possible child links.
        int value; Node left, right; // Child references encode hierarchy; null means no child.
        Node(int value) { this.value = value; } // A fresh node starts as a leaf.
        Node(int value, Node left, Node right) { this.value=value; this.left=left; this.right=right; } // Connect a complete local subtree.
    }
    static Node sample() { // Build a fresh tree so mutation in one demonstration cannot affect another.
        return new Node(4,new Node(2,new Node(1),new Node(3)),new Node(6,new Node(5),new Node(7))); // A seven-node balanced BST also supplies a general binary-tree example.
    }

    static boolean same(Node a,Node b) { // Pair positions that should reflect one another.
        if(a==null||b==null) return a==b; // Symmetry fails when only one reflected position exists.
        return a.value==b.value&&same(a.left,b.right)&&same(a.right,b.left); // Compare values and both cross-paired branches.
    }
    public static boolean solve(Node root) { // Expose the answer as a reusable method as well as a runnable program.
        return root==null||same(root.left,root.right); // Empty trees are symmetric; otherwise compare mirrored child positions.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(new Node(1,new Node(2),new Node(2)))); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(new Node(1,new Node(2),new Node(2))), true); // Equal mirrored children
        Check.equal(solve(sample()), false); // Unequal opposing values
        Check.equal(solve(null), true); // Empty tree
    }
}
