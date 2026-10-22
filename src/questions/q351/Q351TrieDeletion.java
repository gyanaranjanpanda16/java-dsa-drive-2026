package questions.q351; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Delete exact words without damaging shared trie prefixes.
 * Contract: Treat dictionary words as a set of UTF-16 strings. Delete each requested exact word if present and return remaining words in Java lexical order; empty words are supported.
 * Why: A terminal marker distinguishes a complete word from a prefix. Decrement populations only after confirming exact membership, then prune nodes whose population reaches zero. Shared prefixes remain because another word still passes through them. This is the core of mutable autocomplete dictionaries.
 * Cost: O(total input characters*log(A+1)+visited output nodes+output characters) time; O(S+H) space beyond output, with alphabet size A, trie size S and maximum depth H.
 */
public final class Q351TrieDeletion { // Keep the algorithm, demonstration, and boundary checks together.
    private Q351TrieDeletion() { } // Only static entry points are needed for this exercise.
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
    private static void collect(Node node,StringBuilder path,List<String> out) { // Traverse ordered edges to emit lexical results.
        if(node.terminal) out.add(path.toString()); // A shorter complete word sorts before its extensions.
        for(Map.Entry<Character,Node> edge:node.child.entrySet()) { // Each child extends the current prefix by one character.
            path.append(edge.getKey()); collect(edge.getValue(),path,out); path.setLength(path.length()-1); // Restore the prefix after returning from the child.
        }
    }
    public static List<String> solve(String[] words,String[] deletions) { // Each call owns the trie it changes.
        Node root=build(words); // Shared prefix populations start at their distinct-word counts.
        for(String word:deletions) { // Removing an absent word leaves all counts unchanged.
            Node[] path=new Node[word.length()+1]; path[0]=root; // Retain parent nodes for count updates and pruning.
            int i=0; while(i<word.length() && path[i]!=null) { path[i+1]=path[i].child.get(word.charAt(i)); i++; } // Follow the exact requested characters.
            if(path[word.length()]==null || !path[word.length()].terminal) continue; // A prefix without a terminal marker is not an existing word.
            path[word.length()].terminal=false; for(Node node:path) node.pass--; // Remove one distinct stored word from every prefix it visits.
            for(int j=word.length();j>0;j--) if(path[j].pass==0) path[j-1].child.remove(word.charAt(j-1)); // Prune only prefixes unused by all remaining words.
        }
        List<String> result=new ArrayList<>(); collect(root,new StringBuilder(),result); return result; // Report the final dictionary in stable lexical order.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new String[]{"car","cart","dog"},new String[]{"car"}))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new String[]{"car","cart","dog"},new String[]{"car"}), List.of("cart","dog")); // A shared prefix survives deletion.
        Check.equal(solve(new String[]{"","a"},new String[]{""}), List.of("a")); // Delete the empty word at the root.
        Check.equal(solve(new String[]{"a","a"},new String[]{"a","a"}), List.of()); // Set semantics and repeated deletion.
    }
}
