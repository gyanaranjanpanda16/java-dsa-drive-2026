package questions.q459; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Rearrange a string so equal letters are never adjacent.
 * Contract: Given lowercase a..z text, return a permutation with no equal adjacent letters, or the empty string if impossible. Empty input returns empty. Among equally frequent available letters, choose the alphabetically smaller letter to make this implementation deterministic; the output need not be the lexicographically smallest valid arrangement.
 * Why: The most frequent remaining letter is the hardest to separate, so choose it whenever it is available. Temporarily hold the previously emitted letter outside the heap, preventing it from being chosen twice in a row. Reinsert it only after another letter is emitted. A maximum frequency greater than ceil(n/2) makes separation impossible because there are too few other letters to fill the gaps. This is a sequencing pattern for alternating repeated categories, rather than the cooldown-time calculation in Q298.
 * Cost: O(n log u) time and O(u) working space plus O(n) output, where u<=26 is the distinct-letter count. The feasibility bound and holding rule ensure all letters can be placed when separation is possible.
 */
public final class Q459ReorganizeString { // Keep the algorithm, demonstration, and boundary checks together.
    private Q459ReorganizeString() { } // Only static entry points are needed for this exercise.
    public static String solve(String text) { // Produce an actual arrangement, not only a feasibility flag.
        int[] counts=new int[26]; // The alphabet is fixed by the contract.
        for(char c:text.toCharArray()) { if(c<'a'||c>'z') throw new IllegalArgumentException("a..z required"); counts[c-'a']++; } // Count all occurrences before constructing an answer.
        PriorityQueue<int[]> heap=new PriorityQueue<>((a,b)->a[1]!=b[1]?Integer.compare(b[1],a[1]):Integer.compare(a[0],b[0])); // Prefer high remaining counts, breaking ties by letter.
        for(int i=0;i<26;i++) { if(counts[i]>(text.length()+1L)/2) return ""; if(counts[i]>0) heap.add(new int[]{i,counts[i]}); } // Reject impossible spacing before placing any letter.
        StringBuilder answer=new StringBuilder(); int[] held=null; // The held letter cannot be emitted immediately again.
        while(!heap.isEmpty()) { // Choose among letters different from the previous output character.
            int[] next=heap.remove(); answer.append((char)('a'+next[0])); next[1]--; // Emit one occurrence of the most frequent available letter.
            if(held!=null&&held[1]>0) heap.add(held); // The old held letter is eligible only after this different letter was emitted.
            held=next; // Keep the newly emitted letter unavailable for the next choice.
        }
        return answer.length()==text.length()?answer.toString():""; // Never return a partial arrangement.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve("aab"))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve("aab"), "aba"); // A repeated letter needs a separator.
        Check.equal(solve("aaab"), ""); // Too few separators.
        Check.equal(solve("aabb"), "abab"); // Equal counts use alphabetical tie-breaking.
        Check.equal(solve(""), ""); // Empty input.
    }
}
