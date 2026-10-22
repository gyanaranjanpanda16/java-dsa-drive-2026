package questions.q174;

import java.util.*;
import questions.support.Check;

/**
 * Q174: Detect a cycle using two moving pointers.
 * Contract: Return whether following next references eventually revisits a node; null is acyclic.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: Inside a cycle, a fast pointer gains one position per step relative to slow, so they eventually coincide. In an acyclic chain fast reaches null.
 * Complexity: O(n) time and O(1) space.
 */
public final class Q174DetectCycle { // Keep one question and its tests together for independent study.
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
    static Node cyclic(int[] values,int entry) { // Construct a test cycle by an index; -1 requests an acyclic chain.
        Node head=list(values),tail=head,target=null; // Remember the desired entry while finding the tail.
        for(int i=0;tail!=null;i++) { // Walk only the original acyclic links during construction.
            if(i==entry) target=tail; // Save node identity, not merely a matching value.
            if(tail.next==null) break; // The final original node will close the cycle.
            tail=tail.next; // Continue toward the original tail.
        }
        if(tail!=null) tail.next=target; // A null target leaves the list acyclic.
        return head; // The returned chain may deliberately contain a cycle.
    }
    public static boolean solve(Node head) { // Compare node identity because duplicate values do not imply a cycle.
        Node slow=head,fast=head; // Start both walkers at the same entry point.
        while(fast!=null&&fast.next!=null) { // Guard both dereferences of a two-link move.
            slow=slow.next;fast=fast.next.next; // Fast gains one link relative to slow on every round.
            if(slow==fast) return true; // Meeting after movement proves a reference cycle.
        }
        return false; // A null successor proves the chain terminates.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve(cyclic(new int[]{1,2,3},1))); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve(cyclic(new int[]{1,2,3},1)),true); // The tail reconnects to an earlier node.
    Check.equal(solve(list(1,1)),false); // Equal values in distinct nodes do not form a cycle.
    Check.equal(solve(null),false); // Empty input terminates immediately.
    }
}
