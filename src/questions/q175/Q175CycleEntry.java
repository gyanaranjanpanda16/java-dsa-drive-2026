package questions.q175;

import java.util.*;
import questions.support.Check;

/**
 * Q175: Find the entry value of a reference cycle.
 * Contract: Return the value stored in the first cycle node reached from head, or no value for an acyclic list. Node identity determines entry.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: After a Floyd meeting, resetting one pointer to head and advancing both one link at a time makes them meet at the entry, by the cycle-distance congruence.
 * Complexity: O(n) time and O(1) space.
 */
public final class Q175CycleEntry { // Keep one question and its tests together for independent study.
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
    public static OptionalInt solve(Node head) { // Separate cycle detection from locating its entry.
        Node slow=head,fast=head; // Two speeds reveal whether a cycle exists.
        do { // Move before comparing to avoid treating the initial equal pointers as proof.
            if(fast==null||fast.next==null) return OptionalInt.empty(); // A terminating chain has no cycle entry.
            slow=slow.next;fast=fast.next.next; // Preserve Floyd's one-step/two-step relationship.
        } while(slow!=fast); // Maintain the state used by the algorithm described in the lesson.
        slow=head; // Reset one walker while retaining the meeting point of the other.
        while(slow!=fast) { slow=slow.next;fast=fast.next; } // Equal-speed walks meet where the acyclic prefix joins the cycle.
        return OptionalInt.of(slow.value); // Return the entry's value without traversing the cycle indefinitely.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve(cyclic(new int[]{10,20,30,40},1))); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve(cyclic(new int[]{10,20,30,40},1)),OptionalInt.of(20)); // Find a cycle after a nonempty prefix.
    Check.equal(solve(cyclic(new int[]{7},0)),OptionalInt.of(7)); // A self-loop enters at the head.
    Check.equal(solve(list(1,2)),OptionalInt.empty()); // Acyclic lists have no entry.
    }
}
