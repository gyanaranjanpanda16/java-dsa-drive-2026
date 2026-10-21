package questions.q399;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q399: Kth Grammar Symbol.
 * Contract: Rows1..31; k is one-based and within row length.
 * Why it works: Tracing a position to its parent avoids materializing exponential rows. Each right-child step flips the parent symbol; each left-child step preserves it.
 * Cost: O(row) time and stack, versus exponential row construction.
 */
public final class Q399KthGrammarSymbol { // Keep this question independently runnable and testable.
    private Q399KthGrammarSymbol() { } // No object state is needed by this standalone exercise.
    public static int symbol(int row,int k) { // Expose the operation for an independent caller and the executable checks below.
        if(row<1||row>31||k<1||(long)k>(1L<<(row-1))) throw new IllegalArgumentException("Invalid row or position"); // Bound shifts and ensure the symbol exists.
        if(row==1) return 0; // The grammar begins with the single symbol zero.
        int parent=symbol(row-1,(int)((k+1L)/2)); // Two consecutive children share the same parent.
        return (k%2==1)?parent:1-parent; // In 0→01 and 1→10, left child matches and right child flips.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(symbol(4,5)); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(symbol(1,1)==0, "Q399 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(symbol(2,2)==1, "Q399 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(symbol(4,5)==1, "Q399 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
