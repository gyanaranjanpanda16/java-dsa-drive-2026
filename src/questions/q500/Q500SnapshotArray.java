package questions.q500; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Read historical array values without copying every snapshot.
 * Contract: Create length>=0 zero values. Operations[0,index,value] set in the current working version;[1,0,0] save a snapshot and return its increasing ID starting at0;[2,index,snapshotId] get from an already saved snapshot. Return IDs and get values in operation order.
 * Why: Store only the changes for each index, tagged by snapshot ID. Repeated writes within one unsaved version replace the last record; a historical read finds the latest change at or before its snapshot by binary search. This models immutable snapshots over mutable working data without copying the entire array each time.
 * Cost: O(1) amortized set/snapshot, O(log changesAtIndex) get, and O(length+recordedChanges) storage plus output.
 */
public final class Q500SnapshotArray { // Keep the algorithm, demonstration, and boundary checks together.
    private Q500SnapshotArray() { } // Only static entry points are needed for this exercise.
    private record Change(int snapshot,int value) { } // Each history entry is immutable and applies until a later version changes the index.
    public static List<Integer> solve(int length,int[][] operations) { // Keep one sorted change history per array position.
        if(length<0) throw new IllegalArgumentException("negative length"); // A represented array must have a nonnegative size.
        List<List<Change>> history=new ArrayList<>(); for(int i=0;i<length;i++) history.add(new ArrayList<>(List.of(new Change(0,0)))); // Every index begins with zero at the first version.
        int current=0; List<Integer> output=new ArrayList<>(); // current is the working version ID that has not yet been saved.
        for(int[] op:operations) { // Commands alternate mutable working changes and immutable historical observations.
            if(op[0]==0) { // A set changes only the current unsaved version.
                List<Change> changes=history.get(op[1]); Change change=new Change(current,op[2]); // Tag the value with its first applicable snapshot.
                if(changes.get(changes.size()-1).snapshot()==current) changes.set(changes.size()-1,change); else changes.add(change); // Coalesce repeated writes before a snapshot instead of storing unreachable intermediate states.
            } else if(op[0]==1) output.add(current++); // Saving advances the version boundary without copying any index histories.
            else if(op[0]==2) { // Read only a version that has already been committed by snap.
                if(op[2]<0||op[2]>=current) throw new IllegalArgumentException("snapshot not saved"); // Future or negative snapshots have no defined state.
                List<Change> changes=history.get(op[1]); int low=0,high=changes.size(); // Find the first record strictly after the requested snapshot.
                while(low<high) { int middle=low+(high-low)/2; if(changes.get(middle).snapshot()<=op[2]) low=middle+1; else high=middle; } // Monotone version tags support an upper-bound search.
                output.add(changes.get(low-1).value()); // The preceding record is the latest value effective in that snapshot.
            } else throw new IllegalArgumentException("unknown operation"); // Keep saved-version semantics explicit.
        }
        return output; // Later working writes never alter an older Change record.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(1,new int[][]{{0,0,5},{1,0,0},{0,0,6},{2,0,0}}))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(1,new int[][]{{0,0,5},{1,0,0},{0,0,6},{2,0,0}}), List.of(0,5)); // A later write does not change snapshot0.
        Check.equal(solve(2,new int[][]{{1,0,0},{2,1,0}}), List.of(0,0)); // Untouched positions preserve their initial zero.
        Check.equal(solve(1,new int[][]{{0,0,1},{0,0,2},{1,0,0},{1,0,0},{2,0,0},{2,0,1}}), List.of(0,1,2,2)); // Repeated writes coalesce and unchanged snapshots share history.
    }
}
