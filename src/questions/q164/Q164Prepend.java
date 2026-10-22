package questions.q164;

import java.util.*;
import questions.support.Check;

/**
 * Q164: Insert a new head node.
 * Contract: Prepend one value and return the new list display. Existing nodes are reused; null accepts its first node.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: Connecting new.next to the old head preserves the old chain, while making new the head places the value before every old node.
 * Complexity: O(1) insertion; O(n) display time and space.
 */
public final class Q164Prepend { // Keep one question and its tests together for independent study.
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
    public static String solve(Node head,int value) { // The operation returns the sequence with a replacement head.
        Node inserted=new Node(value); // Allocate exactly the new data node.
        inserted.next=head; // Preserve the complete old chain as the new node's suffix.
        return text(inserted); // The inserted node is now the first reachable element.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve(list(2,3),1)); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve(list(2,3),1),"[1, 2, 3]"); // Prefix an existing chain.
    Check.equal(solve(null,5),"[5]"); // Null becomes a singleton.
    Check.equal(solve(list(1),1),"[1, 1]"); // Insertion preserves duplicate occurrences.
    }
}
