package dsa.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/** Patterns are ways to solve problems, not data structures themselves. */
public final class AlgorithmPatterns {
    private AlgorithmPatterns() { }
    public record Interval(int start, int end) {
        public Interval {
            if (start >= end) throw new IllegalArgumentException("interval must have positive length");
        }
    }

    /**
     * Subsets by positions; duplicate input values can produce duplicate lists.
     * O(n * 2^n) time/output storage; O(n) recursion and working-list space.
     */
    public static List<List<Integer>> subsets(int[] values) {
        Objects.requireNonNull(values, "values");
        List<List<Integer>> result = new ArrayList<>();
        choose(values, 0, new ArrayList<>(), result);
        return result;
    }
    private static void choose(int[] values, int index, List<Integer> current, List<List<Integer>> result) {
        if (index == values.length) { result.add(new ArrayList<>(current)); return; }
        choose(values, index + 1, current, result); // Exclude this position.
        current.add(values[index]);
        choose(values, index + 1, current, result); // Include this position.
        current.remove(current.size() - 1); // Restore state for the caller.
    }

    /** Maximum count of non-overlapping half-open intervals; touching is allowed. */
    public static List<Interval> schedule(List<Interval> intervals) {
        Objects.requireNonNull(intervals, "intervals");
        List<Interval> sorted = new ArrayList<>(intervals);
        for (Interval interval : sorted) Objects.requireNonNull(interval, "interval");
        sorted.sort(Comparator.comparingInt(Interval::end).thenComparingInt(Interval::start));
        List<Interval> selected = new ArrayList<>();
        long lastEnd = Long.MIN_VALUE;
        // Earliest finish leaves at least as much room as any competing first choice.
        for (Interval interval : sorted) {
            if (interval.start() >= lastEnd) { selected.add(interval); lastEnd = interval.end(); }
        }
        return selected; // O(n log n) time; O(n) extra space, without mutating input.
    }

    /** Unlimited positive denominations; minimum coins, or -1 when impossible. */
    public static int coinChange(int[] coins, int amount) {
        Objects.requireNonNull(coins, "coins");
        if (amount < 0 || amount == Integer.MAX_VALUE) throw new IllegalArgumentException("invalid amount");
        for (int coin : coins) if (coin <= 0) throw new IllegalArgumentException("coins must be positive");
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0; // Zero coins make amount zero; impossible states stay unreachable.
        for (int total = 1; total <= amount; total++) {
            for (int coin : coins) {
                if (coin <= total && dp[total - coin] != Integer.MAX_VALUE) {
                    dp[total] = Math.min(dp[total], dp[total - coin] + 1);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
        // O(amount * numberOfCoins) time, O(amount) space: pseudo-polynomial.
    }

    /** LCS length over Java UTF-16 char units (not grapheme clusters). */
    public static int lcsLength(String first, String second) {
        Objects.requireNonNull(first, "first");
        Objects.requireNonNull(second, "second");
        int[][] dp = new int[first.length() + 1][second.length() + 1];
        for (int i = 1; i <= first.length(); i++) {
            for (int j = 1; j <= second.length(); j++) {
                if (first.charAt(i - 1) == second.charAt(j - 1)) dp[i][j] = 1 + dp[i - 1][j - 1];
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[first.length()][second.length()]; // O(mn) time and space.
    }

    public static void main(String[] args) {
        System.out.println("Feature combinations: " + subsets(new int[]{1, 2, 3}));
        System.out.println("Meeting plan: " + schedule(List.of(new Interval(1, 4),
                new Interval(2, 3), new Interval(3, 5), new Interval(5, 7))));
        System.out.println("Coins for 6 using 1,3,4: " + coinChange(new int[]{1, 3, 4}, 6));
        System.out.println("LCS of abcde and ace: " + lcsLength("abcde", "ace"));
    }
}
