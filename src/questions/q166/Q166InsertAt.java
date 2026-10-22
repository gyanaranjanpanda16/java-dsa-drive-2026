package questions.q166;

import java.util.*;
import questions.support.Check;

/**
 * Q166: Insert before a zero-based list position.
 * Contract: Insert value at index0..length, allowing append at length; invalid index throws.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: A sentinel provides a predecessor even for index0. Locating the predecessor and reconnecting two links preserves the prefix and suffix around the inserted node.
 * Complexity: O(n) time including display; O(1) link-update space plus output.
 */
public final class Q166InsertAt { // Keep one question and its tests together for independent study.
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
    public static String solve(Node head,int index,int value) { // Treat insertion position as the number of preceding nodes.
        if(index<0) throw new IndexOutOfBoundsException(); // Negative positions cannot identify a valid gap.
        Node dummy=new Node(0);dummy.next=head; // A sentinel makes insertion before the head uniform.
        Node previous=dummy; // Walk to the node immediately before the requested gap.
        for(int i=0;i<index;i++) { // Each advance accounts for one preceding data node.
            if(previous.next==null) throw new IndexOutOfBoundsException(); // A gap beyond the end is invalid.
            previous=previous.next; // Preserve the prefix while locating its final node.
        }
        Node inserted=new Node(value);inserted.next=previous.next; // Save the suffix as the new node's successor.
        previous.next=inserted; // Reconnect the prefix through the inserted node.
        return text(dummy.next); // The sentinel's successor tracks a possibly changed head.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve(list(1,3),1,2)); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve(list(1,3),1,2),"[1, 2, 3]"); // Insert into an interior gap.
    Check.equal(solve(null,0,7),"[7]"); // Index0 is valid for an empty list.
    Check.throwsType(IndexOutOfBoundsException.class,()->solve(list(1),2,9)); // Reject a position past the final gap.
    }
}
