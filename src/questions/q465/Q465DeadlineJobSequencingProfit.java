package questions.q465;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q465: Deadline Job Sequencing Profit.
 * Contract: Unit-duration jobs, slots begin at1, finish by integer deadline; selecting no job is allowed.
 * Why it works: Processing descending profit protects the most valuable feasible choices. Placing each as late as possible leaves maximal early capacity for later jobs with smaller deadlines; feasible job sets obey the scheduling exchange property.
 * Cost: O(n²+n log n) time and O(n) space.
 */
public final class Q465DeadlineJobSequencingProfit { // Keep this question independently runnable and testable.
    private Q465DeadlineJobSequencingProfit() { } // No object state is needed by this standalone exercise.
    public static long maximum(int[][] jobs) { // Expose the operation for an independent caller and the executable checks below.
        int[][] sorted=Arrays.stream(jobs).map(int[]::clone).toArray(int[][]::new); // Reorder jobs by value without modifying caller data.
        for(int[] job:sorted) if(job.length!=2||job[0]<0) throw new IllegalArgumentException("Deadline and profit required"); // Job records use[deadline,profit] with unit duration.
        Arrays.sort(sorted,(a,b)->Integer.compare(b[1],a[1])); // Preserve the most valuable jobs whenever a feasible slot exists.
        boolean[] used=new boolean[jobs.length+1]; long total=0; // At most n unit jobs can be scheduled, so later deadline slots are unnecessary.
        for(int[] job:sorted) { // Search a slot only for jobs that can improve profit.
            if(job[1]<=0) continue; // Choosing no nonpositive-profit job is at least as good.
            for(int slot=Math.min(job[0],jobs.length);slot>=1;slot--) if(!used[slot]) { used[slot]=true; total+=job[1]; break; } // Latest feasible placement preserves earlier slots for tighter deadlines.
        }
        return total; // Accepted jobs each occupy one distinct slot before their deadline.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(maximum(new int[][]{{2,100},{1,19},{2,27},{1,25},{3,15}})); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(maximum(new int[][]{})==0, "Q465 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(maximum(new int[][]{{0,100}})==0, "Q465 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(maximum(new int[][]{{1,5},{1,7}})==7, "Q465 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
