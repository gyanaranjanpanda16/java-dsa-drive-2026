package questions.q183;

import java.util.*;
import questions.support.Check;

/**
 * Q183: Swap neighboring nodes by rewiring links.
 * Contract: Swap each adjacent pair in an acyclic list; leave an unmatched final node in place.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: For predecessor P and pair A,B, rewiring P→B→A→suffix swaps only the pair while preserving all other nodes.
 * Complexity: O(n) time and O(1) link space plus display.
 */
public final class Q183SwapPairs { // Keep one question and its tests together for independent study.
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
    public static String solve(Node head) { // Swap nodes rather than just their stored values.
        Node dummy=new Node(0);dummy.next=head; // A sentinel makes the first pair use the same predecessor rule.
        Node previous=dummy; // The next two nodes form the next candidate pair.
        while(previous.next!=null&&previous.next.next!=null) { // Require two nodes before attempting a swap.
            Node first=previous.next,second=first.next; // Save both pair identities before changing links.
            first.next=second.next; // Attach the former first node to the unchanged suffix.
            second.next=first;previous.next=second; // Reverse the pair and reconnect its prefix.
            previous=first; // The former first node is now the pair's final node.
        }
        return text(dummy.next); // The sentinel exposes the possibly replaced head.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve(list(1,2,3,4,5))); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve(list(1,2,3,4,5)),"[2, 1, 4, 3, 5]"); // Preserve an unmatched final node.
    Check.equal(solve(list(1)),"[1]"); // One node cannot form a pair.
    Check.equal(solve(null),"[]"); // Empty input has no pair.
    }
}
