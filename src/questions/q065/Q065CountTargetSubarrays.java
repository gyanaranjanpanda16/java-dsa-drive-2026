package questions.q065;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q065: Count contiguous subarrays with an integer target sum.
 * Count nonempty contiguous intervals totaling target, allowing negatives and zeros. For each endpoint, earlier prefix sums equal to currentPrefix-target correspond one-to-one with valid starts.
 * Complexity: Expected O(n) time and O(n) space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q065CountTargetSubarrays { // Keep this question independently runnable and easy to find.
    private Q065CountTargetSubarrays() { } // This exercise exposes behavior through static methods.
    public static long solve(int[] a, int target) { // Define the input and result types of this particular problem.
        Map<Long,Long> frequency = new HashMap<>(); // Store how often each preceding prefix sum occurred.
        frequency.put(0L,1L); // The empty prefix allows subarrays beginning at index zero.
        long prefix = 0, answer = 0; // Use wide arithmetic for sums and the potentially quadratic answer count.
        for (int value : a) { // Every counted interval ends at this iteration's current element.
            prefix += value; // Extend the current prefix by one value.
            answer += frequency.getOrDefault(prefix-target,0L); // Earlier prefixes differing by target delimit exactly the desired subarrays.
            frequency.merge(prefix,1L,Long::sum); // Record this prefix only after querying to exclude empty intervals.
        }
        return answer; // Negative values need no special treatment because prefix subtraction is exact.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new int[]{1,-1,0},0))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new int[]{1,-1,0},0), 3L); // Case 1: verify the expected result for this input.
        Check.equal(solve(new int[]{1,1,1},2), 2L); // Case 2: verify the expected result for this input.
        Check.equal(solve(new int[]{},0), 0L); // Case 3: verify the expected result for this input.
    }
}
