package questions.q232; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Pop the most frequent value with recency ties.
 * Contract: Process[0,value] push and[1,0] pop. Pop the value with highest current frequency, breaking ties by most recent unmatched push. Every pop must have a nonempty structure.
 * Why: Each frequency level has a stack of the pushes that reached that level. The top of the highest nonempty level is exactly the most frequent, most recent candidate. This combines ranking with recency without sorting on every pop.
 * Cost: Expected O(m) total time and O(m) space.
 */
public final class Q232FrequencyStack { // Keep the algorithm, demonstration, and boundary checks together.
    private Q232FrequencyStack() { } // Only static entry points are needed for this exercise.
    public static List<Integer> solve(int[][] operations) { // Keep per-value counts and per-frequency push histories consistent.
        Map<Integer,Integer> frequency=new HashMap<>(); Map<Integer,Deque<Integer>> levels=new HashMap<>(); // Hash lookup locates each value and frequency bucket.
        int maximum=0; List<Integer> output=new ArrayList<>(); // Maximum is zero exactly when the logical stack is empty.
        for(int[] op:operations) { // Apply pushes and pops in encounter order.
            if(op[0]==0) { // A new push raises exactly one value's frequency.
                int count=frequency.merge(op[1],1,Integer::sum); // Store the value's new multiplicity.
                levels.computeIfAbsent(count,key->new ArrayDeque<>()).push(op[1]); maximum=Math.max(maximum,count); // Record recency within this frequency level.
            } else if(op[0]==1) { // A pop chooses the highest frequency before consulting recency.
                if(maximum==0) throw new IllegalArgumentException("empty frequency stack"); // There is no valid value to remove.
                Deque<Integer> bucket=levels.get(maximum); int value=bucket.pop(); // Its newest push wins among equal-frequency candidates.
                frequency.put(value,frequency.get(value)-1); output.add(value); // Reflect one removed occurrence and expose the chosen value.
                if(bucket.isEmpty()) { levels.remove(maximum); maximum--; } // The next possible maximum is one level lower.
            } else throw new IllegalArgumentException("unknown operation"); // Unknown command codes must not be interpreted as pops.
        }
        return output; // Lower-level history remains available for later pops.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new int[][]{{0,5},{0,7},{0,5},{0,7},{0,4},{0,5},{1,0},{1,0},{1,0},{1,0}}))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new int[][]{{0,5},{0,7},{0,5},{0,7},{0,4},{0,5},{1,0},{1,0},{1,0},{1,0}}), List.of(5,7,5,4)); // Frequency priority followed by recency.
        Check.equal(solve(new int[][]{{0,1},{0,2},{1,0},{1,0}}), List.of(2,1)); // All frequencies one behaves like a stack.
        Check.equal(solve(new int[][]{}), List.of()); // No operations.
    }
}
