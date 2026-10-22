package questions.q158;

import java.util.*;
import questions.support.Check;

/**
 * Q158: Check a one-to-one character substitution.
 * Contract: Return whether two equal-length strings admit a bijection between their UTF-16 chars.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: Forward and reverse maps prevent both inconsistent source substitutions and two different source chars sharing one target.
 * Complexity: Expected O(n) time and O(u) space.
 */
public final class Q158Isomorphic { // Keep one question and its tests together for independent study.
    public static boolean solve(String a,String b) { // A substitution changes symbols but not string length.
        if(a.length()!=b.length()) return false; // Unequal lengths cannot align position by position.
        Map<Character,Character> forward=new HashMap<>(),reverse=new HashMap<>(); // Check both directions of the proposed bijection.
        for(int i=0;i<a.length();i++) { // Every aligned pair imposes one mapping constraint.
            char x=a.charAt(i),y=b.charAt(i); // Name the source and target chars at this position.
            if(forward.containsKey(x)&&forward.get(x)!=y) return false; // One source must never map to two targets.
            if(reverse.containsKey(y)&&reverse.get(y)!=x) return false; // Two sources must never map to one target.
            forward.put(x,y);reverse.put(y,x); // Record the compatible pair for later repeated occurrences.
        }
        return true; // All positional constraints agree in both directions.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve("egg","add")); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve("egg","add"),true); // Repeated chars obey one mapping.
    Check.equal(solve("foo","bar"),false); // A source char would map inconsistently.
    Check.equal(solve("ab","cc"),false); // Reverse uniqueness is necessary.
    }
}
