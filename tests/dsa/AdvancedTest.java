package dsa;

import dsa.algorithms.AlgorithmPatterns;
import dsa.algorithms.AlgorithmPatterns.Interval;
import dsa.graphs.DisjointSet;
import dsa.graphs.GraphAlgorithms;
import dsa.graphs.GraphAlgorithms.Edge;
import dsa.rangequeries.FenwickTree;
import dsa.rangequeries.SegmentTree;
import dsa.tries.Trie;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.LinkedList;
import java.util.Random;

/** Dependency-free regression and seeded oracle checks; assertions are always active. */
public final class AdvancedTest {
    private static int checks;
    private static void check(boolean condition, String message) {
        checks++;
        if (!condition) throw new AssertionError(message);
    }
    private static void fails(Class<? extends Throwable> expected, Runnable action) {
        checks++;
        try { action.run(); }
        catch (Throwable failure) {
            if (expected.isInstance(failure)) return;
            throw new AssertionError("Expected " + expected.getName() + " but got " + failure, failure);
        }
        throw new AssertionError("Expected " + expected.getName());
    }
    public static void main(String[] args) {
        graphs(); connectivity(); tries(); ranges(); patterns();
        System.out.println("AdvancedTest: " + checks + " checks passed");
    }
    private static void graphs() {
        int[][] graph = {{1, 2}, {2, 3}, {3}, {}, {4}};
        check(Arrays.equals(GraphAlgorithms.bfsDistances(graph, 0), new int[]{0, 1, 1, 2, -1}), "BFS distances");
        check(GraphAlgorithms.dfs(graph, 0).equals(List.of(0, 1, 2, 3)), "DFS follows neighbor order deeply");
        check(GraphAlgorithms.topologicalOrder(new int[0][]).isEmpty(), "empty DAG");
        fails(IllegalArgumentException.class, () -> GraphAlgorithms.topologicalOrder(graph));
        fails(IllegalArgumentException.class, () -> GraphAlgorithms.topologicalOrder(new int[][]{{1}, {0}}));
        fails(IndexOutOfBoundsException.class, () -> GraphAlgorithms.bfsDistances(new int[][]{{2}}, 0));
        fails(IndexOutOfBoundsException.class, () -> GraphAlgorithms.dfs(new int[0][], 0));
        int[][] dag = {{1, 2}, {3}, {3}, {}, {}};
        List<Integer> order = GraphAlgorithms.topologicalOrder(dag);
        check(new HashSet<>(order).size() == 5, "topological permutation");
        for (int i = 0; i < dag.length; i++) for (int j : dag[i]) check(order.indexOf(i) < order.indexOf(j), "dependency order");
        fails(IllegalArgumentException.class, () -> new Edge(0, -1));
        fails(IndexOutOfBoundsException.class, () -> GraphAlgorithms.dijkstra(List.of(List.of(new Edge(2, 0))), 0));
        List<List<Edge>> large = List.of(List.of(new Edge(1, Integer.MAX_VALUE)),
                List.of(new Edge(2, Integer.MAX_VALUE)), List.of(), List.of());
        check(Arrays.equals(GraphAlgorithms.dijkstra(large, 0),
                new long[]{0, Integer.MAX_VALUE, 2L * Integer.MAX_VALUE, Long.MAX_VALUE}), "long paths and unreachable");
        check(Arrays.equals(GraphAlgorithms.dijkstra(new LinkedList<>(large), 0),
                GraphAlgorithms.dijkstra(large, 0)), "sequential outer graph list is supported");
        Random random = new Random(7601);
        for (int trial = 0; trial < 40; trial++) {
            int n = 6;
            long[][] oracle = new long[n][n];
            List<List<Edge>> weighted = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                Arrays.fill(oracle[i], 1_000_000L);
                oracle[i][i] = 0;
                List<Edge> edges = new ArrayList<>();
                for (int j = 0; j < n; j++) if (i != j && random.nextBoolean()) {
                    int weight = random.nextInt(15);
                    edges.add(new Edge(j, weight));
                    oracle[i][j] = weight;
                }
                weighted.add(edges);
            }
            // Independent all-pairs algorithm checks lazy heap and zero-weight edges.
            for (int k = 0; k < n; k++) for (int i = 0; i < n; i++) for (int j = 0; j < n; j++)
                oracle[i][j] = Math.min(oracle[i][j], oracle[i][k] + oracle[k][j]);
            for (int source = 0; source < n; source++) {
                long[] actual = GraphAlgorithms.dijkstra(weighted, source);
                for (int j = 0; j < n; j++) check(actual[j] == (oracle[source][j] == 1_000_000L ? Long.MAX_VALUE : oracle[source][j]), "Dijkstra oracle");
            }
        }
    }
    private static void connectivity() {
        DisjointSet dsu = new DisjointSet(5);
        check(dsu.components() == 5, "initial components");
        check(dsu.union(0, 1) && dsu.union(1, 2), "unions");
        check(!dsu.union(2, 0), "redundant union");
        check(dsu.connected(0, 2) && !dsu.connected(0, 4), "connectivity");
        check(dsu.componentSize(2) == 3 && dsu.components() == 3, "component sizes");
        fails(IndexOutOfBoundsException.class, () -> dsu.find(5));
        fails(IllegalArgumentException.class, () -> new DisjointSet(-1));
        check(new DisjointSet(0).components() == 0, "empty DSU");
    }
    private static void tries() {
        Trie trie = new Trie();
        check(trie.startsWith("") && !trie.contains(""), "empty trie");
        trie.insert("car"); trie.insert("cart"); trie.insert("cat"); trie.insert("car");
        check(trie.startsWith("ca") && !trie.contains("ca"), "prefix versus word");
        check(trie.contains("car") && trie.contains("cart") && !trie.contains("cars"), "word search");
        fails(IllegalArgumentException.class, () -> trie.insert("dog!"));
        check(!trie.startsWith("dog"), "invalid insertion is atomic");
        fails(IllegalArgumentException.class, () -> trie.contains("CAR"));
        fails(NullPointerException.class, () -> trie.insert(null));
        trie.insert(""); check(trie.contains(""), "empty word insertion");
    }
    private static void ranges() {
        Random random = new Random(90210);
        for (int n = 0; n <= 17; n++) {
            long[] values = new long[n];
            for (int i = 0; i < n; i++) values[i] = random.nextInt(41) - 20;
            FenwickTree fenwick = new FenwickTree(values);
            SegmentTree segment = new SegmentTree(values);
            for (int trial = 0; trial < 150; trial++) {
                int left = random.nextInt(n + 1);
                int right = left + random.nextInt(n - left + 1);
                long expected = 0;
                for (int i = left; i < right; i++) expected += values[i];
                check(fenwick.rangeSum(left, right) == expected, "Fenwick direct-sum oracle");
                check(segment.rangeSum(left, right) == expected, "segment direct-sum oracle");
                if (n > 0) {
                    int index = random.nextInt(n);
                    long replacement = random.nextInt(101) - 50;
                    fenwick.add(index, replacement - values[index]);
                    segment.set(index, replacement);
                    values[index] = replacement;
                }
            }
            fails(IndexOutOfBoundsException.class, () -> fenwick.add(-1, 2));
            fails(IndexOutOfBoundsException.class, () -> segment.set(segment.size(), 2));
            fails(IndexOutOfBoundsException.class, () -> fenwick.rangeSum(1, 0));
            fails(IndexOutOfBoundsException.class, () -> segment.rangeSum(-1, 0));
        }
        long big = (long) Integer.MAX_VALUE + 50;
        check(new FenwickTree(new long[]{big, big}).rangeSum(0, 2) == 2 * big, "long Fenwick sums");
        check(new SegmentTree(new long[]{big, big}).rangeSum(0, 2) == 2 * big, "long segment sums");
    }
    private static void patterns() {
        List<List<Integer>> subsets = AlgorithmPatterns.subsets(new int[]{1, 2, 3});
        check(subsets.size() == 8 && new HashSet<>(subsets).size() == 8, "all subsets once");
        check(AlgorithmPatterns.subsets(new int[0]).equals(List.of(List.of())), "empty power set");
        List<Interval> intervals = List.of(new Interval(1, 5), new Interval(1, 2), new Interval(2, 3), new Interval(3, 4));
        check(AlgorithmPatterns.schedule(intervals).size() == 3, "earliest finish scheduling");
        check(AlgorithmPatterns.schedule(List.of()).isEmpty(), "empty schedule");
        fails(IllegalArgumentException.class, () -> new Interval(1, 1));
        check(AlgorithmPatterns.coinChange(new int[]{1, 3, 4}, 6) == 2, "greedy counterexample solved by DP");
        check(AlgorithmPatterns.coinChange(new int[]{2}, 3) == -1, "impossible amount");
        check(AlgorithmPatterns.coinChange(new int[0], 0) == 0, "zero base case");
        check(AlgorithmPatterns.coinChange(new int[0], 5) == -1, "no denominations");
        fails(IllegalArgumentException.class, () -> AlgorithmPatterns.coinChange(new int[]{0}, 0));
        fails(IllegalArgumentException.class, () -> AlgorithmPatterns.coinChange(new int[]{1}, -1));
        check(AlgorithmPatterns.lcsLength("abcde", "ace") == 3, "LCS subsequence");
        check(AlgorithmPatterns.lcsLength("", "abc") == 0, "empty LCS");
        check(AlgorithmPatterns.lcsLength("aaa", "aa") == 2, "repeated characters LCS");
        check(AlgorithmPatterns.lcsLength("ab", "ba") == 1, "LCS order");
    }
}
