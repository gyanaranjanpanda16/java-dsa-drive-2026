package dsa.searchsort;

import java.util.Arrays;
import java.util.Objects;

/** Searches require sorted input. Merge sort changes the supplied array. */
public final class SearchSort {
    private SearchSort() { }

    /** Any matching index, or -1. Sorted nondecreasing input. Time O(log n), space O(1). */
    public static int binarySearch(int[] sorted, int target) {
        Objects.requireNonNull(sorted, "sorted");
        int low = 0;
        int high = sorted.length - 1;
        while (low <= high) {
            int middle = low + (high - low) / 2;
            if (sorted[middle] == target) {
                return middle;
            } else if (sorted[middle] < target) {
                low = middle + 1;
            } else {
                high = middle - 1;
            }
        }
        return -1;
    }

    /** First index with value >= target, or n. Time O(log n), extra space O(1). */
    public static int lowerBound(int[] sorted, int target) {
        Objects.requireNonNull(sorted, "sorted");
        int low = 0;
        int high = sorted.length; // Candidate interval is [low, high).
        while (low < high) {
            int middle = low + (high - low) / 2;
            if (sorted[middle] < target) {
                low = middle + 1;
            } else {
                high = middle;
            }
        }
        return low;
    }

    /** Stable merge sort. Time O(n log n); O(n) buffer plus O(log n) recursive stack. */
    public static void mergeSort(int[] values) {
        Objects.requireNonNull(values, "values");
        mergeSort(values, new int[values.length], 0, values.length);
    }

    private static void mergeSort(int[] values, int[] buffer, int from, int to) {
        if (to - from <= 1) {
            return;
        }
        int middle = from + (to - from) / 2;
        mergeSort(values, buffer, from, middle);
        mergeSort(values, buffer, middle, to);
        int left = from;
        int right = middle;
        int write = from;
        while (left < middle && right < to) {
            // Taking from the left on equality preserves relative order of equal keys.
            buffer[write++] = values[left] <= values[right] ? values[left++] : values[right++];
        }
        while (left < middle) {
            buffer[write++] = values[left++];
        }
        while (right < to) {
            buffer[write++] = values[right++];
        }
        System.arraycopy(buffer, from, values, from, to - from);
    }

    public static void main(String[] args) {
        int[] prices = {9, 3, 7, 3, -1};
        mergeSort(prices);
        System.out.println("Sorted prices: " + Arrays.toString(prices));
        System.out.println("First price >= 3 at index: " + lowerBound(prices, 3));
        System.out.println("Price 7 found at index: " + binarySearch(prices, 7));
    }
}
