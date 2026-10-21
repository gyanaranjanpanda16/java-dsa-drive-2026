package questions.q263; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q263: Return the left view of a binary tree.
 * Contract: A null root denotes an empty tree. Values are int; aggregate results use long where needed.
 * Why: Visit the left child first. The first node reached at any depth is the extreme visible node on that side, so later nodes at that depth cannot replace it.
 * Complexity: O(n) time; O(h) recursion and output.
 */
public final class Q263LeftView { // One class contains this question, its answer, and reproducible checks.

    static final class Node { // A node models one value and its two possible child links.
        int value; Node left, right; // Child references encode hierarchy; null means no child.
        Node(int value) { this.value = value; } // A fresh node starts as a leaf.
        Node(int value, Node left, Node right) { this.value=value; this.left=left; this.right=right; } // Connect a complete local subtree.
    }
    static Node sample() { // Build a fresh tree so mutation in one demonstration cannot affect another.
        return new Node(4,new Node(2,new Node(1),new Node(3)),new Node(6,new Node(5),new Node(7))); // A seven-node balanced BST also supplies a general binary-tree example.
    }

    static void visit(Node n,int depth,List<Integer> view) { // Depth identifies which view position this node could fill.
        if(n==null) return; // Missing branches expose no new value.
        if(depth==view.size()) view.add(n.value); // Append only the first visit to an unseen depth.
        visit(n.left,depth+1,view); visit(n.right,depth+1,view); // Preferred side wins ties at the same depth.
    }
    public static String solve(Node root) { // Expose the answer as a reusable method as well as a runnable program.
        List<Integer> view=new ArrayList<>(); // Store the first visible value at each depth.
        visit(root,0,view); // Preferred-side-first traversal determines visibility.
        return view.toString(); // Values are reported from shallowest to deepest.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(sample())); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(sample()), "[4, 2, 1]"); // Balanced seven-node input
        Check.equal(solve(null), "[]"); // Empty tree
        Check.equal(solve(new Node(9)), "[9]"); // Single-node tree
    }
}
