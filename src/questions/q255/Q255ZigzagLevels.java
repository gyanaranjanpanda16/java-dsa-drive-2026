package questions.q255; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q255: Alternate direction on successive tree levels.
 * Contract: A null root denotes an empty tree. Values are int; aggregate results use long where needed.
 * Why: Ordinary BFS already groups nodes by depth. Reversing every other completed level changes output orientation while leaving the breadth-first invariant intact.
 * Complexity: O(n) time and O(n) output; queue O(w).
 */
public final class Q255ZigzagLevels { // One class contains this question, its answer, and reproducible checks.

    static final class Node { // A node models one value and its two possible child links.
        int value; Node left, right; // Child references encode hierarchy; null means no child.
        Node(int value) { this.value = value; } // A fresh node starts as a leaf.
        Node(int value, Node left, Node right) { this.value=value; this.left=left; this.right=right; } // Connect a complete local subtree.
    }
    static Node sample() { // Build a fresh tree so mutation in one demonstration cannot affect another.
        return new Node(4,new Node(2,new Node(1),new Node(3)),new Node(6,new Node(5),new Node(7))); // A seven-node balanced BST also supplies a general binary-tree example.
    }

    public static String solve(Node root) { // Expose the answer as a reusable method as well as a runnable program.
        List<List<Integer>> levels=new ArrayList<>(); // Keep level boundaries as well as values.
        if(root==null) return levels.toString(); // No root means no level to reverse.
        Deque<Node> q=new ArrayDeque<>(); q.add(root); boolean reverse=false; // Begin left-to-right at the root.
        while(!q.isEmpty()) { // Each iteration consumes exactly one depth.
            int size=q.size(); List<Integer> level=new ArrayList<>(); // Snapshot prevents newly enqueued children entering this level.
            for(int j=0;j<size;j++) { // Consume only nodes that were present at the start.
                Node n=q.remove(); level.add(n.value); // Collect the ordinary left-to-right order first.
                if(n.left!=null) q.add(n.left); if(n.right!=null) q.add(n.right); // Schedule next-depth nodes in consistent order.
            }
            if(reverse) Collections.reverse(level); // Reverse the presentation without changing traversal discovery.
            levels.add(level); reverse=!reverse; // Alternate the presentation direction for the next depth.
        }
        return levels.toString(); // Nested lists reveal the level boundaries.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(sample())); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(sample()), "[[4], [6, 2], [1, 3, 5, 7]]"); // Balanced seven-node input
        Check.equal(solve(null), "[]"); // Empty tree
        Check.equal(solve(new Node(9)), "[[9]]"); // Single-node tree
    }
}
