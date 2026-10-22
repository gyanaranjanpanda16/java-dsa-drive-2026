package questions.q499; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Build an approximate membership filter with no false negatives.
 * Contract: Insert non-null strings into a bit array of positive size using1..16 deterministic derived hashes. Return might-contain booleans for queries. False positives are allowed; deletions are unsupported. This is an educational hash scheme, not a security or production filter.
 * Why: Insertion sets every derived bit; a query with any missing bit is definitely absent, while all bits set means only possibly present. Collisions can make unrelated strings appear present, but inserted strings cannot lose their bits. Useful as a compact prefilter before an exact lookup.
 * Cost: O(h*(inserted+queried)) derived-index operations plus string hashing, O(bits) bit storage and query outputs; hash independence is not guaranteed by this teaching construction.
 */
public final class Q499BloomMembership { // Keep the algorithm, demonstration, and boundary checks together.
    private Q499BloomMembership() { } // Only static entry points are needed for this exercise.
    private static int position(String word,int hash,int bits) { // Derive reproducible bit positions without platform-dependent object identity hashes.
        int code=word.hashCode(); long first=Integer.toUnsignedLong(code),step=Integer.toUnsignedLong(Integer.rotateLeft(code,15)^0x9e3779b9)|1L; // Use a nonzero odd step in a simple double-hash sequence.
        return (int)((first+hash*step)%bits); // The bounded hash count keeps this arithmetic within long.
    }
    public static List<Boolean> solve(int bits,int hashes,String[] inserted,String[] queries) { // Offer probabilistic membership, not exact set semantics.
        if(bits<=0||hashes<1||hashes>16) throw new IllegalArgumentException("invalid filter dimensions"); // Require valid storage and bounded hash work.
        BitSet filter=new BitSet(bits); // One bit may be shared by many inserted strings.
        for(String word:inserted) for(int hash=0;hash<hashes;hash++) filter.set(position(word,hash,bits)); // Insertion only sets bits, so it cannot erase an earlier member's evidence.
        List<Boolean> result=new ArrayList<>(); // Each query states possible presence or definite absence.
        for(String word:queries) { // Check the same positions used during insertion.
            boolean possible=true; for(int hash=0;hash<hashes;hash++) if(!filter.get(position(word,hash,bits))) { possible=false; break; } // One missing bit proves this string was never inserted.
            result.add(possible); // All bits present can still be a collision-induced false positive.
        }
        return result; // An exact backing store must confirm positive results when correctness requires it.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(1,2,new String[]{"a"},new String[]{"a","b"}))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(1,2,new String[]{"a"},new String[]{"a","b"}), List.of(true,true)); // A deliberately tiny filter demonstrates a false positive.
        Check.equal(solve(64,3,new String[]{},new String[]{"apple"}), List.of(false)); // An empty filter gives definite absence.
        Check.equal(solve(128,4,new String[]{"apple","pear"},new String[]{"pear","apple"}), List.of(true,true)); // Inserted members cannot be false negatives.
    }
}
