package dsa.arrays;

import java.util.Arrays;
import java.util.Objects;
import java.util.OptionalInt;
import java.util.OptionalLong;

/** Array building blocks. Null arrays are rejected; empty-array behavior is explicit. */
public final class ArrayProblems {
    private ArrayProblems() { }

    /** Time O(n), extra space O(1). long prevents overflow when summing int elements. */
    public static long sum(int[] values) {
        Objects.requireNonNull(values, "values");
        long total = 0;
        for (int value : values) {
            total += value;
        }
        return total;
    }

    /** Empty input has no maximum. Time O(n), extra space O(1). */
    public static OptionalInt max(int[] values) {
        Objects.requireNonNull(values, "values");
        if (values.length == 0) {
            return OptionalInt.empty();
        }
        int largest = values[0];
        for (int value : values) {
            largest = Math.max(largest, value);
        }
        return OptionalInt.of(largest);
    }

    /** Duplicates of the maximum do not count as second largest. Time O(n), space O(1). */
    public static OptionalInt secondLargestDistinct(int[] values) {
        Objects.requireNonNull(values, "values");
        Integer largest = null;
        Integer second = null;
        for (int value : values) {
            if (largest == null || value > largest) {
                second = largest;
                largest = value;
            } else if (value < largest && (second == null || value > second)) {
                second = value;
            }
        }
        return second == null ? OptionalInt.empty() : OptionalInt.of(second);
    }

    /** Returns the first matching index, or -1. Time O(n), extra space O(1). */
    public static int linearSearch(int[] values, int target) {
        Objects.requireNonNull(values, "values");
        for (int i = 0; i < values.length; i++) {
            if (values[i] == target) {
                return i;
            }
        }
        return -1;
    }

    /** Changes the supplied array. Time O(n), extra space O(1). */
    public static void reverse(int[] values) {
        Objects.requireNonNull(values, "values");
        reverseRange(values, 0, values.length - 1);
    }

    private static void reverseRange(int[] values, int left, int right) {
        while (left < right) {
            int temporary = values[left];
            values[left++] = values[right];
            values[right--] = temporary;
        }
    }

    /** Stable in-place compaction: nonzero values keep their order. Time O(n), space O(1). */
    public static void moveZeros(int[] values) {
        Objects.requireNonNull(values, "values");
        int write = 0;
        for (int read = 0; read < values.length; read++) {
            if (values[read] != 0) {
                // write never passes read, so unread elements are not overwritten.
                values[write++] = values[read];
            }
        }
        while (write < values.length) {
            values[write++] = 0;
        }
    }

    /** Right rotation; negative k means left rotation. Time O(n), extra space O(1). */
    public static void rotateRight(int[] values, int k) {
        Objects.requireNonNull(values, "values");
        if (values.length == 0) {
            return;
        }
        int shift = Math.floorMod(k, values.length);
        // AB -> reverse(B)reverse(A) -> B reverse(A) -> BA.
        reverseRange(values, 0, values.length - 1);
        reverseRange(values, 0, shift - 1);
        reverseRange(values, shift, values.length - 1);
    }

    /** Nondecreasing order, so equal adjacent values are allowed. Time O(n), space O(1). */
    public static boolean isSorted(int[] values) {
        Objects.requireNonNull(values, "values");
        for (int i = 1; i < values.length; i++) {
            if (values[i - 1] > values[i]) {
                return false;
            }
        }
        return true;
    }

    /** prefix[i] is the sum of values[0..i), with prefix[0] = 0. Time/space O(n). */
    public static long[] prefixSums(int[] values) {
        Objects.requireNonNull(values, "values");
        long[] prefix = new long[values.length + 1];
        for (int i = 0; i < values.length; i++) {
            prefix[i + 1] = prefix[i] + values[i];
        }
        return prefix;
    }

    /** Sum of [from, to), using a prefix array produced above. Time/extra space O(1). */
    public static long rangeSum(long[] prefix, int from, int to) {
        Objects.requireNonNull(prefix, "prefix");
        if (prefix.length == 0 || from < 0 || to < from || to >= prefix.length) {
            throw new IllegalArgumentException("Require 0 <= from <= to <= original length");
        }
        return prefix[to] - prefix[from];
    }

    /** Best NONEMPTY contiguous sum; empty input has no answer. Time O(n), space O(1). */
    public static OptionalLong maxSubarray(int[] values) {
        Objects.requireNonNull(values, "values");
        if (values.length == 0) {
            return OptionalLong.empty();
        }
        long endingHere = values[0];
        long best = endingHere;
        for (int i = 1; i < values.length; i++) {
            // Any best segment ending at i either starts at i or extends the previous one.
            endingHere = Math.max((long) values[i], endingHere + values[i]);
            best = Math.max(best, endingHere);
        }
        return OptionalLong.of(best);
    }

    /**
     * Input must be sorted nondecreasing. Returns two distinct indices, or an empty array.
     * Time O(n), extra space O(1); does not sort or mutate input.
     */
    public static int[] twoSumSorted(int[] values, long target) {
        Objects.requireNonNull(values, "values");
        int left = 0;
        int right = values.length - 1;
        while (left < right) {
            long current = (long) values[left] + values[right];
            if (current == target) {
                return new int[] {left, right};
            } else if (current < target) {
                left++; // Even the largest available partner is too small for this left.
            } else {
                right--; // Even the smallest available partner is too large for this right.
            }
        }
        return new int[0];
    }

    public static void main(String[] args) {
        int[] readings = {0, 3, 0, 7, 2};
        System.out.println("Sum: " + sum(readings));
        System.out.println("Second distinct largest: " + secondLargestDistinct(readings));
        moveZeros(readings);
        System.out.println("Compact: " + Arrays.toString(readings));
        rotateRight(readings, 2);
        System.out.println("Rotate: " + Arrays.toString(readings));
        System.out.println("Days [1,4) sales: " + rangeSum(prefixSums(new int[] {5, 2, 7, 1}), 1, 4));
        System.out.println("Best profit interval: " + maxSubarray(new int[] {-2, 3, -1, 4, -5}));
        System.out.println("Two-sum indices: " + Arrays.toString(twoSumSorted(new int[] {1, 3, 5, 8}, 9)));
    }
}
