package questions.q461;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q461: Queue Reconstruction By Height.
 * Contract: Input constraints describe a feasible queue; each record is height and number of earlier people at least that tall.
 * Why it works: When inserting a person, every already-placed person is at least as tall, so inserting at index k gives exactly k qualifying predecessors. Future shorter people do not alter that count.
 * Cost: O(n²) time due to indexed insertion and O(n) extra storage.
 */
public final class Q461QueueReconstructionByHeight { // Keep this question independently runnable and testable.
    private Q461QueueReconstructionByHeight() { } // No object state is needed by this standalone exercise.
    public static int[][] reconstruct(int[][] people) { // Expose the operation for an independent caller and the executable checks below.
        int[][] sorted=Arrays.stream(people).map(int[]::clone).toArray(int[][]::new); // Preserve input while reordering independent person records.
        for(int[] p:sorted) if(p.length!=2||p[1]<0) throw new IllegalArgumentException("Height and nonnegative count required"); // Each record is [height, taller-or-equal-ahead count].
        Arrays.sort(sorted,(a,b)->a[0]!=b[0]?Integer.compare(b[0],a[0]):Integer.compare(a[1],b[1])); // Place taller people first and equal-height people by increasing count.
        List<int[]> queue=new ArrayList<>(); // Indexed insertion controls how many already-tall people precede the new person.
        for(int[] person:sorted) { if(person[1]>queue.size()) throw new IllegalArgumentException("Inconsistent queue constraints"); queue.add(person[1],person); } // Shorter future insertions cannot affect this person's taller-or-equal count.
        return queue.toArray(int[][]::new); // Return the reconstructed order with copied records.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(Arrays.deepToString(reconstruct(new int[][]{{7,0},{4,4},{7,1},{5,0},{6,1},{5,2}}))); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(reconstruct(new int[][]{}).length==0, "Q461 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(Arrays.deepEquals(reconstruct(new int[][]{{5,0}}),new int[][]{{5,0}}), "Q461 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(Arrays.deepEquals(reconstruct(new int[][]{{7,0},{5,0}}),new int[][]{{5,0},{7,0}}), "Q461 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
