package questions.q162;

import java.util.*;
import questions.support.Check;

/**
 * Q162: Count nodes of an acyclic list.
 * Contract: Return the number of nodes, including duplicate values; null has length0.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: One increment corresponds to each visited node. Values do not affect structural length.
 * Complexity: O(n) time and O(1) space.
 */
public final class Q162Length { // Keep one question and its tests together for independent study.
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
    public static int solve(Node head) { // Length depends on node count rather than distinct values.
        int count=0; // Before traversal no nodes have been visited.
        for(Node node=head;node!=null;node=node.next) count++; // Follow each successor once and count its node.
        return count; // The count equals the number of links traversed plus the starting node when present.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve(list(7,7,2))); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve(list(7,7,2)),3); // Duplicate values still occupy distinct nodes.
    Check.equal(solve(null),0); // No head means no nodes.
    Check.equal(solve(list(8)),1); // A leaf node contributes one.
    }
}
