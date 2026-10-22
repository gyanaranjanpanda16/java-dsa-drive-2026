package questions.q159;

import java.util.*;
import questions.support.Check;

/**
 * Q159: Match pattern symbols bijectively to words.
 * Contract: Given a char pattern and an array of word tokens, require one-to-one symbol-to-word mapping with matching lengths.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: Bidirectional maps encode the same equality structure on both sequences: repeated symbols require equal words and distinct symbols cannot reuse a word.
 * Complexity: Expected O(total token characters) time and O(number of distinct tokens) map entries.
 */
public final class Q159WordPattern { // Keep one question and its tests together for independent study.
    public static boolean solve(String pattern,String[] words) { // Tokenization is separate from this matching problem.
        if(pattern.length()!=words.length) return false; // Each symbol must correspond to exactly one token.
        Map<Character,String> forward=new HashMap<>(); // Remember the word assigned to each symbol.
        Map<String,Character> reverse=new HashMap<>(); // Prevent different symbols from claiming the same word.
        for(int i=0;i<words.length;i++) { // Compare aligned symbols and tokens.
            char symbol=pattern.charAt(i);String word=words[i]; // Name the two values imposing this constraint.
            if(forward.containsKey(symbol)&&!forward.get(symbol).equals(word)) return false; // A repeated symbol must keep its earlier word.
            if(reverse.containsKey(word)&&reverse.get(word)!=symbol) return false; // A repeated word must keep its earlier symbol.
            forward.put(symbol,word);reverse.put(word,symbol); // Extend both consistent mappings together.
        }
        return true; // Every symbol-word pair respects the bijection.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve("abba",new String[]{"dog","cat","cat","dog"})); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve("abba",new String[]{"dog","cat","cat","dog"}),true); // Matching repetition structure succeeds.
    Check.equal(solve("ab",new String[]{"dog","dog"}),false); // Distinct symbols cannot share a word.
    Check.equal(solve("",new String[]{}),true); // Both empty sequences match.
    }
}
