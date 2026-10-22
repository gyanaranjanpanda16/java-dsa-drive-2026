package questions.q452;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q452: Maximum Courses Before Deadlines.
 * Contract: A course needs uninterrupted positive duration; only one course at a time, start at time0, finish by its deadline.
 * Why it works: In deadline order, replacing the longest selected duration whenever time exceeds the deadline preserves the greatest feasible count while minimizing its total duration. Shorter total duration cannot harm later deadlines.
 * Cost: O(n log n) time and O(n) space.
 */
public final class Q452MaximumCoursesBeforeDeadlines { // Keep this question independently runnable and testable.
    private Q452MaximumCoursesBeforeDeadlines() { } // No object state is needed by this standalone exercise.
    public static int maximum(int[][] courses) { // Expose the operation for an independent caller and the executable checks below.
        int[][] sorted=Arrays.stream(courses).map(int[]::clone).toArray(int[][]::new); // Preserve caller data while ordering deadline constraints.
        for(int[] c:sorted) if(c.length!=2||c[0]<=0||c[1]<0) throw new IllegalArgumentException("Positive durations and nonnegative deadlines"); // Durations accumulate elapsed time.
        Arrays.sort(sorted,Comparator.comparingInt(c->c[1])); // Handle the tightest deadlines first.
        PriorityQueue<Integer> selected=new PriorityQueue<>(Comparator.reverseOrder()); long time=0; // Retain selected durations with access to the most expensive one.
        for(int[] course:sorted) { // Tentatively extend the current feasible selection.
            selected.offer(course[0]); time+=course[0]; // Include the new course before deciding which duration to discard.
            if(time>course[1]) time-=selected.poll(); // Removing the longest duration leaves the smallest time for the same retained count.
        }
        return selected.size(); // The feasible selection has maximum cardinality under the processed deadlines.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(maximum(new int[][]{{100,200},{200,1300},{1000,1250},{2000,3200}})); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(maximum(new int[][]{})==0, "Q452 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(maximum(new int[][]{{3,2}})==0, "Q452 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(maximum(new int[][]{{1,2},{2,3}})==2, "Q452 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
