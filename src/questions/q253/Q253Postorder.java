package questions.q253; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q253: List tree values in postorder.
 * Contract: A null root denotes an empty tree. Values are int; aggregate results use long where needed.
 * Why: Visit both children before their parent. The recursive call handles one entire subtree, so placing the current value at the specified point establishes the traversal order by induction.
 * Complexity: O(n) time; O(h) recursion plus O(n) output.
 */
public final class Q253Postorder { // One class contains this question, its answer, and reproducible checks.

    static final class Node { // A node models one value and its two possible child links.
        int value; Node left, right; // Child references encode hierarchy; null means no child.
        Node(int value) { this.value = value; } // A fresh node starts as a leaf.
        Node(int value, Node left, Node right) { this.value=value; this.left=left; this.right=right; } // Connect a complete local subtree.
    }
    static Node sample() { // Build a fresh tree so mutation in one demonstration cannot affect another.
        return new Node(4,new Node(2,new Node(1),new Node(3)),new Node(6,new Node(5),new Node(7))); // A seven-node balanced BST also supplies a general binary-tree example.
    }

    static void walk(Node n,List<Integer> out) { // The helper appends exactly one subtree traversal.
        if(n==null) return; // An empty subtree contributes no values.
        walk(n.left,out); walk(n.right,out); out.add(n.value); // Visit both children before their parent to preserve the required order.
    }
    public static String solve(Node root) { // Expose the answer as a reusable method as well as a runnable program.
        List<Integer> out=new ArrayList<>(); // Collect each visited value once in the requested order.
        walk(root,out); // Recursion visits the whole reachable hierarchy.
        return out.toString(); // A stable text form makes the traversal easy to inspect.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(sample())); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(sample()), "[1, 3, 2, 5, 7, 6, 4]"); // Balanced seven-node input
        Check.equal(solve(null), "[]"); // Empty tree
        Check.equal(solve(new Node(9)), "[9]"); // Single-node tree
    }
}
