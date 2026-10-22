package questions.q180;

import java.util.*;
import questions.support.Check;

/**
 * Q180: Check whether list values form a palindrome.
 * Contract: Return value symmetry of an acyclic list without changing its links; empty is a palindrome.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: A stack reverses encounter order. Comparing the forward traversal with popped values tests every pair against its mirrored position.
 * Complexity: O(n) time and O(n) auxiliary space.
 */
public final class Q180Palindrome { // Keep one question and its tests together for independent study.
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
    public static boolean solve(Node head) { // Preserve the caller's node structure throughout the check.
        Deque<Integer> reversed=new ArrayDeque<>(); // LIFO order represents values from tail to head.
        for(Node node=head;node!=null;node=node.next) reversed.push(node.value); // Push every value in forward order.
        for(Node node=head;node!=null;node=node.next) { // Compare the original order with its reversal.
            if(node.value!=reversed.pop()) return false; // One mismatched mirrored pair disproves symmetry.
        }
        return true; // Every forward value matched its reversed counterpart.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve(list(1,2,2,1))); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve(list(1,2,2,1)),true); // Even-length symmetry succeeds.
    Check.equal(solve(list(1,2)),false); // Different endpoints disprove symmetry.
    Check.equal(solve(null),true); // Empty sequences satisfy symmetry.
    }
}
