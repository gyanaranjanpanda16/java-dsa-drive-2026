package questions.q354; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Replace words by their shortest stored root.
 * Contract: Given nonempty lowercase roots and lowercase tokens, replace each token by its shortest root prefix if one exists. Preserve token order; unmatched tokens stay unchanged.
 * Why: The first terminal node encountered while descending a token is its shortest dictionary prefix. Stop there even when longer roots exist. This is useful for simple rule-based normalization or command-prefix substitution.
 * Cost: O(S log(A+1)+T log(A+1)) time and O(S) trie space plus output, for S root characters and T token characters.
 */
public final class Q354ReplaceRoots { // Keep the algorithm, demonstration, and boundary checks together.
    private Q354ReplaceRoots() { } // Only static entry points are needed for this exercise.
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
    public static List<String> solve(String[] roots,String[] tokens) { // Return normalized tokens without changing the originals.
        Node root=build(roots); List<String> result=new ArrayList<>(); // Build the shared root dictionary once.
        for(String token:tokens) { // Independently seek the shortest root of this token.
            Node node=root; String replacement=token; // Fall back to the complete token if no root matches.
            for(int i=0;i<token.length();i++) { // Inspect prefixes in increasing length order.
                node=node.child.get(token.charAt(i)); if(node==null) break; // An absent edge prevents every longer prefix match.
                if(node.terminal) { replacement=token.substring(0,i+1); break; } // The first full root is necessarily shortest.
            }
            result.add(replacement); // Preserve one result per input token.
        }
        return result; // Roots are nonempty by contract, so the root node is never a replacement.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new String[]{"cat","bat","rat"},new String[]{"cattle","battery","rattled"}))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new String[]{"cat","bat","rat"},new String[]{"cattle","battery","rattled"}), List.of("cat","bat","rat")); // Three independent prefix replacements.
        Check.equal(solve(new String[]{"a","ab"},new String[]{"abc","z"}), List.of("a","z")); // Shortest root wins and an unmatched token stays intact.
        Check.equal(solve(new String[]{},new String[]{"word"}), List.of("word")); // No roots means no replacement.
    }
}
