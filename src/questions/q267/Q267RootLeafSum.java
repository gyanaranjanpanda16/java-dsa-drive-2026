package questions.q267; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q267: Check whether a root-to-leaf path has a target sum.
 * Contract: Node values and path sums fit long; target subtraction must fit long. Only complete root-to-leaf paths count.
 * Why: A valid path chooses exactly one child at each internal node. Subtracting the current value reduces the target to precisely what the remaining suffix must contribute.
 * Complexity: O(n) time; O(h) stack.
 */
public final class Q267RootLeafSum { // One class contains this question, its answer, and reproducible checks.

    static final class Node { // A node models one value and its two possible child links.
        int value; Node left, right; // Child references encode hierarchy; null means no child.
        Node(int value) { this.value = value; } // A fresh node starts as a leaf.
        Node(int value, Node left, Node right) { this.value=value; this.left=left; this.right=right; } // Connect a complete local subtree.
    }
    static Node sample() { // Build a fresh tree so mutation in one demonstration cannot affect another.
        return new Node(4,new Node(2,new Node(1),new Node(3)),new Node(6,new Node(5),new Node(7))); // A seven-node balanced BST also supplies a general binary-tree example.
    }

    public static boolean solve(Node root,long target) { // Expose the answer as a reusable method as well as a runnable program.
        if(root==null) return false; // A missing tree has no root-to-leaf path.
        if(root.left==null&&root.right==null) return target==root.value; // Only a leaf may finish a valid path.
        return solve(root.left,target-root.value)||solve(root.right,target-root.value); // Remove the current value from the remaining requirement.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(sample(),7)); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(sample(),7), true); // Path 4→2→1 totals seven
        Check.equal(solve(sample(),4), false); // Internal-node prefix is not a leaf path
        Check.equal(solve(null,0), false); // Empty tree has no path
    }
}
