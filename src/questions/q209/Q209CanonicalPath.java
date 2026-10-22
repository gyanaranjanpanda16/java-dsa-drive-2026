package questions.q209;

import java.util.*;
import questions.support.Check;

/**
 * Q209: Simplify an absolute Unix-style path.
 * Contract: Input is an absolute path. Ignore repeated slashes and dot components; dot-dot removes a preceding component but cannot climb above root. This is lexical simplification,not filesystem/symlink resolution.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: A deque stores the surviving directory components. Ordinary names extend the path,while dot-dot reverses the most recent surviving directory entry.
 * Complexity: O(path length) time and space.
 */
public final class Q209CanonicalPath { // Keep one question and its tests together for independent study.
    public static String solve(String path) { // Interpret path components without accessing the real filesystem.
        if(!path.startsWith("/")) throw new IllegalArgumentException("absolute path required"); // A root is needed to define excess parent traversal.
        Deque<String> parts=new ArrayDeque<>(); // Maintain the normalized component sequence.
        for(String part:path.split("/")) { // Slashes separate component operations.
            if(part.isEmpty()||part.equals(".")) continue; // Empty separators and current-directory markers add no movement.
            if(part.equals("..")) { if(!parts.isEmpty()) parts.removeLast(); } // Parent traversal cancels one surviving component without escaping root.
            else parts.addLast(part); // Every other component is a literal directory name.
        }
        return "/"+String.join("/",parts); // Rebuild one leading slash and one separator between surviving components.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve("/a/./b/../../c/")); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve("/a/./b/../../c/"),"/c"); // Parent markers cancel prior components.
    Check.equal(solve("/../../"),"/"); // Root cannot be traversed above lexically.
    Check.equal(solve("/a//.../"),"/a/..."); // Three dots are a literal name,not parent traversal.
    }
}
