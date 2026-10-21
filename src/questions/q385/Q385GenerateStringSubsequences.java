package questions.q385;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q385: Generate String Subsequences.
 * Contract: Non-null short string; output preserves input order and includes empty string.
 * Why it works: Every position has exactly two choices, exclude or include. The recursion partitions all positional subsets without losing or revisiting a choice sequence.
 * Cost: O(n*2^n) time/output and O(n) recursion/path space.
 */
public final class Q385GenerateStringSubsequences { // Keep this question independently runnable and testable.
    private Q385GenerateStringSubsequences() { } // No object state is needed by this standalone exercise.
    public static List<String> subsequences(String s) { // Expose the operation for an independent caller and the executable checks below.
        List<String> out = new ArrayList<>(); // Each leaf contributes one positional subsequence.
        choose(s, 0, new StringBuilder(), out); // Initially no input position has been decided.
        return out; // Duplicate characters may create equal strings from different choices.
    }
    private static void choose(String s, int i, StringBuilder path, List<String> out) { // Keep recursive state or a repeated sub-operation inside this solution.
        if (i == s.length()) { out.add(path.toString()); return; } // Copy the completed choice before backtracking changes it.
        choose(s, i+1, path, out); // Exclude this input position.
        path.append(s.charAt(i)); // Include the position without changing earlier choices.
        choose(s, i+1, path, out); // Explore every completion using this character.
        path.deleteCharAt(path.length()-1); // Restore the caller's path for its next branch.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(subsequences("ab")); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(subsequences("").equals(List.of("")), "Q385 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(subsequences("a").equals(List.of("","a")), "Q385 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(subsequences("abc").size()==8, "Q385 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
