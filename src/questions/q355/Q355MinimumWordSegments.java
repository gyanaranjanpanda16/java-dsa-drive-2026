package questions.q355; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Find the fewest dictionary pieces forming a string.
 * Contract: Return the fewest nonempty dictionary words whose concatenation equals text, or-1 if impossible. Dictionary entries are nonempty UTF-16 strings and may be reused; empty text needs zero pieces.
 * Why: From each reachable text position, follow trie edges and relax the next boundary whenever a terminal word ends. dp[j] is the minimum pieces forming the prefix ending at j; every valid final word is explored. Useful for minimizing token or command segmentation.
 * Cost: O(S log(A+1)+n*L*log(A+1)) time and O(S+n) space, where L is the longest dictionary word.
 */
public final class Q355MinimumWordSegments { // Keep the algorithm, demonstration, and boundary checks together.
    private Q355MinimumWordSegments() { } // Only static entry points are needed for this exercise.
    private static final class Node { // One node represents one prefix shared by stored words.
        final NavigableMap<Character,Node> child=new TreeMap<>(); boolean terminal; int pass; // Ordered edges, full-word marker, and distinct-word population serve different queries.
    }
    private static Node build(String[] words) { // Build a set-valued trie, collapsing duplicate dictionary entries.
        Node root=new Node(); // The root represents the empty prefix.
        for(String word:new HashSet<>(Arrays.asList(words))) { // Insert each distinct word exactly once.
            Node node=root; node.pass++; // Every stored word contributes to the empty-prefix population.
            for(char c:word.toCharArray()) { node=node.child.computeIfAbsent(c,key->new Node()); node.pass++; } // Share existing prefixes and count each visited prefix.
            node.terminal=true; // A complete word can also be the prefix of a longer word.
        }
        return root; // Callers receive a fresh trie for independent mutations or queries.
    }
    public static int solve(String[] words,String text) { // Compute an optimum over word boundaries instead of enumerating segmentations.
        Node root=build(words); int[] best=new int[text.length()+1]; Arrays.fill(best,Integer.MAX_VALUE); best[0]=0; // Only the empty prefix is initially reachable.
        for(int start=0;start<text.length();start++) { // Process boundaries after all shorter prefixes have been settled.
            if(best[start]==Integer.MAX_VALUE) continue; // No valid segmentation can start from an unreachable prefix.
            Node node=root; // Each word search begins at the dictionary's empty prefix.
            for(int end=start;end<text.length();end++) { // Grow one candidate dictionary word without allocating substrings.
                node=node.child.get(text.charAt(end)); if(node==null) break; // A missing trie edge rules out every longer extension here.
                if(node.terminal) best[end+1]=Math.min(best[end+1],best[start]+1); // Append one complete dictionary word to a best prior segmentation.
            }
        }
        return best[text.length()]==Integer.MAX_VALUE?-1:best[text.length()]; // Distinguish impossible full coverage from a finite minimum.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new String[]{"app","apple","pie","le"},"applepie"))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new String[]{"app","apple","pie","le"},"applepie"), 2); // Choose fewer pieces among multiple segmentations.
        Check.equal(solve(new String[]{"a"},"b"), -1); // No full segmentation.
        Check.equal(solve(new String[]{"a"},""), 0); // The empty prefix needs no pieces.
    }
}
