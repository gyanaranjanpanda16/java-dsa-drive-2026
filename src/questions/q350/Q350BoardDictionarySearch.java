package questions.q350; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Find all dictionary words on a letter board.
 * Contract: Given a rectangular board of lowercase a..z letters and nonempty lowercase dictionary words, return the sorted unique words formed by orthogonal moves. A cell cannot be reused within a word. Empty board or dictionary returns no matches. Use small boards: the search is exponential in maximum word length.
 * Why: Running a separate board search for every dictionary word repeats work for shared prefixes. A trie stores each prefix once. During DFS, abandon a path immediately when its next letter has no trie edge. Mark a cell before exploring neighbors and unmark it on return so different candidate paths can reuse it. Trie nodes encode a prefix, not just whether a whole word exists. This combines dictionary lookup with spatial constraints, as in word-board games.
 * Cost: Trie construction O(S) space/time for S dictionary characters. A conservative search bound is O(R*C*4^L*L), including result-string comparisons, where L is maximum word length. Extra working space is O(S+R*C+L), plus output.
 */
public final class Q350BoardDictionarySearch { // Keep the algorithm, demonstration, and boundary checks together.
    private Q350BoardDictionarySearch() { } // Only static entry points are needed for this exercise.
    private static final class Node { // One trie node represents one shared dictionary prefix.
        final Node[] next=new Node[26]; String word; // A non-null word marks the end of a dictionary entry.
    }
    public static List<String> solve(String[] board,String[] words) { // Keep the caller's board immutable.
        Node root=new Node(); // All words begin at the empty prefix.
        for(String word:words) { // Validate and insert every dictionary word.
            if(word.isEmpty()) throw new IllegalArgumentException("nonempty words required"); // Paths must consume at least one cell.
            Node node=root; // Restart insertion from the empty prefix.
            for(char c:word.toCharArray()) { int index=index(c); if(node.next[index]==null) node.next[index]=new Node(); node=node.next[index]; } // Reuse existing prefixes.
            node.word=word; // Duplicate dictionary entries share the same terminal.
        }
        int rows=board.length,cols=rows==0?0:board[0].length(); // Empty dimensions have no starting cells.
        for(String row:board) { if(row.length()!=cols) throw new IllegalArgumentException("rectangular board required"); for(char c:row.toCharArray()) index(c); } // Validate every cell before searching.
        boolean[][] used=new boolean[rows][cols]; Set<String> found=new TreeSet<>(); // Track current-path membership separately from deduplicated answers.
        for(int r=0;r<rows;r++) for(int c=0;c<cols;c++) search(board,r,c,root,used,found); // A word can start at any cell.
        return new ArrayList<>(found); // Return lexicographic order without exposing internal state.
    }
    private static int index(char c) { // Map the declared alphabet to trie edges.
        if(c<'a'||c>'z') throw new IllegalArgumentException("a..z required"); // Other characters would index outside the trie.
        return c-'a'; // The first letter uses slot zero.
    }
    private static void search(String[] board,int r,int c,Node prefix,boolean[][] used,Set<String> found) { // Explore one path extension.
        if(r<0||r>=board.length||c<0||c>=board[0].length()||used[r][c]) return; // Stop at boundaries or a cell already on this path.
        Node node=prefix.next[board[r].charAt(c)-'a']; if(node==null) return; // No dictionary word can extend this prefix.
        if(node.word!=null) found.add(node.word); // A terminal can be an answer and still have longer descendants.
        used[r][c]=true; // Reserve the cell before visiting neighbors.
        search(board,r+1,c,node,used,found); search(board,r-1,c,node,used,found); // Explore vertical continuations.
        search(board,r,c+1,node,used,found); search(board,r,c-1,node,used,found); // Explore horizontal continuations.
        used[r][c]=false; // Restore state for alternative paths and starting cells.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new String[]{"ab","cd"},new String[]{"ab","ac","abdc","aba"}))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new String[]{"ab","cd"},new String[]{"ab","ac","abdc","aba"}), List.of("ab","abdc","ac")); // Shared prefixes and forbidden cell reuse.
        Check.equal(solve(new String[]{},new String[]{"a"}), List.of()); // Empty board.
        Check.equal(solve(new String[]{"a"},new String[]{"a","a","aa"}), List.of("a")); // Duplicate dictionary words appear once.
    }
}
