package questions.q107;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q107: Use weak keys while retaining live owners.
 * Return cached integer metadata in owner order using weak keys. WeakHashMap is useful when metadata should not extend its owner lifetime. This deterministic example retains owners; it does not assert when garbage collection or eviction occurs. Values must not strongly reference their own keys if automatic reclamation is intended.
 * Complexity: Expected O(n) time and O(n) storage during this operation; reclamation timing is unspecified.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q107WeakKeyCache { // Keep this question independently runnable and easy to find.
    private Q107WeakKeyCache() { } // This exercise exposes behavior through static methods.
    public static List<Integer> solve(int[] values) { // Define the input and result types of this particular problem.
        List<Object> owners = new ArrayList<>(); // Strong references represent owner objects that are still live.
        Map<Object,Integer> cache = new WeakHashMap<>(); // Cache entries alone must not keep their keys alive indefinitely.
        for (int value : values) { // Each owner receives separate derived metadata.
            Object owner = new Object(); // Identity equality gives each owner a distinct cache entry.
            owners.add(owner); // Retain the owner strongly for this deterministic demonstration.
            cache.put(owner,value); // Store metadata that does not point back to its owner.
        }
        List<Integer> result = new ArrayList<>(); // Retrieve metadata in owner order rather than unspecified map order.
        for (Object owner : owners) result.add(cache.get(owner)); // Live owners guarantee these keys remain reachable during lookup.
        java.lang.ref.Reference.reachabilityFence(owners); // Keep all owners strongly reachable through the final lookup.
        return result; // Garbage collection after this point is unspecified and never part of the expected output.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[]{4,7}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[]{4,7}), List.of(4,7)); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[]{}), List.of()); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[]{3,3}), List.of(3,3)); // Case 3: verify the expected result for this input.
    }
}
