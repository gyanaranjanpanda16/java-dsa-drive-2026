package questions.q397;

import java.util.*;
import java.math.BigInteger;
import questions.support.Check;

/**
 * Q397: Restore IPv4 Addresses.
 * Contract: ASCII decimal digits only; four pieces in 0..255 with no multi-digit leading zeros.
 * Why it works: Each valid address corresponds to one choice of four octet lengths. Length pruning and octet validation reject exactly the invalid branches.
 * Cost: At most 3^4 length choices; O(n) digit validation plus constant bounded search, O(1) depth excluding output.
 */
public final class Q397RestoreIPv4Addresses { // Keep this question independently runnable and testable.
    private Q397RestoreIPv4Addresses() { } // No object state is needed by this standalone exercise.
    public static List<String> restore(String digits) { // Expose the operation for an independent caller and the executable checks below.
        for(char c:digits.toCharArray()) if(c<'0'||c>'9') throw new IllegalArgumentException("Decimal digits only"); // IPv4 pieces are decimal integers.
        List<String> out=new ArrayList<>(); // Collect addresses with exactly four valid pieces.
        split(digits,0,new ArrayList<>(),out); // Start before consuming any digit.
        return out; // Impossible input lengths naturally produce no result.
    }
    private static void split(String s,int i,List<String> parts,List<String> out) { // Keep recursive state or a repeated sub-operation inside this solution.
        int left=4-parts.size(); // Count how many address pieces still need values.
        if(s.length()-i<left || s.length()-i>3*left) return; // Each remaining piece uses between one and three digits.
        if(left==0) { out.add(String.join(".",parts)); return; } // The length check ensures all digits have been consumed.
        for(int len=1;len<=3&&i+len<=s.length();len++) { // Consider each permitted next-piece length.
            String p=s.substring(i,i+len); // A candidate preserves the original digit order.
            if((len>1&&p.charAt(0)=='0')||Integer.parseInt(p)>255) continue; // Reject leading zeros and out-of-range octets.
            parts.add(p); split(s,i+len,parts,out); parts.remove(parts.size()-1); // Explore this octet, then release it.
        }
    }
    public static void main(String[] args) { // Show one deterministic input and its answer.
        System.out.println(restore("25525511135")); // Print the answer produced by the demonstrated method call.
    }
    public static void selfTest() { // Check multiple cases without relying on printed output.
        Check.that(restore("").isEmpty(), "Q397 case 1"); // Verify an independently expected ordinary or boundary result.
        Check.that(restore("0000").equals(List.of("0.0.0.0")), "Q397 case 2"); // Verify an independently expected ordinary or boundary result.
        Check.that(restore("25525511135").size()==2, "Q397 case 3"); // Verify an independently expected ordinary or boundary result.
    }
}
