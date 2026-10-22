package questions.q454;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q454: Lemonade Change.
 * Contract: Each drink costs5; initially no change; bills are5,10,20.
 * Why it works: For fifteen change, using a ten plus a five preserves two extra fives compared with three fives. Fives are needed by more future payment types, so this exchange cannot hurt feasibility.
 * Cost: O(n) time and O(1) space.
 */
public final class Q454LemonadeChange { // Keep this question independently runnable and testable.
    private Q454LemonadeChange() { } // No object state is needed by this standalone exercise.
    public static boolean possible(int[] bills) { // Expose the operation for an independent caller and the executable checks below.
        int fives=0,tens=0; // Only these denominations can provide change for future customers.
        for(int bill:bills) { // Customers must be served in the given order.
            if(bill==5) fives++; // Exact payment adds one flexible change unit.
            else if(bill==10) { if(fives==0) return false; fives--; tens++; } // A ten-dollar payment needs exactly one five in change.
            else if(bill==20) { // A twenty-dollar payment needs fifteen in change.
                if(tens>0&&fives>0) { tens--; fives--; } // Prefer one ten and one five to conserve flexible five-dollar bills.
                else if(fives>=3) fives-=3; // Three fives are the only remaining way to make fifteen.
                else return false; // No legal change combination remains.
            } else throw new IllegalArgumentException("Bills must be5,10,20"); // Exclude unsupported denominations from the model.
        }
        return true; // Every customer received exact change immediately.
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(possible(new int[]{5,5,5,10,20})); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(possible(new int[]{}), "Q454 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(!possible(new int[]{10}), "Q454 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(!possible(new int[]{5,5,10,10,20}), "Q454 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
