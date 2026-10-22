package questions.q176;

import java.util.*;
import questions.support.Check;

/**
 * Q176: Merge two sorted linked lists by relinking.
 * Contract: Inputs are disjoint acyclic nondecreasing lists; reuse their nodes and return the merged display.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: Each head is its list smallest remaining value. Selecting the smaller head therefore appends the globally smallest unconsumed node.
 * Complexity: O(n+m) time and O(1) link space plus display.
 */
public final class Q176MergeSorted { // Keep one question and its tests together for independent study.
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
    public static String solve(Node a,Node b) { // Reuse input nodes rather than allocating one new node per result value.
        Node dummy=new Node(0),tail=dummy; // A sentinel removes the first-output-node special case.
        while(a!=null&&b!=null) { // Compare heads while both lists still offer a candidate.
            if(a.value<=b.value) { tail.next=a;a=a.next; } // Prefer the first list on equal values for stable merging.
            else { tail.next=b;b=b.next; } // The second head is the smaller remaining candidate.
            tail=tail.next; // Extend the finalized output prefix by one node.
        }
        tail.next=a!=null?a:b; // The remaining suffix is already sorted and can be attached wholesale.
        return text(dummy.next); // Exclude the helper sentinel from the result.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve(list(1,3,5),list(2,4))); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve(list(1,3,5),list(2,4)),"[1, 2, 3, 4, 5]"); // Interleave ordered candidates.
    Check.equal(solve(null,list(2)),"[2]"); // One empty input contributes no nodes.
    Check.equal(solve(list(1),list(1)),"[1, 1]"); // Distinct equal-valued nodes are both preserved.
    }
}
