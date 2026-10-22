package questions.q102;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import questions.support.Check;
import questions.support.Display;

/**
 * Q102: Maintain an access-order cache with LinkedHashMap.
 * Return retained keys after a sequence of requests to an LRU cache, oldest first. LinkedHashMap access order supplies the recency chain while hashing supplies lookup; the collection is not thread-safe and a get can modify traversal order.
 * Complexity: Expected O(n) time and O(min(n,capacity)+1) storage.
 * Read the matching lesson for examples and a line-by-line walkthrough.
 */
public final class Q102AccessOrderCache { // Keep this question independently runnable and easy to find.
    private Q102AccessOrderCache() { } // This exercise exposes behavior through static methods.
    public static List<String> solve(String[] accesses, int capacity) { // Define the input and result types of this particular problem.
        if (capacity < 0) throw new IllegalArgumentException("negative capacity"); // Zero means every requested value is immediately evicted.
        LinkedHashMap<String,Boolean> cache = new LinkedHashMap<>(16,0.75f,true); // The final flag selects least-to-most-recent access order.
        for (String key : accesses) { // Treat each request as a cache hit or an insertion on miss.
            if (cache.containsKey(key)) cache.get(key); // get updates recency; containsKey alone does not.
            else cache.put(key,Boolean.TRUE); // A new item becomes the most recently accessed entry.
            if (cache.size() > capacity) { // Enforce the application capacity after each request.
                Iterator<String> oldest = cache.keySet().iterator(); // Access-order traversal begins at the least-recent key.
                oldest.next(); oldest.remove(); // Remove through the iterator to keep its structural state valid.
            }
        }
        return new ArrayList<>(cache.keySet()); // Return remaining keys from least to most recent.
    }

    public static void main(String[] args) { // Demonstrate one small input before trying harder cases.
        System.out.println(Display.of(solve(new String[]{"a","b","a","c"},2))); // Format the computed answer, including arrays, for inspection.
    }
    public static void selfTest() { // Check normal and boundary behavior independently of printed output.
        Check.equal(solve(new String[]{"a","b","a","c"},2), List.of("a","c")); // Case 1: verify the expected result for this input.
        Check.equal(solve(new String[]{"x"},0), List.of()); // Case 2: verify the expected result for this input.
        Check.equal(solve(new String[]{"a","b","a"},3), List.of("b","a")); // Case 3: verify the expected result for this input.
    }
}
