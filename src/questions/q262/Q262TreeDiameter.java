package questions.q262; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q262: Find tree diameter measured in edges.
 * Contract: A null root denotes an empty tree. Values are int; aggregate results use long where needed.
 * Why: Every simple tree path has a highest node. At that node its length is left height plus right height; maximizing those candidates covers all paths.
 * Complexity: O(n) time; O(h) recursion.
 */
public final class Q262TreeDiameter { // One class contains this question, its answer, and reproducible checks.

    static final class Node { // A node models one value and its two possible child links.
        int value; Node left, right; // Child references encode hierarchy; null means no child.
        Node(int value) { this.value = value; } // A fresh node starts as a leaf.
        Node(int value, Node left, Node right) { this.value=value; this.left=left; this.right=right; } // Connect a complete local subtree.
    }
    static Node sample() { // Build a fresh tree so mutation in one demonstration cannot affect another.
        return new Node(4,new Node(2,new Node(1),new Node(3)),new Node(6,new Node(5),new Node(7))); // A seven-node balanced BST also supplies a general binary-tree example.
    }

    static int height(Node n,int[] best) { // Return node-height while updating edge-count diameter.
        if(n==null) return 0; // Missing children contribute zero edges through the parent.
        int a=height(n.left,best),b=height(n.right,best); // Heights summarize the longest downward paths.
        best[0]=Math.max(best[0],a+b); // Joining child paths through this node creates the local diameter candidate.
        return 1+Math.max(a,b); // A parent can extend only one of these downward paths.
    }
    public static int solve(Node root) { // Expose the answer as a reusable method as well as a runnable program.
        int[] best={0}; // Mutable holder lets each subtree contribute a candidate path.
        height(root,best); // A postorder pass knows both child heights before scoring a path.
        return best[0]; // The largest path through any node is the tree diameter.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(sample())); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(sample()), 4); // Balanced seven-node input
        Check.equal(solve(null), 0); // Empty tree
        Check.equal(solve(new Node(9)), 0); // Single-node tree
    }
}
