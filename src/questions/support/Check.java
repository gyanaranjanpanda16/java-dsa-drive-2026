package questions.support;

import java.util.Arrays;
import java.util.Objects;

/** Small always-active test helpers; no external library or -ea flag is required. */
public final class Check { // Group reusable assertions instead of repeating them in 500 files.
    private static long checks; // Count checks so the bank runner can report actual coverage.
    private Check() { } // This utility has no per-instance state.
    public static long count() { return checks; } // Expose the count without allowing callers to overwrite it.
    public static void equal(Object actual, Object expected) { // Compare results, including nested object arrays.
        checks++; // Record every executed assertion, including a failing one.
        if (!Objects.deepEquals(actual, expected)) { // Null-safe value comparison avoids reference-identity mistakes.
            throw new AssertionError("Expected " + expected + " but got " + actual); // Stop at incorrect behavior.
        }
    }
    public static void array(int[] actual, int[] expected) { // Primitive arrays require elementwise comparison.
        checks++; // Include this assertion in the report.
        if (!Arrays.equals(actual, expected)) { // Array objects themselves do not implement value equality.
            throw new AssertionError("Expected " + Arrays.toString(expected) + " but got " + Arrays.toString(actual)); // Show the differing data.
        }
    }
    public static void array(long[] actual, long[] expected) { // Support overflow-safe aggregate answers.
        checks++; // Count the check once.
        if (!Arrays.equals(actual, expected)) { // Compare every long value in order.
            throw new AssertionError("Expected " + Arrays.toString(expected) + " but got " + Arrays.toString(actual)); // Fail with useful diagnostics.
        }
    }
    public static void that(boolean condition, String message) { // Check a property when no single expected result exists.
        checks++; // Count the property check.
        if (!condition) throw new AssertionError(message); // A false invariant must fail the suite.
    }
    public static void throwsType(Class<? extends Throwable> expected, Runnable action) { // Verify rejected input explicitly.
        checks++; // Count one rejection check.
        try { // Execute only the operation whose failure is expected.
            action.run(); // Validations happen inside the submitted action.
        } catch (Throwable failure) { // Inspect the actual failure rather than accepting any exception.
            if (expected.isInstance(failure)) return; // The documented exception occurred.
            throw new AssertionError("Expected " + expected.getName() + " but got " + failure, failure); // Preserve the unexpected cause.
        }
        throw new AssertionError("Expected " + expected.getName() + " but nothing was thrown"); // Silent acceptance violates the contract.
    }
}
