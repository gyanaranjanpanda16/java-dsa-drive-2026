package questions.q278; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q278: Insert a missing BST key and show sorted contents.
 * Contract: Input is a valid BST. This method mutates it, and duplicate insertion is ignored.
 * Why: Comparisons choose the only child subtree in which a new key can belong. The recursive return reconnects the changed subtree while preserving all untouched ordering relations.
 * Complexity: O(h) insertion; O(n) display time/output and O(h) stack.
 */
public final class Q278BstInsert { // One class contains this question, its answer, and reproducible checks.

    static final class Node { // A node models one value and its two possible child links.
        int value; Node left, right; // Child references encode hierarchy; null means no child.
        Node(int value) { this.value = value; } // A fresh node starts as a leaf.
        Node(int value, Node left, Node right) { this.value=value; this.left=left; this.right=right; } // Connect a complete local subtree.
    }
    static Node sample() { // Build a fresh tree so mutation in one demonstration cannot affect another.
        return new Node(4,new Node(2,new Node(1),new Node(3)),new Node(6,new Node(5),new Node(7))); // A seven-node balanced BST also supplies a general binary-tree example.
    }

    static Node insert(Node n,int key) { // Return the root of the subtree after insertion.
        if(n==null) return new Node(key); // The first missing position is the insertion point.
        if(key<n.value) n.left=insert(n.left,key); // Smaller keys retain their left-side invariant.
        else if(key>n.value) n.right=insert(n.right,key); // Larger keys retain their right-side invariant.
        return n; // Equal keys cause no change, implementing set semantics.
    }
    static void inorder(Node n,List<Integer> values) { // Render the BST in sorted order.
        if(n==null) return; // Empty subtrees add no keys.
        inorder(n.left,values); values.add(n.value); inorder(n.right,values); // BST ordering makes this sequence sorted.
    }
    public static String solve(Node root,int key) { // Expose the answer as a reusable method as well as a runnable program.
        root=insert(root,key); // Inserting into an empty tree may replace the root reference.
        List<Integer> values=new ArrayList<>(); inorder(root,values); // Inorder verifies the ordering after mutation.
        return values.toString(); // Existing duplicate keys are stored only once.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(sample(),8)); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(sample(),8), "[1, 2, 3, 4, 5, 6, 7, 8]"); // Insert a larger missing key
        Check.equal(solve(null,2), "[2]"); // Insert into an empty tree
        Check.equal(solve(sample(),4), "[1, 2, 3, 4, 5, 6, 7]"); // Duplicate key does not grow the tree
    }
}
