package questions.q097;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q097: Find free slots in a bounded universe.
 * Return unoccupied slots in 0..slots-1. BitSet uses bits for dense integer membership, useful for booking grids or resource pools; capacity is not a bound, so explicit validation enforces the application universe.
 * Complexity: O(slots/64 + occupied.length + k) word-level work and O(slots/64+k) space for k free slots, ignoring allocation constants.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q097BitSetAvailability { // Keep this question independently runnable and easy to find.
    private Q097BitSetAvailability() { } // This exercise exposes behavior through static methods.
    public static List<Integer> solve(int slots, int[] occupied) { // Define the input and result types of this particular problem.
        if (slots < 0) throw new IllegalArgumentException("negative slot count"); // Define a finite nonnegative universe.
        BitSet free = new BitSet(slots); // Allocate a compact bit representation of membership.
        free.set(0,slots); // Initially mark every valid slot as free using a half-open range.
        for (int slot : occupied) { // Clear only indices that belong to this universe.
            if (slot < 0 || slot >= slots) throw new IllegalArgumentException("slot outside universe"); // BitSet itself would otherwise grow for oversized indices.
            free.clear(slot); // Repeated occupancy entries do not change membership semantics.
        }
        List<Integer> result = new ArrayList<>(); // Enumerate free slots in increasing order.
        for (int slot = free.nextSetBit(0); slot >= 0; slot = free.nextSetBit(slot+1)) result.add(slot); // Skip runs of absent bits instead of testing every index individually.
        return result; // The result contains no occupied or out-of-range positions.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(5,new int[]{1,3}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(5,new int[]{1,3}), List.of(0,2,4)); // Case 1: verify the expected result for this input.
        Check.equal(solve(0,new int[]{}), List.of()); // Case 2: verify the expected result for this input.
        Check.equal(solve(2,new int[]{0,0,1}), List.of()); // Case 3: verify the expected result for this input.
    }
}
