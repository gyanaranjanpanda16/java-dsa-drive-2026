package questions.q261; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q261: Check whether every subtree is height-balanced.
 * Contract: A null root denotes an empty tree. Values are int; aggregate results use long where needed.
 * Why: The helper returns a valid height or -1. Rejecting a child sentinel before combining ensures a balanced ancestor cannot hide an unbalanced descendant.
 * Complexity: O(n) time; O(h) recursion.
 */
public final class Q261BalancedTree { // One class contains this question, its answer, and reproducible checks.

    static final class Node { // A node models one value and its two possible child links.
        int value; Node left, right; // Child references encode hierarchy; null means no child.
        Node(int value) { this.value = value; } // A fresh node starts as a leaf.
        Node(int value, Node left, Node right) { this.value=value; this.left=left; this.right=right; } // Connect a complete local subtree.
    }
    static Node sample() { // Build a fresh tree so mutation in one demonstration cannot affect another.
        return new Node(4,new Node(2,new Node(1),new Node(3)),new Node(6,new Node(5),new Node(7))); // A seven-node balanced BST also supplies a general binary-tree example.
    }

    static int height(Node n) { // Compute height and balance together to avoid rescanning subtrees.
        if(n==null) return 0; // Empty subtrees have equal zero heights.
        int left=height(n.left),right=height(n.right); // Obtain child heights exactly once.
        if(left<0||right<0||Math.abs(left-right)>1) return -1; // Any local or inherited violation invalidates this subtree.
        return 1+Math.max(left,right); // Only balanced subtrees return a real height.
    }
    public static boolean solve(Node root) { // Expose the answer as a reusable method as well as a runnable program.
        return height(root)>=0; // A negative sentinel propagates any unbalanced descendant.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(sample())); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(sample()), true); // Balanced tree
        Check.equal(solve(null), true); // Empty tree is balanced
        Check.equal(solve(new Node(1,new Node(2,new Node(3),null),null)), false); // Left chain violates balance
    }
}
