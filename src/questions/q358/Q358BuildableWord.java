package questions.q358; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Find a longest word whose every prefix is a word.
 * Contract: Return the longest dictionary word whose every nonempty prefix is also in the dictionary; break ties lexically. Return empty string if none qualifies.
 * Why: A path is buildable only while every visited node is terminal. Checking this chain excludes words that skip an intermediate prefix. This models incremental word construction or commands that must remain valid after each added character.
 * Cost: O(S log(A+1)+n log n string-comparison work) time and O(S+n) space.
 */
public final class Q358BuildableWord { // Keep the algorithm, demonstration, and boundary checks together.
    private Q358BuildableWord() { } // Only static entry points are needed for this exercise.
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
    public static String solve(String[] words) { // Prefer the lexical first candidate among equal lengths.
        Node root=build(words); String[] ordered=words.clone(); Arrays.sort(ordered); String best=""; // Sorting provides a deterministic tie order.
        for(String word:ordered) { // A later equal-length candidate cannot beat an earlier lexical one.
            Node node=root; boolean valid=true; // Assume validity until a missing terminal prefix disproves it.
            for(char c:word.toCharArray()) { node=node.child.get(c); if(!node.terminal) { valid=false; break; } } // Existing edges alone are not enough; every prefix must end a word.
            if(valid && word.length()>best.length()) best=word; // Replace only with a strictly longer valid chain.
        }
        return best; // Empty string remains the fallback when all nonempty chains fail.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new String[]{"w","wo","wor","worl","world","banana"}))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new String[]{"w","wo","wor","worl","world","banana"}), "world"); // A complete five-prefix chain.
        Check.equal(solve(new String[]{"a","ap","app","b","ba","bat"}), "app"); // Lexical tie-breaking between equal-length chains.
        Check.equal(solve(new String[]{"abc"}), ""); // Missing shorter prefixes prevents construction.
    }
}
