package questions.q266; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q266: Mirror a tree and return its inorder traversal.
 * Contract: A null root denotes an empty tree. Values are int; aggregate results use long where needed.
 * Why: Swapping children at every node reverses every left/right decision. Recursive mirroring handles descendants, so the whole shape is reflected, not just the root.
 * Complexity: O(n) time; O(h) stack plus output.
 */
public final class Q266MirrorTree { // One class contains this question, its answer, and reproducible checks.

    static final class Node { // A node models one value and its two possible child links.
        int value; Node left, right; // Child references encode hierarchy; null means no child.
        Node(int value) { this.value = value; } // A fresh node starts as a leaf.
        Node(int value, Node left, Node right) { this.value=value; this.left=left; this.right=right; } // Connect a complete local subtree.
    }
    static Node sample() { // Build a fresh tree so mutation in one demonstration cannot affect another.
        return new Node(4,new Node(2,new Node(1),new Node(3)),new Node(6,new Node(5),new Node(7))); // A seven-node balanced BST also supplies a general binary-tree example.
    }

    static void mirror(Node n) { // Reflect a subtree in place.
        if(n==null) return; // Empty subtrees are already mirrors of themselves.
        Node old=n.left; n.left=n.right; n.right=old; // A temporary reference prevents losing either child.
        mirror(n.left); mirror(n.right); // Reflect both newly assigned child subtrees too.
    }
    static void inorder(Node n,List<Integer> out) { // Render the changed tree without assuming BST order.
        if(n==null) return; // Stop at missing children.
        inorder(n.left,out); out.add(n.value); inorder(n.right,out); // Collect left-root-right values.
    }
    public static String solve(Node root) { // Expose the answer as a reusable method as well as a runnable program.
        mirror(root); // This operation deliberately mutates the caller's tree.
        List<Integer> out=new ArrayList<>(); inorder(root,out); // Display the result using a familiar traversal.
        return out.toString(); // The sample BST becomes descending after mirroring.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(sample())); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(sample()), "[7, 6, 5, 4, 3, 2, 1]"); // Balanced seven-node input
        Check.equal(solve(null), "[]"); // Empty tree
        Check.equal(solve(new Node(9)), "[9]"); // Single-node tree
    }
}
