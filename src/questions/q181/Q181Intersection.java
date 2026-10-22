package questions.q181;

import java.util.*;
import questions.support.Check;

/**
 * Q181: Find the first shared node of two acyclic lists.
 * Contract: Return the shared node value as OptionalInt, comparing node identity rather than equal values. Both lists must be acyclic.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: Switching each pointer to the other head makes both traverse the same combined prefix length. They align at the shared suffix entry or simultaneously reach null.
 * Complexity: O(n+m) time and O(1) space.
 */
public final class Q181Intersection { // Keep one question and its tests together for independent study.
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
    public static OptionalInt solve(Node a,Node b) { // Equal values alone are not evidence of structural intersection.
        Node first=a,second=b; // Start one walker at each head.
        while(first!=second) { // Identity equality detects a shared node, including shared null at termination.
            first=first==null?b:first.next; // After finishing a, compensate by walking b's prefix.
            second=second==null?a:second.next; // Symmetrically compensate for the other prefix length.
        }
        return first==null?OptionalInt.empty():OptionalInt.of(first.value); // Distinguish no intersection from any valid stored value.
    }
    static OptionalInt sample() { // Build genuinely shared nodes for the demonstration.
        Node shared=list(8,9),a=new Node(1),b=new Node(2); // Two independent prefixes can reference the same suffix.
        a.next=shared;b.next=shared; // Node identity, not copied values, establishes the intersection.
        return solve(a,b); // Both paths first join at the node holding8.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(sample()); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(sample(),OptionalInt.of(8)); // Detect an actual shared suffix.
    Check.equal(solve(list(8,9),list(8,9)),OptionalInt.empty()); // Equal copies share no node identities.
    Check.equal(solve(null,null),OptionalInt.empty()); // Empty lists have no data-node intersection.
    }
}
