package dsa.rangequeries;

import java.util.Objects;

/** Point additions and half-open range sums. Values and all sums must fit long. */
public final class FenwickTree {
    private final long[] tree;
    public FenwickTree(long[] values) {
        Objects.requireNonNull(values, "values");
        tree = new long[values.length + 1];
        for (int i = 0; i < values.length; i++) add(i, values[i]); // O(n log n) build.
    }
    public int size() { return tree.length - 1; }
    /** O(log n) time, O(1) extra space; add delta, do not replace the old value. */
    public void add(int index, long delta) {
        if (index < 0 || index >= size()) throw new IndexOutOfBoundsException("index: " + index);
        for (int i = index + 1; i < tree.length; i += i & -i) tree[i] += delta;
    }
    /** Sum of [0, endExclusive). O(log n) time, O(1) extra space. */
    public long prefixSum(int endExclusive) {
        if (endExclusive < 0 || endExclusive > size()) throw new IndexOutOfBoundsException("end");
        long sum = 0;
        // Each step removes the lowest set bit and consumes one disjoint block.
        for (int i = endExclusive; i > 0; i -= i & -i) sum += tree[i];
        return sum;
    }
    /** Sum of [left, right); empty ranges have sum zero. */
    public long rangeSum(int left, int right) {
        if (left < 0 || left > right || right > size()) throw new IndexOutOfBoundsException("range");
        return prefixSum(right) - prefixSum(left);
    }
    public static void main(String[] args) {
        FenwickTree sales = new FenwickTree(new long[]{10, 20, 30, 40});
        System.out.println("Days [1,4): " + sales.rangeSum(1, 4));
        sales.add(2, 5);
        System.out.println("After +5 correction: " + sales.rangeSum(1, 4));
    }
}
