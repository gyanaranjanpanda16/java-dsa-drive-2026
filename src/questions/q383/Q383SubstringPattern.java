package questions.q383; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Match a pattern to distinct variable-length substrings.
 * Contract: For a pattern of at most10 UTF-16 chars and text of at most20 UTF-16 chars, decide whether each distinct pattern char can map to a distinct nonempty substring so their concatenation equals the whole text. Repeated pattern chars must reuse the same substring. Both empty inputs match; only one empty input does not.
 * Why: Unlike mapping words separated by spaces, substring boundaries are unknown. Backtracking tries every nonempty candidate for a new pattern symbol. A map enforces consistent reuse and a set enforces injectivity: two symbols cannot share one substring. Existing assignments consume fixed prefixes, which prunes most branches. This models matching a small symbolic template against an unsegmented message.
 * Cost: O((m+1)^p * m) conservative exponential time for pattern length p and text length m. O(p*m) assignment storage plus recursion; small input bounds are intentional.
 */
public final class Q383SubstringPattern { // Keep the algorithm, demonstration, and boundary checks together.
    private Q383SubstringPattern() { } // Only static entry points are needed for this exercise.
    public static boolean solve(String pattern,String text) { // Bound the intentionally exponential search.
        if(pattern.length()>10||text.length()>20) throw new IllegalArgumentException("pattern<=10 and text<=20 required"); // Large inputs need a separate performance policy.
        return match(pattern,text,0,0,new HashMap<>(),new HashSet<>()); // Start with no symbol assignments.
    }
    private static boolean match(String pattern,String text,int p,int t,Map<Character,String> mapping,Set<String> used) { // Indices mark the unmatched suffixes.
        if(p==pattern.length()) return t==text.length(); // Matching a prefix alone is insufficient.
        char symbol=pattern.charAt(p); String assigned=mapping.get(symbol); // Repeated symbols must reuse their earlier spelling.
        if(assigned!=null) return text.startsWith(assigned,t)&&match(pattern,text,p+1,t+assigned.length(),mapping,used); // Consume exactly the fixed assignment if it matches.
        for(int end=t+1;end<=text.length();end++) { // A new symbol may consume any nonempty prefix.
            String candidate=text.substring(t,end); if(!used.add(candidate)) continue; // Distinct symbols must map to distinct strings.
            mapping.put(symbol,candidate); // Tentatively bind this previously unassigned symbol.
            if(match(pattern,text,p+1,end,mapping,used)) return true; // A complete matching assignment proves existence.
            mapping.remove(symbol); used.remove(candidate); // Undo both halves of the bijection before another candidate.
        }
        return false; // No legal nonempty candidate completed the match.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve("abab","redblueredblue"))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve("abab","redblueredblue"), true); // Repeated symbols consume repeated substrings.
        Check.equal(solve("ab","xx"), false); // Different symbols cannot both mean x.
        Check.equal(solve("",""), true); // Both inputs exhausted immediately.
        Check.equal(solve("aaaa","xyzxyzxyzxyz"), true); // One substring reused four times.
    }
}
