package questions.q256; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q256: Count every node.
 * Contract: A null root denotes an empty tree. Values are int; aggregate results use long where needed.
 * Why: The root contributes one and its disjoint left and right subtrees contribute their counts.
 * Complexity: O(n) time; O(h) call-stack space.
 */
public final class Q256NodeCount { // One class contains this question, its answer, and reproducible checks.

    static final class Node { // A node models one value and its two possible child links.
        int value; Node left, right; // Child references encode hierarchy; null means no child.
        Node(int value) { this.value = value; } // A fresh node starts as a leaf.
        Node(int value, Node left, Node right) { this.value=value; this.left=left; this.right=right; } // Connect a complete local subtree.
    }
    static Node sample() { // Build a fresh tree so mutation in one demonstration cannot affect another.
        return new Node(4,new Node(2,new Node(1),new Node(3)),new Node(6,new Node(5),new Node(7))); // A seven-node balanced BST also supplies a general binary-tree example.
    }

    public static int solve(Node root) { // Expose the answer as a reusable method as well as a runnable program.
        if(root==null) return 0; // Supply the identity for an empty subtree.
        return 1+solve(root.left)+solve(root.right); // Combine the root with independent child answers.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(sample())); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(sample()), 7); // Seven-node tree
        Check.equal(solve(null), 0); // Empty tree identity
        Check.equal(solve(new Node(9)), 1); // Single node
    }
}
