package questions.q360; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Check whether each stream prefix ends in a dictionary word.
 * Contract: Nonempty lowercase words form a dictionary. For each arriving character in a lowercase stream, report whether the stream so far ends with any dictionary word.
 * Why: Reverse dictionary paths so matching can begin at the newest character and walk backward. Only the last longestWordLength characters can matter, allowing bounded stream memory. This supports suffix-trigger detection in incoming text.
 * Cost: O(S+Q*L) expected time and O(S+L+Q) space including output, with longest word length L.
 */
public final class Q360StreamSuffixMatches { // Keep the algorithm, demonstration, and boundary checks together.
    private Q360StreamSuffixMatches() { } // Only static entry points are needed for this exercise.
    private static final class Node { // Reversed edges encode dictionary words from their final character backward.
        final Map<Character,Node> child=new HashMap<>(); boolean terminal; // A terminal node marks a complete reversed word.
    }
    public static List<Boolean> solve(String[] words,String stream) { // Simulate incremental queries without storing the whole processed stream.
        Node root=new Node(); int longest=0; // The longest word bounds relevant suffix history.
        for(String word:words) { // Build reversed paths once before processing arrivals.
            if(word.isEmpty()) throw new IllegalArgumentException("nonempty words required"); // Empty words would trivially match every position.
            longest=Math.max(longest,word.length()); Node node=root; // Track the maximum useful history length.
            for(int i=word.length()-1;i>=0;i--) node=node.child.computeIfAbsent(word.charAt(i),c->new Node()); // Insert characters from suffix to prefix.
            node.terminal=true; // Mark where a full dictionary word ends along the reversed path.
        }
        Deque<Character> history=new ArrayDeque<>(); List<Boolean> result=new ArrayList<>(); // Keep only bounded recent characters and accumulated answers.
        for(char c:stream.toCharArray()) { // Each arrival produces exactly one boolean query result.
            history.addLast(c); if(history.size()>longest) history.removeFirst(); // Discard characters too old to belong to any matching word.
            Iterator<Character> backward=history.descendingIterator(); Node node=root; boolean found=false; // Start at the newest arrival.
            while(backward.hasNext()) { // Follow only dictionary-compatible recent suffixes.
                node=node.child.get(backward.next()); if(node==null) break; // No longer reversed word can continue after a missing edge.
                if(node.terminal) { found=true; break; } // Any complete word suffix is sufficient.
            }
            result.add(found); // Preserve the chronological arrival order.
        }
        return result; // Empty dictionaries naturally produce false for every arrival.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new String[]{"ab","bc"},"abc"))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new String[]{"ab","bc"},"abc"), List.of(false,true,true)); // Overlapping suffix words.
        Check.equal(solve(new String[]{"a"},"aa"), List.of(true,true)); // Repeated one-letter matches.
        Check.equal(solve(new String[]{},"a"), List.of(false)); // No dictionary words.
    }
}
