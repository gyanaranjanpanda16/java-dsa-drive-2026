package questions.q348; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q348: Count dictionary occurrences under each prefix.
 * Contract: Non-null strings and arrays; case-sensitive UTF-16 code-unit matching; duplicates are allowed unless stated otherwise.
 * Why: Each inserted word increments exactly the nodes corresponding to its prefixes. Therefore pass at a prefix node equals the number of inserted occurrences beginning with that prefix. This avoids enumerating all descendants for popularity counts or prefix-based inventory summaries.
 * Complexity: O((S+P) log(A+1)) time and O(S) space plus results.
 */
public final class Q348TriePrefixCounts { // One class contains this question, its answer, and reproducible checks.
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
        Node root=build(words); List<Integer> counts=new ArrayList<>(); // Insertion counts every occurrence, not merely distinct terminal words.
        for(String prefix:prefixes) { // Retrieve a precomputed subtree population for each query.
            Node node=root; // The empty prefix initially contains all inserted occurrences.
            for(char letter:prefix.toCharArray()) { // Descend only through the queried prefix.
                node=node.child.get(letter); if(node==null) break; // An absent prefix has population zero.
            }
            counts.add(node==null?0:node.pass); // Every insertion incremented precisely the nodes its word passes through.
        }
        return counts.toString(); // Duplicate dictionary words contribute repeatedly by contract.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(new String[]{"app","apple","app","ape"},new String[]{"ap","app","z",""})); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(new String[]{"app","apple","app","ape"},new String[]{"ap","app","z",""}), "[4, 3, 0, 4]"); // Duplicates count as separate occurrences
        Check.equal(solve(new String[]{},new String[]{""}), "[0]"); // No stored occurrences
        Check.equal(solve(new String[]{"a","ab"},new String[]{"a","ab","abc"}), "[2, 1, 0]"); // Nested prefix populations
    }
}
