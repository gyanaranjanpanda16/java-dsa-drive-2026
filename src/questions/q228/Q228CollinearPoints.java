package questions.q228; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Find the maximum number of collinear points.
 * Contract: Return the largest number of input point occurrences on a single line; duplicate coordinates count separately. Coordinates are arbitrary ints.
 * Why: Fix an anchor and normalize each direction by its gcd and sign. Equal rational slopes share a key without floating-point error. Duplicate anchors lie on every line through that coordinate and are added separately. Useful for detecting straight-line alignment in geometric data.
 * Cost: Expected O(n² log C) time for gcd on coordinate differences up to C, and O(n) space.
 */
public final class Q228CollinearPoints { // Keep the algorithm, demonstration, and boundary checks together.
    private Q228CollinearPoints() { } // Only static entry points are needed for this exercise.
    private record Slope(long dx,long dy) { } // Record equality compares the normalized rational direction exactly.
    private static long gcd(long a,long b) { // Reduce a direction to its smallest integer pair.
        while(b!=0) { long next=a%b; a=b; b=next; } // Euclid preserves the common divisors while shrinking the remainder.
        return a; // The final nonzero remainder is the gcd.
    }
    public static int solve(int[][] points) { // Treat each occurrence as a distinct point for counting purposes.
        int best=0; // Empty input has no line members.
        for(int i=0;i<points.length;i++) { // The earliest point on an optimal line can serve as its anchor.
            Map<Slope,Integer> counts=new HashMap<>(); int duplicates=0,most=0; // Separate zero-length directions from true slopes.
            for(int j=i+1;j<points.length;j++) { // Only later points are needed when every anchor is tried.
                long dx=(long)points[j][0]-points[i][0],dy=(long)points[j][1]-points[i][1]; // Widen before subtracting extreme coordinates.
                if(dx==0 && dy==0) { duplicates++; continue; } // An identical coordinate belongs to every anchor line.
                long divisor=gcd(Math.abs(dx),Math.abs(dy)); dx/=divisor; dy/=divisor; // Remove scale while retaining direction.
                if(dx<0 || (dx==0 && dy<0)) { dx=-dx; dy=-dy; } // Opposite directions describe the same undirected line.
                int count=counts.merge(new Slope(dx,dy),1,Integer::sum); most=Math.max(most,count); // Aggregate exactly equal directions.
            }
            best=Math.max(best,1+duplicates+most); // Include the anchor and every duplicate on its best line.
        }
        return best; // Every possible line has been represented by one of its anchors.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new int[][]{{0,0},{1,1},{2,2},{0,1}}))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new int[][]{{0,0},{1,1},{2,2},{0,1}}), 3); // A diagonal line beats the alternatives.
        Check.equal(solve(new int[][]{{1,1},{1,1},{2,2}}), 3); // Duplicate coordinates join the same line.
        Check.equal(solve(new int[][]{}), 0); // No points.
    }
}
