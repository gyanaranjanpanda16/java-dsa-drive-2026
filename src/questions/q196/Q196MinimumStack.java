package questions.q196;

import java.util.*;
import questions.support.Check;

/**
 * Q196: Maintain stack minima through pushes and pops.
 * Contract: Commands are push:x,pop,min. Valid sequences never pop or query an empty stack. Return values produced by min commands.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: Each depth stores both its value and the minimum of the prefix below it. Popping restores the previous depth minimum automatically, including duplicate minima.
 * Complexity: O(commands) time and O(max depth) auxiliary space plus output.
 */
public final class Q196MinimumStack { // Keep one question and its tests together for independent study.
    public static String solve(String[] commands) { // Evaluate a small command language for the data-structure exercise.
        Deque<Integer> values=new ArrayDeque<>(),minima=new ArrayDeque<>(); // Parallel stacks keep the minimum belonging to each depth.
        List<Integer> output=new ArrayList<>(); // Record only requested minimum observations.
        for(String command:commands) { // Apply state changes in their specified order.
            if(command.startsWith("push:")) { // A push introduces one new depth.
                int value=Integer.parseInt(command.substring(5)); // Parse the value after the command prefix.
                values.push(value);minima.push(minima.isEmpty()?value:Math.min(value,minima.peek())); // The new prefix minimum includes the new value and all previous depths.
            } else if(command.equals("pop")) { values.pop();minima.pop(); } // Removing both entries restores the preceding state.
            else if(command.equals("min")) output.add(minima.peek()); // The top cached minimum answers in constant time.
            else throw new IllegalArgumentException("unknown command"); // Reject operations outside the stated interface.
        }
        return output.toString(); // Preserve query order in the returned observations.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve(new String[]{"push:3","push:1","min","pop","min"})); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve(new String[]{"push:3","push:1","min","pop","min"}),"[1, 3]"); // Popping restores the previous minimum.
    Check.equal(solve(new String[]{"push:1","push:1","pop","min"}),"[1]"); // Duplicate minima survive one removal.
    Check.equal(solve(new String[]{}),"[]"); // No commands produce no observations.
    }
}
