package questions.q185;

import java.util.*;
import questions.support.Check;

/**
 * Q185: Count nodes in a circular list.
 * Contract: Build a circular list from values and count one complete circuit; empty input returns0.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: A nonempty circular list has no null terminator. Returning to the exact head identity marks one complete visit of every node.
 * Complexity: O(n) time and O(n) construction space; counting uses O(1) auxiliary space.
 */
public final class Q185CircularLength { // Keep one question and its tests together for independent study.
    static final class Node { // One successor per node is enough for a circular singly linked list.
        int value;Node next; // The final node will link back to the first.
        Node(int value) { this.value=value; } // Store the value independently of its link.
    }
    public static int solve(int[] values) { // Construct a well-formed circle before demonstrating its traversal.
        if(values.length==0) return 0; // There is no head identity to return to in an empty circle.
        Node head=new Node(values[0]),tail=head; // Start with the first data node.
        for(int i=1;i<values.length;i++) { tail.next=new Node(values[i]);tail=tail.next; } // Append the remaining nodes in input order.
        tail.next=head; // Close the circle without introducing any extra data node.
        int count=0;Node current=head; // Count visits until the original head is reached again.
        do { count++;current=current.next; } while(current!=head); // Execute once for a singleton self-loop, then stop on identity.
        return count; // Exactly one full circuit has been counted.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve(new int[]{1,2,3})); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve(new int[]{1,2,3}),3); // A full circuit visits all three nodes.
    Check.equal(solve(new int[]{7}),1); // A self-loop requires one visit.
    Check.equal(solve(new int[]{}),0); // Empty input has no circuit.
    }
}
