package questions.q161;

import java.util.*;
import questions.support.Check;

/**
 * Q161: Traverse a singly linked list.
 * Contract: Return the values of an acyclic singly linked list in head-to-tail order; null means empty.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: The head starts the sequence and each next reference identifies its successor. Following links until null visits every node in order.
 * Complexity: O(n) time and O(n) display space.
 */
public final class Q161Traverse { // Keep one question and its tests together for independent study.
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
    public static String solve(Node head) { // A list must be traversed by links, not numeric array positions.
        List<Integer> result=new ArrayList<>(); // Preserve the visit order for inspection.
        while(head!=null) { // Null marks the end rather than a valid element.
            result.add(head.value); // Record the current value before leaving the node.
            head=head.next; // Advance one link so the scan eventually terminates.
        }
        return result.toString(); // Expose logical values instead of node addresses.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve(list(1,2,3))); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve(list(1,2,3)),"[1, 2, 3]"); // Visit all nodes in link order.
    Check.equal(solve(null),"[]"); // Empty head needs no dereference.
    Check.equal(solve(list(5)),"[5]"); // A singleton ends after its first value.
    }
}
