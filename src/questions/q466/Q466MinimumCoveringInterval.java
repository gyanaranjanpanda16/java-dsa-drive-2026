package questions.q466; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Find the shortest interval covering each query.
 * Contract: Given closed int intervals [left,right] with left<=right and int point queries, return each query's minimum covering interval length right-left+1, or -1 if none covers it. Preserve query order and all inputs. Lengths use long to handle the full int domain.
 * Why: Checking every interval per query takes O(nq). Sort intervals by left endpoint and process queries in increasing order. Insert intervals that have started, and remove expired intervals from the heap head. A min heap ordered by length then exposes the shortest covering candidate. An expired interval below the head can wait: it cannot affect the minimum until it reaches the head and is removed. This pattern supports selecting the tightest numeric range containing a request.
 * Cost: O(n log n + q log q + (n+q) log(n+1)) time, O(n+q) extra space including sorted copies and the answer. Every interval is inserted and removed at most once.
 */
public final class Q466MinimumCoveringInterval { // Keep the algorithm, demonstration, and boundary checks together.
    private Q466MinimumCoveringInterval() { } // Only static entry points are needed for this exercise.
    public static long[] solve(int[][] intervals,int[] queries) { // Keep results aligned with the caller's original query order.
        int[][] sorted=new int[intervals.length][2]; // Copy interval rows so sorting does not mutate input.
        for(int i=0;i<intervals.length;i++) { if(intervals[i].length!=2||intervals[i][0]>intervals[i][1]) throw new IllegalArgumentException("ordered endpoint pairs required"); sorted[i]=intervals[i].clone(); } // Validate before using endpoints.
        Arrays.sort(sorted,Comparator.comparingInt(interval->interval[0])); // The sweep can insert intervals in one forward pass.
        Integer[] order=new Integer[queries.length]; for(int i=0;i<order.length;i++) order[i]=i; // Retain the original position of every query, including duplicates.
        Arrays.sort(order,Comparator.comparingInt(i->queries[i])); // Process points from left to right.
        PriorityQueue<long[]> active=new PriorityQueue<>((a,b)->a[0]!=b[0]?Long.compare(a[0],b[0]):Long.compare(a[1],b[1])); // Heap records contain length and inclusive right endpoint.
        long[] answer=new long[queries.length]; int next=0; // Every interval enters the heap once.
        for(int index:order) { // Answer queries in sorted processing order but store at original indices.
            int point=queries[index]; // This query determines which intervals have started and expired.
            while(next<sorted.length&&sorted[next][0]<=point) { int[] interval=sorted[next++]; active.add(new long[]{(long)interval[1]-interval[0]+1,interval[1]}); } // Widen before subtracting endpoints.
            while(!active.isEmpty()&&active.peek()[1]<point) active.remove(); // Expired candidates cannot cover this or any later query.
            answer[index]=active.isEmpty()?-1:active.peek()[0]; // The shortest remaining candidate covers the current point.
        }
        return answer; // Empty queries naturally produce an empty array.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new int[][]{{1,4},{2,4},{3,6},{4,4}},new int[]{2,3,4,5}))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new int[][]{{1,4},{2,4},{3,6},{4,4}},new int[]{2,3,4,5}), new long[]{3,3,1,4}); // Nested intervals and expiring candidates.
        Check.equal(solve(new int[][]{},new int[]{2}), new long[]{-1}); // No interval covers the query.
        Check.equal(solve(new int[][]{{Integer.MIN_VALUE,Integer.MAX_VALUE}},new int[]{0}), new long[]{4294967296L}); // Inclusive length exceeds int range.
        Check.equal(solve(new int[][]{{1,2}},new int[]{3,1,1}), new long[]{-1,2,2}); // Preserve order and repeated queries.
    }
}
