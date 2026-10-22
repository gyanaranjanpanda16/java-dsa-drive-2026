package questions.q165;

import java.util.*;
import questions.support.Check;

/**
 * Q165: Append a new tail node.
 * Contract: Append a value to an acyclic list and return its display. Null input produces a singleton.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: The final node is the unique node with next=null. Attaching there preserves every earlier link and places the new value last.
 * Complexity: O(n) search and display time; O(1) insertion space plus display.
 */
public final class Q165Append { // Keep one question and its tests together for independent study.
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
    public static String solve(Node head,int value) { // Without a stored tail pointer, locate the end by traversal.
        Node inserted=new Node(value); // A new tail initially has no successor.
        if(head==null) return text(inserted); // The first insertion also establishes the head.
        Node tail=head; // Begin searching from the only known node reference.
        while(tail.next!=null) tail=tail.next; // Stop at the last existing node rather than stepping beyond it.
        tail.next=inserted; // Connect the previous tail to the new final node.
        return text(head); // Appending leaves the original head unchanged.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve(list(1,2),3)); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve(list(1,2),3),"[1, 2, 3]"); // Preserve the original prefix.
    Check.equal(solve(null,8),"[8]"); // Empty list accepts its first element.
    Check.equal(solve(list(1),1),"[1, 1]"); // Repeated values remain separate nodes.
    }
}
