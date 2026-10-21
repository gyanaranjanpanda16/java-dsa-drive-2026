package questions.q384;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q384: Tower Of Hanoi Moves.
 * Contract: Return legal source-to-destination moves for 0..15 disks.
 * Why it works: The largest disk cannot move until all smaller disks are on the spare peg. Recursing on both smaller transfers keeps every move legal and gives 2^n-1 moves.
 * Cost: O(2^n) time/output and O(n) stack.
 */
public final class Q384TowerOfHanoiMoves { // Keep this question independently runnable and testable.
    private Q384TowerOfHanoiMoves() { } // No object state is needed by this standalone exercise.
    public static List<String> moves(int disks) { // Expose the operation for an independent caller and the executable checks below.
        if (disks < 0 || disks > 15) throw new IllegalArgumentException("Use 0..15 disks"); // Explicitly bound exponential demonstration output.
        List<String> out = new ArrayList<>(); // Collect moves in the only order they should be executed.
        move(disks, 'A', 'C', 'B', out); // Transfer the full tower from A to C using B.
        return out; // The list contains the complete legal move sequence.
    }
    private static void move(int n, char from, char to, char spare, List<String> out) { // Keep recursive state or a repeated sub-operation inside this solution.
        if (n == 0) return; // There is no move for an empty tower.
        move(n-1, from, spare, to, out); // Clear the largest disk by relocating the smaller tower.
        out.add(from + "->" + to); // Move the now-exposed largest disk once.
        move(n-1, spare, to, from, out); // Reassemble the smaller tower above it.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(moves(2)); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(moves(0).isEmpty(), "Q384 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(moves(1).equals(List.of("A->C")), "Q384 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(moves(3).size()==7, "Q384 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
