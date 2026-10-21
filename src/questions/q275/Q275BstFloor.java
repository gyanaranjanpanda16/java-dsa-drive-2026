package questions.q275; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q275: Find the greatest BST key not exceeding a target.
 * Contract: Input is a valid BST; null means no floor exists.
 * Why: When a node is at most the target, it becomes a candidate and only its right subtree can improve it. Otherwise only the left subtree can contain a valid answer.
 * Complexity: O(h) time; O(1) space.
 */
public final class Q275BstFloor { // One class contains this question, its answer, and reproducible checks.

    static final class Node { // A node models one value and its two possible child links.
        int value; Node left, right; // Child references encode hierarchy; null means no child.
        Node(int value) { this.value = value; } // A fresh node starts as a leaf.
        Node(int value, Node left, Node right) { this.value=value; this.left=left; this.right=right; } // Connect a complete local subtree.
    }
    static Node sample() { // Build a fresh tree so mutation in one demonstration cannot affect another.
        return new Node(4,new Node(2,new Node(1),new Node(3)),new Node(6,new Node(5),new Node(7))); // A seven-node balanced BST also supplies a general binary-tree example.
    }

    public static Integer solve(Node root,int key) { // Expose the answer as a reusable method as well as a runnable program.
        Integer answer=null; // Null distinguishes no floor from any actual integer key.
        while(root!=null) { // Search one root-to-leaf route.
            if(root.value<=key) { answer=root.value; root=root.right; } // A valid floor may be improved only by a larger key.
            else root=root.left; // An oversized key and its right subtree cannot be a floor.
        }
        return answer; // The last admissible candidate is the greatest one.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(sample(),5)); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(sample(),5), Integer.valueOf(5)); // Exact target
        Check.equal(solve(sample(),0), null); // No smaller key
        Check.equal(solve(new Node(9),12), Integer.valueOf(9)); // Target above every key
    }
}
