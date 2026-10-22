package questions.q350; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q350: Find the longest prefix shared by all dictionary words.
 * Contract: Non-null strings and arrays; case-sensitive UTF-16 code-unit matching; duplicates are allowed unless stated otherwise.
 * Why: A common prefix can continue only while all words follow the same next edge and none has already ended. The first branching node or terminal node is therefore the exact stopping boundary. Shared-path discovery appears in namespace grouping and directory compression.
 * Complexity: O(S log(A+1)) construction time plus O(H) descent; O(S) space. A direct character scan uses less auxiliary memory if no other trie queries are needed.
 */
public final class Q350TrieCommonPrefix { // One class contains this question, its answer, and reproducible checks.
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

    public static String solve(String[] words) { // Expose the answer as a reusable method as well as a runnable program.
        if(words.length==0) return ""; // No common characters are defined for an empty collection.
        Node node=build(words); StringBuilder common=new StringBuilder(); // Begin before any character has been chosen.
        while(!node.terminal&&node.child.size()==1) { // A word ending here or a branch prevents a longer common prefix.
            Map.Entry<Character,Node> edge=node.child.firstEntry(); // The sole edge is shared by every word beneath this node.
            common.append(edge.getKey()); node=edge.getValue(); // Commit this shared character and inspect the next prefix.
        }
        return common.toString(); // Stop exactly where words end or disagree.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(new String[]{"flower","flow","flight"})); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(new String[]{"flower","flow","flight"}), "fl"); // Words branch after fl
        Check.equal(solve(new String[]{"dog","dog"}), "dog"); // Identical words share their full length
        Check.equal(solve(new String[]{"","abc"}), ""); // An empty word forces an empty common prefix
    }
}
