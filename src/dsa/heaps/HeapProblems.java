package dsa.heaps;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;

public final class HeapProblems {
    private HeapProblems() { }

    /**
     * kth largest counting duplicate values separately; input is not mutated.
     * Requires 1 <= k <= n. O(n log(k+1)) time, O(k) working space.
     */
    public static int kthLargest(int[] values, int k) {
        Objects.requireNonNull(values, "values");
        if (k < 1 || k > values.length) throw new IllegalArgumentException("Invalid rank");
        PriorityQueue<Integer> largest = new PriorityQueue<>();
        for (int value : values) {
            largest.offer(value);
            if (largest.size() > k) largest.poll();
            // The heap retains the k largest values seen; its minimum is kth largest.
        }
        return largest.element();
    }

    private record Cursor(int value, int array, int index) { }

    /**
     * Merge ascending sorted arrays, including empty ones; validates sortedness.
     * If N is total items and m is array count: O(m + N log(m+1)) time,
     * O(m) working space plus O(N) result. Null arrays/rows are rejected.
     */
    public static int[] mergeSortedArrays(int[][] arrays) {
        Objects.requireNonNull(arrays, "arrays");
        int length = 0;
        PriorityQueue<Cursor> heads = new PriorityQueue<>(Comparator.comparingInt(Cursor::value));
        for (int i = 0; i < arrays.length; i++) {
            Objects.requireNonNull(arrays[i], "row");
            length = Math.addExact(length, arrays[i].length);
            for (int j = 1; j < arrays[i].length; j++) {
                if (arrays[i][j - 1] > arrays[i][j]) throw new IllegalArgumentException("Rows must be sorted");
            }
            if (arrays[i].length > 0) heads.offer(new Cursor(arrays[i][0], i, 0));
        }
        int[] result = new int[length];
        int output = 0;
        while (!heads.isEmpty()) {
            Cursor smallest = heads.poll();
            result[output++] = smallest.value();
            int next = smallest.index() + 1;
            if (next < arrays[smallest.array()].length) {
                heads.offer(new Cursor(arrays[smallest.array()][next], smallest.array(), next));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("Third largest: " + kthLargest(new int[]{3, 2, 1, 5, 6, 4}, 3));
        System.out.println("Merged event timestamps: " + Arrays.toString(
                mergeSortedArrays(new int[][]{{1, 4, 9}, {2, 3, 8}, {}, {5, 7}})));
    }
}
