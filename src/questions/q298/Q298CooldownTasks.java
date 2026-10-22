package questions.q298; // Keep each numbered answer independently discoverable.

import java.util.*; // Use standard containers; the problem-specific algorithm remains below.
import questions.support.Check; // Fail loudly when an example violates the documented contract.

/**
 * Q298: Find the minimum slots for tasks with a cooldown.
 * Contract: UTF-16 char task labels; unit durations; any task order; all labels share the same nonnegative cooldown.
 * Why: The most frequent task needs maximum-1 gaps of cooldown slots. A frame of maximum-1 blocks of width cooldown+1 plus tied final labels gives a lower bound. Other tasks fill the gaps; when they overflow the frame, total task count is sufficient. This standard unit-duration, identical-cooldown model permits the bound to be attained.
 * Complexity: O(n+u) time and O(u) space.
 */
public final class Q298CooldownTasks { // One class contains this question, its answer, and reproducible checks.

    public static long solve(String tasks,int cooldown) { // Expose the answer as a reusable method as well as a runnable program.
        if(cooldown<0) throw new IllegalArgumentException("negative cooldown"); // A spacing constraint cannot be negative.
        Map<Character,Integer> counts=new HashMap<>(); // Equal task labels share a required cooldown.
        for(char c:tasks.toCharArray()) counts.merge(c,1,Integer::sum); // Count the exact number of each task to schedule.
        int maximum=0,tied=0; // Track the largest frequency and how many labels reach it.
        for(int count:counts.values()) { // Only the most frequent labels create the strongest spacing bound.
            if(count>maximum) { maximum=count; tied=1; } // A new largest frequency starts a new tie group.
            else if(count==maximum) tied++; // Another maximally frequent label occupies the last block.
        }
        if(tasks.isEmpty()) return 0; // No tasks require no time slots.
        return Math.max((long)tasks.length(),(long)(maximum-1)*(cooldown+1L)+tied); // Use either total work or the spacing frame, whichever dominates.
    }

    public static void main(String[] args) { // Run a deterministic example without needing keyboard input.
        System.out.println(solve("AAABBB",2)); // Print the answer shown in the accompanying question page.
    }
    public static void selfTest() { // Cover a normal case and two distinct boundary or variation cases.
        Check.equal(solve("AAABBB",2), 8L); // Idle slots are required
        Check.equal(solve("AAABBB",0), 6L); // No spacing restriction
        Check.equal(solve("",3), 0L); // No tasks
    }
}
