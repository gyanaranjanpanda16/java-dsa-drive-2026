package questions.q467;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q467: Minimum Rescue Boats.
 * Contract: At most two people per boat, total weight at most limit; return-1 if an individual exceeds it.
 * Why it works: The heaviest remaining person must take a boat. If the lightest cannot accompany them, nobody can; otherwise pairing with the lightest can be exchanged into an optimal solution without adding boats.
 * Cost: O(n log n) time and O(n) copied space.
 */
public final class Q467MinimumRescueBoats { // Keep this question independently runnable and testable.
    private Q467MinimumRescueBoats() { } // No object state is needed by this standalone exercise.
    public static int minimum(int[] weights,int limit) { // Expose the operation for an independent caller and the executable checks below.
        if(limit<0) throw new IllegalArgumentException("Nonnegative limit"); // Boat capacity must be meaningful.
        int[] sorted=weights.clone(); Arrays.sort(sorted); // Pairing decisions require the lightest and heaviest remaining people.
        for(int w:sorted) { if(w<0) throw new IllegalArgumentException("Nonnegative weights"); if(w>limit) return -1; } // One overweight person makes rescue impossible.
        int left=0,right=sorted.length-1,boats=0; // Each iteration assigns the heaviest remaining person to one boat.
        while(left<=right) { // Continue until every person has been assigned.
            if((long)sorted[left]+sorted[right]<=limit) left++; // If possible, add the lightest remaining partner without excluding any feasible better pairing.
            right--; boats++; // The heaviest person leaves on this boat either paired or alone.
        }
        return boats; // Every assigned boat is necessary for its heaviest passenger.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(minimum(new int[]{3,2,2,1},3)); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(minimum(new int[]{},3)==0, "Q467 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(minimum(new int[]{4},3)==-1, "Q467 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(minimum(new int[]{1,2},3)==1, "Q467 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
