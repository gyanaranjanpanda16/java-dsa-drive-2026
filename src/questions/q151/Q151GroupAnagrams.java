package questions.q151;

import java.util.*;
import questions.support.Check;

/**
 * Q151: Group words with equal character multiplicities.
 * Contract: Group non-null words by exact UTF-16 character multiset, preserving first group appearance and input order inside groups.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: Sorting a word produces the same signature exactly when its character multiset is equal; the map assigns every word to that equivalence class.
 * Complexity: O(sum of k log(k+1)) time and O(total characters) space.
 */
public final class Q151GroupAnagrams { // Keep one question and its tests together for independent study.
    public static List<List<String>> solve(String[] words) { // Return deterministic group order rather than hash iteration order.
        Map<String,List<String>> groups=new LinkedHashMap<>(); // First insertion order records when a signature first appears.
        for(String word:words) { // Each input occurrence must belong to exactly one group.
            char[] letters=word.toCharArray(); // Copy because sorting must not mutate the original word.
            Arrays.sort(letters); // Equal multisets produce an identical sorted sequence.
            String key=new String(letters); // Use value equality rather than char-array identity as the key.
            groups.computeIfAbsent(key,k->new ArrayList<>()).add(word); // Create the equivalence class once and append in input order.
        }
        return new ArrayList<>(groups.values()); // Preserve the stable order of groups.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve(new String[]{"eat","tea","tan","ate"})); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve(new String[]{"eat","tea","tan","ate"}),List.of(List.of("eat","tea","ate"),List.of("tan"))); // Equal signatures share a group.
    Check.equal(solve(new String[]{}),List.of()); // No words means no groups.
    Check.equal(solve(new String[]{"",""}),List.of(List.of("",""))); // Empty words share an empty signature.
    }
}
