package questions.q347; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q347: Check whether dictionary words share a requested prefix.
 * Contract: Non-null strings and arrays; case-sensitive UTF-16 code-unit matching; duplicates are allowed unless stated otherwise.
 * Why: All words under a trie node share its root-to-node prefix, so reaching a node with positive pass count is exactly the existence condition. Prefix search is useful for interactive lookup and early rejection during word-game exploration. The TreeMap edges support deterministic traversal and arbitrary UTF-16 units.
 * Complexity: O((S+P) log(A+1)) time for dictionary and queried prefix characters; O(S) space plus results.
 */
public final class Q347TriePrefixExists { // One class contains this question, its answer, and reproducible checks.
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

    public static String solve(String[] words,String[] prefixes) { // Expose the answer as a reusable method as well as a runnable program.
        Node root=build(words); List<Boolean> answers=new ArrayList<>(); // Index shared prefixes once for repeated existence checks.
        for(String prefix:prefixes) { // No complete-word marker is required for a prefix query.
            Node node=root; // Start from the prefix shared by all words.
            for(char letter:prefix.toCharArray()) { // Consume the requested prefix one edge at a time.
                node=node.child.get(letter); if(node==null) break; // No word can continue through an absent edge.
            }
            answers.add(node!=null&&node.pass>0); // Require at least one stored word, even for the empty prefix.
        }
        return answers.toString(); // Return one answer per requested prefix.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(new String[]{"apple","ape"},new String[]{"ap","app","bat"})); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(new String[]{"apple","ape"},new String[]{"ap","app","bat"}), "[true, true, false]"); // Shared and absent prefixes
        Check.equal(solve(new String[]{},new String[]{""}), "[false]"); // An empty dictionary has no matching word
        Check.equal(solve(new String[]{""},new String[]{"","a"}), "[true, false]"); // An empty stored word shares the empty prefix
    }
}
