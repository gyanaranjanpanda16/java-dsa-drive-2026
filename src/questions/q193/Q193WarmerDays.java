package questions.q193;

import java.util.*;
import questions.support.Check;

/**
 * Q193: Count days until a strictly warmer temperature.
 * Contract: Return waiting days for the next greater temperature; zero means no warmer future day.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: Unresolved days form a decreasing stack. The first incoming warmer temperature resolves every smaller stacked temperature, so its index distance is the minimum wait.
 * Complexity: O(n) time and O(n) space.
 */
public final class Q193WarmerDays { // Keep one question and its tests together for independent study.
    public static String solve(int[] temperatures) { // A zero-initialized answer naturally represents no future warmer day.
        int[] wait=new int[temperatures.length]; // Store waiting distances rather than future temperatures.
        Deque<Integer> unresolved=new ArrayDeque<>(); // Remember days whose first warmer successor has not appeared.
        for(int day=0;day<temperatures.length;day++) { // Chronological order guarantees the first resolver is nearest.
            while(!unresolved.isEmpty()&&temperatures[day]>temperatures[unresolved.peek()]) { // Today resolves all strictly cooler waiting days on top.
                int previous=unresolved.pop(); // Remove a day once its earliest answer is known.
                wait[previous]=day-previous; // Index difference equals the number of days waited.
            }
            unresolved.push(day); // Today now waits for its own warmer successor.
        }
        return Arrays.toString(wait); // Unresolved entries correctly retain zero.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve(new int[]{73,74,75,71,69,72,76,73})); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve(new int[]{73,74,75,71,69,72,76,73}),"[1, 1, 4, 2, 1, 1, 0, 0]"); // Resolve several earlier cooler days together.
    Check.equal(solve(new int[]{5,5}),"[0, 0]"); // Equal temperature is not warmer.
    Check.equal(solve(new int[]{}),"[]"); // Empty input requires no waits.
    }
}
