package questions.q494; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Support insert remove and uniform random lookup.
 * Contract: Operations[0,value] insert,[1,value] remove,[2,0] choose a random stored value. Return stringified booleans for mutations and values for random queries. Random queries require a nonempty set; use a supplied reproducible seed.
 * Why: An array permits random indexing; a map locates each value. On removal, move the final array value into the removed slot and update its index, avoiding suffix shifts. This combination gives expected constant-time set mutations and uniform random selection.
 * Cost: Expected amortized O(1) per operation and O(u) live storage plus outputs for u members.
 */
public final class Q494RandomizedSet { // Keep the algorithm, demonstration, and boundary checks together.
    private Q494RandomizedSet() { } // Only static entry points are needed for this exercise.
    public static List<String> solve(int[][] operations,long seed) { // Keep array positions and map indexes synchronized after every operation.
        List<Integer> values=new ArrayList<>(); Map<Integer,Integer> index=new HashMap<>(); Random random=new Random(seed); List<String> output=new ArrayList<>(); // Each unique value occupies one array slot.
        for(int[] op:operations) { // Return an observable result for every command.
            if(op[0]==0) { // Insert only if the value is absent.
                boolean fresh=!index.containsKey(op[1]); if(fresh) { index.put(op[1],values.size()); values.add(op[1]); } // Append avoids shifting existing entries.
                output.add(Boolean.toString(fresh)); // Expose whether membership changed.
            } else if(op[0]==1) { // Locate the deletion slot in expected constant time.
                Integer slot=index.remove(op[1]); // Remove the map entry first; null denotes absence.
                if(slot!=null) { int last=values.remove(values.size()-1); if(slot<values.size()) { values.set(slot,last); index.put(last,slot); } } // Fill a nonfinal gap with the former last element and repair its index.
                output.add(Boolean.toString(slot!=null)); // A missing deletion is harmless and reports false.
            } else if(op[0]==2) { // Uniform array positions correspond one-to-one with current set values.
                if(values.isEmpty()) throw new IllegalArgumentException("random query on empty set"); // No valid selection exists without members.
                output.add(Integer.toString(values.get(random.nextInt(values.size())))); // Choose one current member with equal probability.
            } else throw new IllegalArgumentException("unknown operation"); // Keep command semantics explicit.
        }
        return output; // Array order may change after removal but set membership remains correct.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new int[][]{{0,7},{0,7},{0,9},{1,7},{2,0}},0))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new int[][]{{0,7},{0,7},{0,9},{1,7},{2,0}},0), List.of("true","false","true","true","9")); // Duplicate insertion and swap removal.
        Check.equal(solve(new int[][]{{1,5}},0), List.of("false")); // Removing an absent member.
        Check.equal(solve(new int[][]{{0,1},{0,2},{1,2},{2,0}},7), List.of("true","true","true","1")); // Removing the final array slot needs no swap.
    }
}
