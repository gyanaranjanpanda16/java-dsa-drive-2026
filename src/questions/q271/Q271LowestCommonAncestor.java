package questions.q271; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q271: Find the lowest common ancestor of two existing values.
 * Contract: Values are unique and BOTH requested values must exist; existence is a precondition, not fully validated.
 * Why: If targets appear in different child subtrees, the current node is their first meeting point. If both lie on one side, pass that side upward. Reaching a target makes it a candidate ancestor of the other.
 * Complexity: O(n) time; O(h) recursion.
 */
public final class Q271LowestCommonAncestor { // One class contains this question, its answer, and reproducible checks.

    static final class Node { // A node models one value and its two possible child links.
        int value; Node left, right; // Child references encode hierarchy; null means no child.
        Node(int value) { this.value = value; } // A fresh node starts as a leaf.
        Node(int value, Node left, Node right) { this.value=value; this.left=left; this.right=right; } // Connect a complete local subtree.
    }
    static Node sample() { // Build a fresh tree so mutation in one demonstration cannot affect another.
        return new Node(4,new Node(2,new Node(1),new Node(3)),new Node(6,new Node(5),new Node(7))); // A seven-node balanced BST also supplies a general binary-tree example.
    }

    static Node lca(Node n,int a,int b) { // Search both subtrees without relying on BST ordering.
        if(n==null||n.value==a||n.value==b) return n; // A target can itself be an ancestor.
        Node left=lca(n.left,a,b),right=lca(n.right,a,b); // Determine which target-containing branches exist.
        if(left!=null&&right!=null) return n; // Two sides meet for the first time at this node.
        return left!=null?left:right; // Propagate the only side that contains a target.
    }
    public static int solve(Node root,int a,int b) { // Expose the answer as a reusable method as well as a runnable program.
        Node answer=lca(root,a,b); // A split of the two target paths identifies the lowest shared ancestor.
        if(answer==null) throw new IllegalArgumentException("targets absent"); // A completely absent search cannot produce an ancestor.
        return answer.value; // Return the ancestor value under the unique-value contract.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(sample(),1,3)); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(sample(),1,3), 2); // Two targets in one subtree
        Check.equal(solve(sample(),1,7), 4); // Targets on opposite sides
        Check.equal(solve(new Node(9),9,9), 9); // Both targets are the root
    }
}
