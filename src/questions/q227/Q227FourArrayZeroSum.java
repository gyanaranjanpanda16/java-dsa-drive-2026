package questions.q227; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Count zero-sum tuples from four arrays.
 * Contract: Count index tuples taking one int from each of four arrays whose total is zero. Duplicate occurrences count separately; the answer must fit long.
 * Why: Store frequencies of all sums from the first two arrays, then query the negation of each sum from the other two. Every tuple splits uniquely into one stored and one queried pair. This meet-in-the-middle technique replaces a four-way enumeration.
 * Cost: Expected O(ab+cd) time and O(ab) space for the four array lengths.
 */
public final class Q227FourArrayZeroSum { // Keep the algorithm, demonstration, and boundary checks together.
    private Q227FourArrayZeroSum() { } // Only static entry points are needed for this exercise.
    public static long solve(int[] a,int[] b,int[] c,int[] d) { // Preserve indexed multiplicity rather than deduplicating equal values.
        Map<Long,Long> sums=new HashMap<>(); // Long keys avoid overflow when two ints are added.
        for(int x:a) for(int y:b) sums.merge((long)x+y,1L,Long::sum); // Count every first-half pair exactly once.
        long total=0; // Accumulate the number of compatible full tuples.
        for(int x:c) for(int y:d) total+=sums.getOrDefault(-((long)x+y),0L); // A complementary sum is necessary and sufficient for total zero.
        return total; // Each second-half pair contributes every matching first-half pair.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new int[]{1,2},new int[]{-2,-1},new int[]{-1,2},new int[]{0,2}))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new int[]{1,2},new int[]{-2,-1},new int[]{-1,2},new int[]{0,2}), 2L); // Multiple sum combinations.
        Check.equal(solve(new int[]{0,0},new int[]{0},new int[]{0},new int[]{0}), 2L); // Duplicate occurrences are distinct choices.
        Check.equal(solve(new int[]{},new int[]{1},new int[]{2},new int[]{3}), 0L); // An empty array prevents any tuple.
    }
}
