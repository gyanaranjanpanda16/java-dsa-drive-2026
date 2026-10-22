package questions.q184;

import java.util.*;
import questions.support.Check;

/**
 * Q184: Reverse a doubly linked list.
 * Contract: Build a doubly linked list from input values, reverse both link directions, and return forward values.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: Swapping next and previous at every node makes every original edge traversable in the opposite direction. The original tail becomes the new head.
 * Complexity: O(n) time and O(n) node/output storage; reversal itself uses O(1) extra space.
 */
public final class Q184DoublyReverse { // Keep one question and its tests together for independent study.
    static final class Node { // Doubly linked nodes support traversal in either direction.
        int value;Node next,previous; // Both neighboring identities must stay consistent after reversal.
        Node(int value) { this.value=value; } // New nodes start with no neighbors.
    }
    public static String solve(int[] values) { // Build fresh nodes to isolate the mutation demonstration.
        Node head=null,tail=null; // Empty input has neither endpoint.
        for(int value:values) { // Append values while maintaining both directions.
            Node node=new Node(value);node.previous=tail; // The old tail is the new node's predecessor.
            if(tail==null) head=node;else tail.next=node; // Establish the first head or connect the old tail forward.
            tail=node; // The newly appended node becomes the tail.
        }
        Node current=head,newHead=null; // The last node processed will become the new head.
        while(current!=null) { // Follow original forward links saved before swapping.
            Node next=current.next; // Preserve the original next node before changing directions.
            current.next=current.previous;current.previous=next; // Reverse both incident link directions at this node.
            newHead=current;current=next; // Advance along the original chain while retaining its latest node.
        }
        List<Integer> result=new ArrayList<>(); // Display the newly reversed forward chain.
        for(Node node=newHead;node!=null;node=node.next) result.add(node.value); // The swapped next links now lead toward the original head.
        return result.toString(); // Return values in reversed traversal order.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve(new int[]{1,2,3})); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve(new int[]{1,2,3}),"[3, 2, 1]"); // Both directional links are reversed.
    Check.equal(solve(new int[]{}),"[]"); // Empty construction and reversal stay empty.
    Check.equal(solve(new int[]{7}),"[7]"); // A singleton has no neighboring links.
    }
}
