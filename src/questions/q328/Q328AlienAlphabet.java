package questions.q328; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q328: Infer an alphabet order from sorted words.
 * Contract: Non-null words; UTF-16 char symbols; returns one deterministic valid alphabet or INVALID.
 * Why: The first different characters in adjacent lexicographically sorted words determine a directed precedence edge. A topological order satisfies all such constraints; a prefix violation or cycle proves no alphabet can explain the ordering.
 * Complexity: O(total characters + U log(U+1) + E) time, with ordered-set insertion adding O(E log(U+1)); O(U+E) graph space.
 */
public final class Q328AlienAlphabet { // One class contains this question, its answer, and reproducible checks.

    public static String solve(String[] words) { // Expose the answer as a reusable method as well as a runnable program.
        Map<Character,Set<Character>> edges=new TreeMap<>(); // Track only observed symbols and deduplicate ordering constraints.
        Map<Character,Integer> indegree=new HashMap<>(); // Count distinct required predecessors per symbol.
        for(String word:words) for(char c:word.toCharArray()) { edges.computeIfAbsent(c,x->new TreeSet<>()); indegree.putIfAbsent(c,0); } // Even unconstrained symbols must appear in the answer.
        for(int i=1;i<words.length;i++) { // Adjacent sorted words provide all necessary first-difference constraints.
            String a=words[i-1],b=words[i]; int j=0; // Compare the common prefix before deriving any order.
            while(j<Math.min(a.length(),b.length())&&a.charAt(j)==b.charAt(j)) j++; // Equal prefix letters reveal no new ordering.
            if(j==b.length()&&a.length()>b.length()) return "INVALID"; // A longer word cannot precede its own prefix in lexicographic order.
            if(j<Math.min(a.length(),b.length())&&edges.get(a.charAt(j)).add(b.charAt(j))) indegree.merge(b.charAt(j),1,Integer::sum); // Only the first differing pair constrains the alphabet.
        }
        PriorityQueue<Character> ready=new PriorityQueue<>(); for(char c:edges.keySet()) if(indegree.get(c)==0) ready.offer(c); // Natural char order breaks otherwise unconstrained ties deterministically.
        StringBuilder result=new StringBuilder(); // Append symbols as their predecessors are satisfied.
        while(!ready.isEmpty()) { // Process the constraint DAG one available symbol at a time.
            char c=ready.poll(); result.append(c); // This symbol can safely precede every remaining dependent.
            for(char next:edges.get(c)) if(indegree.merge(next,-1,Integer::sum)==0) ready.offer(next); // Release a symbol after its final predecessor is output.
        }
        return result.length()==edges.size()?result.toString():"INVALID"; // An unresolved cycle contradicts the claimed sorted input.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve(new String[]{"wrt","wrf","er","ett","rftt"})); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve(new String[]{"wrt","wrf","er","ett","rftt"}), "wertf"); // Multiple first-difference constraints
        Check.equal(solve(new String[]{"abc","ab"}), "INVALID"); // Invalid prefix order
        Check.equal(solve(new String[]{"z"}), "z"); // Only one observed symbol
    }
}
