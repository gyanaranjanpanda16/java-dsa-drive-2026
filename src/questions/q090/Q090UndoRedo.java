package questions.q090;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q090: Implement text undo and redo with two stacks.
 * Process SET:text, UNDO and REDO commands and return the final text. Two LIFO histories model movement through editor states; a new edit clears redo because it creates a different future. Empty-stack UNDO/REDO is a no-op.
 * Complexity: O(total command text plus cleared history size) time and O(total retained text) space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q090UndoRedo { // Keep this question independently runnable and easy to find.
    private Q090UndoRedo() { } // This exercise exposes behavior through static methods.
    public static String solve(String[] commands) { // Define the input and result types of this particular problem.
        Deque<String> undo = new ArrayDeque<>(); // Keep previous document states with the newest on top.
        Deque<String> redo = new ArrayDeque<>(); // Keep undone states eligible to be restored.
        String current = ""; // The document begins empty.
        for (String command : commands) { // Commands are SET:text, UNDO, or REDO.
            if (command.startsWith("SET:")) { // Replacing the document creates a fresh editing branch.
                undo.push(current); // Save the current state before changing it.
                current = command.substring(4); // Preserve all text after the command prefix.
                redo.clear(); // Old future states no longer follow the new edit.
            } else if (command.equals("UNDO")) { // Undo is harmless when there is no earlier state.
                if (!undo.isEmpty()) { redo.push(current); current = undo.pop(); } // Move the present to redo and restore the latest past.
            } else if (command.equals("REDO")) { // Redo only follows an undo that has not been superseded.
                if (!redo.isEmpty()) { undo.push(current); current = redo.pop(); } // Restore a future state while preserving another undo.
            } else throw new IllegalArgumentException("unknown command"); // Reject malformed commands instead of treating them as edits.
        }
        return current; // Expose the final document after all state transitions.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new String[]{"SET:a","SET:b","UNDO","REDO"}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new String[]{"SET:a","SET:b","UNDO","REDO"}), "b"); // Case 1: verify the expected result for this input.
        Check.equal(solve(new String[]{"SET:a","UNDO","SET:c","REDO"}), "c"); // Case 2: verify the expected result for this input.
        Check.equal(solve(new String[]{"UNDO","REDO"}), ""); // Case 3: verify the expected result for this input.
    }
}
