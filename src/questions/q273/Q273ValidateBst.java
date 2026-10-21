package questions.q273; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q273: Validate BST ordering against every ancestor.
 * Contract: A null root denotes an empty tree. Values are int; aggregate results use long where needed.
 * Why: A node must satisfy bounds inherited from all ancestors, not just its parent. Narrowing the upper bound on the left and lower bound on the right enforces the full BST invariant.
 * Complexity: O(n) time; O(h) stack.
 */
public final class Q273ValidateBst { // One class contains this question, its answer, and reproducible checks.

    static final class Node { // A node models one value and its two possible child links.
        int value; Node left, right; // Child references encode hierarchy; null means no child.
        Node(int value) { this.value = value; } // A fresh node starts as a leaf.
        Node(int value, Node left, Node right) { this.value=value; this.left=left; this.right=right; } // Connect a complete local subtree.
    }
    static Node sample() { // Build a fresh tree so mutation in one demonstration cannot affect another.
        return new Node(4,new Node(2,new Node(1),new Node(3)),new Node(6,new Node(5),new Node(7))); // A seven-node balanced BST also supplies a general binary-tree example.
    }

    static boolean valid(Node n,long low,long high) { // Bounds are exclusive because duplicate keys are disallowed.
        if(n==null) return true; // Empty subtrees cannot violate ordering.
        if(n.value<=low||n.value>=high) return false; // Reject an ancestor-bound violation immediately.
        return valid(n.left,low,n.value)&&valid(n.right,n.value,high); // Pass the appropriate stricter bound to each side.
    }
    public static boolean solve(Node root) { // Expose the answer as a reusable method as well as a runnable program.
        return valid(root,Long.MIN_VALUE,Long.MAX_VALUE); // Wide initial bounds allow every int value.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(sample())); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(sample()), true); // Valid balanced BST
        Check.equal(solve(new Node(5,new Node(2,null,new Node(8)),null)), false); // Descendant violates root upper bound
        Check.equal(solve(null), true); // Empty BST
    }
}
