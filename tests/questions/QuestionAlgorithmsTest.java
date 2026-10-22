package questions;

import java.util.*;
import questions.support.Check;
import questions.q217.Q217NearbyAlmostDuplicate;
import questions.q218.Q218DivisibleSubarrays;
import questions.q222.Q222ExactlyKDistinct;
import questions.q228.Q228CollinearPoints;
import questions.q229.Q229TicketItinerary;
import questions.q244.Q244BubbleSort;
import questions.q245.Q245SelectionSort;
import questions.q246.Q246InsertionSort;
import questions.q247.Q247MergeSort;
import questions.q248.Q248ThreeWayQuickSort;
import questions.q249.Q249CountingSort;
import questions.q250.Q250SignedRadixSort;
import questions.q363.Q363FenwickRangeAddPoint;
import questions.q364.Q364FenwickRangeAddSum;
import questions.q371.Q371LazyRangeSum;
import questions.q372.Q372LazyRangeMinimum;
import questions.q373.Q373SparseTableMinimum;
import questions.q375.Q375PersistentRangeKth;
import questions.q489.Q489ModularInverse;
import questions.q495.Q495ManualLruCache;
import questions.q496.Q496LfuCache;
import questions.q500.Q500SnapshotArray;

/** Differential checks compare optimized answers with independent, deliberately simple models. */
public final class QuestionAlgorithmsTest {
    private QuestionAlgorithmsTest() { }

    public static void main(String[] args) {
        Random random = new Random(20261022L);
        checkArrays(random);
        checkRanges(random);
        checkCaches(random);
        checkSnapshots(random);
        checkInverses();
        Check.throwsType(IllegalArgumentException.class, () -> Q229TicketItinerary.solve(
                new String[][]{{"A", "B"}, {"A", "C"}}, "A"));
        Check.throwsType(IllegalArgumentException.class, () -> Q229TicketItinerary.solve(
                new String[][]{{"A", "B"}, {"C", "D"}}, "A"));
        System.out.println("QuestionAlgorithmsTest: " + Check.count() + " reference-model checks passed.");
    }

    private static void checkArrays(Random random) {
        for (int trial = 0; trial < 200; trial++) {
            int[] input = new int[random.nextInt(21)];
            for (int i = 0; i < input.length; i++) input[i] = random.nextInt(21) - 10;
            int[] untouched = input.clone(), sorted = input.clone();
            Arrays.sort(sorted);
            Check.array(Q244BubbleSort.solve(input), sorted);
            Check.array(Q245SelectionSort.solve(input), sorted);
            Check.array(Q246InsertionSort.solve(input), sorted);
            Check.array(Q247MergeSort.solve(input), sorted);
            Check.array(Q248ThreeWayQuickSort.solve(input), sorted);
            Check.array(Q249CountingSort.solve(input), sorted);
            Check.array(Q250SignedRadixSort.solve(input), sorted);
            Check.array(input, untouched);
            int[] fullWidth = new int[input.length];
            for (int i = 0; i < fullWidth.length; i++) fullWidth[i] = random.nextInt();
            int[] fullSorted = fullWidth.clone(); Arrays.sort(fullSorted);
            Check.array(Q250SignedRadixSort.solve(fullWidth), fullSorted);

            int k = random.nextInt(6), tolerance = random.nextInt(6), modulus = random.nextInt(7) + 1;
            boolean nearby = false;
            for (int i = 0; i < input.length; i++) for (int j = i + 1; j < input.length; j++) {
                if (j - i <= k && Math.abs((long) input[i] - input[j]) <= tolerance) nearby = true;
            }
            Check.equal(Q217NearbyAlmostDuplicate.solve(input, k, tolerance), nearby);
            long divisible = 0, exact = 0;
            for (int left = 0; left < input.length; left++) {
                long sum = 0; Set<Integer> distinct = new HashSet<>();
                for (int right = left; right < input.length; right++) {
                    sum += input[right]; distinct.add(input[right]);
                    if (sum % modulus == 0) divisible++;
                    if (distinct.size() == k) exact++;
                }
            }
            Check.equal(Q218DivisibleSubarrays.solve(input, modulus), divisible);
            Check.equal(Q222ExactlyKDistinct.solve(input, k), exact);

            int[][] points = new int[random.nextInt(9)][2];
            for (int[] point : points) { point[0] = random.nextInt(7) - 3; point[1] = random.nextInt(7) - 3; }
            int maximum = points.length; boolean differentPair = false;
            for (int i = 0; i < points.length; i++) for (int j = i + 1; j < points.length; j++) {
                long dx = points[j][0] - points[i][0], dy = points[j][1] - points[i][1];
                if (dx == 0 && dy == 0) continue;
                if (!differentPair) { maximum = 0; differentPair = true; }
                int count = 0;
                for (int[] point : points) if ((point[0] - points[i][0]) * dy == (point[1] - points[i][1]) * dx) count++;
                maximum = Math.max(maximum, count);
            }
            Check.equal(Q228CollinearPoints.solve(points), maximum);
        }
    }

