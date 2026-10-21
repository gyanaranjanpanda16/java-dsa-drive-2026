package questions.q277; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q277: Sum BST keys in an inclusive interval.
 * Contract: Input is a valid BST. Both interval endpoints are inclusive; low>high denotes an empty interval.
 * Why: BST ordering rules out whole subtrees when the current value is outside the query interval. Included subtrees are still partitioned without overlap.
 * Complexity: O(n) worst-case time, often pruned; O(h) recursion.
 */
public final class Q277BstRangeSum { // One class contains this question, its answer, and reproducible checks.

    static final class Node { // A node models one value and its two possible child links.
        int value; Node left, right; // Child references encode hierarchy; null means no child.
        Node(int value) { this.value = value; } // A fresh node starts as a leaf.
        Node(int value, Node left, Node right) { this.value=value; this.left=left; this.right=right; } // Connect a complete local subtree.
    }
    static Node sample() { // Build a fresh tree so mutation in one demonstration cannot affect another.
        return new Node(4,new Node(2,new Node(1),new Node(3)),new Node(6,new Node(5),new Node(7))); // A seven-node balanced BST also supplies a general binary-tree example.
    }

    public static long solve(Node root,int low,int high) { // Expose the answer as a reusable method as well as a runnable program.
        if(root==null||low>high) return 0; // Empty trees or empty intervals contribute nothing.
        if(root.value<low) return solve(root.right,low,high); // All left keys are even smaller and can be pruned.
        if(root.value>high) return solve(root.left,low,high); // All right keys are even larger and can be pruned.
        return root.value+solve(root.left,low,high)+solve(root.right,low,high); // Include the admissible node and search both remaining sides.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(sample(),3,6)); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(sample(),3,6), 18L); // Inclusive keys 3,4,5,6
        Check.equal(solve(sample(),8,9), 0L); // Disjoint interval
        Check.equal(solve(null,1,7), 0L); // Empty tree
    }
}
