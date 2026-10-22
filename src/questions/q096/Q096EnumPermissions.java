package questions.q096;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q096: Combine and revoke finite permission flags.
 * Return granted permissions after revocations using EnumSet. A fixed enum universe allows compact bit-based membership and prevents unrelated values from entering the set; useful for feature flags and access capabilities. Inputs contain non-null Permission values.
 * Complexity: O(g+r) time and O(1) space for this fixed three-value enum.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q096EnumPermissions { // Keep this question independently runnable and easy to find.
    private Q096EnumPermissions() { } // This exercise exposes behavior through static methods.
    public static Set<Permission> solve(Permission[] granted, Permission[] revoked) { // Define the input and result types of this particular problem.
        EnumSet<Permission> effective = EnumSet.noneOf(Permission.class); // Supply the enum type even when the initial set is empty.
        Collections.addAll(effective,granted); // Add each allowed flag, automatically collapsing duplicates.
        for (Permission permission : revoked) effective.remove(permission); // Revocation removes a flag whether or not it was present.
        return effective; // EnumSet traverses flags in their enum declaration order.
    }
    public enum Permission { READ, WRITE, ADMIN } // Enumerate the entire valid flag universe at compile time.

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new Permission[]{Permission.READ,Permission.WRITE},new Permission[]{Permission.WRITE}))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new Permission[]{Permission.READ,Permission.WRITE},new Permission[]{Permission.WRITE}), EnumSet.of(Permission.READ)); // Case 1: verify the expected result for this input.
        Check.equal(solve(new Permission[]{},new Permission[]{}), EnumSet.noneOf(Permission.class)); // Case 2: verify the expected result for this input.
        Check.equal(solve(new Permission[]{Permission.ADMIN,Permission.READ,Permission.READ},new Permission[]{Permission.WRITE}), EnumSet.of(Permission.READ,Permission.ADMIN)); // Case 3: verify the expected result for this input.
    }
}
