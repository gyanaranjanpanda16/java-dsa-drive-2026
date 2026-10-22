package questions.q359; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Count distinct nonempty substrings with a suffix trie.
 * Contract: Return the number of distinct nonempty UTF-16 substrings. Keep inputs small enough for a quadratic-size trie.
 * Why: Insert every suffix. Every root-to-node path spells one substring, and each distinct substring creates exactly one node beyond the root. Shared prefixes merge equal substrings automatically. This is an introductory text-indexing structure; suffix arrays or automata scale better.
 * Cost: Expected O(n²) time and O(n²) space; a suffix trie is deliberately memory-intensive.
 */
public final class Q359DistinctSubstrings { // Keep the algorithm, demonstration, and boundary checks together.
    private Q359DistinctSubstrings() { } // Only static entry points are needed for this exercise.
    private static final class Node { // A node represents one distinct substring beginning at the trie root.
        final Map<Character,Node> child=new HashMap<>(); // Sparse edges allocate only characters actually encountered.
    }
    public static long solve(String text) { // Exclude the empty substring represented by the root itself.
        Node root=new Node(); long distinct=0; // Count only newly created nonroot nodes.
        for(int start=0;start<text.length();start++) { // Every substring starts at some suffix beginning.
            Node node=root; // Reuse prefixes already inserted by earlier suffixes.
            for(int end=start;end<text.length();end++) { // Insert every nonempty prefix of this suffix.
                char c=text.charAt(end); Node next=node.child.get(c); // Determine whether this substring path already exists.
                if(next==null) { next=new Node(); node.child.put(c,next); distinct++; } // One new node contributes exactly one new distinct substring.
                node=next; // Extend the current suffix path by another character.
            }
        }
        return distinct; // Equal substrings share a path regardless of where they occurred in the original text.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve("ababa"))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve("ababa"), 9L); // Overlapping repeated substrings share nodes.
        Check.equal(solve("aaa"), 3L); // Only a,aa,aaa are distinct.
        Check.equal(solve(""), 0L); // No nonempty substrings.
    }
}
