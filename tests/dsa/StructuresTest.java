package dsa;

import dsa.linkedlist.SinglyLinkedList;
import dsa.stacksqueues.StackQueueProblems;
import dsa.hashing.HashingProblems;
import dsa.hashing.LruCache;
import dsa.trees.BinarySearchTree;
import dsa.heaps.HeapProblems;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeSet;

/** Dependency-free checks; failures throw even when Java assertions are disabled. */
public final class StructuresTest {
    private static int checks;

    private static void check(boolean condition, String label) {
        checks++;
        if (!condition) throw new AssertionError(label);
    }

    private static void array(int[] actual, int[] expected, String label) {
        check(Arrays.equals(actual, expected), label + ": got " + Arrays.toString(actual));
    }

    private static void rejects(Class<? extends Throwable> type, Runnable action, String label) {
        try { action.run(); }
        catch (Throwable failure) {
            check(type.isInstance(failure), label + ": wrong exception " + failure);
            return;
        }
        throw new AssertionError(label + ": no exception");
    }

    public static void main(String[] args) {
        lists();
        stacksAndQueues();
        hashingAndCache();
        trees();
        heaps();
        System.out.println("StructuresTest: " + checks + " checks passed");
    }

    private static void lists() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.reverse();
        check(list.isEmpty() && !list.contains(1) && !list.removeFirstOccurrence(1), "empty list");
        list.addLast(2);
        list.addFirst(1);
        list.addLast(2);
        list.addLast(3);
        check(list.size() == 4 && list.contains(3), "size and search");
        check(list.removeFirstOccurrence(2), "remove first duplicate");
        array(list.toArray(), new int[]{1, 2, 3}, "later duplicate remains");
        list.reverse();
        list.addLast(0);
        array(list.toArray(), new int[]{3, 2, 1, 0}, "reverse updates tail");
        check(list.removeFirstOccurrence(3) && list.removeFirstOccurrence(0), "remove head and tail");
        array(list.toArray(), new int[]{2, 1}, "end removal");
        list.removeFirstOccurrence(2);
        list.removeFirstOccurrence(1);
        check(list.size() == 0 && list.isEmpty(), "singleton removal empties list");
        list.addFirst(7);
        list.reverse();
        list.addLast(8);
        array(list.toArray(), new int[]{7, 8}, "reusable after empty");
        int[] snapshot = list.toArray();
        snapshot[0] = 100;
        check(list.contains(7), "snapshot does not expose internals");
    }

    private static void stacksAndQueues() {
        check(StackQueueProblems.balancedBrackets("") && StackQueueProblems.balancedBrackets("{[()]}"), "balanced input");
        check(!StackQueueProblems.balancedBrackets("([)]") && !StackQueueProblems.balancedBrackets(")(")
                && !StackQueueProblems.balancedBrackets("(("), "mismatches");
        rejects(IllegalArgumentException.class, () -> StackQueueProblems.balancedBrackets(")x"), "validates whole alphabet");
        rejects(NullPointerException.class, () -> StackQueueProblems.balancedBrackets(null), "null brackets");
        array(StackQueueProblems.nextGreaterIndices(new int[]{2, 1, 4, 3}), new int[]{2, 2, -1, -1}, "next greater");
        array(StackQueueProblems.nextGreaterIndices(new int[]{-2, -1, -1}), new int[]{1, -1, -1}, "strict greater and negative values");
        array(StackQueueProblems.nextGreaterIndices(new int[]{}), new int[]{}, "empty next greater");
        array(StackQueueProblems.slidingWindowMaximum(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3),
                new int[]{3, 3, 5, 5, 6, 7}, "rolling maximum");
        array(StackQueueProblems.slidingWindowMaximum(new int[]{2, 2, 2}, 2), new int[]{2, 2}, "duplicate maxima");
        array(StackQueueProblems.slidingWindowMaximum(new int[]{-2, -4}, 1), new int[]{-2, -4}, "unit window");
        array(StackQueueProblems.slidingWindowMaximum(new int[]{-2, -4}, 2), new int[]{-2}, "full window");
        rejects(IllegalArgumentException.class, () -> StackQueueProblems.slidingWindowMaximum(new int[]{}, 1), "empty window input");
        rejects(IllegalArgumentException.class, () -> StackQueueProblems.slidingWindowMaximum(new int[]{1}, 0), "zero window");
        Random random = new Random(2026);
        for (int trial = 0; trial < 60; trial++) {
            int[] values = new int[1 + random.nextInt(15)];
            for (int i = 0; i < values.length; i++) values[i] = random.nextInt(11) - 5;
            int[] next = new int[values.length];
            Arrays.fill(next, -1);
            for (int i = 0; i < values.length; i++) {
                for (int j = i + 1; j < values.length; j++) {
                    if (values[j] > values[i]) { next[i] = j; break; }
                }
            }
            array(StackQueueProblems.nextGreaterIndices(values), next, "next greater vs brute force");
            int k = 1 + random.nextInt(values.length);
            int[] maxima = new int[values.length - k + 1];
            for (int i = 0; i < maxima.length; i++) {
                maxima[i] = Integer.MIN_VALUE;
                for (int j = i; j < i + k; j++) maxima[i] = Math.max(maxima[i], values[j]);
            }
            array(StackQueueProblems.slidingWindowMaximum(values, k), maxima, "window vs brute force");
        }
    }

    private static void hashingAndCache() {
        check(HashingProblems.frequencies(new int[]{2, 1, 2}).equals(Map.of(1, 1, 2, 2)), "frequencies");
        array(HashingProblems.twoSum(new int[]{3, 3}, 6), new int[]{0, 1}, "duplicate pair");
        array(HashingProblems.twoSum(new int[]{3}, 6), new int[]{}, "cannot reuse element");
        array(HashingProblems.twoSum(new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE}, -2), new int[]{}, "overflow is not a valid sum");
        check(HashingProblems.countSubarraysWithSum(new int[]{1, -1, 0}, 0) == 3, "negative ledger");
        check(HashingProblems.countSubarraysWithSum(new int[]{0, 0, 0}, 0) == 6, "repeated prefix frequency");
        check(HashingProblems.countSubarraysWithSum(new int[]{}, 0) == 0, "no empty subarray counted");
        check(HashingProblems.countSubarraysWithSum(new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE}, 4294967294L) == 1, "long sum");
        check(HashingProblems.countSubarraysWithSum(new int[]{1}, Long.MIN_VALUE) == 0, "extreme target");
        Random random = new Random(7);
        for (int trial = 0; trial < 80; trial++) {
            int[] values = new int[random.nextInt(15)];
            for (int i = 0; i < values.length; i++) values[i] = random.nextInt(9) - 4;
            long target = random.nextInt(11) - 5;
            long expected = 0;
            for (int start = 0; start < values.length; start++) {
                long sum = 0;
                for (int end = start; end < values.length; end++) {
                    sum += values[end];
                    if (sum == target) expected++;
                }
            }
            check(HashingProblems.countSubarraysWithSum(values, target) == expected, "prefix counts vs brute force");
        }
        LruCache<String, Integer> cache = new LruCache<>(2);
        cache.put("A", 1);
        cache.put("B", 2);
        check(cache.get("A") == 1, "cache hit");
        cache.put("C", 3);
        check(cache.get("B") == null, "evicts least recent");
        check(cache.keysMostRecentFirst().equals(List.of("C", "A")), "recency order");
        cache.put("A", 10);
        check(cache.size() == 2 && cache.keysMostRecentFirst().equals(List.of("A", "C")), "update moves to front without growth");
        check(cache.get("A") == 10, "updated value");
        LruCache<Integer, String> one = new LruCache<>(1);
        one.put(1, "first");
        one.put(2, "second");
        check(one.get(1) == null && one.get(2).equals("second"), "capacity one");
        rejects(IllegalArgumentException.class, () -> new LruCache<>(0), "zero cache capacity");
        rejects(NullPointerException.class, () -> cache.put("X", null), "null value");
        rejects(NullPointerException.class, () -> cache.get(null), "null key");
    }

    private static void trees() {
        BinarySearchTree tree = new BinarySearchTree();
        check(tree.height() == -1 && tree.inorder().isEmpty() && !tree.delete(2), "empty tree");
        for (int value : new int[]{8, 3, 10, 1, 6, 14, 4, 7, 13}) tree.insert(value);
        check(!tree.insert(8) && tree.size() == 9, "set duplicate policy");
        check(tree.contains(7) && !tree.contains(5), "BST lookup");
        check(tree.delete(8), "delete two-child root");
        check(tree.inorder().equals(List.of(1, 3, 4, 6, 7, 10, 13, 14)), "successor preserves order");
        check(tree.delete(14) && tree.delete(1), "delete one-child and leaf");
        check(tree.inorder().equals(List.of(3, 4, 6, 7, 10, 13)), "all deletion cases");
        BinarySearchTree singleton = new BinarySearchTree();
        singleton.insert(1);
        check(singleton.height() == 0 && singleton.delete(1) && singleton.size() == 0, "singleton root");
        BinarySearchTree skewed = new BinarySearchTree();
        for (int i = 0; i < 20; i++) skewed.insert(i);
        check(skewed.height() == 19, "sorted insertion degenerates");
        Random random = new Random(42);
        BinarySearchTree actual = new BinarySearchTree();
        TreeSet<Integer> oracle = new TreeSet<>();
        for (int i = 0; i < 300; i++) {
            int value = random.nextInt(50) - 25;
            if (random.nextBoolean()) check(actual.insert(value) == oracle.add(value), "BST insertion vs TreeSet");
            else check(actual.delete(value) == oracle.remove(value), "BST deletion vs TreeSet");
            check(actual.inorder().equals(new ArrayList<>(oracle)) && actual.size() == oracle.size(), "BST order/size invariant");
        }
    }

    private static void heaps() {
        check(HeapProblems.kthLargest(new int[]{5, 5, 1}, 2) == 5, "rank counts duplicates");
        check(HeapProblems.kthLargest(new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE}, 1) == Integer.MAX_VALUE, "heap comparison overflow");
        rejects(IllegalArgumentException.class, () -> HeapProblems.kthLargest(new int[]{}, 1), "invalid kth rank");
        array(HeapProblems.mergeSortedArrays(new int[][]{{1, 4, 9}, {2, 3, 8}, {}, {5, 7}}),
                new int[]{1, 2, 3, 4, 5, 7, 8, 9}, "k-way merge");
        array(HeapProblems.mergeSortedArrays(new int[][]{}), new int[]{}, "no streams");
        array(HeapProblems.mergeSortedArrays(new int[][]{{Integer.MAX_VALUE}, {Integer.MIN_VALUE, 0}}),
                new int[]{Integer.MIN_VALUE, 0, Integer.MAX_VALUE}, "safe comparator");
        rejects(IllegalArgumentException.class, () -> HeapProblems.mergeSortedArrays(new int[][]{{2, 1}}), "unsorted input rejected");
        rejects(NullPointerException.class, () -> HeapProblems.mergeSortedArrays(new int[][]{null}), "null stream rejected");
        Random random = new Random(99);
        for (int trial = 0; trial < 60; trial++) {
            int[] values = new int[1 + random.nextInt(15)];
            for (int i = 0; i < values.length; i++) values[i] = random.nextInt(21) - 10;
            int[] sorted = values.clone();
            Arrays.sort(sorted);
            int k = 1 + random.nextInt(values.length);
            check(HeapProblems.kthLargest(values, k) == sorted[sorted.length - k], "kth largest vs sorting");
        }
    }
}
