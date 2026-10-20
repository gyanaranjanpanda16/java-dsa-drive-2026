package dsa.strings;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/** Text algorithms with explicit character contracts rather than hidden ASCII assumptions. */
public final class StringProblems {
    private StringProblems() { }

    /**
     * Exact, case-sensitive palindrome by Unicode code point; punctuation participates.
     * Time O(n), extra space O(n) for code points. Does not normalize grapheme clusters.
     */
    public static boolean isPalindrome(String text) {
        Objects.requireNonNull(text, "text");
        int[] points = text.codePoints().toArray();
        for (int left = 0, right = points.length - 1; left < right; left++, right--) {
            if (points[left] != points[right]) {
                return false;
            }
        }
        return true;
    }

    /** Only lowercase English a..z; rejects other characters. Time O(a+b), space O(1). */
    public static boolean areAnagramsLowercase(String first, String second) {
        Objects.requireNonNull(first, "first");
        Objects.requireNonNull(second, "second");
        int[] counts = new int[26];
        for (int i = 0; i < first.length(); i++) {
            counts[letterIndex(first.charAt(i))]++;
        }
        for (int i = 0; i < second.length(); i++) {
            counts[letterIndex(second.charAt(i))]--;
        }
        for (int count : counts) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }

    private static int letterIndex(char letter) {
        if (letter < 'a' || letter > 'z') {
            throw new IllegalArgumentException("Only lowercase English a..z is supported");
        }
        return letter - 'a';
    }

    /**
     * Length in Unicode code points, not UTF-16 chars. Expected O(n) time, O(n) space.
     * Combining marks count separately; this is not a user-perceived-character tokenizer.
     */
    public static int longestUniqueSubstring(String text) {
        Objects.requireNonNull(text, "text");
        int[] points = text.codePoints().toArray();
        Map<Integer, Integer> lastSeen = new HashMap<>();
        int left = 0;
        int best = 0;
        for (int right = 0; right < points.length; right++) {
            Integer previous = lastSeen.put(points[right], right);
            if (previous != null) {
                // Never move backwards when the previous occurrence is outside our window.
                left = Math.max(left, previous + 1);
            }
            best = Math.max(best, right - left + 1);
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println("level is palindrome: " + isPalindrome("level"));
        System.out.println("listen/silent anagrams: " + areAnagramsLowercase("listen", "silent"));
        System.out.println("Longest unique segment in abba: " + longestUniqueSubstring("abba"));
        System.out.println("Unicode example 😀a😀: " + longestUniqueSubstring("😀a😀"));
    }
}
