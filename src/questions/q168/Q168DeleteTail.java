package questions.q168;

import java.util.*;
import questions.support.Check;

/**
 * Q168: Remove the final node.
 * Contract: Remove the tail; empty and singleton inputs return empty.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: The predecessor of the tail is recognized by next.next=null. Clearing its next link removes only the final node.
 * Complexity: O(n) time including display; O(1) working space plus display.
 */
public final class Q168DeleteTail { // Keep one question and its tests together for independent study.
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
    public static String solve(Node head) { // A singly linked list must locate the final node's predecessor.
        if(head==null||head.next==null) return "[]"; // No predecessor exists for these two boundary cases.
        Node previous=head; // Start before at least one remaining node.
        while(previous.next.next!=null) previous=previous.next; // Stop one node before the tail.
        previous.next=null; // End the chain at the old tail's predecessor.
        return text(head); // Earlier nodes retain their order and references.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve(list(1,2,3))); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve(list(1,2,3)),"[1, 2]"); // Remove only the final node.
    Check.equal(solve(list(1)),"[]"); // Removing a singleton removes the head too.
    Check.equal(solve(null),"[]"); // Empty lists remain empty.
    }
}
