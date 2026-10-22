package questions.q208;

import java.util.*;
import questions.support.Check;

/**
 * Q208: Decode nested repetition brackets.
 * Contract: Input is valid lowercase text with k[encoded] groups,positive int k,proper brackets,and final/intermediate output lengths fitting available memory.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: Each opening bracket saves the completed outer prefix and repeat count. On closing,repeat the fully decoded inner segment and append it to that saved prefix,matching the recursive grammar.
 * Complexity: O(input length plus expanded work) time and O(expanded output plus nesting) space.
 */
public final class Q208DecodeRepeats { // Keep one question and its tests together for independent study.
    public static String solve(String encoded) { // Decode innermost groups before combining their enclosing groups.
        Deque<StringBuilder> prefixes=new ArrayDeque<>(); // Save unfinished outer output buffers at each nesting level.
        Deque<Integer> repeats=new ArrayDeque<>(); // Match each saved prefix with its repetition multiplier.
        StringBuilder current=new StringBuilder();int number=0; // Build the current level and parse its next multiplier.
        for(char c:encoded.toCharArray()) { // Interpret each grammar character in order.
            if(c>='0'&&c<='9') number=Math.addExact(Math.multiplyExact(number,10),c-'0'); // Accumulate multi-digit counts while rejecting int overflow.
            else if(c=='[') { prefixes.push(current);repeats.push(number);current=new StringBuilder();number=0; } // Enter an empty inner level while saving its outer context.
            else if(c==']') { // The current level is now fully decoded.
                String inner=current.toString();current=prefixes.pop(); // Restore the outer prefix before adding repeated inner text.
                int times=repeats.pop(); // Recover the multiplier belonging to this exact opening bracket.
                for(int i=0;i<times;i++) current.append(inner); // Append the decoded inner segment the requested number of times.
            } else current.append(c); // A literal lowercase character belongs to the current level.
        }
        return current.toString(); // Valid input leaves only the completed outermost output.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve("3[a2[c]]")); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve("3[a2[c]]"),"accaccacc"); // Inner repetition completes before outer repetition.
    Check.equal(solve("2[ab]x"),"ababx"); // Literal suffix follows the repeated group.
    Check.equal(solve(""),""); // Empty valid text decodes to empty.
    }
}
