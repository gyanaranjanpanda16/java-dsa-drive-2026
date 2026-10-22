package questions.q170;

import java.util.*;
import questions.support.Check;

/**
 * Q170: Remove every node containing a selected value.
 * Contract: Delete all matching values, preserving order of survivors; null returns empty.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: The predecessor advances only when its successor survives. After a deletion it stays in place so consecutive matching nodes are not skipped.
 * Complexity: O(n) time and O(1) link-update space plus display.
 */
public final class Q170DeleteValue { // Keep one question and its tests together for independent study.
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
    public static String solve(Node head,int target) { // The sentinel allows the first several nodes to be removed uniformly.
        Node dummy=new Node(0);dummy.next=head; // Preserve a stable predecessor before the logical head.
        Node previous=dummy; // Its next node is always the next unclassified candidate.
        while(previous.next!=null) { // Continue while any candidate remains.
            if(previous.next.value==target) previous.next=previous.next.next; // Bypass a match without advancing past another possible match.
            else previous=previous.next; // A surviving node can become the next stable predecessor.
        }
        return text(dummy.next); // All remaining nodes are nonmatches in original order.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve(list(2,2,1,2,3),2)); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve(list(2,2,1,2,3),2),"[1, 3]"); // Consecutive head and interior matches are removed.
    Check.equal(solve(list(4,4),4),"[]"); // Every node may be removed.
    Check.equal(solve(null,1),"[]"); // Empty lists need no iteration.
    }
}
