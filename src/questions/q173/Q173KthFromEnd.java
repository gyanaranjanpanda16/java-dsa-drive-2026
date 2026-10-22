package questions.q173;

import java.util.*;
import questions.support.Check;

/**
 * Q173: Find the kth node from the end.
 * Contract: k is one-based and positive; return no value when k exceeds length.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: Keeping a gap of k links between lead and follow means that when lead reaches null, follow is exactly k nodes from the end.
 * Complexity: O(n) time and O(1) space.
 */
public final class Q173KthFromEnd { // Keep one question and its tests together for independent study.
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
    public static OptionalInt solve(Node head,int k) { // One-based k makes k=1 mean the final node.
        if(k<1) throw new IllegalArgumentException("positive k required"); // Zero or negative distance has no node meaning.
        Node lead=head,follow=head; // Establish a fixed gap before moving both pointers.
        for(int i=0;i<k;i++) { // Move lead exactly k links ahead when possible.
            if(lead==null) return OptionalInt.empty(); // The requested suffix position exceeds the list length.
            lead=lead.next; // Increase the lead-follow gap by one.
        }
        while(lead!=null) { lead=lead.next;follow=follow.next; } // Preserve the gap until the lead pointer reaches the end.
        return OptionalInt.of(follow.value); // The remaining follow suffix has exactly k nodes.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve(list(4,5,6,7),2)); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve(list(4,5,6,7),2),OptionalInt.of(6)); // A fixed gap locates an interior suffix position.
    Check.equal(solve(list(9),1),OptionalInt.of(9)); // The last node is also the head.
    Check.equal(solve(list(1),2),OptionalInt.empty()); // Too-large k has no result.
    }
}
