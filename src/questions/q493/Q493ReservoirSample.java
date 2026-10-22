package questions.q493; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Sample k stream values uniformly with bounded storage.
 * Contract: For0<=k<=input.length, return a size-k reservoir sample using java.util.Random with the supplied seed. Order is reservoir-slot order; sampling is over positions, so equal values remain distinct occurrences.
 * Why: After seeing i+1 elements, select a uniform integer in0..i. If it is below k, replace that reservoir slot. A new item enters with probability k/(i+1), and each older item survives with exactly the probability needed to maintain that same inclusion chance. Useful when a stream length is unknown ahead of time.
 * Cost: O(n) time and O(k) output/storage; the fixed seed is for reproducible teaching, not security.
 */
public final class Q493ReservoirSample { // Keep the algorithm, demonstration, and boundary checks together.
    private Q493ReservoirSample() { } // Only static entry points are needed for this exercise.
    public static int[] solve(int[] input,int k,long seed) { // Sample positions without keeping an additional copy of the entire stream.
        if(k<0||k>input.length) throw new IllegalArgumentException("invalid sample size"); // A fixed-size sample cannot exceed observed input length.
        int[] reservoir=Arrays.copyOf(input,k); Random random=new Random(seed); // The first k observations initially occupy the reservoir.
        for(int i=k;i<input.length;i++) { // Incorporate one later observation using a uniform replacement decision.
            int slot=random.nextInt(i+1); // Each of the i+1 observed positions is equally likely to receive this draw.
            if(slot<k) reservoir[slot]=input[i]; // Only k of those outcomes replace a retained sample position.
        }
        return reservoir; // Uniform inclusion does not imply sorted output or cryptographic unpredictability.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new int[]{1,2,3,4,5},2,0))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new int[]{1,2,3,4,5},2,0), new int[]{3,2}); // A reproducible nontrivial replacement sequence.
        Check.equal(solve(new int[]{1,2},0,9), new int[]{}); // A zero-size reservoir stays empty.
        Check.equal(solve(new int[]{4,4,7},3,5), new int[]{4,4,7}); // Sampling every position retains duplicates.
    }
}
