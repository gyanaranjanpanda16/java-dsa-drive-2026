package questions.q197;

import java.util.*;
import questions.support.Check;

/**
 * Q197: Implement FIFO removal with two stacks.
 * Contract: Commands are add:x or remove; valid sequences remove only when nonempty. Return removed values.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: The incoming stack stores newest-first arrivals. Transferring all values to an empty outgoing stack reverses them into oldest-first order; remaining outgoing values always precede new arrivals.
 * Complexity: O(commands) amortized time and O(max queue length) space.
 */
public final class Q197QueueFromStacks { // Keep one question and its tests together for independent study.
    public static String solve(String[] commands) { // Expose queue behavior using only stack-end operations.
        Deque<Integer> incoming=new ArrayDeque<>(),outgoing=new ArrayDeque<>(); // Separate newly arrived values from values already ordered for removal.
        List<Integer> result=new ArrayList<>(); // Record the FIFO removal sequence.
        for(String command:commands) { // Preserve the interleaving of additions and removals.
            if(command.startsWith("add:")) incoming.push(Integer.parseInt(command.substring(4))); // New arrivals join the newest-first stack.
            else if(command.equals("remove")) { // A removal must expose the oldest waiting value.
                if(outgoing.isEmpty()) while(!incoming.isEmpty()) outgoing.push(incoming.pop()); // Reverse arrivals only when no older outgoing values remain.
                result.add(outgoing.pop()); // The top outgoing value is the oldest pending arrival.
            } else throw new IllegalArgumentException("unknown command"); // Reject unsupported operations.
        }
        return result.toString(); // Each value moves between stacks at most once before removal.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve(new String[]{"add:1","add:2","remove","add:3","remove","remove"})); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve(new String[]{"add:1","add:2","remove","add:3","remove","remove"}),"[1, 2, 3]"); // New arrivals do not overtake older outgoing items.
    Check.equal(solve(new String[]{"add:7","remove"}),"[7]"); // Single transfer handles a singleton.
    Check.equal(solve(new String[]{}),"[]"); // Empty command sequence has no removals.
    }
}
