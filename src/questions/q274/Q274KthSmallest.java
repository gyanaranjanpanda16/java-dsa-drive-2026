package questions.q274; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q274: Find the kth smallest BST key.
 * Contract: Input is a valid BST with unique values; k is one-based and must not exceed node count.
 * Why: Inorder traversal of a BST is sorted. Stopping at the kth visit therefore returns the kth smallest key without sorting a separate collection.
 * Complexity: O(h+k) time until a valid rank; O(h) stack.
 */
public final class Q274KthSmallest { // One class contains this question, its answer, and reproducible checks.

    static final class Node { // A node models one value and its two possible child links.
        int value; Node left, right; // Child references encode hierarchy; null means no child.
        Node(int value) { this.value = value; } // A fresh node starts as a leaf.
        Node(int value, Node left, Node right) { this.value=value; this.left=left; this.right=right; } // Connect a complete local subtree.
    }
    static Node sample() { // Build a fresh tree so mutation in one demonstration cannot affect another.
        return new Node(4,new Node(2,new Node(1),new Node(3)),new Node(6,new Node(5),new Node(7))); // A seven-node balanced BST also supplies a general binary-tree example.
    }

    public static int solve(Node root,int k) { // Expose the answer as a reusable method as well as a runnable program.
        if(k<1) throw new IllegalArgumentException("positive k required"); // Rank is one-based.
        Deque<Node> stack=new ArrayDeque<>(); // Explicit frames avoid recursive inorder traversal.
        while(root!=null||!stack.isEmpty()) { // Continue while a left descent or deferred ancestor remains.
            while(root!=null) { stack.push(root); root=root.left; } // Descend to the smallest unvisited key.
            root=stack.pop(); if(--k==0) return root.value; // Each inorder visit advances the sorted rank once.
            root=root.right; // The next greater key lies in the right subtree or a deferred ancestor.
        }
        throw new IllegalArgumentException("k exceeds tree size"); // No kth key exists after exhausting the tree.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(sample(),3)); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(sample(),3), 3); // Third sorted key
        Check.equal(solve(sample(),7), 7); // Last sorted key
        Check.equal(solve(new Node(9),1), 9); // Singleton rank one
    }
}
