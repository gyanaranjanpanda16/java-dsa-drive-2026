package questions.q199;

import java.util.*;
import questions.support.Check;

/**
 * Q199: Simulate a bounded circular-array queue.
 * Contract: Positive capacity; commands add:x or remove. Add returns true/false for accepted/full; remove returns the value or empty. Return all operation results.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: Logical position head+size modulo capacity identifies the next insertion slot. Head advances only on removal; a separate size distinguishes full from empty when indices coincide.
 * Complexity: O(commands) time and O(capacity) storage plus output.
 */
public final class Q199CircularQueue { // Keep one question and its tests together for independent study.
    public static String solve(int capacity,String[] commands) { // Bound memory independently of the number of attempted arrivals.
        if(capacity<1) throw new IllegalArgumentException("positive capacity"); // Modulo indexing requires a nonzero capacity.
        int[] data=new int[capacity]; // Allocate the ring buffer once.
        int head=0,size=0; // Track the first logical value and occupied-slot count.
        List<String> result=new ArrayList<>(); // Record whether each operation succeeds and what it returns.
        for(String command:commands) { // Process operations in arrival order.
            if(command.startsWith("add:")) { // An enqueue may fail when every slot is occupied.
                if(size==capacity) result.add("false"); // Reject without overwriting an older queued value.
                else { data[(head+size)%capacity]=Integer.parseInt(command.substring(4));size++;result.add("true"); } // Wrap the insertion position and grow logical length.
            } else if(command.equals("remove")) { // A dequeue may find the ring empty.
                if(size==0) result.add("empty"); // Avoid reading an unoccupied physical slot.
                else { result.add(Integer.toString(data[head]));head=(head+1)%capacity;size--; } // Return the oldest value and advance the logical front.
            } else throw new IllegalArgumentException("unknown command"); // Reject commands outside the queue interface.
        }
        return result.toString(); // Physical wraparound does not change FIFO order.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve(2,new String[]{"add:1","add:2","add:3","remove","add:3","remove","remove"})); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve(2,new String[]{"add:1","add:2","add:3","remove","add:3","remove","remove"}),"[true, true, false, 1, true, 2, 3]"); // Wraparound reuses a freed slot without losing FIFO order.
    Check.equal(solve(1,new String[]{"remove","add:7","remove"}),"[empty, true, 7]"); // Distinguish empty and full at capacity1.
    Check.equal(solve(3,new String[]{}),"[]"); // No commands require no queue activity.
    }
}
