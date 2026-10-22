package questions.q108;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q108: Count two workers updates without lost increments.
 * Return the combined count from two concurrent workers. ConcurrentHashMap.merge performs one atomic per-key update; a separate get followed by put can lose increments even on a thread-safe map. The example uses small nonnegative counts and demonstrates correctness, not benchmarking or transactions across keys.
 * Complexity: O(first+second) updates and O(1) shared map space for one key; thread scheduling costs vary.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q108ConcurrentCounter { // Keep this question independently runnable and easy to find.
    private Q108ConcurrentCounter() { } // This exercise exposes behavior through static methods.
    public static int solve(int first, int second) { // Define the input and result types of this particular problem.
        if (first < 0 || second < 0 || (long) first+second > Integer.MAX_VALUE) throw new IllegalArgumentException("invalid count"); // Keep the expected counter nonnegative and representable.
        ConcurrentHashMap<String,Integer> counts = new ConcurrentHashMap<>(); // Provide thread-safe access to shared per-key state.
        Thread a = new Thread(() -> increment(counts,first)); // The first worker will update the same key repeatedly.
        Thread b = new Thread(() -> increment(counts,second)); // A second worker creates genuine overlapping updates.
        a.start(); b.start(); // Start both workers before waiting for either to finish.
        try { a.join(); b.join(); } catch (InterruptedException interrupted) { // Join makes completed worker effects visible before reading.
            Thread.currentThread().interrupt(); // Preserve the caller's interruption signal.
            throw new IllegalStateException("interrupted while awaiting workers",interrupted); // Do not return an incomplete count as success.
        }
        return counts.getOrDefault("events",0); // Empty workloads may never create the key.
    }
    private static void increment(ConcurrentHashMap<String,Integer> counts, int times) { // Encapsulate the identical worker behavior.
        for (int i = 0; i < times; i++) counts.merge("events",1,Integer::sum); // Atomically initialize or increment this key without a read-modify-write race.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(100,150))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(100,150), 250); // Case 1: verify the expected result for this input.
        Check.equal(solve(0,0), 0); // Case 2: verify the expected result for this input.
        Check.equal(solve(0,7), 7); // Case 3: verify the expected result for this input.
    }
}
