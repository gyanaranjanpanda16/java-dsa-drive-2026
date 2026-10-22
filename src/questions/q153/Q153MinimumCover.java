package questions.q153;

import java.util.*;
import questions.support.Check;

/**
 * Q153: Find a shortest substring covering required characters.
 * Contract: Return the earliest shortest substring containing all pattern chars with multiplicity; empty pattern or no covering window returns empty text.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: A missing-occurrence counter reaches zero exactly when the window covers all requirements. Shrinking while covered finds the minimal window at each right endpoint.
 * Complexity: O(n+m+alphabet) time and O(alphabet) auxiliary space, alphabet=65536 char units.
 */
public final class Q153MinimumCover { // Keep one question and its tests together for independent study.
    public static String solve(String text,String pattern) { // Treat required duplicates as separate occurrences.
        if(pattern.isEmpty()) return ""; // The empty requirement needs no characters.
        int[] need=new int[65536]; // Direct char indexing stores deficits and surplus counts.
        for(char c:pattern.toCharArray()) need[c]++; // Initialize the required multiplicity of every char.
        int missing=pattern.length(),left=0,start=0,best=Integer.MAX_VALUE; // Track coverage and the best complete window.
        for(int right=0;right<text.length();right++) { // Expand until every required occurrence is covered.
            if(need[text.charAt(right)]-->0) missing--; // Only filling a positive deficit reduces the missing total.
            while(missing==0) { // Every current window in this loop is a valid candidate.
                if(right-left+1<best) { best=right-left+1;start=left; } // Strict improvement preserves the earliest tie.
                if(++need[text.charAt(left++)]>0) missing++; // Removing the last required copy makes the window incomplete.
            }
        }
        return best==Integer.MAX_VALUE?"":text.substring(start,start+best); // Distinguish absent coverage from a valid interval.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve("ADOBECODEBANC","ABC")); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve("ADOBECODEBANC","ABC"),"BANC"); // Shrinking finds a later shorter cover.
    Check.equal(solve("a","aa"),""); // Multiplicity matters.
    Check.equal(solve("abc",""),""); // Empty pattern follows its explicit convention.
    }
}
