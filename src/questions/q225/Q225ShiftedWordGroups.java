package questions.q225; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Group words equal under one cyclic alphabet shift.
 * Contract: Group lowercase ASCII strings when shifting every letter by the same offset modulo26 transforms one into another. Preserve duplicates; sort words within groups and sort groups by their first word.
 * Why: Adjacent modular differences are unchanged by a uniform shift, and reconstruct the whole word once its first letter is chosen. Empty strings need their own signature. This is a canonicalization technique for equivalence-class grouping.
 * Cost: O(total characters+n log n string-comparison work) time and O(total characters+n) space.
 */
public final class Q225ShiftedWordGroups { // Keep the algorithm, demonstration, and boundary checks together.
    private Q225ShiftedWordGroups() { } // Only static entry points are needed for this exercise.
    public static List<List<String>> solve(String[] words) { // Return stable groups rather than relying on hash-map iteration order.
        Map<String,List<String>> groups=new HashMap<>(); // Map each normalized shape to its matching words.
        for(String word:words) { // Construct a signature independently for each word.
            StringBuilder key=new StringBuilder(word.isEmpty()?"empty":"word:"); // Separate the empty string from one-letter words.
            for(int i=0;i<word.length();i++) { // Validate characters while recording adjacent differences.
                char c=word.charAt(i); if(c<'a'||c>'z') throw new IllegalArgumentException("lowercase ASCII required"); // Define modulo26 over one alphabet.
                if(i>0) key.append((c-word.charAt(i-1)+26)%26).append(','); // Delimit differences so multi-digit offsets remain unambiguous.
            }
            groups.computeIfAbsent(key.toString(),ignored->new ArrayList<>()).add(word); // Equal signatures share exactly one bucket.
        }
        List<List<String>> result=new ArrayList<>(groups.values()); // Convert unordered buckets into sortable output.
        result.forEach(Collections::sort); result.sort(Comparator.comparing(group->group.get(0))); // Specify both inner and outer ordering.
        return result; // Every input occurrence belongs to one canonical group.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new String[]{"abc","bcd","az","ba"}))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new String[]{"abc","bcd","az","ba"}), List.of(List.of("abc","bcd"),List.of("az","ba"))); // Two distinct shift shapes.
        Check.equal(solve(new String[]{"","a","z"}), List.of(List.of(""),List.of("a","z"))); // Empty strings differ from single letters.
        Check.equal(solve(new String[]{"a","a"}), List.of(List.of("a","a"))); // Duplicates remain in their group.
    }
}
