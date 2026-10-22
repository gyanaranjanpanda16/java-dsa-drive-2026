package questions.q496; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Evict the least frequently used cache entry with LRU ties.
 * Contract: Capacity>=0. Operations[0,key,value] put,[1,key,0] get. Gets return value or null. Hits and updates increment frequency; new entries start at1. Evict minimum frequency, breaking ties by least recent access within that frequency.
 * Why: Map keys to entries and map frequencies to insertion-ordered key sets. A hit moves a key into the next-frequency bucket, at its newest end. Track the smallest nonempty frequency for direct eviction. This combines usage frequency with recency without scanning all entries.
 * Cost: Expected O(1) per operation and O(capacity) live storage plus outputs.
 */
public final class Q496LfuCache { // Keep the algorithm, demonstration, and boundary checks together.
    private Q496LfuCache() { } // Only static entry points are needed for this exercise.
    private static final class Entry { // Separate stored values from the frequency index that orders eviction.
        int value,frequency=1; // New entries count their initial insertion as their first use.
        Entry(int value) { this.value=value; } // Every new key starts in the frequency-one bucket.
    }
    private static final class Cache { // One object owns all indexes and the minimum-frequency invariant.
        final int capacity; int minimum; final Map<Integer,Entry> entries=new HashMap<>(); final Map<Integer,LinkedHashSet<Integer>> buckets=new HashMap<>(); // Bucket insertion order resolves recency ties.
        Cache(int capacity) { this.capacity=capacity; } // Capacity is validated by the public operation runner.
        void touch(int key,Entry entry) { // Promote one successful access to the next frequency level.
            LinkedHashSet<Integer> old=buckets.get(entry.frequency); old.remove(key); // Remove the key from its former frequency population.
            if(old.isEmpty()) { buckets.remove(entry.frequency); if(minimum==entry.frequency) minimum++; } // The promoted key guarantees the next frequency is populated.
            entry.frequency++; buckets.computeIfAbsent(entry.frequency,f->new LinkedHashSet<>()).add(key); // Promotion is the newest arrival within the higher bucket.
        }
        Integer get(int key) { // A miss leaves frequencies and tie order unchanged.
            Entry entry=entries.get(key); if(entry==null) return null; // Null represents absence without restricting stored int values.
            touch(key,entry); return entry.value; // Successful reads count as uses.
        }
        void put(int key,int value) { // A replacement counts as another access, while a new key may require eviction.
            if(capacity==0) return; // An always-empty cache cannot retain a new value.
            Entry existing=entries.get(key); if(existing!=null) { existing.value=value; touch(key,existing); return; } // Updating an existing mapping does not consume a new slot.
            if(entries.size()==capacity) { // Evict before inserting to enforce the fixed entry bound.
                LinkedHashSet<Integer> least=buckets.get(minimum); int victim=least.iterator().next(); least.remove(victim); entries.remove(victim); // The bucket's oldest key wins the LRU tie-break.
                if(least.isEmpty()) buckets.remove(minimum); // Drop empty metadata instead of accumulating stale buckets.
            }
            entries.put(key,new Entry(value)); buckets.computeIfAbsent(1,f->new LinkedHashSet<>()).add(key); minimum=1; // A new entry creates the lowest possible live frequency.
        }
    }
    public static List<Integer> solve(int capacity,int[][] operations) { // Expose a deterministic command trace for the cache design.
        if(capacity<0) throw new IllegalArgumentException("negative capacity"); Cache cache=new Cache(capacity); List<Integer> output=new ArrayList<>(); // Own all state within one independent simulation.
        for(int[] op:operations) { // Return only get results.
            if(op[0]==0) cache.put(op[1],op[2]); else if(op[0]==1) output.add(cache.get(op[1])); else throw new IllegalArgumentException("unknown operation"); // Keep put/get semantics explicit.
        }
        return output; // Every eviction respects frequency first and recency second.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(2,new int[][]{{0,1,10},{0,2,20},{1,1,0},{0,3,30},{1,2,0},{1,3,0},{0,4,40},{1,1,0},{1,3,0},{1,4,0}}))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(2,new int[][]{{0,1,10},{0,2,20},{1,1,0},{0,3,30},{1,2,0},{1,3,0},{0,4,40},{1,1,0},{1,3,0},{1,4,0}}), Arrays.asList(10,null,30,null,30,40)); // Frequency eviction followed by a recency tie.
        Check.equal(solve(0,new int[][]{{0,1,9},{1,1,0}}), Arrays.asList((Integer)null)); // Zero capacity.
        Check.equal(solve(2,new int[][]{{0,1,10},{0,2,20},{0,1,11},{0,3,30},{1,1,0},{1,2,0}}), Arrays.asList(11,null)); // Updating an existing key also increases its frequency.
    }
}
