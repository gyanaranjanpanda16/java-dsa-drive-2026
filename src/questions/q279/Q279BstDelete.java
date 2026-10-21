package questions.q279; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q279: Delete a BST key while preserving ordering.
 * Contract: Input is a valid unique-key BST. The tree is mutated; deleting an absent key is a no-op.
 * Why: A node with one child is replaced by that child. A node with two children uses the smallest right-subtree key as successor, then removes its original occurrence; this preserves both ordering boundaries.
 * Complexity: O(h) deletion; O(n) rendering and O(h) recursion.
 */
public final class Q279BstDelete { // One class contains this question, its answer, and reproducible checks.

    static final class Node { // A node models one value and its two possible child links.
        int value; Node left, right; // Child references encode hierarchy; null means no child.
        Node(int value) { this.value = value; } // A fresh node starts as a leaf.
        Node(int value, Node left, Node right) { this.value=value; this.left=left; this.right=right; } // Connect a complete local subtree.
    }
    static Node sample() { // Build a fresh tree so mutation in one demonstration cannot affect another.
        return new Node(4,new Node(2,new Node(1),new Node(3)),new Node(6,new Node(5),new Node(7))); // A seven-node balanced BST also supplies a general binary-tree example.
    }

    static Node remove(Node n,int key) { // Rebuild only the references on the search path.
        if(n==null) return null; // An absent key requires no structural change.
        if(key<n.value) n.left=remove(n.left,key); // Search the ordered left side.
        else if(key>n.value) n.right=remove(n.right,key); // Search the ordered right side.
        else { // The node itself must be removed or replaced.
            if(n.left==null) return n.right; if(n.right==null) return n.left; // A single child already satisfies all ancestor bounds.
            Node successor=n.right; while(successor.left!=null) successor=successor.left; // Find the smallest larger key.
            n.value=successor.value; n.right=remove(n.right,successor.value); // Move the successor value here and remove its duplicate below.
        }
        return n; // Return the still-valid root of this subtree.
    }
    static void inorder(Node n,List<Integer> out) { // Show the final ordered key set.
        if(n==null) return; // Stop at an empty child.
        inorder(n.left,out); out.add(n.value); inorder(n.right,out); // Collect sorted contents.
    }
    public static String solve(Node root,int key) { // Expose the answer as a reusable method as well as a runnable program.
        root=remove(root,key); // Root replacement handles deletion of the original root.
        List<Integer> out=new ArrayList<>(); inorder(root,out); // Sorted traversal exposes the remaining keys.
        return out.toString(); // Missing-key deletion leaves the original set unchanged.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(sample(),4)); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(sample(),4), "[1, 2, 3, 5, 6, 7]"); // Delete root with two children
        Check.equal(solve(new Node(9),9), "[]"); // Delete the only node
        Check.equal(solve(sample(),99), "[1, 2, 3, 4, 5, 6, 7]"); // Absent key
    }
}
