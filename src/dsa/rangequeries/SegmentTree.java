package dsa.rangequeries;

import java.util.Objects;

/** Iterative sum segment tree: point replacement and half-open range queries. */
public final class SegmentTree {
    private final int size;
    private final long[] tree;
    public SegmentTree(long[] values) {
        Objects.requireNonNull(values, "values");
        size = values.length;
        tree = new long[Math.multiplyExact(2, size)];
        System.arraycopy(values, 0, tree, size, size);
        for (int i = size - 1; i > 0; i--) tree[i] = tree[2 * i] + tree[2 * i + 1];
    }
    public int size() { return size; }
    /** Replace a value; O(log n) time, O(1) auxiliary space. */
    public void set(int index, long value) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("index: " + index);
        int position = index + size;
        tree[position] = value;
        while (position > 1) {
            position /= 2;
            tree[position] = tree[2 * position] + tree[2 * position + 1];
        }
    }
    /** O(log n) time, O(1) auxiliary space. All intermediate sums must fit long. */
    public long rangeSum(int left, int right) {
        if (left < 0 || left > right || right > size) throw new IndexOutOfBoundsException("range");
        long sum = 0;
        for (left += size, right += size; left < right; left /= 2, right /= 2) {
            // Consume exposed boundary nodes; the remainder consists of whole parents.
            if ((left & 1) == 1) sum += tree[left++];
            if ((right & 1) == 1) sum += tree[--right];
        }
        return sum;
    }
    public static void main(String[] args) {
        SegmentTree stock = new SegmentTree(new long[]{5, 3, 8, 2, 7});
        System.out.println("Warehouse [1,4): " + stock.rangeSum(1, 4));
        stock.set(2, 10);
        System.out.println("After replacement: " + stock.rangeSum(1, 4));
    }
}
