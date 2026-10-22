package questions.q229; // Isolate the numbered answer so it can be studied and run independently.

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;
import questions.support.Display;

/**
 * Use every ticket in lexical itinerary order.
 * Contract: Given directed[from,to] tickets and a start airport, return the lexicographically smallest airport sequence using every ticket once. Throw if no complete itinerary exists.
 * Why: Hierholzer traversal consumes edges and appends airports only after exhausting their outgoing edges. Reversing this postorder splices dead-end detours into the proper trail. A min-heap selects lexical choices; a final edge-count check rejects invalid input. This models route reconstruction from tickets, including repeated routes.
 * Cost: O(E log(E+1)) time and O(E) storage.
 */
public final class Q229TicketItinerary { // Keep the algorithm, demonstration, and boundary checks together.
    private Q229TicketItinerary() { } // Only static entry points are needed for this exercise.
    public static List<String> solve(String[][] tickets,String start) { // Reconstruct a trail over ticket occurrences, not just unique routes.
        Map<String,PriorityQueue<String>> next=new HashMap<>(); // Choose the smallest available destination from each airport.
        Map<List<String>,Integer> remaining=new HashMap<>(); // Keep original edge multiplicities for final validity checks.
        for(String[] ticket:tickets) { // Insert duplicate tickets as separate heap entries.
            next.computeIfAbsent(ticket[0],key->new PriorityQueue<>()).add(ticket[1]); // Order outgoing choices lexically.
            remaining.merge(List.of(ticket[0],ticket[1]),1,Integer::sum); // Count each exact directed route.
        }
        Deque<String> path=new ArrayDeque<>(); path.push(start); List<String> result=new ArrayList<>(); // Begin the unfinished traversal at the requested origin.
        while(!path.isEmpty()) { // Exhaust outgoing edges before finalizing an airport's trail position.
            PriorityQueue<String> outgoing=next.get(path.peek()); // Inspect the current endpoint without removing it.
            if(outgoing!=null && !outgoing.isEmpty()) path.push(outgoing.remove()); // Follow and consume one lexical edge.
            else result.add(path.pop()); // A completed suffix is appended in reverse traversal order.
        }
        Collections.reverse(result); // Convert reverse completion order into forward itinerary order.
        if(result.size()!=tickets.length+1) throw new IllegalArgumentException("unreachable tickets"); // Every ticket must contribute one transition.
        for(int i=1;i<result.size();i++) { // Check that splicing produced a valid directed ticket sequence.
            List<String> edge=List.of(result.get(i-1),result.get(i)); int count=remaining.getOrDefault(edge,0); // Read remaining occurrences of this transition.
            if(count==0) throw new IllegalArgumentException("no complete itinerary"); // Invalid degree patterns can create an impossible transition.
            remaining.put(edge,count-1); // Consume one original occurrence to validate duplicates correctly.
        }
        return result; // All transitions are valid and every ticket has been consumed.
    }
    public static void main(String[] args) { // Run the example explained in the matching lesson.
        System.out.println(Display.of(solve(new String[][]{{"JFK","KUL"},{"JFK","NRT"},{"NRT","JFK"}},"JFK"))); // Format arrays and scalar results as readable values.
    }
    public static void selfTest() { // Check independently expected results, not just successful execution.
        Check.equal(solve(new String[][]{{"JFK","KUL"},{"JFK","NRT"},{"NRT","JFK"}},"JFK"), List.of("JFK","NRT","JFK","KUL")); // A lexical dead end is placed last.
        Check.equal(solve(new String[][]{},"A"), List.of("A")); // No tickets leaves only the start.
        Check.equal(solve(new String[][]{{"A","B"},{"B","A"},{"A","B"}},"A"), List.of("A","B","A","B")); // Repeated tickets remain separate edges.
    }
}
