package questions.q177;

import java.util.*;
import questions.support.Check;

/**
 * Q177: Remove repeated values from a sorted list.
 * Contract: Input is nondecreasing and acyclic; retain one node per distinct value.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: Equal values form adjacent runs. Keeping the first node and repeatedly bypassing equal successors collapses each run without affecting other values.
 * Complexity: O(n) time and O(1) working space plus display.
 */
public final class Q177DeduplicateSorted { // Keep one question and its tests together for independent study.
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
    public static String solve(Node head) { // Sorted adjacency avoids a separate membership set.
        Node node=head; // Retain this node as the representative of its current run.
        while(node!=null&&node.next!=null) { // A successor is required before comparing adjacent values.
            if(node.value==node.next.value) node.next=node.next.next; // Remove another occurrence without advancing beyond the run.
            else node=node.next; // A changed value begins the next distinct run.
        }
        return text(head); // Every surviving adjacent pair has different values.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve(list(1,1,2,3,3))); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve(list(1,1,2,3,3)),"[1, 2, 3]"); // Collapse multiple repeated runs.
    Check.equal(solve(list(4,4,4)),"[4]"); // Keep exactly one representative.
    Check.equal(solve(null),"[]"); // Empty input has no runs.
    }
}
