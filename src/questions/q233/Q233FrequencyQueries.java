package questions.q233; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Answer whether any value has an exact frequency.
 * Contract: Process[1,value] insert,[2,value] delete one if present,[3,positiveFrequency] query. Return query booleans.
 * Why: Maintain both value→frequency and frequency→number of values. Every insertion or deletion moves one value from its old frequency bucket to its new one. Exact-frequency existence then takes expected constant time rather than scanning all distinct values.
 * Cost: Expected O(m) time and O(u) space for u distinct values ever retained.
 */
public final class Q233FrequencyQueries { // Keep the algorithm, demonstration, and boundary checks together.
    private Q233FrequencyQueries() { } // Only static entry points are needed for this exercise.
    private static void change(Map<Integer,Integer> buckets,int frequency,int delta) { // Update only positive-frequency populations.
        if(frequency==0) return; // Absent values are not a finite stored bucket of frequency zero.
        int count=buckets.getOrDefault(frequency,0)+delta; // Add or remove one value from this population.
        if(count==0) buckets.remove(frequency); else buckets.put(frequency,count); // Keep only populated frequency buckets.
    }
    public static List<Boolean> solve(int[][] operations) { // Decouple membership counts from frequency-existence queries.
        Map<Integer,Integer> values=new HashMap<>(),buckets=new HashMap<>(); List<Boolean> output=new ArrayList<>(); // Both indexes begin empty.
        for(int[] op:operations) { // Each mutation affects exactly one value and two populations.
            if(op[0]==3) { // Queries concern frequency, not whether op[1] is itself stored.
                if(op[1]<=0) throw new IllegalArgumentException("positive queried frequency required"); // Zero-frequency universe is outside the data structure.
                output.add(buckets.containsKey(op[1])); continue; // Presence of a bucket proves at least one matching value.
            }
            if(op[0]!=1 && op[0]!=2) throw new IllegalArgumentException("unknown operation"); // Only insert and delete modify counts.
            int old=values.getOrDefault(op[1],0),next=op[0]==1?old+1:Math.max(0,old-1); // Deleting a missing value leaves it absent.
            change(buckets,old,-1); change(buckets,next,1); // Move this value between its old and new populations.
            if(next==0) values.remove(op[1]); else values.put(op[1],next); // Synchronize the primary frequency map.
        }
        return output; // Every query was answered from the maintained secondary index.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new int[][]{{1,9},{1,9},{1,8},{3,2},{2,9},{3,2},{3,1}}))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new int[][]{{1,9},{1,9},{1,8},{3,2},{2,9},{3,2},{3,1}}), List.of(true,false,true)); // Moving a value empties one frequency bucket.
        Check.equal(solve(new int[][]{{2,7},{3,1}}), List.of(false)); // Deletion of a missing value is harmless.
        Check.equal(solve(new int[][]{{1,1},{1,2},{3,1}}), List.of(true)); // Two values may share the queried frequency.
    }
}
