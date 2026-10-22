package questions.q497; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Expire cache entries without deleting newer replacements.
 * Contract: Operations[time,code,key,value,ttl] use nonnegative nondecreasing integer times. Code0 puts with ttl>=0; code1 gets. An entry is absent at time>=putTime+ttl. Return get values or null. This example has expiration but no capacity limit.
 * Why: The map stores the current version of each key, while a min-heap schedules expirations. An old heap record must remove a key only if its version still matches; otherwise a newer replacement would be incorrectly deleted. Useful for timeout indexes and cache expiry scheduling.
 * Cost: O(m log(m+1)) total time and O(m) worst-case space because superseded heap records remain until their deadlines.
 */
public final class Q497ExpiringCache { // Keep the algorithm, demonstration, and boundary checks together.
    private Q497ExpiringCache() { } // Only static entry points are needed for this exercise.
    private record Item(int value,long expiry,long version) { } // A replacement receives a new identity even when key and deadline repeat.
    private record Expiry(int key,long time,long version) { } // Heap records schedule one historical version's expiration.
    public static List<Integer> solve(int[][] operations) { // Use logical time so behavior is reproducible without sleeping.
        Map<Integer,Item> entries=new HashMap<>(); PriorityQueue<Expiry> deadlines=new PriorityQueue<>(Comparator.comparingLong(Expiry::time).thenComparingLong(Expiry::version)); // Earliest deadlines are processed first.
        List<Integer> output=new ArrayList<>(); long version=0,previous=-1; // Version counters distinguish replacements and previous validates monotone time.
        for(int[] op:operations) { // Advance logical time before applying the operation at that time.
            long now=op[0]; if(now<0||now<previous) throw new IllegalArgumentException("times must be nondecreasing"); previous=now; // A one-way expiry sweep cannot support time travel.
            while(!deadlines.isEmpty() && deadlines.peek().time()<=now) { // Expire entries exactly at their deadline, not one tick later.
                Expiry expired=deadlines.remove(); Item current=entries.get(expired.key()); // The heap may contain superseded versions.
                if(current!=null && current.version()==expired.version()) entries.remove(expired.key()); // Never let an old timeout delete a newer replacement.
            }
            if(op[1]==0) { // Put replaces the current value and its expiration policy.
                if(op[4]<0) throw new IllegalArgumentException("negative ttl"); // Negative lifetimes are outside this model.
                if(op[4]==0) { entries.remove(op[2]); continue; } // A zero-lifetime insertion is immediately absent.
                Item item=new Item(op[3],now+op[4],++version); entries.put(op[2],item); // Widened time addition avoids int deadline overflow.
                deadlines.add(new Expiry(op[2],item.expiry(),item.version())); // Schedule this precise version for future cleanup.
            } else if(op[1]==1) { Item item=entries.get(op[2]); output.add(item==null?null:item.value()); } // Cleanup guarantees every remaining entry is live now.
            else throw new IllegalArgumentException("unknown operation"); // Only puts and gets are defined.
        }
        return output; // Reads do not refresh TTL in this contract.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new int[][]{{0,0,1,10,5},{2,0,1,20,10},{5,1,1,0,0},{12,1,1,0,0}}))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new int[][]{{0,0,1,10,5},{2,0,1,20,10},{5,1,1,0,0},{12,1,1,0,0}}), Arrays.asList(20,null)); // Stale expiration cannot remove a replacement.
        Check.equal(solve(new int[][]{{0,0,1,7,0},{0,1,1,0,0}}), Arrays.asList((Integer)null)); // Zero lifetime is immediately expired.
        Check.equal(solve(new int[][]{{0,0,1,7,2},{1,1,1,0,0},{2,1,1,0,0}}), Arrays.asList(7,null)); // The exact expiration boundary is exclusive.
    }
}
