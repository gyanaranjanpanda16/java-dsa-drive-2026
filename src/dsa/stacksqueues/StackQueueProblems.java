package dsa.stacksqueues;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Objects;

/** LIFO, FIFO and monotonic deques solve different ordering requirements. */
public final class StackQueueProblems {
    private StackQueueProblems() { }

    /**
     * Input may contain only ()[]{}. Empty input is balanced; null is rejected.
     * O(n) time, O(n) space. The stack stores unmatched opening brackets.
     */
    public static boolean balancedBrackets(String text) {
        Objects.requireNonNull(text, "text");
        // Validate first so invalid characters are rejected even after a mismatch.
        for (int i = 0; i < text.length(); i++) {
            if ("()[]{}".indexOf(text.charAt(i)) < 0) {
                throw new IllegalArgumentException("Only bracket characters are allowed");
            }
        }
        Deque<Character> openings = new ArrayDeque<>();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == '(' || c == '[' || c == '{') openings.push(c);
            else {
                if (openings.isEmpty()) return false;
                char opening = openings.pop();
                if ((c == ')' && opening != '(') || (c == ']' && opening != '[')
                        || (c == '}' && opening != '{')) return false;
            }
        }
        return openings.isEmpty();
    }

    /**
     * For each position, returns the nearest index to its right with a STRICTLY
     * greater value, or -1. Indices avoid ambiguity when actual values are -1.
     * O(n) time, O(n) space: every index is pushed once and popped at most once.
     */
    public static int[] nextGreaterIndices(int[] values) {
        Objects.requireNonNull(values, "values");
        int[] result = new int[values.length];
        Arrays.fill(result, -1);
        Deque<Integer> unresolved = new ArrayDeque<>();
        for (int i = 0; i < values.length; i++) {
            while (!unresolved.isEmpty() && values[i] > values[unresolved.peek()]) {
                result[unresolved.pop()] = i;
            }
            unresolved.push(i);
        }
        return result;
    }

    /**
     * Maximum of each contiguous window. Requires 1 <= k <= values.length.
     * O(n) time, O(k) working space plus O(n-k+1) output space; does not mutate.
     */
    public static int[] slidingWindowMaximum(int[] values, int k) {
        Objects.requireNonNull(values, "values");
        if (k < 1 || k > values.length) throw new IllegalArgumentException("Invalid window size");
        int[] result = new int[values.length - k + 1];
        Deque<Integer> candidates = new ArrayDeque<>();
        for (int i = 0; i < values.length; i++) {
            while (!candidates.isEmpty() && candidates.peekFirst() <= i - k) {
                candidates.removeFirst(); // Expired indices cannot answer this window.
            }
            while (!candidates.isEmpty() && values[candidates.peekLast()] <= values[i]) {
                // The newer value is at least as large and expires later.
                candidates.removeLast();
            }
            candidates.addLast(i);
            if (i >= k - 1) result[i - k + 1] = values[candidates.peekFirst()];
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("Balanced {[()]}: " + balancedBrackets("{[()]}"));
        System.out.println("Next greater indices: " + Arrays.toString(nextGreaterIndices(new int[]{2, 1, 4, 3})));
        System.out.println("Three-reading peak: " + Arrays.toString(
                slidingWindowMaximum(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
        Deque<String> jobs = new ArrayDeque<>();
        jobs.offerLast("receipt-1");
        jobs.offerLast("receipt-2");
        while (!jobs.isEmpty()) System.out.println("Process FIFO: " + jobs.pollFirst());
    }
}
