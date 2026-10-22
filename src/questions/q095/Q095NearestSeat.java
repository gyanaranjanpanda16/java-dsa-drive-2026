package questions.q095;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q095: Allocate the nearest free seat with ordered neighbors.
 * Allocate the nearest available integer seat for each request, breaking equal-distance ties toward the smaller seat and stopping when exhausted. floor/ceiling are sufficient because every other available seat is farther on the same side. This is sequential nearest-seat behavior, not optimal matching for all requests.
 * Complexity: O((n+r) log(u+1)) time and O(u+r) space for u distinct seats and r requests.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q095NearestSeat { // Keep this question independently runnable and easy to find.
    private Q095NearestSeat() { } // This exercise exposes behavior through static methods.
    public static List<Integer> solve(Collection<Integer> seats, int[] requests) { // Define the input and result types of this particular problem.
        NavigableSet<Integer> free = new TreeSet<>(seats); // Maintain unique currently available positions in sorted order.
        List<Integer> assigned = new ArrayList<>(); // Record one seat per successfully served request.
        for (int desired : requests) { // Handle requests sequentially because allocations consume seats.
            if (free.isEmpty()) break; // No further request can be served after the last seat is used.
            Integer lower = free.floor(desired); // Closest available seat at or below the target.
            Integer upper = free.ceiling(desired); // Closest available seat at or above the target.
            int chosen; // Exactly one neighbor is enough when the target lies outside the remaining range.
            if (lower == null) chosen = upper; // Every free seat is above the desired location.
            else if (upper == null) chosen = lower; // Every free seat is below the desired location.
            else chosen = (long) desired-lower <= (long) upper-desired ? lower : upper; // Compare widened distances and prefer the lower seat on ties.
            free.remove(chosen); // Prevent assigning the same physical seat twice.
            assigned.add(chosen); // Preserve request order in the response.
        }
        return assigned; // Greedy nearest allocation is local to each request, not a global cost optimizer.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(List.of(1,5,9),new int[]{6,6,0}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(List.of(1,5,9),new int[]{6,6,0}), List.of(5,9,1)); // Case 1: verify the expected result for this input.
        Check.equal(solve(List.of(),new int[]{1}), List.of()); // Case 2: verify the expected result for this input.
        Check.equal(solve(List.of(Integer.MIN_VALUE,Integer.MAX_VALUE),new int[]{0}), List.of(Integer.MAX_VALUE)); // Case 3: verify the expected result for this input.
    }
}
