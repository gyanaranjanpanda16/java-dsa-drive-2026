package questions.q282; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q282: Find minimum root-to-leaf depth in nodes.
 * Contract: A null root denotes an empty tree. Values are int; aggregate results use long where needed.
 * Why: The minimum must range over real leaf paths. Treating a missing child as a depth-zero candidate would incorrectly stop early at one-child nodes.
 * Complexity: O(n) time; O(h) recursion.
 */
public final class Q282MinimumDepth { // One class contains this question, its answer, and reproducible checks.

    static final class Node { // A node models one value and its two possible child links.
        int value; Node left, right; // Child references encode hierarchy; null means no child.
        Node(int value) { this.value = value; } // A fresh node starts as a leaf.
        Node(int value, Node left, Node right) { this.value=value; this.left=left; this.right=right; } // Connect a complete local subtree.
    }
    static Node sample() { // Build a fresh tree so mutation in one demonstration cannot affect another.
        return new Node(4,new Node(2,new Node(1),new Node(3)),new Node(6,new Node(5),new Node(7))); // A seven-node balanced BST also supplies a general binary-tree example.
    }

    public static int solve(Node root) { // Expose the answer as a reusable method as well as a runnable program.
        if(root==null) return 0; // No path exists in an empty tree.
        if(root.left==null) return 1+solve(root.right); // A missing child is not a terminating leaf path.
        if(root.right==null) return 1+solve(root.left); // The existing branch must be followed.
        return 1+Math.min(solve(root.left),solve(root.right)); // With both branches present, choose the nearer leaf.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(sample())); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(sample()), 3); // All leaves at depth three
        Check.equal(solve(new Node(1,new Node(2),null)), 2); // One-child chain
        Check.equal(solve(null), 0); // Empty tree
    }
}
