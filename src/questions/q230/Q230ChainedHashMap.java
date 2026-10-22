package questions.q230; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Implement an integer map with collision chains.
 * Contract: Process operations[code,key,value], where0 puts/replaces,1 gets, and2 removes. Return get results, using null for absent keys. Use17 buckets and separate chaining for educational collision handling.
 * Why: Hashing chooses a bucket, then equality locates a key within its chain. Updating an existing node avoids duplicate keys; removal reconnects its predecessor. This exposes the mechanics behind map lookup, including collisions and negative keys.
 * Cost: O(m*n) worst-case for m operations and n stored keys with this fixed bucket count; O(n+17) space. Production maps resize to control load.
 */
public final class Q230ChainedHashMap { // Keep the algorithm, demonstration, and boundary checks together.
    private Q230ChainedHashMap() { } // Only static entry points are needed for this exercise.
    private static final class Node { // Each chain entry stores one key, value, and collision successor.
        final int key; int value; Node next; // Keys stay fixed while updates can replace values.
        Node(int key,int value,Node next) { this.key=key; this.value=value; this.next=next; } // Link a new entry at the chain head.
    }
    public static List<Integer> solve(int[][] operations) { // Return only explicit get results, leaving updates silent.
        Node[] buckets=new Node[17]; List<Integer> output=new ArrayList<>(); // Empty buckets start as null heads.
        for(int[] op:operations) { // Interpret one map operation at a time.
            int bucket=Math.floorMod(op[1],buckets.length); Node node=buckets[bucket],previous=null; // Normalize negative hash keys into a valid bucket.
            while(node!=null && node.key!=op[1]) { previous=node; node=node.next; } // Equality, not hash alone, identifies the requested key.
            if(op[0]==0) { // Put either replaces an existing mapping or inserts a fresh node.
                if(node==null) buckets[bucket]=new Node(op[1],op[2],buckets[bucket]); else node.value=op[2]; // Preserve every other collision entry.
            } else if(op[0]==1) output.add(node==null?null:node.value); // Null distinguishes absence from any stored int value.
            else if(op[0]==2) { // Removing an absent key has no effect.
                if(node!=null) { if(previous==null) buckets[bucket]=node.next; else previous.next=node.next; } // Unlink exactly the matching node.
            } else throw new IllegalArgumentException("unknown map operation"); // Reject unsupported command codes.
        }
        return output; // Gets observe all preceding updates and removals.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new int[][]{{0,1,10},{0,18,20},{0,1,11},{2,1,0},{1,18,0},{1,1,0}}))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new int[][]{{0,1,10},{0,18,20},{0,1,11},{2,1,0},{1,18,0},{1,1,0}}), Arrays.asList(20,null)); // Collision survival and absence after removal.
        Check.equal(solve(new int[][]{{0,-1,7},{1,-1,0}}), List.of(7)); // Negative keys normalize safely.
        Check.equal(solve(new int[][]{}), List.of()); // No commands produce no get results.
    }
}
