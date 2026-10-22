package questions.q160;

import java.util.*;
import questions.support.Check;

/**
 * Q160: Find starts containing a permutation of equal-length words.
 * Contract: Words are nonempty and have equal positive char length. Return all starts whose fixed-size substring is a concatenation of every word once, including duplicate multiplicities. Empty word array returns no starts.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: At a candidate start, aligned chunks partition the whole candidate substring. Matching their frequency map to required multiplicities proves every required word occurs exactly once per required copy.
 * Complexity: O(n·wordCount·wordLength) time and O(wordCount·wordLength) temporary space; straightforward baseline.
 */
public final class Q160ConcatenatedWords { // Keep one question and its tests together for independent study.
    public static List<Integer> solve(String text,String[] words) { // Return starts in increasing order for deterministic results.
        List<Integer> answer=new ArrayList<>(); // Store only successful candidate boundaries.
        if(words.length==0) return answer; // No required words follows the explicit no-start convention.
        int width=words[0].length(); // All chunks must have this shared length.
        if(width==0) throw new IllegalArgumentException("nonempty words required"); // Zero-length chunks would not advance.
        Map<String,Integer> required=new HashMap<>(); // Preserve duplicate word requirements.
        for(String word:words) { // Validate the fixed-width decomposition before searching.
            if(word.length()!=width) throw new IllegalArgumentException("equal lengths required"); // Unequal lengths need a different parsing algorithm.
            required.merge(word,1,Integer::sum); // Count how many copies of this word must appear.
        }
        long total=(long)width*words.length; // Widen multiplication before comparing the combined length.
        for(int start=0;start+total<=text.length();start++) { // Try every start with enough remaining characters.
            Map<String,Integer> seen=new HashMap<>(); // Count chunks independently for this candidate.
            int count=0; // Number of accepted required-word occurrences so far.
            while(count<words.length) { // Consume the candidate in fixed-width blocks.
                String word=text.substring(start+count*width,start+(count+1)*width); // Extract exactly the next aligned chunk.
                int frequency=seen.merge(word,1,Integer::sum); // Include its occurrence before testing the quota.
                if(frequency>required.getOrDefault(word,0)) break; // An unknown or overused word makes this start impossible.
                count++; // Advance only after accepting the current word occurrence.
            }
            if(count==words.length) answer.add(start); // All required copies fit exactly in the candidate window.
        }
        return answer; // Every legal start was tested without assuming a particular word permutation.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve("barfoothefoobarman",new String[]{"foo","bar"})); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve("barfoothefoobarman",new String[]{"foo","bar"}),List.of(0,9)); // Accept both word orders.
    Check.equal(solve("aaaaaa",new String[]{"aa","aa"}),List.of(0,1,2)); // Duplicate required words retain their multiplicity.
    Check.equal(solve("abc",new String[]{}),List.of()); // Empty requirement follows the documented convention.
    }
}
