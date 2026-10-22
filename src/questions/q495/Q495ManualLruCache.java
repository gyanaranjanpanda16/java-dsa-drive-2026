package questions.q495; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Implement an LRU cache with a hash map and linked nodes.
 * Contract: Capacity is nonnegative. Operations[0,key,value] put and[1,key,0] get; return get values with null for misses. Reads and replacement writes refresh recency; evict the least recently used key when full.
 * Why: The map locates a node directly, and a doubly linked list orders least-to-most recent accesses. Unlinking a known node and appending it at the recent end take constant time. This teaches the mechanism behind access-order caches without delegating it to LinkedHashMap.
 * Cost: Expected O(1) per operation and O(capacity) live entries plus outputs.
 */
public final class Q495ManualLruCache { // Keep the algorithm, demonstration, and boundary checks together.
    private Q495ManualLruCache() { } // Only static entry points are needed for this exercise.
    private static final class Node { // Doubly linked entries let a map lookup move a node without searching the list.
        final int key; int value; Node previous,next; // Links encode recency independently of hash-map bucket order.
        Node(int key,int value) { this.key=key; this.value=value; } // New entries are linked only when attached to the cache.
    }
    private static void unlink(Node node) { node.previous.next=node.next; node.next.previous=node.previous; } // Reconnect neighbors when removing a known interior node.
    private static void newest(Node tail,Node node) { node.previous=tail.previous; node.next=tail; tail.previous.next=node; tail.previous=node; } // Insert immediately before the recent-end sentinel.
    public static List<Integer> solve(int capacity,int[][] operations) { // Keep the map and recency list consistent after every command.
        if(capacity<0) throw new IllegalArgumentException("negative capacity"); // Zero is a valid always-empty cache.
        Map<Integer,Node> entries=new HashMap<>(); Node head=new Node(0,0),tail=new Node(0,0); head.next=tail; tail.previous=head; // Sentinels eliminate empty/end special cases in link changes.
        List<Integer> result=new ArrayList<>(); // Cache misses use null instead of reserving an integer value.
        for(int[] op:operations) { // Both successful get and put make an entry most recent.
            Node node=entries.get(op[1]); // Expected constant-time lookup locates the exact linked node.
            if(op[0]==1) { // A miss must not disturb the order of existing entries.
                result.add(node==null?null:node.value); if(node!=null) { unlink(node); newest(tail,node); } // Move a hit to the recent end.
            } else if(op[0]==0) { // Insert a new mapping or replace an existing value.
                if(node==null) { node=new Node(op[1],op[2]); entries.put(op[1],node); } else { node.value=op[2]; unlink(node); } // Existing entries keep their identity but leave their old recency position.
                newest(tail,node); // Every write counts as the latest access.
                if(entries.size()>capacity) { Node oldest=head.next; unlink(oldest); entries.remove(oldest.key); } // Evict exactly the least recent node from both indexes.
            } else throw new IllegalArgumentException("unknown operation"); // Reject unsupported commands.
        }
        return result; // Recency changes never require a full-list scan.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(2,new int[][]{{0,1,10},{0,2,20},{1,1,0},{0,3,30},{1,2,0},{1,3,0}}))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(2,new int[][]{{0,1,10},{0,2,20},{1,1,0},{0,3,30},{1,2,0},{1,3,0}}), Arrays.asList(10,null,30)); // A read changes the eviction victim.
        Check.equal(solve(0,new int[][]{{0,1,7},{1,1,0}}), Arrays.asList((Integer)null)); // Capacity zero retains nothing.
        Check.equal(solve(1,new int[][]{{0,1,4},{0,1,5},{1,1,0}}), List.of(5)); // Updating an existing key replaces its value.
    }
}
