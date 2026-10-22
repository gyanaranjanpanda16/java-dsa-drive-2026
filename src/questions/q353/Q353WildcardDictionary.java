package questions.q353; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Search dictionary patterns with single-character wildcards.
 * Contract: Dictionary words and patterns use lowercase ASCII letters; dot in a pattern matches exactly one letter. Return whether each full pattern matches a stored word. Empty words/patterns are supported; recursion depth must fit the JVM stack.
 * Why: Literal characters choose one trie edge, while a dot explores all available edges. A match succeeds only when the entire pattern ends at a terminal node. This models wildcard dictionary queries without expanding every possible alphabet string.
 * Cost: O(S*Q) worst-case query node visits plus construction for Q patterns and S trie nodes; O(S+H) storage.
 */
public final class Q353WildcardDictionary { // Keep the algorithm, demonstration, and boundary checks together.
    private Q353WildcardDictionary() { } // Only static entry points are needed for this exercise.
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
    private static boolean match(Node node,String pattern,int index) { // Match the remaining pattern against exactly this prefix subtree.
        if(index==pattern.length()) return node.terminal; // A prefix alone is insufficient when no word ends here.
        char c=pattern.charAt(index); // Select literal descent or wildcard branching.
        if(c!='.') { Node next=node.child.get(c); return next!=null && match(next,pattern,index+1); } // A missing literal edge immediately disproves this branch.
        for(Node next:node.child.values()) if(match(next,pattern,index+1)) return true; // A wildcard needs only one successful child branch.
        return false; // Every permitted next character failed to complete a word.
    }
    public static List<Boolean> solve(String[] words,String[] patterns) { // Share dictionary construction across independent pattern queries.
        Node root=build(words); List<Boolean> result=new ArrayList<>(); // Terminal markers encode exact word lengths.
        for(String pattern:patterns) result.add(match(root,pattern,0)); // Begin each full-word match at the empty prefix.
        return result; // Preserve query order in the returned booleans.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new String[]{"bad","dad","mad"},new String[]{".ad","b..",".."}))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new String[]{"bad","dad","mad"},new String[]{".ad","b..",".."}), List.of(true,true,false)); // Wildcard alternatives and exact length.
        Check.equal(solve(new String[]{""},new String[]{"","."}), List.of(true,false)); // Empty words match only empty patterns.
        Check.equal(solve(new String[]{},new String[]{"a"}), List.of(false)); // No dictionary words.
    }
}
