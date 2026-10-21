package questions.q269; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q269: Sum only the deepest leaves.
 * Contract: A null root denotes an empty tree. Values are int; aggregate results use long where needed.
 * Why: When a deeper leaf appears, shallower candidates become irrelevant and the sum resets. Equal-depth leaves are added, preserving exactly the deepest-leaf total.
 * Complexity: O(n) time; O(h) recursion.
 */
public final class Q269DeepestLeavesSum { // One class contains this question, its answer, and reproducible checks.

    static final class Node { // A node models one value and its two possible child links.
        int value; Node left, right; // Child references encode hierarchy; null means no child.
        Node(int value) { this.value = value; } // A fresh node starts as a leaf.
        Node(int value, Node left, Node right) { this.value=value; this.left=left; this.right=right; } // Connect a complete local subtree.
    }
    static Node sample() { // Build a fresh tree so mutation in one demonstration cannot affect another.
        return new Node(4,new Node(2,new Node(1),new Node(3)),new Node(6,new Node(5),new Node(7))); // A seven-node balanced BST also supplies a general binary-tree example.
    }

    static void visit(Node n,int depth,long[] state) { // Compare leaf depth against the best depth so far.
        if(n==null) return; // Missing nodes are not leaves.
        if(n.left==null&&n.right==null) { // Only terminal nodes contribute candidates.
            if(depth>state[0]) { state[0]=depth; state[1]=0; } // A deeper level replaces all earlier shallower sums.
            if(depth==state[0]) state[1]+=n.value; // Equal-depth leaves belong to the same deepest level.
        }
        visit(n.left,depth+1,state); visit(n.right,depth+1,state); // Explore every root-to-leaf branch.
    }
    public static long solve(Node root) { // Expose the answer as a reusable method as well as a runnable program.
        long[] state={-1,0}; // Store the deepest depth encountered and the sum at that depth.
        visit(root,0,state); // Traverse all branches because the deepest level may occur anywhere.
        return state[1]; // An empty tree leaves the sum at zero.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(sample())); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(sample()), 16L); // Deepest leaves 1,3,5,7
        Check.equal(solve(null), 0L); // Empty tree
        Check.equal(solve(new Node(9)), 9L); // Root is also deepest leaf
    }
}
