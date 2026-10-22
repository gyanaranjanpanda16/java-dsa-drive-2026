package questions.q182;

import java.util.*;
import questions.support.Check;

/**
 * Q182: Rotate a list right by k positions.
 * Contract: k is nonnegative. Rotate an acyclic list right, reusing nodes; empty remains empty.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: Temporarily linking tail to head creates the cyclic order. Breaking after length-k nodes selects exactly the right-rotated start and restores acyclicity.
 * Complexity: O(n) time and O(1) link space plus display.
 */
public final class Q182Rotate { // Keep one question and its tests together for independent study.
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
    public static String solve(Node head,int k) { // Reduce large rotations by the list's actual length.
        if(k<0) throw new IllegalArgumentException("nonnegative k required"); // The contract chooses only rightward distances.
        if(head==null) return "[]"; // Empty input avoids division by zero.
        int length=1;Node tail=head; // Count the head while finding the original tail.
        while(tail.next!=null) { tail=tail.next;length++; } // Determine the cycle modulus from the nodes themselves.
        k%=length; // Full-length rotations leave the sequence unchanged.
        tail.next=head; // Close the chain temporarily to preserve cyclic order.
        Node newTail=head; // Locate the node immediately before the rotated start.
        for(int i=1;i<length-k;i++) newTail=newTail.next; // Move to position length-k-1.
        Node newHead=newTail.next;newTail.next=null; // Preserve the new start before breaking the temporary cycle.
        return text(newHead); // Traverse only after acyclicity has been restored.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve(list(1,2,3,4,5),2)); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve(list(1,2,3,4,5),2),"[4, 5, 1, 2, 3]"); // Move the last two nodes to the front.
    Check.equal(solve(list(1,2),4),"[1, 2]"); // Reduce complete rotations.
    Check.equal(solve(null,3),"[]"); // Empty input remains empty.
    }
}
