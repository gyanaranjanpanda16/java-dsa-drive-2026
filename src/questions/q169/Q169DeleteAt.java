package questions.q169;

import java.util.*;
import questions.support.Check;

/**
 * Q169: Remove a node at a specified position.
 * Contract: Delete index0..length-1; invalid positions throw.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: After finding the predecessor, assigning previous.next to target.next bypasses precisely the requested node while preserving the suffix.
 * Complexity: O(n) time and O(1) working space plus display.
 */
public final class Q169DeleteAt { // Keep one question and its tests together for independent study.
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
    public static String solve(Node head,int index) { // Use a sentinel to handle deletion of index0 uniformly.
        if(index<0) throw new IndexOutOfBoundsException(); // Negative positions are outside the list.
        Node dummy=new Node(0);dummy.next=head; // Track the result even when the old head is removed.
        Node previous=dummy; // Walk until the target is immediately after previous.
        for(int i=0;i<index;i++) { // Advance across the nodes preceding the target.
            if(previous.next==null) throw new IndexOutOfBoundsException(); // Stop before dereferencing a missing predecessor.
            previous=previous.next; // One more preceding node has been located.
        }
        if(previous.next==null) throw new IndexOutOfBoundsException(); // The requested target itself must exist.
        previous.next=previous.next.next; // Bypass the target without disturbing later nodes.
        return text(dummy.next); // Read the updated head through the sentinel.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve(list(4,5,6),1)); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve(list(4,5,6),1),"[4, 6]"); // Remove an interior target.
    Check.equal(solve(list(4),0),"[]"); // Head deletion uses the same sentinel logic.
    Check.throwsType(IndexOutOfBoundsException.class,()->solve(null,0)); // Empty input has no deletable position.
    }
}
