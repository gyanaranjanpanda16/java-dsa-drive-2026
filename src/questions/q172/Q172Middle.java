package questions.q172;

import java.util.*;
import questions.support.Check;

/**
 * Q172: Find the second middle node of a list.
 * Contract: Return the middle value as OptionalInt; for even length choose the second middle; empty returns no value.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: Fast moves twice as many links as slow. When fast reaches the end, slow has traversed half the list, rounded toward the second middle.
 * Complexity: O(n) time and O(1) space.
 */
public final class Q172Middle { // Keep one question and its tests together for independent study.
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
    public static OptionalInt solve(Node head) { // Return absence explicitly rather than using a valid int as a sentinel.
        Node slow=head,fast=head; // Both pointers begin at the same node.
        while(fast!=null&&fast.next!=null) { // Two forward links remain available to the fast pointer.
            slow=slow.next;fast=fast.next.next; // Maintain the two-to-one travel relationship.
        }
        return slow==null?OptionalInt.empty():OptionalInt.of(slow.value); // Slow identifies the documented middle convention.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve(list(1,2,3,4))); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve(list(1,2,3,4)),OptionalInt.of(3)); // Even length selects the second middle.
    Check.equal(solve(list(1,2,3)),OptionalInt.of(2)); // Odd length has one middle.
    Check.equal(solve(null),OptionalInt.empty()); // Empty input has no middle.
    }
}
