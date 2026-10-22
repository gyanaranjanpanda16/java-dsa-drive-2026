package questions.q231; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Implement a hash set with linear probing and tombstones.
 * Contract: Use a fixed positive capacity. Operations[0,value] insert,[1,value] query membership,[2,value] remove. Return query booleans. Duplicate insertion is harmless; inserting into a genuinely full table throws.
 * Why: A collision advances through consecutive slots. Deletion must leave a tombstone, because turning a slot directly into empty would incorrectly stop searches for later colliding values. This illustrates the central invariant of open-addressed hash tables.
 * Cost: O(m*capacity) worst-case time and O(capacity) space; good load-factor control gives much better typical probes.
 */
public final class Q231OpenAddressSet { // Keep the algorithm, demonstration, and boundary checks together.
    private Q231OpenAddressSet() { } // Only static entry points are needed for this exercise.
    public static List<Boolean> solve(int capacity,int[][] operations) { // Model empty, occupied, and deleted slots separately.
        if(capacity<=0) throw new IllegalArgumentException("positive capacity required"); // Probing needs at least one slot.
        int[] values=new int[capacity]; byte[] state=new byte[capacity]; List<Boolean> output=new ArrayList<>(); // State0 is empty,1 occupied,2 tombstone.
        for(int[] op:operations) { // Search before either querying or changing membership.
            int home=Math.floorMod(op[1],capacity),found=-1,insertion=-1; // Remember the first reusable slot while continuing equality checks.
            for(int step=0;step<capacity;step++) { // At most one full cycle prevents infinite loops in a full table.
                int slot=(int)(((long)home+step)%capacity); // Widen addition before wrapping the probe index.
                if(state[slot]==1 && values[slot]==op[1]) { found=slot; break; } // A live equal key ends the search successfully.
                if(state[slot]!=1 && insertion<0) insertion=slot; // Prefer the earliest empty slot or tombstone for future insertion.
                if(state[slot]==0) break; // Never-used storage proves no later key in this probe chain can match.
            }
            if(op[0]==1) output.add(found>=0); // Queries expose membership without changing storage.
            else if(op[0]==2) { if(found>=0) state[found]=2; } // A tombstone preserves the chain for colliding survivors.
            else if(op[0]==0) { // Duplicate insertion leaves the existing slot alone.
                if(found<0) { // A missing key needs one reusable slot.
                    if(insertion<0) throw new IllegalStateException("table full"); // This teaching table does not resize automatically.
                    values[insertion]=op[1]; state[insertion]=1; // Reuse a tombstone or fill a never-used slot.
                }
            } else throw new IllegalArgumentException("unknown operation"); // Restrict commands to the documented set API.
        }
        return output; // Every query reflects preceding insertion and deletion commands.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(3,new int[][]{{0,0},{0,3},{2,0},{1,3},{1,0},{0,6},{1,6}}))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(3,new int[][]{{0,0},{0,3},{2,0},{1,3},{1,0},{0,6},{1,6}}), List.of(true,false,true)); // Search and insertion through tombstones.
        Check.equal(solve(1,new int[][]{{0,-1},{0,-1},{1,-1}}), List.of(true)); // Duplicate insertion into a full table is valid.
        Check.equal(solve(2,new int[][]{{1,7},{2,7},{1,7}}), List.of(false,false)); // Removing an absent value changes nothing.
    }
}
