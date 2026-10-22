package questions.q349; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q349: Return the first k lexicographic completions of a prefix.
 * Contract: Non-null strings; k>=0; distinct suggestions in UTF-16 lexical order; recursive word depth must fit the JVM stack.
 * Why: The prefix node contains exactly all completions. Emit a terminal node before its children because a string sorts before its extensions, then explore child characters in sorted order. This is the basic autocomplete engine; a production ranking system may store popularity instead of using lexical order.
 * Complexity: O(S log(A+1)+P log(A+1)+visited nodes+output characters) time; O(S+H) space beyond output, H maximum word length.
 */
public final class Q349TrieAutocomplete { // One class contains this question, its answer, and reproducible checks.
    static final class Node { // Each node represents one shared prefix, not a complete stored string.
        final TreeMap<Character,Node> child=new TreeMap<>(); // Sorted UTF-16 edges make lexicographic traversal deterministic.
        boolean terminal; int pass; // terminal marks a complete word; pass counts inserted words sharing this prefix.
    }
    static Node build(String[] words) { // Share common prefixes while retaining word boundaries.
        Node root=new Node(); // The root represents the empty prefix.
        for(String word:words) { // Insert every occurrence so prefix counts can include duplicates.
            Node node=root; node.pass++; // Every word shares the empty prefix.
            for(char letter:word.toCharArray()) { // Advance one UTF-16 code unit per trie edge.
                node=node.child.computeIfAbsent(letter,key->new Node()); node.pass++; // Reuse a prefix node or allocate it once, then count this occurrence.
            }
            node.terminal=true; // A complete word may also be a prefix of a longer word.
        }
        return root; // Searches begin at the shared empty prefix.
    }
    static void collect(Node node,StringBuilder path,int k,List<String> result) { // Traverse matching words in lexical order with an early limit.
        if(result.size()>=k) return; // Stop before exploring unused descendants.
        if(node.terminal) result.add(path.toString()); // A word precedes every proper extension of itself.
        for(Map.Entry<Character,Node> edge:node.child.entrySet()) { // TreeMap yields increasing character order.
            if(result.size()>=k) break; // Avoid traversing more branches after meeting the limit.
            path.append(edge.getKey()); collect(edge.getValue(),path,k,result); // Extend the current prefix for this branch.
            path.setLength(path.length()-1); // Restore the parent prefix before trying the next child.
        }
    }
    public static String solve(String[] words,String prefix,int k) { // Expose the answer as a reusable method as well as a runnable program.
        Node node=build(words); // Merge duplicate words into terminal flags so suggestions are distinct.
        for(char letter:prefix.toCharArray()) { // Locate the subtree containing exactly the matching completions.
            node=node.child.get(letter); if(node==null) return "[]"; // Missing prefixes have no suggestions.
        }
        List<String> result=new ArrayList<>(); collect(node,new StringBuilder(prefix),k,result); // Traverse only until enough completions have been collected.
        return result.toString(); // TreeMap edge order makes the suggestion list deterministic.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(new String[]{"cart","cat","car","care","car"},"ca",3)); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(new String[]{"cart","cat","car","care","car"},"ca",3), "[car, care, cart]"); // Distinct lexical suggestions stop at k
        Check.equal(solve(new String[]{"a"},"z",2), "[]"); // Unknown prefix
        Check.equal(solve(new String[]{"a"},"",0), "[]"); // Zero suggestions requested
    }
}
