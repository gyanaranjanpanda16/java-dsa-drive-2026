package questions.q283; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q283: List nodes at a given depth from the root.
 * Contract: A null root denotes an empty tree. Values are int; aggregate results use long where needed.
 * Why: Each descent consumes one unit of depth. A node is selected precisely when the remaining depth becomes zero, so no shallower or deeper node is included.
 * Complexity: O(n) worst-case time; O(h) recursion plus selected output.
 */
public final class Q283NodesAtDistance { // One class contains this question, its answer, and reproducible checks.

    static final class Node { // A node models one value and its two possible child links.
        int value; Node left, right; // Child references encode hierarchy; null means no child.
        Node(int value) { this.value = value; } // A fresh node starts as a leaf.
        Node(int value, Node left, Node right) { this.value=value; this.left=left; this.right=right; } // Connect a complete local subtree.
    }
    static Node sample() { // Build a fresh tree so mutation in one demonstration cannot affect another.
        return new Node(4,new Node(2,new Node(1),new Node(3)),new Node(6,new Node(5),new Node(7))); // A seven-node balanced BST also supplies a general binary-tree example.
    }

    static void collect(Node n,int remaining,List<Integer> out) { // Remaining depth describes the exact selection target below n.
        if(n==null) return; // Missing branches cannot reach the requested level.
        if(remaining==0) { out.add(n.value); return; } // Stop descent once the required level is reached.
        collect(n.left,remaining-1,out); collect(n.right,remaining-1,out); // Both children are one step closer to the requested depth.
    }
    public static String solve(Node root,int depth) { // Expose the answer as a reusable method as well as a runnable program.
        if(depth<0) throw new IllegalArgumentException("nonnegative depth required"); // Depth zero denotes the root itself.
        List<Integer> out=new ArrayList<>(); collect(root,depth,out); // Carry the remaining depth instead of recomputing levels.
        return out.toString(); // Values remain left-to-right within the selected level.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(sample(),2)); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(sample(),2), "[1, 3, 5, 7]"); // Depth two leaves
        Check.equal(solve(sample(),0), "[4]"); // Depth zero root
        Check.equal(solve(sample(),9), "[]"); // Depth beyond tree height
    }
}
