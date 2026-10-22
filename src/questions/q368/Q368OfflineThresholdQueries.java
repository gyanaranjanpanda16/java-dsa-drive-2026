package questions.q368; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Count range values above independent thresholds.
 * Contract: For each valid query[left,right,threshold], count indices in[left,right) whose value exceeds threshold. Return answers in original query order; the array is static.
 * Why: Sort values and queries by descending threshold. Activate exactly the indices whose values are now greater than the query threshold; a Fenwick range total counts active positions in that interval. Reordering work improves efficiency while stored query indices restore output order.
 * Cost: O((n+q) log(n+q+1)) time and O(n+q) space.
 */
public final class Q368OfflineThresholdQueries { // Keep the algorithm, demonstration, and boundary checks together.
    private Q368OfflineThresholdQueries() { } // Only static entry points are needed for this exercise.
    private static final class Fenwick { // tree[i] stores the sum of a lowbit-sized block ending at one-based index i.
        final long[] tree; // Wider storage protects ordinary int-input aggregates.
        Fenwick(int size) { tree=new long[size+1]; } // Index zero stays unused to simplify parent jumps.
        void add(int index,long delta) { // Change a zero-based position by an additive difference.
            for(int i=index+1;i<tree.length;i+=i&-i) tree[i]+=delta; // Every containing block is reached by adding the lowest set bit.
        }
        long prefix(int end) { // Sum the half-open original range [0,end).
            long sum=0; for(int i=end;i>0;i-=i&-i) sum+=tree[i]; // Remove the final covered block until no prefix remains.
            return sum; // The visited blocks are disjoint and exactly cover the prefix.
        }
    }
    public static int[] solve(int[] a,int[][] queries) { // Answer many static range predicates by one descending activation sweep.
        Integer[] positions=new Integer[a.length],order=new Integer[queries.length]; // Index arrays let sorting preserve the caller's data.
        for(int i=0;i<a.length;i++) positions[i]=i; for(int i=0;i<queries.length;i++) order[i]=i; // Initialize both permutations.
        Arrays.sort(positions,(i,j)->Integer.compare(a[j],a[i])); Arrays.sort(order,(i,j)->Integer.compare(queries[j][2],queries[i][2])); // Use comparisons instead of overflow-prone subtraction.
        Fenwick active=new Fenwick(a.length); int next=0; int[] result=new int[queries.length]; // Each array position is activated at most once.
        for(int index:order) { // Later queries never require deactivating a value because thresholds only decrease.
            int[] query=queries[index]; // Retain the original slot for this reordered query.
            while(next<positions.length && a[positions[next]]>query[2]) active.add(positions[next++],1); // Strictly greater values are eligible; equals remain inactive.
            result[index]=(int)(active.prefix(query[1])-active.prefix(query[0])); // Range cancellation counts active positions inside the requested interval.
        }
        return result; // Original query order is restored by indexed assignments.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new int[]{5,1,4,2},new int[][]{{0,4,3},{1,3,1}}))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new int[]{5,1,4,2},new int[][]{{0,4,3},{1,3,1}}), new int[]{2,1}); // Queries need different active populations.
        Check.equal(solve(new int[]{2,2},new int[][]{{0,2,2},{0,2,1}}), new int[]{0,2}); // Strict threshold excludes equal values.
        Check.equal(solve(new int[]{},new int[][]{{0,0,7}}), new int[]{0}); // An empty range contains no active position.
    }
}
