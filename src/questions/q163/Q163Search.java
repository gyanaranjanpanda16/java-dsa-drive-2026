package questions.q163;

import java.util.*;
import questions.support.Check;

/**
 * Q163: Find the first index of a list value.
 * Contract: Return zero-based first matching index or -1 in an acyclic list.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: Traversal order matches index order, so returning on the first match gives the smallest valid index.
 * Complexity: O(n) time and O(1) space.
 */
public final class Q163Search { // Keep one question and its tests together for independent study.
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
    public static int solve(Node head,int target) { // Linked lists require sequential search without an external index.
        int index=0; // The head occupies logical index zero.
        for(Node node=head;node!=null;node=node.next,index++) { // Keep the logical index synchronized with link traversal.
            if(node.value==target) return index; // The first matching visit has the smallest index.
        }
        return -1; // Exhausting the chain proves the value is absent.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve(list(4,9,4),9)); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve(list(4,9,4),9),1); // Find an interior node.
    Check.equal(solve(list(4,9,4),4),0); // Return the earliest duplicate.
    Check.equal(solve(null,3),-1); // Empty lists contain no target.
    }
}
