package dsa.tries;

import java.util.Objects;

/** Lowercase English a-z trie. Empty words and prefixes are supported. */
public final class Trie {
    private static final class Node {
        final Node[] children = new Node[26];
        boolean word;
    }
    private final Node root = new Node();

    /** O(L) time, up to O(26L) new reference slots. Duplicate insertion is harmless. */
    public void insert(String word) {
        validate(word); // Validate before mutation, so rejected input changes nothing.
        Node current = root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (current.children[index] == null) current.children[index] = new Node();
            current = current.children[index];
        }
        current.word = true; // A path alone is only a prefix, not necessarily a word.
    }
    /** O(L) time, O(1) auxiliary space. */
    public boolean contains(String word) {
        Node node = walk(word);
        return node != null && node.word;
    }
    /** O(L) time, O(1) auxiliary space. The empty prefix always exists. */
    public boolean startsWith(String prefix) { return walk(prefix) != null; }
    private Node walk(String text) {
        validate(text);
        Node current = root;
        for (int i = 0; i < text.length(); i++) {
            current = current.children[text.charAt(i) - 'a'];
            if (current == null) return null;
        }
        return current;
    }
    private static void validate(String text) {
        Objects.requireNonNull(text, "text");
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c < 'a' || c > 'z') throw new IllegalArgumentException("only lowercase a-z supported");
        }
    }
    public static void main(String[] args) {
        Trie dictionary = new Trie();
        dictionary.insert("car"); dictionary.insert("cart"); dictionary.insert("cat");
        System.out.println("Prefix ca: " + dictionary.startsWith("ca"));
        System.out.println("Word ca: " + dictionary.contains("ca"));
        System.out.println("Word car: " + dictionary.contains("car"));
    }
}
