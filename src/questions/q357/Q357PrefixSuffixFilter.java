package questions.q357; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Find the latest word matching both prefix and suffix.
 * Contract: Words and query prefixes/suffixes use lowercase ASCII, including empty strings. Return the largest dictionary index matching both constraints for each[prefix,suffix] query, or-1.
 * Why: Insert every suffix followed by a separator and the whole word. Querying suffix+separator+prefix reaches exactly words with both required ends. Store the maximum index at every node so overlapping prefixes and suffixes work without intersecting candidate lists. Useful for indexed text filters.
 * Cost: O(sum L²) expected construction time/space and O(total query characters) expected query time.
 */
public final class Q357PrefixSuffixFilter { // Keep the algorithm, demonstration, and boundary checks together.
    private Q357PrefixSuffixFilter() { } // Only static entry points are needed for this exercise.
    private static final class Node { // A node stores the best dictionary position below a combined-key prefix.
        final Map<Character,Node> child=new HashMap<>(); int latest=-1; // Negative one means no indexed word reaches this node.
    }
    public static int[] solve(String[] words,String[][] queries) { // Encode two-ended matching as one forward trie lookup.
        Node root=new Node(); // Lowercase input leaves the separator character unambiguous.
        for(int index=0;index<words.length;index++) { // Increasing insertion order lets assignment keep the maximum index.
            String word=words[index]; // Reuse the full word for every suffix combination.
            for(int start=0;start<=word.length();start++) { // Include the empty suffix as well as every nonempty suffix.
                String key=word.substring(start)+"{"+word; Node node=root; node.latest=index; // The separator marks the switch from suffix to prefix matching.
                for(char c:key.toCharArray()) { node=node.child.computeIfAbsent(c,k->new Node()); node.latest=index; } // Every prefix of this combined key inherits the latest match.
            }
        }
        int[] result=new int[queries.length]; // Preserve the request order.
        for(int i=0;i<queries.length;i++) { // A query may stop partway through the full-word portion.
            Node node=root; String key=queries[i][1]+"{"+queries[i][0]; // Match the complete suffix and only the requested prefix.
            for(char c:key.toCharArray()) { node=node.child.get(c); if(node==null) break; } // Missing edges prove no candidate has both constraints.
            result[i]=node==null?-1:node.latest; // Stored maxima resolve duplicates and later matching words.
        }
        return result; // Prefix/suffix overlap is permitted because the encoding stores both independently.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new String[]{"apple","apply","ape"},new String[][]{{"ap","le"},{"ap","e"},{"z",""}}))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new String[]{"apple","apply","ape"},new String[][]{{"ap","le"},{"ap","e"},{"z",""}}), new int[]{0,2,-1}); // Latest matching index and absence.
        Check.equal(solve(new String[]{"a","a"},new String[][]{{"",""}}), new int[]{1}); // Empty constraints choose the last dictionary word.
        Check.equal(solve(new String[]{"aaa"},new String[][]{{"aa","aa"}}), new int[]{0}); // Prefix and suffix can overlap.
    }
}