    private static void checkRanges(Random random) {
        for (int trial = 0; trial < 80; trial++) {
            int n = random.nextInt(12) + 1;
            long[] plain = new long[n];
            int[][] operations = new int[80][4], pointOperations = new int[80][4];
            List<Long> sums = new ArrayList<>(), minima = new ArrayList<>(), points = new ArrayList<>();
            for (int i = 0; i < operations.length; i++) {
                int left = random.nextInt(n), right = left + 1 + random.nextInt(n - left);
                if (random.nextBoolean()) {
                    int delta = random.nextInt(21) - 10;
                    operations[i] = new int[]{0, left, right, delta}; pointOperations[i] = operations[i].clone();
                    for (int at = left; at < right; at++) plain[at] += delta;
                } else {
                    operations[i] = new int[]{1, left, right, 0};
                    long total = 0, minimum = Long.MAX_VALUE;
                    for (int at = left; at < right; at++) { total += plain[at]; minimum = Math.min(minimum, plain[at]); }
                    sums.add(total); minima.add(minimum);
                    int at = random.nextInt(n); pointOperations[i] = new int[]{1, at, 0, 0}; points.add(plain[at]);
                }
            }
            Check.equal(Q364FenwickRangeAddSum.solve(n, operations), sums);
            Check.equal(Q371LazyRangeSum.solve(n, operations), sums);
            Check.equal(Q372LazyRangeMinimum.solve(n, operations), minima);
            Check.equal(Q363FenwickRangeAddPoint.solve(n, pointOperations), points);

            int[] values = new int[n]; for (int i = 0; i < n; i++) values[i] = random.nextInt(21) - 10;
            int[][] queries = new int[30][3], minimumQueries = new int[30][2];
            int[] kth = new int[30], minimum = new int[30];
            for (int i = 0; i < queries.length; i++) {
                int left = random.nextInt(n), right = left + 1 + random.nextInt(n - left), rank = random.nextInt(right - left) + 1;
                queries[i] = new int[]{left, right, rank}; minimumQueries[i] = new int[]{left, right};
                int[] selected = Arrays.copyOfRange(values, left, right); Arrays.sort(selected);
                kth[i] = selected[rank - 1]; minimum[i] = selected[0];
            }
            Check.array(Q375PersistentRangeKth.solve(values, queries), kth);
            Check.array(Q373SparseTableMinimum.solve(values, minimumQueries), minimum);
        }
    }

    private static void checkCaches(Random random) {
        for (int capacity = 0; capacity <= 5; capacity++) for (int trial = 0; trial < 30; trial++) {
            int[][] operations = new int[100][3];
            LinkedHashMap<Integer, Integer> lru = new LinkedHashMap<>(16, 0.75f, true);
            Map<Integer, int[]> lfu = new HashMap<>();
            List<Integer> lruExpected = new ArrayList<>(), lfuExpected = new ArrayList<>();
            for (int tick = 0; tick < operations.length; tick++) {
                int key = random.nextInt(8), value = random.nextInt(), kind = random.nextBoolean() ? 0 : 1;
                operations[tick] = new int[]{kind, key, value};
                if (kind == 1) {
                    lruExpected.add(lru.get(key));
                    int[] entry = lfu.get(key); lfuExpected.add(entry == null ? null : entry[0]);
                    if (entry != null) { entry[1]++; entry[2] = tick; }
                } else {
                    lru.put(key, value);
                    if (lru.size() > capacity) lru.remove(lru.keySet().iterator().next());
                    if (capacity == 0) continue;
                    int[] entry = lfu.get(key);
                    if (entry != null) { entry[0] = value; entry[1]++; entry[2] = tick; }
                    else {
                        if (lfu.size() == capacity) {
                            Integer victim = null;
                            for (Map.Entry<Integer, int[]> candidate : lfu.entrySet()) {
                                if (victim == null || candidate.getValue()[1] < lfu.get(victim)[1]
                                        || (candidate.getValue()[1] == lfu.get(victim)[1] && candidate.getValue()[2] < lfu.get(victim)[2])) victim = candidate.getKey();
                            }
                            lfu.remove(victim);
                        }
                        lfu.put(key, new int[]{value, 1, tick});
                    }
                }
            }
            Check.equal(Q495ManualLruCache.solve(capacity, operations), lruExpected);
            Check.equal(Q496LfuCache.solve(capacity, operations), lfuExpected);
        }
    }

    private static void checkSnapshots(Random random) {
        for (int trial = 0; trial < 100; trial++) {
            int n = random.nextInt(8) + 1;
            int[] current = new int[n]; List<int[]> snapshots = new ArrayList<>();
            List<int[]> operations = new ArrayList<>(); List<Integer> expected = new ArrayList<>();
            for (int step = 0; step < 100; step++) {
                int kind = random.nextInt(3), index = random.nextInt(n);
                if (kind == 0) {
                    int value = random.nextInt(); operations.add(new int[]{0, index, value}); current[index] = value;
                } else if (kind == 1 || snapshots.isEmpty()) {
                    operations.add(new int[]{1, 0, 0}); expected.add(snapshots.size()); snapshots.add(current.clone());
                } else {
                    int snapshot = random.nextInt(snapshots.size()); operations.add(new int[]{2, index, snapshot}); expected.add(snapshots.get(snapshot)[index]);
                }
            }
            Check.equal(Q500SnapshotArray.solve(n, operations.toArray(new int[0][])), expected);
        }
    }

    private static void checkInverses() {
        for (int modulus = 2; modulus <= 50; modulus++) for (int value = -50; value <= 50; value++) {
            OptionalLong expected = OptionalLong.empty();
            for (int candidate = 0; candidate < modulus; candidate++) {
                if (Math.floorMod((long) value * candidate, modulus) == 1) { expected = OptionalLong.of(candidate); break; }
            }
            Check.equal(Q489ModularInverse.solve(value, modulus), expected);
        }
    }
}
