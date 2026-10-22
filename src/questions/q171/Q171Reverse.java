package questions.q171;

import java.util.*;
import questions.support.Check;

/**
 * Q171: Reverse a singly linked list in place.
 * Contract: Reverse links of an acyclic list and return its value sequence.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: At every step previous is the reversed processed prefix and current begins the untouched suffix. Saving next before rewiring preserves access to that suffix.
 * Complexity: O(n) time and O(1) pointer space plus display.
 */
public final class Q171Reverse { // Keep one question and its tests together for independent study.
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
    public static String solve(Node head) { // Reuse nodes instead of copying their values into a reversed array.
        Node previous=null,current=head; // Start with an empty reversed prefix and the full unprocessed chain.
        while(current!=null) { // Remove one node from the unprocessed suffix each iteration.
            Node next=current.next; // Save the forward route before reversing this link.
            current.next=previous; // Attach this node before the reversed prefix.
            previous=current;current=next; // Advance both invariant regions without losing any node.
        }
        return text(previous); // The reversed prefix now contains the entire list.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve(list(1,2,3))); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve(list(1,2,3)),"[3, 2, 1]"); // Reverse every link.
    Check.equal(solve(null),"[]"); // Empty reversal stays empty.
    Check.equal(solve(list(7)),"[7]"); // Singleton reversal retains its value.
    }
}
