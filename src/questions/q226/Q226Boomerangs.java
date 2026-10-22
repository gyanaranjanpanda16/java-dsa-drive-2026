package questions.q226; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Count ordered equal-distance point triples.
 * Contract: For distinct2D integer points with coordinates between -1000000 and1000000, count ordered triples(i,j,k) of distinct indices where distance(i,j)==distance(i,k).
 * Why: For a fixed center, a distance bucket of f points contributes f*(f-1) ordered endpoint choices. Sum buckets independently for every center. Squared distance avoids floating-point equality; applications include finding symmetric spatial relationships.
 * Cost: O(n²) expected time and O(n) auxiliary space; result must fit long.
 */
public final class Q226Boomerangs { // Keep the algorithm, demonstration, and boundary checks together.
    private Q226Boomerangs() { } // Only static entry points are needed for this exercise.
    public static long solve(int[][] points) { // Count ordered endpoint pairs around each possible center.
        long answer=0; // A center can contribute quadratically many pairs.
        for(int i=0;i<points.length;i++) { // Build fresh distance frequencies for this center.
            Map<Long,Long> counts=new HashMap<>(); // Use exact squared distances as keys.
            for(int j=0;j<points.length;j++) if(i!=j) { // A center cannot also be an endpoint.
                long dx=(long)points[i][0]-points[j][0],dy=(long)points[i][1]-points[j][1]; // Widen before subtraction and multiplication.
                long distance=dx*dx+dy*dy; // Coordinate bounds keep squared sums within long.
                long before=counts.getOrDefault(distance,0L); // Each earlier endpoint at this distance forms two ordered pairs.
                answer+=2*before; counts.put(distance,before+1); // Incremental counting equals f*(f-1) after the bucket is complete.
            }
        }
        return answer; // Each triple has one unique center, so centers do not double-count it.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new int[][]{{0,0},{1,0},{2,0}}))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new int[][]{{0,0},{1,0},{2,0}}), 2L); // Two ordered pairs around the middle.
        Check.equal(solve(new int[][]{}), 0L); // No possible center.
        Check.equal(solve(new int[][]{{0,0},{1,0}}), 0L); // Two points cannot form a triple.
    }
}
