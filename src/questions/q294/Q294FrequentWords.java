package questions.q294; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q294: Select the most frequent words with lexical ties.
 * Contract: Non-null words; exact Java String equality and natural UTF-16 lexical order; k>=0.
 * Why: Map frequencies establish each word priority. Comparing frequency first and text second defines a total deterministic ranking; queue removal always yields the best unreturned word.
 * Complexity: O(nL+u log(u+1)L) worst-case character work for maximum word length L; O(uL) stored keys/reference-related data.
 */
public final class Q294FrequentWords { // One class contains this question, its answer, and reproducible checks.

    public static String solve(String[] words,int k) { // Expose the answer as a reusable method as well as a runnable program.
        if(k<0) throw new IllegalArgumentException("negative count"); // A count cannot be negative.
        Map<String,Integer> counts=new HashMap<>(); // Equal words share one frequency entry.
        for(String word:words) counts.merge(Objects.requireNonNull(word),1,Integer::sum); // Reject absent words and count exact case-sensitive matches.
        PriorityQueue<String> heap=new PriorityQueue<>((a,b)->counts.get(a).equals(counts.get(b))?a.compareTo(b):Integer.compare(counts.get(b),counts.get(a))); // Frequency dominates, then lexical order determines ties.
        heap.addAll(counts.keySet()); // Each distinct word is a separate ranked candidate.
        List<String> answer=new ArrayList<>(); // Poll order becomes answer order.
        while(answer.size()<k&&!heap.isEmpty()) answer.add(heap.poll()); // Emit at most the number of available words.
        return answer.toString(); // String list formatting reveals the ranking.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(new String[]{"java","dsa","java","array","dsa"},2)); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(new String[]{"java","dsa","java","array","dsa"},2), "[dsa, java]"); // Lexical tie-break
        Check.equal(solve(new String[]{},3), "[]"); // Empty vocabulary
        Check.equal(solve(new String[]{"A","a"},5), "[A, a]"); // Case-sensitive ordering
    }
}
