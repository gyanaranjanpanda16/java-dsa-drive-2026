package questions.q352; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Find the shortest distinguishing prefix of each word.
 * Contract: For distinct UTF-16 dictionary words, return each shortest prefix shared by no other word, in input order. Return null when no such prefix exists; an empty word is uniquely identified only in a singleton dictionary.
 * Why: The pass count at a prefix node equals the number of distinct dictionary words below it. The first count-one node is therefore the shortest distinguishing prefix. This supports compact labels or unambiguous command abbreviations.
 * Cost: O(S log(A+1)) construction and query time and O(S) space plus output.
 */
public final class Q352UniqueWordPrefixes { // Keep the algorithm, demonstration, and boundary checks together.
    private Q352UniqueWordPrefixes() { } // Only static entry points are needed for this exercise.
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
    public static List<String> solve(String[] words) { // Match each output to its original dictionary position.
        Node root=build(words); List<String> result=new ArrayList<>(); // Population counts let queries stop before enumerating descendants.
        for(String word:words) { // Locate the first prefix belonging only to this word.
            Node node=root; String answer=null; // Null explicitly represents an impossible distinguishing prefix.
            if(word.isEmpty() && root.pass==1) answer=""; // An empty word has no nonempty prefix to inspect.
            for(int i=0;i<word.length();i++) { // Grow the prefix one character at a time.
                node=node.child.get(word.charAt(i)); // Inserted words guarantee this path exists.
                if(node.pass==1) { answer=word.substring(0,i+1); break; } // The earliest unique node gives the shortest answer.
            }
            result.add(answer); // A word that prefixes another remains null after its final node.
        }
        return result; // Inputs are assumed distinct so one word does not request duplicate identity labels.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new String[]{"dog","dove","duck"}))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new String[]{"dog","dove","duck"}), List.of("dog","dov","du")); // Different branch depths give different prefix lengths.
        Check.equal(solve(new String[]{"a","ab"}), Arrays.asList(null,"ab")); // A complete word can still prefix another.
        Check.equal(solve(new String[]{""}), List.of("")); // The lone empty word is distinguishable.
    }
}
