package questions.q284; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q284: Serialize a tree with null markers.
 * Contract: A null root denotes an empty tree. Values are int; aggregate results use long where needed.
 * Why: Values alone do not identify a tree shape. Preorder plus explicit null-child markers uniquely describes where each child subtree begins and ends.
 * Complexity: O(n) node visits and O(n) output for fixed-width int values; O(h) stack.
 */
public final class Q284SerializeTree { // One class contains this question, its answer, and reproducible checks.

    static final class Node { // A node models one value and its two possible child links.
        int value; Node left, right; // Child references encode hierarchy; null means no child.
        Node(int value) { this.value = value; } // A fresh node starts as a leaf.
        Node(int value, Node left, Node right) { this.value=value; this.left=left; this.right=right; } // Connect a complete local subtree.
    }
    static Node sample() { // Build a fresh tree so mutation in one demonstration cannot affect another.
        return new Node(4,new Node(2,new Node(1),new Node(3)),new Node(6,new Node(5),new Node(7))); // A seven-node balanced BST also supplies a general binary-tree example.
    }

    static void encode(Node n,StringBuilder out) { // Write exactly one subtree representation.
        if(n==null) { out.append("#,"); return; } // Null markers preserve the position of absent children.
        out.append(n.value).append(','); // Write the root before either child subtree.
        encode(n.left,out); encode(n.right,out); // Fixed child order makes the representation deterministic.
    }
    public static String solve(Node root) { // Expose the answer as a reusable method as well as a runnable program.
        StringBuilder out=new StringBuilder(); encode(root,out); // One preorder pass writes values and structural markers.
        return out.toString(); // Commas separate multi-digit and negative values unambiguously.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(sample())); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(sample()), "4,2,1,#,#,3,#,#,6,5,#,#,7,#,#,"); // Balanced seven-node input
        Check.equal(solve(null), "#,"); // Empty tree
        Check.equal(solve(new Node(9)), "9,#,#,"); // Single-node tree
    }
}
