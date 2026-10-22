package questions.q372; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Maintain interval minima under interval increments.
 * Contract: Start with n>0 zeros. Operations[0,left,right,delta] add to a valid half-open range;[1,left,right,0] query a nonempty range minimum. Values and deferred additions fit long.
 * Why: Adding the same delta to every member changes the minimum by delta, independent of segment length. Deferred updates can therefore be accumulated at a parent and pushed only before partial descent. This supports live minimum-load queries after bulk adjustments.
 * Cost: O(q log(n+1)) time and O(n) space.
 */
public final class Q372LazyRangeMinimum { // Keep the algorithm, demonstration, and boundary checks together.
    private Q372LazyRangeMinimum() { } // Only static entry points are needed for this exercise.
    private static final class Tree { // Each node represents a half-open interval and a deferred uniform increment.
        final long[] value,lazy; // Aggregate values are current even when child updates are deferred.
        Tree(int n) { value=new long[4*n]; lazy=new long[4*n]; } // An initially zero array gives zero aggregates and zero pending increments.
        void apply(int node,int length,long delta) { value[node]+=delta; lazy[node]+=delta; } // Update the whole aggregate and remember the increment owed to children.
        void push(int node,int left,int right) { // Descend only after making both children's deferred state current.
            if(lazy[node]!=0 && right-left>1) { // Leaves have no children to receive a pending increment.
                int middle=left+(right-left)/2; // Divide the represented interval into disjoint children.
                apply(node*2,middle-left,lazy[node]); apply(node*2+1,right-middle,lazy[node]); lazy[node]=0; // Transfer the debt once, then clear it at the parent.
            }
        }
        void add(int node,int left,int right,int from,int to,long delta) { // Add only to the intersection of node range and update range.
            if(to<=left || right<=from) return; // Disjoint intervals require no work.
            if(from<=left && right<=to) { apply(node,right-left,delta); return; } // Full coverage is handled without visiting descendants.
            push(node,left,right); int middle=left+(right-left)/2; // Partial coverage needs current child states.
            add(node*2,left,middle,from,to,delta); add(node*2+1,middle,right,from,to,delta); // Recurse only where overlap can exist.
            value[node]=Math.min(value[node*2],value[node*2+1]); // Restore the parent aggregate after child changes.
        }
        long query(int node,int left,int right,int from,int to) { // Combine aggregates for exactly the requested overlap.
            if(to<=left || right<=from) return Long.MAX_VALUE; // Disjoint nodes contribute the operation's neutral element.
            if(from<=left && right<=to) return value[node]; // Full coverage already has an up-to-date aggregate.
            push(node,left,right); int middle=left+(right-left)/2; // Expose deferred updates before inspecting a partial child range.
            return Math.min(query(node*2,left,middle,from,to),query(node*2+1,middle,right,from,to)); // Child overlaps partition the requested portion of this node.
        }
    }
    public static List<Long> solve(int n,int[][] operations) { // Start from n zeros and process half-open interval operations.
        if(n<=0) throw new IllegalArgumentException("positive size required"); // Recursive intervals must have at least one leaf.
        Tree tree=new Tree(n); List<Long> result=new ArrayList<>(); // One mutable tree serves the complete operation stream.
        for(int[] op:operations) { // Valid bounds and long-representable arithmetic are required by contract.
            if(op[0]==0) tree.add(1,0,n,op[1],op[2],op[3]); // A uniform update touches only logarithmically many boundary paths.
            else if(op[0]==1) result.add(tree.query(1,0,n,op[1],op[2])); // Read the current aggregate after all earlier updates.
            else throw new IllegalArgumentException("unknown operation"); // Reject unsupported commands.
        }
        return result; // Query outputs retain operation order.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(4,new int[][]{{0,0,4,5},{0,1,3,-3},{1,0,4,0},{1,0,1,0}}))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(4,new int[][]{{0,0,4,5},{0,1,3,-3},{1,0,4,0},{1,0,1,0}}), List.of(2L,5L)); // A narrower decrease changes only covered minima.
        Check.equal(solve(1,new int[][]{{0,0,1,-7},{1,0,1,0}}), List.of(-7L)); // Single-leaf negative value.
        Check.equal(solve(3,new int[][]{{1,1,3,0}}), List.of(0L)); // Untouched ranges remain zero.
    }
}
