package dsa;

import dsa.arrays.ArrayProblems;
import dsa.foundations.JavaBasics;
import dsa.searchsort.SearchSort;
import dsa.strings.StringProblems;
import java.util.Arrays;
import java.util.OptionalInt;
import java.util.OptionalLong;
import java.util.Random;

/** Dependency-free tests. Checks throw even when the JVM's -ea flag is absent. */
public final class FoundationsTest {
    private static int checks;

    private FoundationsTest() { }

    private static void check(boolean condition, String message) {
        checks++;
        if (!condition) {
            throw new AssertionError(message);
        }
    }

    private static void arrayEquals(int[] actual, int[] expected, String message) {
        check(Arrays.equals(actual, expected),
                message + ": expected " + Arrays.toString(expected) + ", got " + Arrays.toString(actual));
    }

    private static void throwsType(Class<? extends Throwable> expected, Runnable action) {
        try {
            action.run();
        } catch (Throwable error) {
            check(expected.isInstance(error), "Wrong exception: " + error);
            return;
        }
        throw new AssertionError("Expected " + expected.getSimpleName());
    }

    public static void main(String[] args) {
        check(JavaBasics.countEven(new int[] {-2, -1, 0, 3, 4}) == 3, "even count");
        check(JavaBasics.factorial(0) == 1, "zero factorial");
        check(JavaBasics.factorial(20) == 2_432_902_008_176_640_000L, "largest long factorial");
        throwsType(IllegalArgumentException.class, () -> JavaBasics.factorial(21));
        throwsType(IllegalArgumentException.class, () -> JavaBasics.factorial(-1));
        check(!JavaBasics.isPrime(-7) && !JavaBasics.isPrime(0) && !JavaBasics.isPrime(1), "nonprimes");
        check(JavaBasics.isPrime(2) && JavaBasics.isPrime(29), "small primes");
        check(!JavaBasics.isPrime(49) && JavaBasics.isPrime(Integer.MAX_VALUE), "prime overflow boundary");

        check(ArrayProblems.sum(new int[] {Integer.MAX_VALUE, Integer.MAX_VALUE}) == 4_294_967_294L,
                "sum widens before overflow");
        check(ArrayProblems.max(new int[0]).isEmpty(), "empty maximum");
        check(ArrayProblems.secondLargestDistinct(new int[] {7, 7}).isEmpty(), "distinct requirement");
        check(ArrayProblems.secondLargestDistinct(new int[] {Integer.MAX_VALUE, Integer.MIN_VALUE})
                .equals(OptionalInt.of(Integer.MIN_VALUE)), "no sentinel collision");
        check(ArrayProblems.maxSubarray(new int[] {-5, -2, -9}).equals(OptionalLong.of(-2)),
                "nonempty all-negative maximum");
        check(ArrayProblems.maxSubarray(new int[0]).isEmpty(), "empty maximum subarray");
        check(ArrayProblems.linearSearch(new int[] {3, 1, 3}, 3) == 0, "first occurrence");
        check(ArrayProblems.linearSearch(new int[0], 2) == -1, "missing occurrence");
        check(ArrayProblems.isSorted(new int[] {1, 1, 2}) && !ArrayProblems.isSorted(new int[] {2, 1}),
                "nondecreasing order");
        throwsType(NullPointerException.class, () -> ArrayProblems.sum(null));
        throwsType(IllegalArgumentException.class, () -> ArrayProblems.rangeSum(new long[] {0, 5}, 1, 0));
        throwsType(IllegalArgumentException.class, () -> ArrayProblems.rangeSum(new long[] {0, 5}, 0, 2));
        throwsType(IllegalArgumentException.class, () -> ArrayProblems.rangeSum(new long[0], 0, 0));

        check(StringProblems.isPalindrome("") && StringProblems.isPalindrome("😀a😀"), "unicode palindrome");
        check(!StringProblems.isPalindrome("Level"), "case-sensitive palindrome");
        check(StringProblems.areAnagramsLowercase("listen", "silent"), "anagram");
        check(!StringProblems.areAnagramsLowercase("aab", "abb"), "multiplicities");
        check(!StringProblems.areAnagramsLowercase("a", ""), "anagram lengths");
        check(StringProblems.areAnagramsLowercase("", ""), "empty anagram");
        throwsType(IllegalArgumentException.class, () -> StringProblems.areAnagramsLowercase("A", "a"));
        check(StringProblems.longestUniqueSubstring("abba") == 2, "left boundary never moves backward");
        check(StringProblems.longestUniqueSubstring("😀a😀b") == 3, "code-point length");
        check(StringProblems.longestUniqueSubstring("") == 0, "empty unique substring");
        check(StringProblems.longestUniqueSubstring("abcabcbb") == 3, "repeated pattern");
        throwsType(NullPointerException.class, () -> StringProblems.isPalindrome(null));

        Random random = new Random(2026);
        for (int trial = 0; trial < 250; trial++) {
            int[] input = new int[random.nextInt(18)];
            for (int i = 0; i < input.length; i++) {
                input[i] = random.nextInt(21) - 10;
            }
            int[] sorted = input.clone();
            Arrays.sort(sorted);
            int[] actualSort = input.clone();
            SearchSort.mergeSort(actualSort);
            arrayEquals(actualSort, sorted, "merge sort oracle");
            for (int target = -12; target <= 12; target++) {
                int expectedLower = 0;
                while (expectedLower < sorted.length && sorted[expectedLower] < target) {
                    expectedLower++;
                }
                check(SearchSort.lowerBound(sorted, target) == expectedLower, "lower-bound oracle");
                int found = SearchSort.binarySearch(sorted, target);
                boolean present = expectedLower < sorted.length && sorted[expectedLower] == target;
                check(present ? found >= 0 && found < sorted.length && sorted[found] == target : found == -1,
                        "binary-search oracle");
            }
            OptionalInt expectedSecond = OptionalInt.empty();
            if (sorted.length > 1) {
                for (int i = sorted.length - 2; i >= 0; i--) {
                    if (sorted[i] != sorted[sorted.length - 1]) {
                        expectedSecond = OptionalInt.of(sorted[i]);
                        break;
                    }
                }
            }
            check(ArrayProblems.secondLargestDistinct(input).equals(expectedSecond), "second-largest oracle");
            OptionalInt expectedMax = sorted.length == 0 ? OptionalInt.empty()
                    : OptionalInt.of(sorted[sorted.length - 1]);
            check(ArrayProblems.max(input).equals(expectedMax), "maximum oracle");
            int[] reverse = input.clone();
            ArrayProblems.reverse(reverse);
            for (int i = 0; i < input.length; i++) {
                check(reverse[i] == input[input.length - 1 - i], "reverse oracle");
            }
            int[] compactExpected = new int[input.length];
            int write = 0;
            for (int value : input) {
                if (value != 0) {
                    compactExpected[write++] = value;
                }
            }
            int[] compactActual = input.clone();
            ArrayProblems.moveZeros(compactActual);
            arrayEquals(compactActual, compactExpected, "stable compaction oracle");

            int shift = trial == 0 ? Integer.MIN_VALUE : random.nextInt();
            int[] rotatedExpected = new int[input.length];
            if (input.length > 0) {
                int normalized = Math.floorMod(shift, input.length);
                for (int i = 0; i < input.length; i++) {
                    rotatedExpected[(i + normalized) % input.length] = input[i];
                }
            }
            int[] rotatedActual = input.clone();
            ArrayProblems.rotateRight(rotatedActual, shift);
            arrayEquals(rotatedActual, rotatedExpected, "rotation oracle");

            long[] prefix = ArrayProblems.prefixSums(input);
            long maximum = Long.MIN_VALUE;
            for (int from = 0; from <= input.length; from++) {
                long expected = 0;
                for (int to = from; to <= input.length; to++) {
                    if (to > from) {
                        expected += input[to - 1];
                        maximum = Math.max(maximum, expected);
                    }
                    check(ArrayProblems.rangeSum(prefix, from, to) == expected, "range-sum oracle");
                }
            }
            OptionalLong expectedBest = input.length == 0 ? OptionalLong.empty() : OptionalLong.of(maximum);
            check(ArrayProblems.maxSubarray(input).equals(expectedBest), "Kadane brute-force oracle");
            long target = random.nextInt(41) - 20;
            boolean pairExists = false;
            for (int i = 0; i < sorted.length; i++) {
                for (int j = i + 1; j < sorted.length; j++) {
                    pairExists |= (long) sorted[i] + sorted[j] == target;
                }
            }
            int[] pair = ArrayProblems.twoSumSorted(sorted, target);
            check(pairExists
                    ? pair.length == 2 && pair[0] < pair[1]
                            && (long) sorted[pair[0]] + sorted[pair[1]] == target
                    : pair.length == 0, "two-sum oracle");
        }
        arrayEquals(ArrayProblems.twoSumSorted(new int[] {Integer.MAX_VALUE, Integer.MAX_VALUE},
                4_294_967_294L), new int[] {0, 1}, "two-sum overflow");
        System.out.println("FoundationsTest: " + checks + " checks passed.");
    }
}
