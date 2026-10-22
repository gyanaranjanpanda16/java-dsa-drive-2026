package questions.q098;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q098: Reserve available inventory atomically within one operation.
 * Return remaining inventory if every nonnegative ordered quantity is available, otherwise null. Missing products have zero stock; zero requests may create a zero entry. All keys/values must be non-null. Validate first to model all-or-nothing processing in this single-threaded method; this is not a concurrent transaction.
 * Complexity: Expected O(s+o) validation/update time plus O(s log s + o log(s+o)) ordered result construction; O(s+o) space.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q098InventoryReservation { // Keep this question independently runnable and easy to find.
    private Q098InventoryReservation() { } // This exercise exposes behavior through static methods.
    public static Map<String,Integer> solve(Map<String,Integer> stock, Map<String,Integer> order) { // Define the input and result types of this particular problem.
        Map<String,Integer> remaining = new HashMap<>(stock); // Preserve the caller's stock whether the order succeeds or fails.
        for (Map.Entry<String,Integer> item : stock.entrySet()) { // Reject impossible inventories before processing requests.
            if (item.getValue() < 0) throw new IllegalArgumentException("negative stock"); // Inventory counts must be nonnegative.
        }
        for (Map.Entry<String,Integer> item : order.entrySet()) { // Validate the entire order before applying any subtraction.
            if (item.getValue() < 0 || item.getValue() > remaining.getOrDefault(item.getKey(),0)) return null; // Null denotes an unfulfillable order without changing stock.
        }
        order.forEach((name,quantity) -> remaining.put(name,remaining.getOrDefault(name,0)-quantity)); // All requested quantities are now known to be feasible.
        return new TreeMap<>(remaining); // Sort keys for predictable demonstration output and reports.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(Map.of("pen",5,"book",2),Map.of("pen",3)))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(Map.of("pen",5,"book",2),Map.of("pen",3)), Map.of("pen",2,"book",2)); // Case 1: verify the expected result for this input.
        Check.equal(solve(Map.of("pen",1),Map.of("pen",2)), null); // Case 2: verify the expected result for this input.
        Check.equal(solve(Map.of(),Map.of()), Map.of()); // Case 3: verify the expected result for this input.
    }
}
