package questions.q178;

import java.util.*;
import questions.support.Check;

/**
 * Q178: Remove later duplicate values from an unsorted list.
 * Contract: Preserve the first occurrence of each value in an acyclic list, retaining original order.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: The set contains values of the surviving prefix. A seen value is bypassed; a new value extends both the prefix and its membership set.
 * Complexity: Expected O(n) time and O(u) auxiliary space plus display.
 */
public final class Q178DeduplicateUnsorted { // Keep one question and its tests together for independent study.
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
    public static String solve(Node head) { // Unsorted duplicate positions require remembered membership.
        Set<Integer> seen=new HashSet<>(); // Represent values already kept in the output prefix.
        Node dummy=new Node(0);dummy.next=head; // Permit duplicates at any position to be bypassed uniformly.
        Node previous=dummy; // The next node is always the next candidate to classify.
        while(previous.next!=null) { // Stop after every original node has been classified.
            if(!seen.add(previous.next.value)) previous.next=previous.next.next; // A repeated value must not enter the surviving chain again.
            else previous=previous.next; // A newly retained value becomes the next predecessor.
        }
        return text(dummy.next); // Survivors preserve first-appearance order.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve(list(3,1,3,2,1))); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve(list(3,1,3,2,1)),"[3, 1, 2]"); // Nonadjacent duplicates are removed.
    Check.equal(solve(list(0,0)),"[0]"); // Zero is a regular set key.
    Check.equal(solve(null),"[]"); // Empty input needs no state.
    }
}
