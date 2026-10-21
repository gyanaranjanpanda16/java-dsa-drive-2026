package questions.q268; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q268: Sum numbers represented by root-to-leaf digits.
 * Contract: Every value is a decimal digit 0–9. Arithmetic overflow throws ArithmeticException.
 * Why: Appending decimal digit d to prefix p produces 10p+d. Each leaf closes one number; summing disjoint child results counts every root-to-leaf number once.
 * Complexity: O(n) time; O(h) recursion.
 */
public final class Q268RootLeafNumbers { // One class contains this question, its answer, and reproducible checks.

    static final class Node { // A node models one value and its two possible child links.
        int value; Node left, right; // Child references encode hierarchy; null means no child.
        Node(int value) { this.value = value; } // A fresh node starts as a leaf.
        Node(int value, Node left, Node right) { this.value=value; this.left=left; this.right=right; } // Connect a complete local subtree.
    }
    static Node sample() { // Build a fresh tree so mutation in one demonstration cannot affect another.
        return new Node(4,new Node(2,new Node(1),new Node(3)),new Node(6,new Node(5),new Node(7))); // A seven-node balanced BST also supplies a general binary-tree example.
    }

    static long sum(Node n,long prefix) { // Carry the number formed by ancestor digits.
        if(n==null) return 0; // A nonexistent path contributes no number.
        if(n.value<0||n.value>9) throw new IllegalArgumentException("digit required"); // Decimal composition assumes a single digit at each node.
        long value=Math.addExact(Math.multiplyExact(prefix,10),n.value); // Detect overflow instead of silently changing the number.
        if(n.left==null&&n.right==null) return value; // A leaf completes the current number.
        return Math.addExact(sum(n.left,value),sum(n.right,value)); // Combine independently completed numbers safely.
    }
    public static long solve(Node root) { // Expose the answer as a reusable method as well as a runnable program.
        return sum(root,0); // Prefix zero means no digit has been read yet.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(sample())); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(sample()), 1776L); // Numbers 421,423,465,467
        Check.equal(solve(null), 0L); // Empty tree
        Check.equal(solve(new Node(9)), 9L); // One digit
    }
}
