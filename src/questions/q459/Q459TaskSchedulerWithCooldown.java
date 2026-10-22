package questions.q459;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q459: Task Scheduler With Cooldown.
 * Contract: Tasks may be reordered; each takes one slot; identical uppercase types need cooldown intervening slots.
 * Why it works: Most frequent tasks require maximum-1 separating gaps; tied types occupy the final frame. This spacing lower bound is achievable by filling gaps with other tasks or idles, but cannot be smaller than the total task count.
 * Cost: O(n+26) time and O(26) space.
 */
public final class Q459TaskSchedulerWithCooldown { // Keep this question independently runnable and testable.
    private Q459TaskSchedulerWithCooldown() { } // No object state is needed by this standalone exercise.
    public static long minimumSlots(String tasks,int cooldown) { // Expose the operation for an independent caller and the executable checks below.
        if(cooldown<0) throw new IllegalArgumentException("Nonnegative cooldown"); // Cooldown counts required intervening slots.
        int[] counts=new int[26]; // This exercise uses uppercase task labels only.
        for(char task:tasks.toCharArray()) { if(task<'A'||task>'Z') throw new IllegalArgumentException("Uppercase labels"); counts[task-'A']++; } // Count identical task types independently of their original order.
        int maximum=0,tied=0; // Most frequent types determine the strongest spacing lower bound.
        for(int count:counts) { if(count>maximum) { maximum=count; tied=1; } else if(count==maximum&&count>0) tied++; } // Record how many types share the maximum frequency.
        if(maximum==0) return 0; // Empty work needs no idle slots.
        long frame=(long)(maximum-1)*(cooldown+1L)+tied; // Place all but the last frequent tasks into spaced frames, then append tied final tasks.
        return Math.max(tasks.length(),frame); // Other tasks fill idle gaps; if plentiful, total task count dominates.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(minimumSlots("AAABBB",2)); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(minimumSlots("",3)==0, "Q459 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(minimumSlots("AA",0)==2, "Q459 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(minimumSlots("AAABBB",2)==8, "Q459 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
