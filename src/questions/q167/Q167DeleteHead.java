package questions.q167;

import java.util.*;
import questions.support.Check;

/**
 * Q167: Remove the first node.
 * Contract: Remove the head of an acyclic list; empty input remains empty.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: All nodes after the head already form the desired result, so returning its next reference removes exactly the first position.
 * Complexity: O(1) deletion; O(n) display time and space.
 */
public final class Q167DeleteHead { // Keep one question and its tests together for independent study.
    static final class Node { // Store one value and a reference to the next node.
        int value;Node next; // The next reference, not physical adjacency, determines sequence order.
        Node(int value) { this.value=value; } // A new node starts with no successor.
    }
    static Node list(int... values) { // Build a fresh acyclic list for each independent example.
        Node dummy=new Node(0),tail=dummy; // A sentinel makes the first append identical to later appends.
        for(int value:values) { tail.next=new Node(value);tail=tail.next; } // Attach each new value at the current tail.
        return dummy.next; // Exclude the sentinel from the logical data.
    }
    static String text(Node head) { // Format an acyclic list without exposing object identities.
        List<Integer> values=new ArrayList<>(); // Collect the values in link order.
        for(Node node=head;node!=null;node=node.next) values.add(node.value); // Following next visits each node once.
        return values.toString(); // Produce deterministic display text for the resulting sequence.
    }
    public static String solve(Node head) { // Deletion changes which node is considered the start.
        if(head!=null) head=head.next; // Skip the first node while retaining its entire suffix.
        return text(head); // A singleton naturally becomes null and displays as empty.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve(list(1,2,3))); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve(list(1,2,3)),"[2, 3]"); // Preserve the suffix.
    Check.equal(solve(list(1)),"[]"); // Singleton deletion yields empty.
    Check.equal(solve(null),"[]"); // Empty deletion is a no-op.
    }
}
