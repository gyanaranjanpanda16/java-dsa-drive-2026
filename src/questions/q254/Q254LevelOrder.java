package questions.q254; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q254: List values in breadth-first level order.
 * Contract: A null root denotes an empty tree. Values are int; aggregate results use long where needed.
 * Why: FIFO processes a level before its children because all existing queued nodes were discovered earlier. Each child is appended exactly once.
 * Complexity: O(n) time; O(w) queue and O(n) output, where w is maximum width.
 */
public final class Q254LevelOrder { // One class contains this question, its answer, and reproducible checks.

    static final class Node { // A node models one value and its two possible child links.
        int value; Node left, right; // Child references encode hierarchy; null means no child.
        Node(int value) { this.value = value; } // A fresh node starts as a leaf.
        Node(int value, Node left, Node right) { this.value=value; this.left=left; this.right=right; } // Connect a complete local subtree.
    }
    static Node sample() { // Build a fresh tree so mutation in one demonstration cannot affect another.
        return new Node(4,new Node(2,new Node(1),new Node(3)),new Node(6,new Node(5),new Node(7))); // A seven-node balanced BST also supplies a general binary-tree example.
    }

    public static String solve(Node root) { // Expose the answer as a reusable method as well as a runnable program.
        List<Integer> out=new ArrayList<>(); // Preserve the order in which nodes leave the queue.
        if(root==null) return out.toString(); // Empty trees contain no levels.
        Deque<Node> queue=new ArrayDeque<>(); queue.add(root); // FIFO begins with the root at depth zero.
        while(!queue.isEmpty()) { // Continue until every discovered node has been processed.
            Node n=queue.removeFirst(); out.add(n.value); // The oldest pending node has minimum pending depth.
            if(n.left!=null) queue.addLast(n.left); // Children join after all nodes already waiting at this level.
            if(n.right!=null) queue.addLast(n.right); // Left-before-right insertion defines a stable level order.
        }
        return out.toString(); // Return the completed visitation sequence.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(sample())); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(sample()), "[4, 2, 6, 1, 3, 5, 7]"); // Balanced seven-node input
        Check.equal(solve(null), "[]"); // Empty tree
        Check.equal(solve(new Node(9)), "[9]"); // Single-node tree
    }
}
