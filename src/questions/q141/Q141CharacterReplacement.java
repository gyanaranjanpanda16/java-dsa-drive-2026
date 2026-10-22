package questions.q141; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Longest repeating-character window after replacements.
 * Contract: Return the longest substring that can become one repeated letter using at most k replacements. Input contains only A..Z; k is nonnegative.
 * Why: A baseline tries every substring and counts its letters. In a window of length L whose most frequent letter occurs F times, exactly L-F replacements suffice: keep that letter and change the others. Maintain 26 counts and move the left boundary while this cost exceeds k. Removing a letter cannot increase the minimum replacement cost, so every suffix of a valid window is valid. This is useful for finding stretches of almost-identical categorical events; it is not a general text-similarity metric.
 * Cost: O(26n)=O(n) time, O(26)=O(1) extra space. Each boundary advances at most n times; scanning the fixed alphabet is constant work.
 */
public final class Q141CharacterReplacement { // Keep the algorithm, demonstration, and boundary checks together.
    private Q141CharacterReplacement() { } // Only static entry points are needed for this exercise.
    public static int solve(String text,int k) { // Find a length, without actually modifying the input string.
        if(k<0) throw new IllegalArgumentException("negative replacements"); // Reject a budget with no meaning.
        int[] counts=new int[26]; int left=0,best=0; // Counts describe exactly the current inclusive window.
        for(int right=0;right<text.length();right++) { // Try each possible right endpoint once.
            char letter=text.charAt(right); // Java char indexing is sufficient for the declared ASCII alphabet.
            if(letter<'A'||letter>'Z') throw new IllegalArgumentException("A..Z required"); // Keep array indices valid.
            counts[letter-'A']++; // Add the newly included letter to the window.
            while(right-left+1-maximum(counts)>k) counts[text.charAt(left++)-'A']--; // Discard starts that cannot meet the budget.
            best=Math.max(best,right-left+1); // The remaining window is valid and longest for this endpoint.
        }
        return best; // An empty string naturally yields zero.
    }
    private static int maximum(int[] counts) { // Recompute the true largest frequency after removals as well as additions.
        int maximum=0; // Counts cannot be negative.
        for(int count:counts) maximum=Math.max(maximum,count); // Examine the complete fixed alphabet.
        return maximum; // Keeping this many equal letters minimizes replacements.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve("AABABBA",1))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve("AABABBA",1), 4); // Best window can end before the string ends.
        Check.equal(solve("ABAB",0), 1); // No edits allowed.
        Check.equal(solve("",2), 0); // Empty input.
        Check.equal(solve("AAAA",0), 4); // Already uniform.
    }
}
