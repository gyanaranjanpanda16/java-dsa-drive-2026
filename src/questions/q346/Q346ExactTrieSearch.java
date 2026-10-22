package questions.q346; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q346: Find complete dictionary words with a trie.
 * Contract: Non-null strings and arrays; case-sensitive UTF-16 code-unit matching; duplicates are allowed unless stated otherwise.
 * Why: A trie stores shared prefixes as shared paths. Following the query reaches its unique prefix node if that prefix exists; the terminal flag then distinguishes a stored word from a partial prefix. This supports dictionary validation. Java has no standard Trie collection, so nodes compose a TreeMap of outgoing character edges.
 * Complexity: O((S+Q) log(A+1)) time, where S is total dictionary characters, Q total query characters and A alphabet size; O(S) trie space plus result.
 */
public final class Q346ExactTrieSearch { // One class contains this question, its answer, and reproducible checks.
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

    public static String solve(String[] words,String[] queries) { // Expose the answer as a reusable method as well as a runnable program.
        Node root=build(words); List<Boolean> answers=new ArrayList<>(); // Build the dictionary once for repeated queries.
        for(String query:queries) { // Each query starts independently at the empty prefix.
            Node node=root; // A missing edge will set this cursor to null.
            for(char letter:query.toCharArray()) { // Follow the query without scanning unrelated dictionary words.
                node=node.child.get(letter); if(node==null) break; // Missing prefix proves the complete word is absent.
            }
            answers.add(node!=null&&node.terminal); // Reaching a prefix alone does not prove a full dictionary word exists.
        }
        return answers.toString(); // Preserve query order for callers.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(new String[]{"car","cart"},new String[]{"car","ca","cat"})); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(new String[]{"car","cart"},new String[]{"car","ca","cat"}), "[true, false, false]"); // Distinguish complete words, prefixes, and missing paths
        Check.equal(solve(new String[]{""},new String[]{"","a"}), "[true, false]"); // The root can represent an empty word
        Check.equal(solve(new String[]{},new String[]{"x"}), "[false]"); // An empty dictionary
    }
}
