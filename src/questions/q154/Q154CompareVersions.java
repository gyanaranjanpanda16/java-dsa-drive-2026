package questions.q154;

import java.util.*;
import questions.support.Check;

/**
 * Q154: Compare arbitrarily large version components.
 * Contract: Compare dot-separated nonnegative decimal components; missing components are zero, leading zeros ignored. Return -1,0,1; valid nonempty components assumed.
 * Baseline: A direct scan or simulation establishes the baseline; the implementation below chooses the operations required by the contract.
 * Why correct: After stripping leading zeros, longer digit strings represent larger numbers; equal lengths compare lexicographically without numeric overflow.
 * Complexity: O(total text length) time and space.
 */
public final class Q154CompareVersions { // Keep one question and its tests together for independent study.
    public static int solve(String first,String second) { // Avoid converting arbitrarily long version components to integers.
        String[] a=first.split("[.]"),b=second.split("[.]"); // Dots delimit independent numeric components.
        for(int i=0;i<Math.max(a.length,b.length);i++) { // Missing trailing components behave as zero.
            String x=i<a.length?a[i]:"0",y=i<b.length?b[i]:"0"; // Select the corresponding component or its neutral default.
            x=x.replaceFirst("^0+(?!$)","");y=y.replaceFirst("^0+(?!$)",""); // Remove redundant leading zeros while retaining one digit for zero.
            if(x.length()!=y.length()) return Integer.compare(x.length(),y.length()); // More significant digits imply a larger nonnegative value.
            int comparison=x.compareTo(y); // Equal-length canonical decimal strings have numeric lexicographic order.
            if(comparison!=0) return Integer.signum(comparison); // The first unequal component decides the entire version.
        }
        return 0; // Every component agrees after normalization.
    }

    public static void main(String[] args) { // Run the small example from the question page.
        System.out.println(solve("1.01.0","1.1")); // Display the exact expected answer rather than an object address.
    }

    public static void selfTest() { // Check normal behavior and important boundaries without external libraries.
    Check.equal(solve("1.01.0","1.1"),0); // Normalize zeros and missing trailing components.
    Check.equal(solve("1.9","1.10"),-1); // Numeric ordering differs from raw string ordering.
    Check.equal(solve("99999999999999999999","2"),1); // Large components require no integer parsing.
    }
}
