package questions.q179;

import java.util.*;
import questions.support.Check;

/**
 * Q179: Insert a value into a sorted list.
 * Contract: Input is acyclic and nondecreasing; insert after all existing equal values.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: The scan stops at the first greater successor, so the new node is no smaller than its predecessor and no larger than its successor.
 * Complexity: O(n) time and O(1) insertion space plus display.
 */
public final class Q179SortedInsert { // Keep one question and its tests together for independent study.
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
    public static String solve(Node head,int value) { // Preserve sorted order without sorting the entire list again.
        Node dummy=new Node(0);dummy.next=head; // A sentinel supplies a predecessor for a new minimum.
        Node previous=dummy; // Search for the last node whose value is at most the inserted value.
        while(previous.next!=null&&previous.next.value<=value) previous=previous.next; // Passing equal values implements the documented tie placement.
        Node inserted=new Node(value);inserted.next=previous.next; // Preserve the old suffix before reconnecting it.
        previous.next=inserted; // Join the sorted prefix to the inserted value and unchanged suffix.
        return text(dummy.next); // The head may change if the new value is smallest.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve(list(1,3,5),4)); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve(list(1,3,5),4),"[1, 3, 4, 5]"); // Insert between neighboring bounds.
    Check.equal(solve(null,2),"[2]"); // Empty list has one valid insertion gap.
    Check.equal(solve(list(1,1),1),"[1, 1, 1]"); // Equal values remain allowed.
    }
}
