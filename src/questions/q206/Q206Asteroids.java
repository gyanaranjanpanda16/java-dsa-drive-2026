package questions.q206;

import java.util.*;
import questions.support.Check;

/**
 * Q206: Resolve one-dimensional asteroid collisions.
 * Contract: Nonzero signed ints represent size and direction. Positive moves right,negative left; equal speeds. Return surviving values in order.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: Only an incoming left-mover can collide with prior right-movers. A stack holds the already stable prefix; repeated collisions remove exactly the obstacles encountered before the incoming object survives or is destroyed.
 * Complexity: O(n) time and O(n) space.
 */
public final class Q206Asteroids { // Keep one question and its tests together for independent study.
    public static String solve(int[] asteroids) { // Preserve physical left-to-right survivor order.
        Deque<Integer> survivors=new ArrayDeque<>(); // The last survivor is the next possible collision partner.
        for(int asteroid:asteroids) { // Process objects in their original position order.
            boolean alive=true; // The incoming object may survive multiple smaller obstacles.
            while(alive&&asteroid<0&&!survivors.isEmpty()&&survivors.peekLast()>0) { // Only facing right/left pairs can meet.
                long incoming=-(long)asteroid; // Widen before negating to handle Integer.MIN_VALUE safely.
                int previous=survivors.peekLast(); // The nearest right-moving obstacle is encountered first.
                if(previous<incoming) survivors.removeLast(); // The incoming larger object destroys this obstacle and continues.
                else { if(previous==incoming) survivors.removeLast();alive=false; } // Equal sizes destroy both;larger obstacle destroys only the incoming object.
            }
            if(alive) survivors.addLast(asteroid); // A surviving object joins the stable prefix.
        }
        return survivors.toString(); // No remaining adjacent facing pair can collide.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve(new int[]{10,2,-5})); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve(new int[]{10,2,-5}),"[10]"); // One incoming object can destroy a smaller obstacle before being destroyed.
    Check.equal(solve(new int[]{8,-8}),"[]"); // Equal sizes annihilate.
    Check.equal(solve(new int[]{-2,-1,1,2}),"[-2, -1, 1, 2]"); // Objects moving apart never collide.
    }
}
