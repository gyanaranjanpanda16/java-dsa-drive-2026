package questions.q326; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q326: Find the shortest one-letter word transformation.
 * Contract: Lowercase English words of the same length; every transformed word must belong to the dictionary; begin itself need not belong.
 * Why: Words form an implicit graph with edges between equal-length words differing in one letter. BFS explores this graph by transformation count; dictionary removal ensures each word enters the queue once.
 * Complexity: O(N L² A) time with copied strings of length L and alphabet size A=26; O(NL) dictionary/frontier storage.
 */
public final class Q326WordLadder { // One class contains this question, its answer, and reproducible checks.

    public static int solve(String begin,String end,String[] words) { // Expose the answer as a reusable method as well as a runnable program.
        if(begin.equals(end)) return 1; // A zero-change route contains the starting word itself.
        Set<String> remaining=new HashSet<>(Arrays.asList(words)); // The dictionary also acts as the unvisited vertex set.
        if(!remaining.contains(end)) return 0; // The destination must be an allowed transformed word.
        Deque<String> q=new ArrayDeque<>(); q.add(begin); remaining.remove(begin); // Start one BFS frontier without revisiting the starting word.
        int length=1; // Count words, rather than transformation edges, in the answer.
        while(!q.isEmpty()) { // All words in one queue layer have equal shortest route length.
            int size=q.size(); // New arrivals belong to the next layer, not the current one.
            for(int j=0;j<size;j++) { // Expand every word at the current distance.
                char[] text=q.remove().toCharArray(); // Change one position at a time to enumerate implicit neighbors.
                for(int i=0;i<text.length;i++) { // Each position may be the only changed letter.
                    char original=text[i]; // Restore this position before trying another position.
                    for(char c='a';c<='z';c++) { // Every permitted replacement letter is considered.
                        text[i]=c; String candidate=new String(text); // Materialize the neighbor for dictionary lookup.
                        if(remaining.remove(candidate)) { // Removal marks discovery atomically within this single-threaded search.
                            if(candidate.equals(end)) return length+1; // First BFS discovery gives the minimum transformation count.
                            q.add(candidate); // Schedule other unseen dictionary neighbors for the next layer.
                        }
                    }
                    text[i]=original; // Keep other positions identical to the current source word.
                }
            }
            length++; // Advance only after finishing the entire current frontier.
        }
        return 0; // Exhausting reachable dictionary words proves no transformation exists.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve("hit","cog",new String[]{"hot","dot","dog","lot","log","cog"})); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve("hit","cog",new String[]{"hot","dot","dog","lot","log","cog"}), 5); // Shortest route has five words
        Check.equal(solve("a","c",new String[]{"b"}), 0); // Destination missing
        Check.equal(solve("same","same",new String[]{}), 1); // No transformation needed
    }
}
