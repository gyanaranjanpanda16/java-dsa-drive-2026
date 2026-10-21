package questions.q272; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q272: Search a value in a binary search tree.
 * Contract: Input is a valid BST; duplicates are not stored.
 * Why: The BST invariant places every smaller value to the left and every larger value to the right. Following that one admissible branch cannot discard the key.
 * Complexity: O(h) time and O(1) extra space; h can equal n in an unbalanced tree.
 */
public final class Q272BstSearch { // One class contains this question, its answer, and reproducible checks.

    static final class Node { // A node models one value and its two possible child links.
        int value; Node left, right; // Child references encode hierarchy; null means no child.
        Node(int value) { this.value = value; } // A fresh node starts as a leaf.
        Node(int value, Node left, Node right) { this.value=value; this.left=left; this.right=right; } // Connect a complete local subtree.
    }
    static Node sample() { // Build a fresh tree so mutation in one demonstration cannot affect another.
        return new Node(4,new Node(2,new Node(1),new Node(3)),new Node(6,new Node(5),new Node(7))); // A seven-node balanced BST also supplies a general binary-tree example.
    }

    public static boolean solve(Node root,int key) { // Expose the answer as a reusable method as well as a runnable program.
        while(root!=null) { // Each comparison discards one entire ordered subtree.
            if(root.value==key) return true; // Equality proves membership immediately.
            root=key<root.value?root.left:root.right; // Smaller keys can only occur on the left.
        }
        return false; // Reaching an absent child exhausts the only possible search path.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(sample(),5)); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(sample(),5), true); // Present interior key
        Check.equal(solve(sample(),8), false); // Absent key
        Check.equal(solve(null,1), false); // Empty BST
    }
}
