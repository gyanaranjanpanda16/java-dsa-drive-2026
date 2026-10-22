package questions.q152;

import java.util.*;
import questions.support.Check;

/**
 * Q152: Find a longest substring with at most k distinct chars.
 * Contract: Return maximum contiguous length with at most k distinct UTF-16 chars; k must be nonnegative.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: Frequencies describe exactly the current window. Removing left chars until the distinct count is legal yields its longest valid suffix at every right endpoint.
 * Complexity: Expected O(n) time and O(u) space.
 */
public final class Q152AtMostKDistinct { // Keep one question and its tests together for independent study.
    public static int solve(String text,int k) { // Distinct-character limits differ from repeated-character limits.
        if(k<0) throw new IllegalArgumentException("negative limit"); // Reject an impossible distinct-count allowance.
        Map<Character,Integer> counts=new HashMap<>(); // Frequencies allow removing a key only after its final window occurrence expires.
        int left=0,best=0; // Begin with the empty valid window.
        for(int right=0;right<text.length();right++) { // Add exactly one incoming char per step.
            counts.merge(text.charAt(right),1,Integer::sum); // Update the representation of the expanded window.
            while(counts.size()>k) { // Restore the distinct-count constraint by moving only the left boundary.
                char c=text.charAt(left++); // This outgoing occurrence must be removed from its count.
                if(counts.merge(c,-1,Integer::sum)==0) counts.remove(c); // A zero frequency must not count as a distinct window char.
            }
            best=Math.max(best,right-left+1); // Record the longest valid suffix ending at this right endpoint.
        }
        return best; // All candidate endpoints have been evaluated.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve("eceba",2)); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve("eceba",2),3); // ece has two distinct chars.
    Check.equal(solve("abc",0),0); // No nonempty window has zero distinct chars.
    Check.equal(solve("aaaa",1),4); // Repetitions do not increase distinct count.
    }
}
