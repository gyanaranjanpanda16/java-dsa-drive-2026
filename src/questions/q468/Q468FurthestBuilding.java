package questions.q468; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Reach the furthest building with bricks and ladders.
 * Contract: Given int building heights, nonnegative long bricks, and a nonnegative number of ladders, start at index0. A positive climb costs that many bricks or one ladder; flat or downward moves are free. Return the furthest reachable index, or -1 for no buildings. Heights may span the int domain.
 * Why: Using ladders on the first climbs can waste them on small gaps. Keep positive climbs in a min heap as tentative ladder uses. Whenever there are more climbs than ladders, pay bricks for the smallest climb. This reserves ladders for the largest climbs seen so far. Swapping a ladder from a smaller to a larger climb cannot increase brick use, which proves the greedy assignment is optimal for each prefix. This models allocating a limited number of fixed-cost exemptions to the most expensive transitions.
 * Cost: O(n log(min(n,ladders+1)+1)) time and O(min(n,ladders+1)) heap space. Long differences prevent overflow when heights cross the signed int extremes.
 */
public final class Q468FurthestBuilding { // Keep the algorithm, demonstration, and boundary checks together.
    private Q468FurthestBuilding() { } // Only static entry points are needed for this exercise.
    public static int solve(int[] heights,long bricks,int ladders) { // Evaluate each reachable prefix with its cheapest brick allocation.
        if(bricks<0||ladders<0) throw new IllegalArgumentException("nonnegative resources required"); // Resource budgets cannot be negative.
        PriorityQueue<Long> climbs=new PriorityQueue<>(); // Keep tentative ladder climbs ordered from smallest to largest.
        for(int i=0;i+1<heights.length;i++) { // Try the next building in the fixed sequence.
            long climb=(long)heights[i+1]-heights[i]; if(climb<=0) continue; // Flat and downward transitions consume no resources.
            climbs.add(climb); // Tentatively reserve a ladder for this positive gap.
            if(climbs.size()>ladders) { // Too many reservations require paying for the cheapest one.
                long cost=climbs.remove(); // All remaining heap entries are at least this large.
                if(cost>bricks) return i; // Even the cheapest assignment cannot cross to i+1.
                bricks-=cost; // Pay once and retain ladders for the larger climbs.
            }
        }
        return heights.length-1; // This is also -1 when the input is empty.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new int[]{4,2,7,6,9,14,12},5,1))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new int[]{4,2,7,6,9,14,12},5,1), 4); // Reassign the ladder to preserve bricks.
        Check.equal(solve(new int[]{5,4,3},0,0), 2); // All moves are free.
        Check.equal(solve(new int[]{},0,0), -1); // No starting building.
        Check.equal(solve(new int[]{Integer.MIN_VALUE,Integer.MAX_VALUE},4294967295L,0), 1); // Full int-domain height difference.
    }
}
