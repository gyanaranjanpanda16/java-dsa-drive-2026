package questions.q265; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q265: Compute the sum at every depth.
 * Contract: A null root denotes an empty tree. Values are int; aggregate results use long where needed.
 * Why: Depth uniquely partitions the nodes. Accumulating each node into its depth bucket yields each level sum regardless of traversal order.
 * Complexity: O(n) time; O(h) recursion plus O(h) sums.
 */
public final class Q265LevelSums { // One class contains this question, its answer, and reproducible checks.

    static final class Node { // A node models one value and its two possible child links.
        int value; Node left, right; // Child references encode hierarchy; null means no child.
        Node(int value) { this.value = value; } // A fresh node starts as a leaf.
        Node(int value, Node left, Node right) { this.value=value; this.left=left; this.right=right; } // Connect a complete local subtree.
    }
    static Node sample() { // Build a fresh tree so mutation in one demonstration cannot affect another.
        return new Node(4,new Node(2,new Node(1),new Node(3)),new Node(6,new Node(5),new Node(7))); // A seven-node balanced BST also supplies a general binary-tree example.
    }

    static void add(Node n,int depth,List<Long> sums) { // Carry depth explicitly through the recursion.
        if(n==null) return; // No value exists to accumulate.
        if(depth==sums.size()) sums.add(0L); // Allocate an accumulator the first time a depth appears.
        sums.set(depth,sums.get(depth)+n.value); // Use long addition for aggregate values.
        add(n.left,depth+1,sums); add(n.right,depth+1,sums); // Both child subtrees belong one level lower.
    }
    public static String solve(Node root) { // Expose the answer as a reusable method as well as a runnable program.
        List<Long> sums=new ArrayList<>(); // Keep one long accumulator per level.
        add(root,0,sums); // Each node contributes to exactly its own depth.
        return sums.toString(); // Preserve the level order for a dashboard-like summary.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(sample())); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(sample()), "[4, 8, 16]"); // Balanced seven-node input
        Check.equal(solve(null), "[]"); // Empty tree
        Check.equal(solve(new Node(9)), "[9]"); // Single-node tree
    }
}
