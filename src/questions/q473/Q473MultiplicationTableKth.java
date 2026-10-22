package questions.q473; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Find the kth entry in a multiplication table.
 * Contract: In an m-by-n table whose1-based cell(i,j) is i*j, return the one-based kth value in sorted multiset order. m,n>0 and1<=k<=m*n.
 * Why: For a candidate x, row i contains min(n,floor(x/i)) values at most x. Summing row counts gives a monotone rank predicate, so binary search finds the first value whose count reaches k. No table materialization is needed.
 * Cost: O(min(m,n)*log(m*n)) time and O(1) space; choose practical dimensions for the row-count loop.
 */
public final class Q473MultiplicationTableKth { // Keep the algorithm, demonstration, and boundary checks together.
    private Q473MultiplicationTableKth() { } // Only static entry points are needed for this exercise.
    public static long solve(int m,int n,long k) { // Count duplicate table values as separate cells.
        if(m<=0||n<=0||k<1||k>(long)m*n) throw new IllegalArgumentException("invalid dimensions or rank"); // Validate before constructing search bounds.
        int rows=Math.min(m,n),columns=Math.max(m,n); long low=1,high=(long)m*n; // Count along the smaller dimension because the table is symmetric.
        while(low<high) { // Locate the first value reaching the desired cumulative rank.
            long middle=low+(high-low)/2,count=0; // No multiplication is needed inside the row count.
            for(long row=1;row<=rows;row++) { count+=Math.min(columns,middle/row); if(count>=k) break; } // Stop counting as soon as feasibility is established.
            if(count>=k) high=middle; else low=middle+1; // More candidate value can only increase the number of eligible cells.
        }
        return low; // The first sufficient threshold must equal an actual table entry.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(3,3,5))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(3,3,5), 3L); // Duplicates occupy separate ranks.
        Check.equal(solve(2,3,6), 6L); // The final rank is the maximum cell.
        Check.equal(solve(1,5,2), 2L); // A one-row table.
    }
}
