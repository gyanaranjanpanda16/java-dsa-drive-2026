package dsa.hashing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public final class HashingProblems {
    private HashingProblems() { }

    /** Expected O(n) time, O(u) space for u distinct values; first-seen key order. */
    public static Map<Integer, Integer> frequencies(int[] values) {
        Objects.requireNonNull(values, "values");
        Map<Integer, Integer> counts = new LinkedHashMap<>();
        for (int value : values) counts.merge(value, 1, Integer::sum);
        return counts;
    }

    /**
     * Returns two distinct indices, or an empty array when no pair exists.
     * Expected O(n) time, O(n) space. Long arithmetic prevents int overflow.
     */
    public static int[] twoSum(int[] values, int target) {
        Objects.requireNonNull(values, "values");
        Map<Long, Integer> seen = new HashMap<>();
        for (int i = 0; i < values.length; i++) {
            Integer other = seen.get((long) target - values[i]);
            if (other != null) return new int[]{other, i};
            // Query before insertion: the same array element cannot be used twice.
            seen.putIfAbsent((long) values[i], i);
        }
        return new int[0];
    }

    /**
     * Counts contiguous subarrays with sum target, including negative numbers.
     * Expected O(n) time, O(n) space. Prefix sums and answer use long.
     */
    public static long countSubarraysWithSum(int[] values, long target) {
        Objects.requireNonNull(values, "values");
        Map<Long, Long> prefixCounts = new HashMap<>();
        prefixCounts.put(0L, 1L); // Empty prefix permits subarrays starting at index 0.
        long prefix = 0;
        long count = 0;
        for (int value : values) {
            prefix += value;
            try {
                long earlierPrefix = Math.subtractExact(prefix, target);
                count += prefixCounts.getOrDefault(earlierPrefix, 0L);
            } catch (ArithmeticException impossibleDifference) {
                // A difference outside long range cannot match any stored prefix.
            }
            prefixCounts.merge(prefix, 1L, Long::sum);
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println("Product counts: " + frequencies(new int[]{7, 2, 7, 4, 2, 7}));
        System.out.println("Two Sum indices: " + Arrays.toString(twoSum(new int[]{2, 7, 11, 15}, 9)));
        System.out.println("Zero-net transaction intervals: " + countSubarraysWithSum(new int[]{1, -1, 0}, 0));
    }
}
