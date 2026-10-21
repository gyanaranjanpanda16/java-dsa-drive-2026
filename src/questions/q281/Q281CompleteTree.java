package questions.q281; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q281: Check whether a binary tree is complete.
 * Contract: A null root denotes an empty tree. Values are int; aggregate results use long where needed.
 * Why: Completeness means occupied level-order positions form a prefix. Recording the first gap and rejecting any later child is exactly this definition.
 * Complexity: O(n) time; O(w) queue.
 */
public final class Q281CompleteTree { // One class contains this question, its answer, and reproducible checks.

    static final class Node { // A node models one value and its two possible child links.
        int value; Node left, right; // Child references encode hierarchy; null means no child.
        Node(int value) { this.value = value; } // A fresh node starts as a leaf.
        Node(int value, Node left, Node right) { this.value=value; this.left=left; this.right=right; } // Connect a complete local subtree.
    }
    static Node sample() { // Build a fresh tree so mutation in one demonstration cannot affect another.
        return new Node(4,new Node(2,new Node(1),new Node(3)),new Node(6,new Node(5),new Node(7))); // A seven-node balanced BST also supplies a general binary-tree example.
    }

    public static boolean solve(Node root) { // Expose the answer as a reusable method as well as a runnable program.
        if(root==null) return true; // Empty trees have no missing-before-present violation.
        Deque<Node> q=new ArrayDeque<>(); q.add(root); boolean gap=false; // Once a missing child appears, no later child may exist.
        while(!q.isEmpty()) { // BFS inspects positions from left to right on each level.
            Node n=q.remove(); // Process the next existing parent in positional order.
            for(Node child:new Node[]{n.left,n.right}) { // Left positions must precede right positions.
                if(child==null) gap=true; // Remember the earliest missing position.
                else { if(gap) return false; q.add(child); } // Any child after a gap violates completeness.
            }
        }
        return true; // All existing positions occurred before the first gap.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(sample())); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(sample()), true); // Full tree is complete
        Check.equal(solve(new Node(1,null,new Node(2))), false); // Right child after missing left child
        Check.equal(solve(null), true); // Empty tree
    }
}
